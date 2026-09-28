---
name: erd
description: Generate or refresh the ER diagrams under a module's doc/erd directory -- _<module>.svg for the whole schema and one <TABLE>.svg per table -- from that module's Jakarta Persistence mappings. Use when asked to create, update, regenerate or replace a module's ERD, entity diagram or schema diagram, and after adding, removing or remapping an entity, column or relationship.
---

# Module ERDs

Each persistence module keeps its ER diagrams in `<module>/doc/erd`, as SVG and nothing
else:

| File | Shows |
| ---- | ----- |
| `_<module>.svg` | every table the module maps, with every mapped column |
| `<TABLE>.svg` | one table in full, plus the tables it joins to, keyed columns only |

The underscore keeps the whole-schema diagram first in a directory listing. The files are
generated -- never hand-edit one; fix the mappings or the generator and regenerate.

## Generate

```shell
$ python3 .claude/skills/erd/scripts/generate_erd.py            # co, hr and sh
$ python3 .claude/skills/erd/scripts/generate_erd.py hr         # one module
$ python3 .claude/skills/erd/scripts/generate_erd.py hr --dump  # the parsed model, as JSON
```

Rendering is Graphviz, so `dot` must be on `PATH` (`brew install graphviz`); the script
stops with that message if it is missing. Each run rewrites every diagram for the module
and deletes files in `doc/erd` it did not write, so a dropped entity takes its diagram with
it. `test-base` has no entities and gets no directory.

## Where the facts come from

| Fact | Source |
| ---- | ------ |
| tables, columns, keys, `NN` | the `@Entity` classes under `<module>/src/main/java` |
| relationships and cardinality | `@ManyToOne` / `@JoinColumn` (and a `@OneToMany` whose other side nothing owns) |
| column types | the upstream DDL in `db-sample-schemas/<schema>/<x>_create.sql` |

The generator resolves `COLUMN_NAME_*` / `TABLE_NAME` constants, including cross-class
references such as `OrderItemWithEmbeddedId.COLUMN_NAME_PRODUCT_ID`, so it depends on the
conventions in CLAUDE.md: every mapped column names itself through a constant, and every
entity declares `TABLE_NAME`.

## Rules the output follows

- **One node per table, not per class.** The `WithEmbeddedId` / `WithIdClass` pairs map the
  same table and merge into a single node.
- **An edge per foreign key**, drawn child to parent: crow's foot on the child end, `tee`
  (exactly one) or `odot` (zero or one) on the parent end, by the FK's nullability. Each
  edge attaches to the port of the column it is about, which is why edges carry no label.
- **Only what the module maps.** A column the entities do not map, or an upstream FK with
  no `@ManyToOne` behind it, is absent by design. That absence is information; do not add it
  by hand -- add the mapping instead, and regenerate.
- The focus table of a per-table diagram wears the accent header; its neighbours are
  greyed and reduced to their keyed columns.

## After generating

1. Watch the run for Graphviz warnings -- an `unrecognized port` means a relationship names
   a column the table does not map, i.e. a broken mapping, not a broken diagram.
2. `python3 ... --dump <module> | grep '"sqlType": ""'` -- an empty SQL type means the
   column was not found in the DDL, usually a renamed or mistyped `COLUMN_NAME_*`
   constant. The diagram falls back to the Java type; check the mapping before accepting it.
3. `git status <module>/doc/erd` -- a deleted diagram should correspond to a table the
   module genuinely stopped mapping.
4. Open `_<module>.svg` and confirm every table is connected, unless the module genuinely
   maps a standalone table (`SH.COUNTRIES` is one).
5. Report which modules changed and why -- a new entity, a changed FK, a renamed column.

If the mappings are right but a diagram is wrong, the bug is in
`scripts/generate_erd.py`; fix it there so the next regeneration stays correct.
