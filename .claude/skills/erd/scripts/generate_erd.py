#!/usr/bin/env python3
"""Generate <module>/doc/erd/*.svg -- ER diagrams of the tables a module maps.

Writes _<module>.svg (the whole schema) and one <TABLE>.svg per table (that table with its
direct neighbours). Rendering is Graphviz; `dot` must be on PATH.

Reads the Jakarta Persistence entities under <module>/src/main/java and, when the
matching upstream DDL is checked out under db-sample-schemas/, the SQL column types.
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


def parse_ddl(root, module):
    """column types, per table, from the upstream CREATE TABLE statements"""
    if module not in DDL_FILES:
        return {}
    ddl = root / "db-sample-schemas" / DDL_DIRS[module] / DDL_FILES[module]
    if not ddl.is_file():
        return {}
    text = strip_comments(ddl.read_text(errors="replace"))
    text = re.sub(r"(?im)^\s*rem[^\n]*$", "", text)
    types = {}
    for m in re.finditer(r"(?is)\bCREATE\s+TABLE\s+(\w+)\s*\((.*?)\)\s*(?:TABLESPACE|PCTFREE|"
                         r"PARTITION|ORGANIZATION|NOLOGGING|;)", text):
        table, body = m.group(1).upper(), m.group(2)
        cols = {}
        depth = 0
        current = ""
        for ch in body:
            if ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
            if ch == "," and depth == 0:
                cols.update(ddl_column(current))
                current = ""
            else:
                current += ch
        cols.update(ddl_column(current))
        types.setdefault(table, {}).update(cols)
    return types


def ddl_column(fragment):
    fragment = " ".join(fragment.split())
    m = re.match(r"(?i)^(\w+)\s+((?:VARCHAR2|NVARCHAR2|VARCHAR|NCHAR|CHAR|NUMBER|INTEGER|INT|"
                 r"SMALLINT|DECIMAL|NUMERIC|FLOAT|BINARY_FLOAT|BINARY_DOUBLE|DATE|TIMESTAMP|"
                 r"INTERVAL|NCLOB|CLOB|BLOB|RAW|LONG)\b(?:\s*\([^)]*\))?"
                 r"(?:\s+WITH\s+(?:LOCAL\s+)?TIME\s+ZONE)?)", fragment)
    if not m or m.group(1).upper() in ("CONSTRAINT", "PRIMARY", "FOREIGN", "UNIQUE", "CHECK"):
        return {}
    sql = " ".join(m.group(2).split()).lower()
    # VARCHAR2(255 CHAR) and VARCHAR2(255 BYTE) read as VARCHAR2(255) in a diagram
    sql = re.sub(r"\(\s*(\d+)\s+(?:char|byte)\s*\)", r"(\1)", sql)
    return {m.group(1).upper(): sql}


def parse_module(root, module):
    src = root / module / "src" / "main" / "java"
    classes = {}
    for path in sorted(src.rglob("*.java")):
        classes[path.stem] = Klass(path, strip_comments(path.read_text(errors="replace")))

    ddl = parse_ddl(root, module)
    entities = []
    for name, k in classes.items():
        if annotation(k.text, "Entity") is None:
            continue
        table = resolve(attr(annotation(k.text, "Table") or "", "name"), k, classes) or name.upper()
        id_class = annotation(k.text, "IdClass")
        entity = {
            "class": name,
            "table": table,
            "source": str(k.path.relative_to(root)),
            "idClass": re.sub(r"[()\s.]|class", "", id_class) if id_class else None,
            "columns": [],
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
                            c2, b2, t2, n2, embeddable, classes, ddl.get(table, {}), True))
                continue
            if col is None:
                continue
            entity["columns"].append(
                column_of(col, block, ftype, fname, k, classes, ddl.get(table, {}), is_id))
        entities.append(entity)

    for e in entities:
        fks = {r["column"] for r in e["relations"] if r.get("column")}
        for c in e["columns"]:
            c["fk"] = c["column"] in fks
    return {"module": module, "entities": sorted(entities, key=lambda e: e["table"])}


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
FONT = "Helvetica,Arial,sans-serif"


def merge_by_table(entities):
    """Two entity classes may map one table (the WithEmbeddedId / WithIdClass pairs)."""
    tables = {}
    for e in entities:
        t = tables.setdefault(e["table"], {"table": e["table"], "classes": [], "sources": [],
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


def node(t, accent=HEAD, columns=None):
    """A table as a Graphviz HTML label: header, then one row per column."""
    rows = ['<TR><TD BGCOLOR="%s" COLSPAN="4" ALIGN="CENTER">'
            '<FONT POINT-SIZE="12" COLOR="white"><B>%s</B></FONT></TD></TR>' % (accent, t["table"])]
    for c in columns if columns is not None else t["columns"]:
        keys = " ".join(k for k, on in (("PK", c["pk"]), ("FK", c["fk"])) if on)
        rows.append("<TR>%s%s%s%s</TR>" % (
            cell(c["column"].lower(), port=c["column"].lower(), bold=c["pk"]),
            cell(column_type(c["sqlType"], c["javaType"]), color="#5b6770"),
            cell(keys, align="CENTER", color=accent, bold=True, size=9),
            cell("NN" if c["notNull"] and not c["pk"] else "", align="CENTER",
                 color="#8b949e", size=9)))
    return ('  "%s" [label=<<TABLE BORDER="1" CELLBORDER="0" CELLSPACING="0" CELLPADDING="4" '
            'COLOR="%s" BGCOLOR="%s">%s</TABLE>>];' % (t["table"], RULE, FILL, "".join(rows)))


def edge(child, column, parent, optional, tables_by_name):
    """child -> parent, crow's foot on the many end, IE notation on the one end."""
    pk = next((c["column"].lower() for c in tables_by_name[parent]["columns"] if c["pk"]), None)
    tail = ':"%s"' % column.lower() if any(
        c["column"] == column for c in tables_by_name[child]["columns"]) else ""
    head = ':"%s"' % pk if pk else ""
    # no edge label: each edge lands on the port of the very column that would name it
    return ('  "%s"%s -> "%s"%s [dir=both, arrowtail=crow, arrowhead=%s, '
            'color="#7d8b99", penwidth=1.1];' % (child, tail, parent, head,
                                                 "odot" if optional else "tee"))


def graph(body, rankdir="LR"):
    return "\n".join([
        "digraph erd {",
        '  graph [rankdir=%s, bgcolor="white", fontname="%s", nodesep=0.45, ranksep=1.1, '
        'splines=spline, pad=0.3];' % (rankdir, FONT),
        '  node [shape=plain, fontname="%s"];' % FONT,
        '  edge [fontname="%s"];' % FONT,
    ] + body + ["}", ""])


def schema_dot(tables, table_of):
    body = [node(t) for t in tables]
    body += [edge(*fk, tables_by_name={t["table"]: t for t in tables})
             for fk in foreign_keys(tables, table_of)]
    return graph(body)


def table_dot(focus, tables, table_of):
    """The focus table in full, plus every table it joins to, keyed columns only."""
    by_name = {t["table"]: t for t in tables}
    fks = [fk for fk in foreign_keys(tables, table_of)
           if focus["table"] in (fk[0], fk[2])]
    neighbours = {n for fk in fks for n in (fk[0], fk[2])} - {focus["table"]}
    body = [node(focus, accent=FOCUS)]
    for name in sorted(neighbours):
        t = by_name[name]
        keyed = [c for c in t["columns"] if c["pk"] or c["fk"]]
        body.append(node(t, columns=keyed or t["columns"]))
    body += [edge(*fk, tables_by_name=by_name) for fk in fks]
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
        tables = merge_by_table(model["entities"])
        table_of = {e["class"]: e["table"] for e in model["entities"]}
        out = root / module / "doc" / "erd"
        out.mkdir(parents=True, exist_ok=True)

        written = {out / ("_%s.svg" % module)}
        render_svg(schema_dot(tables, table_of), out / ("_%s.svg" % module),
                   "%s: every table the %s module maps" % (
                       SCHEMA_TITLES.get(module, module.upper()), module))
        for t in tables:
            svg = out / ("%s.svg" % t["table"])
            render_svg(table_dot(t, tables, table_of), svg,
                       "%s and its direct neighbours, as the %s module maps them" % (
                           t["table"], module))
            written.add(svg)
        stale = [p for p in sorted(out.iterdir())
                 if p.is_file() and p not in written and p.suffix in (".svg", ".md")]
        for p in stale:
            p.unlink()
        print("%s -- %d svg%s" % (out.relative_to(root), len(written),
                                  ", removed %d stale" % len(stale) if stale else ""))
