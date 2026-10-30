#!/usr/bin/env python3
"""Generate the logical ER diagrams of a schema, as SVG, from its model.

    $ python3 .claude/skills/erd/scripts/generate_erd.py                  # every model under ../models
    $ python3 .claude/skills/erd/scripts/generate_erd.py models/sakila.toml

A model (TOML) declares the schema's tables and views by their keys alone -- each table's primary key, foreign keys and,
for a table with no primary key, the key that identifies a row -- each view's sources, and the schema's logical
groups. From it, into the model's `output` directory (relative to the repository root), every run writes

    __<schema>.svg   every table and the foreign keys among them, top to bottom
    _<group>.svg     a group's tables and the foreign keys among them only, top to bottom
    <table>.svg      the table, the tables it references and the tables referencing it, and its foreign keys,
                     left to right
    <view>.svg       the view and the tables it reads, without their foreign keys, left to right

and deletes every other .svg there. A table shows only the columns of its keys, and of a join no foreign key declares, dotted; a view shows none. The same object is
drawn the same way, at the same size, in every diagram of every project that uses this script.

The script is the same file in every project; change it in one, copy it to the others.
"""

import html
import shutil
import subprocess
import sys
import tomllib
from pathlib import Path

# the sans-serif the book's PDF draws SVG text in (its theme's svg fallback font), so Graphviz sizes every cell for the
# text that will be in it
FONT = "Noto Sans KR"
# 9pt comes out at 8.1pt at the book's svg-scale of 0.9, a little under its 9pt body text
FONT_SIZE = 9
BADGE_SIZE = 7.5
INK = "#000000"
MUTED = "#555555"
LINE = "#8a8a8a"
HEADER = "#d9e2ec"
SUBJECT = "#1f4e79"
SUBJECT_INK = "#ffffff"


class Table:
    def __init__(self, name, spec):
        self.name = name
        self.pk = list(spec.get("pk", []))
        self.ak = list(spec.get("ak", []))
        self.fks = []
        for fk in spec.get("fk", []):
            self.fks.append({
                "columns": list(fk["columns"]),
                "references": fk["references"],
                "ref_columns": list(fk.get("ref_columns", [])),
                "nullable": bool(fk.get("nullable", False)),
                "declared": bool(fk.get("declared", True)),
            })
        fk_columns = [c for fk in self.fks for c in fk["columns"]]
        # a column of a join no foreign key declares is drawn, for the dotted line to leave from, but badged FK only if
        # some declared foreign key has it too
        declared = {c for fk in self.fks if fk["declared"] for c in fk["columns"]}
        rows = []
        for c in self.pk + self.ak + fk_columns:
            if c not in rows:
                rows.append(c)
        self.rows = rows
        self.badges = {}
        for c in rows:
            badge = []
            if c in self.pk:
                badge.append("PK")
            elif c in self.ak:
                badge.append("AK")
            if c in declared:
                badge.append("FK")
            self.badges[c] = ",".join(badge)
        self.optional = {c for fk in self.fks if fk["nullable"] for c in fk["columns"]}


class View:
    def __init__(self, name, spec):
        self.name = name
        self.kind = spec.get("kind", "view")
        self.sources = list(spec.get("sources", []))


def port(column, side):
    """A row's port: its name cell on the west, where a foreign key arrives, its badge cell on the east, where one leaves."""
    return f"{side}_{column}"


def node(obj, subject):
    """The HTML-like label of a table or view."""
    head_bg, head_ink = (SUBJECT, SUBJECT_INK) if subject else (HEADER, INK)
    if isinstance(obj, View):
        tag = "MATERIALIZED VIEW" if obj.kind == "materialized view" else "VIEW"
        return (f'<<TABLE BORDER="1" CELLBORDER="0" CELLSPACING="0" CELLPADDING="5" STYLE="dashed" COLOR="{INK}">'
                f'<TR><TD BGCOLOR="{head_bg}"><FONT COLOR="{head_ink}" POINT-SIZE="{FONT_SIZE}"><B>'
                f'{html.escape(obj.name)}</B></FONT></TD></TR>'
                f'<TR><TD><FONT COLOR="{MUTED}" POINT-SIZE="{BADGE_SIZE}">{tag}</FONT></TD></TR></TABLE>>')
    rows = []
    for c in obj.rows:
        text = html.escape(c)
        if c in obj.pk:
            text = f"<U>{text}</U>"
        if c in obj.optional:
            text = f"<I>{text}</I>"
        badge = f'<FONT COLOR="{MUTED}" POINT-SIZE="{BADGE_SIZE}">{obj.badges[c]}</FONT>' if obj.badges[c] else " "
        rows.append(f'<TR><TD ALIGN="LEFT" PORT="{port(c, 'w')}">{text}</TD>'
                    f'<TD ALIGN="LEFT" PORT="{port(c, 'e')}">{badge}</TD></TR>')
    return (f'<<TABLE BORDER="0" CELLBORDER="1" CELLSPACING="0" CELLPADDING="5" COLOR="{INK}">'
            f'<TR><TD COLSPAN="2" BGCOLOR="{head_bg}"><FONT COLOR="{head_ink}"><B>{html.escape(obj.name)}</B></FONT></TD></TR>'
            + "".join(rows) + '</TABLE>>')


def fk_edge(child, fk, parent):
    """A foreign key, child to parent, in crow's foot: the child's end says how many, the parent's whether it must be."""
    ref_columns = fk["ref_columns"] or parent.pk or parent.ak
    head = f'"{parent.name}":"{port(ref_columns[0], "w")}":w' if ref_columns else f'"{parent.name}"'
    one_to_one = set(fk["columns"]) == set(child.pk) and child.pk
    tail = "teeodot" if one_to_one else "crowodot"
    end = "teeodot" if fk["nullable"] else "teetee"
    style = "solid" if fk["declared"] else "dotted"
    return (f'"{child.name}":"{port(fk["columns"][0], "e")}":e -> {head} '
            f'[dir=both, arrowtail={tail}, arrowhead={end}, style={style}];')


def graph(model, objects, subject, rankdir, views_of=None):
    names = set(objects)
    lines = [
        "digraph erd {",
        f'  graph [rankdir={rankdir}, nodesep=0.4, ranksep=0.6, pad=0.1];',
        f'  node [shape=plain, fontname="{FONT}", fontsize={FONT_SIZE}];',
        f'  edge [color="{LINE}", arrowsize=0.8, penwidth=1.2];',
    ]
    for name in objects:
        obj = model["objects"][name]
        lines.append(f'  "{name}" [label={node(obj, name == subject)}];')
    for name in objects:
        obj = model["objects"][name]
        if isinstance(obj, Table):
            for fk in obj.fks:
                # a table's diagram draws its own foreign keys only, and a view's none, so the neighbours stay one
                # column on each side
                if fk["references"] in names and (subject is None or subject in (name, fk["references"])):
                    lines.append("  " + fk_edge(obj, fk, model["objects"][fk["references"]]))
        elif views_of is not None and name == views_of:
            for source in obj.sources:
                if source in names:
                    lines.append(f'  "{source}" -> "{name}" [style=dashed, arrowhead=vee];')
    lines.append("}")
    return "\n".join(lines) + "\n"


def render(dot_source, comment, path):
    svg = subprocess.run(["dot", "-Tsvg"], input=dot_source, capture_output=True, text=True, check=True).stdout
    # the comment goes after the XML declaration and the doctype, ahead of the <svg> element
    i = svg.index("<svg")
    svg = svg[:i] + f"<!-- {html.escape(comment, quote=False).replace('--', '- -')} -->\n" + svg[i:]
    path.write_text(svg, encoding="utf-8")


def load(path):
    spec = tomllib.loads(path.read_text(encoding="utf-8"))
    objects = {}
    for name, t in spec.get("table", {}).items():
        objects[name] = Table(name, t)
    for name, v in spec.get("view", {}).items():
        objects[name] = View(name, v)
    for obj in objects.values():
        refs = [fk["references"] for fk in obj.fks] if isinstance(obj, Table) else obj.sources
        for ref in refs:
            if ref not in objects or not isinstance(objects[ref], Table):
                sys.exit(f"{path}: {obj.name} refers to {ref}, which is no table of the model")
    groups = spec.get("group", [])
    for group in groups:
        for name in group["tables"]:
            if not isinstance(objects.get(name), Table):
                sys.exit(f"{path}: group {group['name']} lists {name}, which is no table of the model")
    return {"schema": spec["schema"], "title": spec.get("title", spec["schema"]), "output": spec["output"],
            "objects": objects, "groups": groups}


def generate(model, root):
    out = root / model["output"]
    out.mkdir(parents=True, exist_ok=True)
    objects = model["objects"]
    tables = [n for n, o in objects.items() if isinstance(o, Table)]
    views = [n for n, o in objects.items() if isinstance(o, View)]
    schema = model["schema"]
    written = {}

    written[f"__{schema}.svg"] = (
        graph(model, tables, None, "TB"),
        f"{model['title']}: every table, its key columns, and the foreign keys among them")
    for group in model["groups"]:
        written[f"_{group['name']}.svg"] = (
            graph(model, group["tables"], None, "TB"),
            f"{schema}, {group.get('title', group['name'])}: {', '.join(group['tables'])}; "
            f"the foreign keys among them only")
    for name in tables:
        table = objects[name]
        parents = [fk["references"] for fk in table.fks]
        children = [n for n in tables if any(fk["references"] == name for fk in objects[n].fks)]
        members = [name] + [n for n in dict.fromkeys(parents + children) if n != name]
        written[f"{name}.svg"] = (
            graph(model, members, name, "LR"),
            f"{schema}.{name}: the table, the tables it references and the tables referencing it")
    for name in views:
        view = objects[name]
        written[f"{name}.svg"] = (
            graph(model, [name] + view.sources, name, "LR", views_of=name),
            f"{schema}.{name}: the {view.kind} and the tables it reads")

    for file, (source, comment) in written.items():
        render(source, comment, out / file)
    stale = [p for p in out.glob("*.svg") if p.name not in written]
    for p in stale:
        p.unlink()
    print(f"{model['output']}: {len(written)} diagrams ({len(tables)} tables, {len(views)} views, "
          f"{len(model['groups'])} groups){f', {len(stale)} removed' if stale else ''}")


def main():
    if not shutil.which("dot"):
        sys.exit("Graphviz's dot is not on PATH (brew install graphviz)")
    here = Path(__file__).resolve()
    root = here.parents[4]
    models = here.parents[1] / "models"
    paths = [Path(a) if Path(a).is_absolute() else here.parents[1] / a for a in sys.argv[1:]] \
        or sorted(models.glob("*.toml"))
    if not paths:
        sys.exit(f"no model under {models}")
    for path in paths:
        generate(load(path), root)


if __name__ == "__main__":
    main()
