#!/usr/bin/env python3
"""Generate <module>/doc/erd/*.svg -- ER diagrams of the tables and views a module maps.

Writes _<module>.svg (the whole schema) and one <OBJECT>.svg per mapped table or view (that
object with its direct neighbours). Rendering is Graphviz; `dot` must be on PATH.

Reads the Jakarta Persistence entities under <module>/src/main/java and, when the
matching upstream DDL is checked out under db-sample-schemas/, the SQL column types, which
object is a table and which a view, and what each view selects from.
Parsing is regex-based and leans on this project's conventions:
every entity declares TABLE_NAME and COLUMN_NAME_* constants, and every @Column /
@JoinColumn names its column through one of them.
"""
import json
import re
import shutil
import subprocess
import sys
from pathlib import Path

MODULE_DIRS = {m: "oracle-sample-schemas-persistence-" + m for m in ("co", "hr", "sh")}
DDL_DIRS = {"co": "customer_orders", "hr": "human_resources", "sh": "sales_history"}
DDL_FILES = {"co": "co_create.sql", "hr": "hr_create.sql", "sh": "sh_create.sql"}

BLOCK_COMMENT = re.compile(r"/\*.*?\*/", re.S)
LINE_COMMENT = re.compile(r"//[^\n]*")
CONST = re.compile(
    r"public\s+static\s+final\s+(?:String|int)\s+(\w+)\s*=\s*([^;]+);", re.S)
# the lookbehind keeps the previous declaration's ';' unconsumed, so fields match in a row
FIELD = re.compile(r"(?<=[;{}])((?:\s*@\w[\w.]*(?:\([^;]*?\))?\s*)+)"
                   # '@' in the type group: a collection may be declared List<@Valid @NotNull X>
                   r"(?:private|protected|public)\s+([\w.<>,\[\]\s?@]+?)\s+(\w+)\s*;", re.S)


def strip_comments(text):
    return LINE_COMMENT.sub("", BLOCK_COMMENT.sub("", text))


def attr(annotation, key):
    """Value of a single annotation attribute, source text, unresolved."""
    m = re.search(key + r"\s*=\s*([^,()]+(?:\([^)]*\))?)", annotation)
    return m.group(1).strip() if m else None


def annotation(block, name):
    """The @name(...) annotation from an annotation block, or None."""
    m = re.search(r"@" + name + r"\b(\s*\((?:[^()]|\([^()]*\))*\))?", block)
    if not m:
        return None
    return m.group(1) or ""


class Klass:
    def __init__(self, path, text):
        self.path = path
        self.name = path.stem
        self.text = text
        self.consts = {}
        for k, v in CONST.findall(text):
            self.consts[k] = v.strip()


def resolve(expr, klass, classes, seen=()):
    """Resolve a constant expression to its literal value."""
    if expr is None:
        return None
    expr = expr.strip()
    # a quoted identifier is written as '"' + CONST + '"', because a reserved word has to reach the database
    # quoted; the diagram wants the bare column name, so the quoting parts are dropped
    quoted = re.fullmatch(r"""'"'\s*\+(.+?)\+\s*'"'""", expr)
    if quoted:
        return resolve(quoted.group(1), klass, classes, seen)
    if expr.startswith('"'):
        return expr.strip('"')
    if re.fullmatch(r"\d+", expr):
        return int(expr)
    m = re.fullmatch(r"(?:(\w+)\.)?(\w+)", expr)
    if not m:
        return expr
    owner, const = m.groups()
    target = classes.get(owner, klass) if owner else klass
    if target is None or (target.name, const) in seen:
        return expr
    value = target.consts.get(const)
    if value is None:
        return expr
    return resolve(value, target, classes, seen + ((target.name, const),))


def strip_sql_comments(text):
    """Drop `rem` and `--` comments. A `--` inside a string literal is left alone."""
    text = re.sub(r"(?im)^\s*rem\b[^\n]*$", "", text)
    out = []
    for line in text.split("\n"):
        cut = None
        quoted = False
        for i, ch in enumerate(line):
            if ch == "'":
                quoted = not quoted
            elif ch == "-" and not quoted and line[i:i + 2] == "--":
                cut = i
                break
        out.append(line if cut is None else line[:cut])
    return "\n".join(out)


def split_top_level(text, separators=(",",)):
    """Split on separators that sit outside parentheses and string literals."""
    parts, current, depth, quoted = [], "", 0, False
    for ch in text:
        if ch == "'":
            quoted = not quoted
        elif not quoted:
            if ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
            elif ch in separators and depth == 0:
                parts.append(current)
                current = ""
                continue
        current += ch
    parts.append(current)
    return [p for p in parts if p.strip()]


def parse_ddl(root, module):
    """The upstream objects: kind, column types, and -- for a view -- what it selects from.

    Returns {NAME: {"kind": "table"|"view"|"materialized view",
                    "columns": {COLUMN: sql type}, "sources": [NAME, ...]}}.
    """
    if module not in DDL_FILES:
        return {}
    ddl = root / "db-sample-schemas" / DDL_DIRS[module] / DDL_FILES[module]
    if not ddl.is_file():
        return {}
    text = strip_sql_comments(strip_comments(ddl.read_text(errors="replace")))
    objects = {}
    for m in re.finditer(r"(?is)\bCREATE\s+TABLE\s+(?:\w+\.)?(\w+)\s*\((.*?)\)\s*(?:TABLESPACE|"
                         r"PCTFREE|PARTITION|ORGANIZATION|NOLOGGING|;)", text):
        table = m.group(1).upper()
        obj = objects.setdefault(table, new_object("table"))
        for fragment in split_top_level(m.group(2)):
            cols, notnull = ddl_column(fragment)
            obj["columns"].update(cols)
            obj["notNull"] += [c for c in notnull if c not in obj["notNull"]]
            ddl_constraint(fragment, obj)

    # sh declares its keys inside CREATE TABLE; co and hr add them afterwards, and hr wraps
    # several constraints in one ADD ( ..., ... )
    for m in re.finditer(r"(?is)\bALTER\s+TABLE\s+(?:\w+\.)?(\w+)\s+ADD\s+(.*?);", text):
        obj = objects.get(m.group(1).upper())
        if obj is None:
            continue
        added = m.group(2).strip()
        if added.startswith("("):
            added = added[1:split_paren_end(added) - 1]
        for fragment in split_top_level(added):
            ddl_constraint(fragment, obj)

    views = []
    for m in re.finditer(r"(?is)\bCREATE\s+(?:OR\s+REPLACE\s+)?(?:FORCE\s+|NO\s*FORCE\s+)?"
                         r"(MATERIALIZED\s+)?VIEW\s+(?:\w+\.)?(\w+)", text):
        name = m.group(2).upper()
        kind = "materialized view" if m.group(1) else "view"
        body = split_top_level(text[m.end():], (";",))
        if not body:
            continue
        views.append((name, kind, body[0]))
        objects.setdefault(name, new_object(kind))["kind"] = kind

    # two passes, so a view that selects from a view declared after it still resolves
    for _ in range(2):
        for name, _kind, body in views:
            columns, sources = ddl_view(body, objects)
            objects[name]["columns"], objects[name]["sources"] = columns, sources
    return objects


def ddl_view(body, objects):
    """(columns, source objects) of a view, from the text following its name."""
    declared = None
    head = body.lstrip()
    if head.startswith("("):
        head = head[:split_paren_end(head)]
        declared = [c.strip().strip('"').upper() for c in split_top_level(head[1:-1])]
    m = re.search(r"(?is)\bSELECT\b", body)
    if not m:
        return {}, []
    parts = body[m.end():]
    m = re.search(r"(?is)\bFROM\b", parts)
    if not m:
        return {}, []
    items = split_top_level(parts[:m.start()])
    rest = parts[m.end():]
    m = re.search(r"(?is)\b(WHERE|GROUP\s+BY|HAVING|ORDER\s+BY|CONNECT\s+BY|START\s+WITH|MODEL|"
                  r"UNION|INTERSECT|MINUS|WITH\s+READ|WITH\s+CHECK)\b", rest)
    from_clause = rest[:m.start()] if m else rest

    aliases, sources = {}, []
    for fragment in split_top_level(re.sub(r"(?i)\b(?:INNER|LEFT|RIGHT|FULL|CROSS|OUTER|NATURAL)\b",
                                           " ", from_clause).replace("\n", " "),
                                    (",",)):
        for piece in re.split(r"(?i)\bJOIN\b", fragment):
            piece = re.split(r"(?i)\bON\b|\bUSING\b", piece)[0].strip()
            n = re.match(r"^(?:\w+\.)?(\w+)\s*(?:AS\s+)?(\w+)?\s*$", piece, re.I)
            if not n:
                continue  # an inline view, a table function such as JSON_TABLE, a subquery
            source, alias = n.group(1).upper(), n.group(2)
            if source not in sources:
                sources.append(source)
            if alias and alias.upper() not in ("AS",):
                aliases[alias.upper()] = source

    columns = {}
    for i, item in enumerate(items):
        item = " ".join(item.split())
        name, ref = view_column(item)
        if declared and i < len(declared):
            name = declared[i]
        if not name:
            continue
        sql = ""
        if ref:
            qualifier, column = ref
            table = aliases.get(qualifier, qualifier) if qualifier else None
            candidates = [table] if table else sources
            for candidate in candidates:
                sql = objects.get(candidate, {}).get("columns", {}).get(column, "")
                if sql:
                    break
        columns[name] = sql
    return columns, sources


def view_column(item):
    """(output column, (qualifier, column) when the item is a plain reference)."""
    plain = re.fullmatch(r'(?:(\w+)\.)?("?\w+"?)', item)
    if plain:
        column = plain.group(2).strip('"').upper()
        return column, (plain.group(1).upper() if plain.group(1) else None, column)
    alias = re.search(r'(?i)(?:\s+AS)?\s+("?\w+"?)$', item)
    if alias and not re.fullmatch(r"(?i)END|\*", alias.group(1)):
        return alias.group(1).strip('"').upper(), None
    return None, None


def split_paren_end(text):
    """The index just past the ')' that closes the '(' text starts with."""
    depth = 0
    for i, ch in enumerate(text):
        if ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                return i + 1
    return len(text)


def new_object(kind):
    return {"kind": kind, "columns": {}, "notNull": [], "pk": [], "fks": [], "sources": []}


def ddl_column(fragment):
    """({COLUMN: sql type}, {COLUMN} when NOT NULL) for a column declaration, else empty."""
    fragment = " ".join(fragment.split())
    m = re.match(r"(?i)^(\w+)\s+((?:VARCHAR2|NVARCHAR2|VARCHAR|NCHAR|CHAR|NUMBER|INTEGER|INT|"
                 r"SMALLINT|DECIMAL|NUMERIC|FLOAT|BINARY_FLOAT|BINARY_DOUBLE|DATE|TIMESTAMP|"
                 r"INTERVAL|NCLOB|CLOB|BLOB|RAW|LONG)\b(?:\s*\([^)]*\))?"
                 r"(?:\s+WITH\s+(?:LOCAL\s+)?TIME\s+ZONE)?)", fragment)
    if not m or m.group(1).upper() in ("CONSTRAINT", "PRIMARY", "FOREIGN", "UNIQUE", "CHECK"):
        return {}, set()
    column = m.group(1).upper()
    sql = " ".join(m.group(2).split()).lower()
    # VARCHAR2(255 CHAR) and VARCHAR2(255 BYTE) read as VARCHAR2(255) in a diagram
    sql = re.sub(r"\(\s*(\d+)\s+(?:char|byte)\s*\)", r"(\1)", sql)
    rest = fragment[m.end():]
    return {column: sql}, ([column] if re.search(r"(?i)\bNOT\s+NULL\b", rest) else [])


def ddl_constraint(fragment, obj):
    """Record a PRIMARY KEY / FOREIGN KEY declaration, table-level or on a column."""
    fragment = " ".join(fragment.split())
    column = re.match(r"(?i)^(\w+)\s+\w", fragment)
    owner = None
    if column and column.group(1).upper() not in ("CONSTRAINT", "PRIMARY", "FOREIGN", "UNIQUE",
                                                  "CHECK"):
        owner = column.group(1).upper()  # a column-level constraint applies to this column

    m = re.search(r"(?i)\bPRIMARY\s+KEY\s*(?:\(([^)]*)\))?", fragment)
    if m:
        cols = [c.strip().strip('"').upper() for c in (m.group(1) or "").split(",") if c.strip()]
        for c in cols or ([owner] if owner else []):
            if c not in obj["pk"]:
                obj["pk"].append(c)

    m = re.search(r"(?i)\b(?:FOREIGN\s+KEY\s*\(([^)]*)\)\s*)?REFERENCES\s+(?:\w+\.)?(\w+)"
                  r"\s*(?:\(([^)]*)\))?", fragment)
    if m:
        cols = [c.strip().strip('"').upper() for c in (m.group(1) or "").split(",") if c.strip()]
        cols = cols or ([owner] if owner else [])
        if cols:
            obj["fks"].append({"columns": cols, "references": m.group(2).upper()})


def parse_module(root, module):
    src = root / MODULE_DIRS[module] / "src" / "main" / "java"
    classes = {}
    for path in sorted(src.rglob("*.java")):
        classes[path.stem] = Klass(path, strip_comments(path.read_text(errors="replace")))

    objects = parse_ddl(root, module)
    entities = []
    for name, k in classes.items():
        if annotation(k.text, "Entity") is None:
            continue
        table = resolve(attr(annotation(k.text, "Table") or "", "name"), k, classes) or name.upper()
        id_class = annotation(k.text, "IdClass")
        # what the upstream DDL calls this object: a table, a view, or a materialized view
        upstream = objects.get(str(table).upper(), {})
        entity = {
            "class": name,
            "table": table,
            "kind": upstream.get("kind", "table"),
            "source": str(k.path.relative_to(root)),
            "idClass": re.sub(r"[()\s.]|class", "", id_class) if id_class else None,
            "columns": [],
            # the objects a view selects from; empty for a table
            "selectsFrom": upstream.get("sources", []),
            "relations": [],
        }
        for block, ftype, fname in FIELD.findall(k.text):
            ftype = " ".join(ftype.split())
            col = annotation(block, "Column")
            join = annotation(block, "JoinColumn")
            to_one = annotation(block, "ManyToOne") or annotation(block, "OneToOne")
            to_many = annotation(block, "OneToMany") or annotation(block, "ManyToMany")
            embedded_id = annotation(block, "EmbeddedId") is not None
            is_id = annotation(block, "Id") is not None or embedded_id

            if to_one is not None and join is not None:
                column = resolve(attr(join, "name"), k, classes)
                entity["relations"].append({
                    "kind": "toOne",
                    "attribute": fname,
                    "target": ftype,
                    "column": column,
                    "optional": attr(to_one, "optional") == "true"
                                or attr(join, "nullable") == "true",
                })
                continue
            if to_many is not None:
                entity["relations"].append({
                    "kind": "toMany",
                    "attribute": fname,
                    # the last identifier of List<@Valid @NotNull Order> / Map<K, V> is the target
                    "target": re.findall(r"[\w.]+", ftype)[-1],
                    "mappedBy": resolve(attr(to_many, "mappedBy"), k, classes),
                })
                continue
            if embedded_id:
                embeddable = classes.get(ftype)
                if embeddable is not None:
                    for b2, t2, n2 in FIELD.findall(embeddable.text):
                        c2 = annotation(b2, "Column")
                        if c2 is None:
                            continue
                        entity["columns"].append(column_of(
                            c2, b2, t2, n2, embeddable, classes,
                            upstream.get("columns", {}), True))
                continue
            if col is None:
                continue
            entity["columns"].append(
                column_of(col, block, ftype, fname, k, classes,
                          upstream.get("columns", {}), is_id))
        entities.append(entity)

    for e in entities:
        fks = {r["column"] for r in e["relations"] if r.get("column")}
        for c in e["columns"]:
            c["fk"] = c["column"] in fks
    return {"module": module, "entities": sorted(entities, key=lambda e: e["table"]),
            "objects": objects}


def column_of(col, block, ftype, fname, klass, classes, ddl_types, is_id):
    column = resolve(attr(col, "name"), klass, classes)
    length = resolve(attr(col, "length"), klass, classes)
    sql = ddl_types.get(str(column).upper())
    if sql is None and length:
        sql = "varchar2(%s)" % length
    return {
        "attribute": fname,
        "column": column,
        "javaType": ftype.split(".")[-1],
        "sqlType": sql or "",
        "pk": is_id,
        "notNull": attr(col, "nullable") == "false",
        "lob": annotation(block, "Lob") is not None,
        "generated": annotation(block, "GeneratedValue") is not None,
    }


SCHEMA_TITLES = {"co": "Customer Orders", "hr": "Human Resources", "sh": "Sales History"}

# one dark header, one muted accent for the table in focus, everything else greyscale
INK, HEAD, FOCUS, RULE, FILL = "#1f2933", "#2f4858", "#8c4a3f", "#b8c2cc", "#ffffff"
# a derived object reads as secondary: a lighter header, and a dashed edge from each source
VIEW_HEAD, DERIVES = "#5a7078", "#a9b4bf"
# an object the module does not map is drawn from the DDL alone, and greyed throughout
UNMAPPED_HEAD, UNMAPPED_INK, UNMAPPED_RULE = "#a3adb8", "#8a949e", "#d6dce2"
KIND_TAGS = {"view": "VIEW", "materialized view": "MATERIALIZED VIEW"}
FONT = "Helvetica,Arial,sans-serif"


def merge_by_table(entities):
    """Two entity classes may map one table (the WithEmbeddedId / WithIdClass pairs)."""
    tables = {}
    for e in entities:
        t = tables.setdefault(e["table"], {"table": e["table"], "kind": e["kind"],
                                           "mapped": True, "classes": [], "sources": [],
                                           "selectsFrom": e["selectsFrom"],
                                           "columns": [], "relations": []})
        t["classes"].append(e["class"])
        t["sources"].append(e["source"])
        seen = {c["column"] for c in t["columns"]}
        t["columns"] += [c for c in e["columns"] if c["column"] not in seen]
        known = {(r.get("target"), r.get("column")) for r in t["relations"]}
        t["relations"] += [r for r in e["relations"]
                           if (r.get("target"), r.get("column")) not in known]
    return [tables[k] for k in sorted(tables)]


def foreign_keys(tables, table_of):
    """(child table, fk column, parent table, optional) per mapped foreign key."""
    out = []
    for t in tables:
        for r in t["relations"]:
            if r["kind"] != "toOne":
                continue
            parent = table_of.get(r["target"])
            if parent is not None:
                out.append((t["table"], str(r["column"]), parent, r["optional"]))
    return sorted(dict.fromkeys(out))


def unmapped_nodes(objects, mapped):
    """A node per upstream object no entity maps, built from the DDL alone."""
    out = []
    for name in sorted(objects):
        if name in mapped:
            continue
        o = objects[name]
        fk_columns = {c for fk in o["fks"] for c in fk["columns"]}
        out.append({
            "table": name, "kind": o["kind"], "mapped": False,
            "classes": [], "sources": [], "selectsFrom": o["sources"], "relations": [],
            "columns": [{"attribute": column.lower(), "column": column, "javaType": "",
                         "sqlType": sql, "pk": column in o["pk"], "fk": column in fk_columns,
                         "notNull": column in o["notNull"], "lob": False, "generated": False}
                        for column, sql in o["columns"].items()],
        })
    return out


def upstream_foreign_keys(tables, objects):
    """Foreign keys from the DDL, for the links a @ManyToOne cannot carry.

    Only where one end is unmapped: between two mapped objects a missing @ManyToOne is a fact
    about the mapping, and the diagram keeps saying so by leaving the edge out.
    """
    drawn = {t["table"]: t for t in tables}
    out = []
    for name, t in drawn.items():
        for fk in objects.get(name, {}).get("fks", []):
            parent = fk["references"]
            if parent not in drawn or (t["mapped"] and drawn[parent]["mapped"]):
                continue
            column = fk["columns"][0]
            optional = not any(c["column"] == column and c["notNull"] for c in t["columns"])
            out.append((name, column, parent, optional))
    return sorted(dict.fromkeys(out))


def derivations(tables):
    """(source object, view) per view-to-source link, for sources the module maps too."""
    mapped = {t["table"] for t in tables}
    return sorted({(source, t["table"]) for t in tables for source in t["selectsFrom"]
                   if source in mapped and source != t["table"]})


def column_type(sql, java):
    """The SQL type when the DDL knew the column, else the Java type as a fallback."""
    return sql or java.lower()


def cell(text, port=None, align="LEFT", color=INK, bold=False, size=10):
    # graphviz rejects an empty <FONT></FONT>, so a blank cell carries no font element
    opening = '<TD ALIGN="%s"%s>' % (align, ' PORT="%s"' % port if port else "")
    if not text:
        return opening + "</TD>"
    body = "<B>%s</B>" % text if bold else text
    return '%s<FONT POINT-SIZE="%d" COLOR="%s">%s</FONT></TD>' % (opening, size, color, body)


def node(t, accent=None, columns=None):
    """A table or view as a Graphviz HTML label: header, then one row per column."""
    view = t["kind"] != "table"
    mapped = t["mapped"]
    if accent is None:
        accent = (VIEW_HEAD if view else HEAD) if mapped else UNMAPPED_HEAD
    tags = [tag for tag in (KIND_TAGS.get(t["kind"], ""), "" if mapped else "NOT MAPPED") if tag]
    ink = INK if mapped else UNMAPPED_INK
    rows = ['<TR><TD BGCOLOR="%s" COLSPAN="4" ALIGN="CENTER">'
            '<FONT POINT-SIZE="12" COLOR="white"><B>%s</B></FONT>%s</TD></TR>'
            % (accent, t["table"],
               '<FONT POINT-SIZE="8" COLOR="#eef2f5">&#160;&#160;%s</FONT>' % " &#183; ".join(tags)
               if tags else "")]
    for c in columns if columns is not None else t["columns"]:
        # a view has no primary key; the badge names what it is, a JPA identifier
        key = "PK" if not view else ("ID" if mapped else "")
        keys = " ".join(k for k, on in ((key, c["pk"] and key), ("FK", c["fk"])) if on)
        rows.append("<TR>%s%s%s%s</TR>" % (
            cell(c["column"].lower(), port=c["column"].lower(), bold=c["pk"], color=ink),
            cell(column_type(c["sqlType"], c["javaType"]),
                 color="#5b6770" if mapped else UNMAPPED_INK),
            cell(keys, align="CENTER", color=accent, bold=True, size=9),
            cell("NN" if c["notNull"] and not c["pk"] else "", align="CENTER",
                 color="#8b949e" if mapped else UNMAPPED_INK, size=9)))
    return ('  "%s" [label=<<TABLE BORDER="1" CELLBORDER="0" CELLSPACING="0" CELLPADDING="4" '
            'COLOR="%s" BGCOLOR="%s"%s>%s</TABLE>>];'
            % (t["table"], RULE if mapped else UNMAPPED_RULE, FILL,
               ' STYLE="ROUNDED"' if view else "", "".join(rows)))


def edge(child, column, parent, optional, tables_by_name, upstream=False):
    """child -> parent, crow's foot on the many end, IE notation on the one end."""
    pk = next((c["column"].lower() for c in tables_by_name[parent]["columns"] if c["pk"]), None)
    tail = ':"%s"' % column.lower() if any(
        c["column"] == column for c in tables_by_name[child]["columns"]) else ""
    head = ':"%s"' % pk if pk else ""
    # no edge label: each edge lands on the port of the very column that would name it
    return ('  "%s"%s -> "%s"%s [dir=both, arrowtail=crow, arrowhead=%s, '
            'color="%s", penwidth=%s];' % (child, tail, parent, head,
                                           "odot" if optional else "tee",
                                           "#b3bcc5" if upstream else "#7d8b99",
                                           "0.9" if upstream else "1.1"))


def derives_edge(source, view):
    """source -> view: the view selects from it. Dashed, and attached to no column."""
    return ('  "%s" -> "%s" [style=dashed, arrowhead=vee, arrowsize=0.8, '
            'color="%s", penwidth=1.0];' % (source, view, DERIVES))


def graph(body, rankdir="LR"):
    return "\n".join([
        "digraph erd {",
        '  graph [rankdir=%s, bgcolor="white", fontname="%s", nodesep=0.45, ranksep=1.1, '
        'splines=spline, pad=0.3];' % (rankdir, FONT),
        '  node [shape=plain, fontname="%s"];' % FONT,
        '  edge [fontname="%s"];' % FONT,
    ] + body + ["}", ""])


def schema_dot(tables, table_of, objects):
    by_name = {t["table"]: t for t in tables}
    body = [node(t) for t in tables]
    body += [edge(*fk, tables_by_name=by_name) for fk in foreign_keys(tables, table_of)]
    body += [edge(*fk, tables_by_name=by_name, upstream=True)
             for fk in upstream_foreign_keys(tables, objects)]
    body += [derives_edge(*d) for d in derivations(tables)]
    return graph(body)


def table_dot(focus, tables, table_of, objects):
    """The focus object in full, plus everything it joins to or is derived from/into."""
    by_name = {t["table"]: t for t in tables}
    fks = [fk for fk in foreign_keys(tables, table_of) if focus["table"] in (fk[0], fk[2])]
    upstream = [fk for fk in upstream_foreign_keys(tables, objects)
                if focus["table"] in (fk[0], fk[2])]
    # a view is joined to its sources, and a table to every view that selects from it
    derived = [d for d in derivations(tables) if focus["table"] in d]
    neighbours = ({n for fk in fks + upstream for n in (fk[0], fk[2])}
                  | {n for d in derived for n in d}) - {focus["table"]}
    body = [node(focus, accent=FOCUS)]
    for name in sorted(neighbours):
        t = by_name[name]
        keyed = [c for c in t["columns"] if c["pk"] or c["fk"]]
        body.append(node(t, columns=keyed or t["columns"]))
    body += [edge(*fk, tables_by_name=by_name) for fk in fks]
    body += [edge(*fk, tables_by_name=by_name, upstream=True) for fk in upstream]
    body += [derives_edge(*d) for d in derived]
    return graph(body, rankdir="LR" if len(neighbours) < 3 else "TB")


def render_svg(dot_source, path, note):
    result = subprocess.run(["dot", "-Tsvg"], input=dot_source, capture_output=True, text=True)
    if result.returncode != 0:
        raise SystemExit("dot failed for %s:\n%s" % (path, result.stderr.strip()))
    if result.stderr.strip():
        print("  %s: %s" % (path.name, result.stderr.strip()), file=sys.stderr)
    # '--' cannot appear inside an XML comment, so the note never carries one
    note = note.replace("--", "-")
    svg = result.stdout.replace(
        "<svg ", "<!-- %s. Generated by the erd skill; do not edit by hand. -->\n<svg " % note, 1)
    path.write_text(svg)


if __name__ == "__main__":
    if shutil.which("dot") is None:
        raise SystemExit("graphviz is required: `brew install graphviz`")
    root = Path(__file__).resolve().parents[4]
    args = [a for a in sys.argv[1:] if not a.startswith("-")]
    for module in args or ["co", "hr", "sh"]:
        model = parse_module(root, module)
        if "--dump" in sys.argv:
            print(json.dumps(model, indent=2))
            continue
        objects = model["objects"]
        tables = merge_by_table(model["entities"])
        # every upstream table and view is drawn; the ones no entity maps are greyed
        tables = sorted(tables + unmapped_nodes(objects, {t["table"] for t in tables}),
                        key=lambda t: t["table"])
        table_of = {e["class"]: e["table"] for e in model["entities"]}
        out = root / MODULE_DIRS[module] / "doc" / "erd"
        out.mkdir(parents=True, exist_ok=True)

        written = {out / ("_%s.svg" % module)}
        render_svg(schema_dot(tables, table_of, objects), out / ("_%s.svg" % module),
                   "%s: every table and view of the schema, as the %s module maps them" % (
                       SCHEMA_TITLES.get(module, module.upper()), module))
        for t in tables:
            svg = out / ("%s.svg" % t["table"])
            render_svg(table_dot(t, tables, table_of, objects), svg,
                       "the %s %s and its direct neighbours, %s" % (
                           t["table"], t["kind"],
                           "as the %s module maps them" % module if t["mapped"]
                           else "from the upstream DDL; the %s module maps no entity to it"
                                % module))
            written.add(svg)
        stale = [p for p in sorted(out.iterdir())
                 if p.is_file() and p not in written and p.suffix in (".svg", ".md")]
        for p in stale:
            p.unlink()
        views = sum(1 for t in tables if t["kind"] != "table")
        unmapped = sum(1 for t in tables if not t["mapped"])
        print("%s -- %d svg (%d table%s, %d view%s; %d unmapped)%s" % (
            out.relative_to(root), len(written), len(tables) - views,
            "" if len(tables) - views == 1 else "s", views, "" if views == 1 else "s",
            unmapped, ", removed %d stale" % len(stale) if stale else ""))
