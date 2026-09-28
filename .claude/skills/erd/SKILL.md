---
name: erd
description: Generate or refresh the ER diagrams under a module's doc/erd directory -- _<module>.svg for the whole schema and one <OBJECT>.svg per table or view -- from that module's Jakarta Persistence mappings and the upstream DDL. Use when asked to create, update, regenerate or replace a module's ERD, entity diagram or schema diagram, and after adding, removing or remapping an entity, view, column or relationship.
---

# Module ERDs

Each persistence module keeps its ER diagrams in `<module>/doc/erd`, as SVG and nothing
else:

| File | Shows |
| ---- | ----- |
| `_<module>.svg` | every table and view of the schema, with every mapped column |
| `<OBJECT>.svg` | one table or view in full, plus its neighbours, keyed columns only |

**Every table and view the upstream schema declares gets drawn**, and gets its own file --
a view as much as a table, an object no entity maps as much as a mapped one. The schema
diagram is the schema, not just the part of it that reached Java.

An object the module maps is drawn from its entity. An object it does not map is drawn from
the DDL alone -- columns, types, `NOT NULL`, primary and foreign keys -- greyed throughout
and tagged `NOT MAPPED`. `CO.STORE_ORDERS` and `CO.PRODUCT_REVIEWS` are the two right now:
neither has a non-null unique column set to carry an `@Id`.

The underscore keeps the whole-schema diagram first in a directory listing. The files are
generated -- never hand-edit one; fix the mappings or the generator and regenerate.

## Generate

```shell
$ python3 .claude/skills/erd/scripts/generate_erd.py            # co, hr and sh
$ python3 .claude/skills/erd/scripts/generate_erd.py hr         # one module
$ python3 .claude/skills/erd/scripts/generate_erd.py hr --dump  # the parsed model, as JSON
```

The run prints how many tables and views each module's diagrams cover, and how many of them
no entity maps. `--dump` carries `kind` and `selectsFrom` per entity, and the whole parsed
upstream schema under `objects`.

Rendering is Graphviz, so `dot` must be on `PATH` (`brew install graphviz`); the script
stops with that message if it is missing. Each run rewrites every diagram for the module
and deletes files in `doc/erd` it did not write, so a dropped entity takes its diagram with
it. `test-base` has no entities and gets no directory.

## Where the facts come from

| Fact | Source |
| ---- | ------ |
| objects, columns, keys, `NN` | the `@Entity` classes under `<module>/src/main/java` |
| relationships and cardinality | `@ManyToOne` / `@JoinColumn` (and a `@OneToMany` whose other side nothing owns) |
| column types | the upstream DDL in `db-sample-schemas/<schema>/<x>_create.sql` |
| table vs. view vs. materialized view | whether that DDL declares the object with `CREATE TABLE` or `CREATE [MATERIALIZED] VIEW` |
| what a view is derived from | the `FROM` clause of the view's `CREATE` statement |
| an unmapped object's columns, keys and `NN` | that DDL alone -- there is no entity to read |

Upstream keys are read from both shapes the schemas use: declared inside `CREATE TABLE`
(`sh`), or added afterwards by `ALTER TABLE ... ADD` -- one constraint at a time (`co`) or
several wrapped in one `ADD ( ..., ... )` (`hr`).

A view's column types come from its `SELECT` list: an item that is a plain column reference
takes the type of the base column it projects, so `EMP_DETAILS_VIEW` is fully typed. An item
that is an expression -- `SUM(...)`, `COALESCE(...)`, `CASE ... END`, a `JSON_TABLE` column --
has no upstream type, so the diagram falls back to the Java type. That is correct, not a gap;
those are exactly the columns whose type the mapping alone decides.

The generator resolves `COLUMN_NAME_*` / `TABLE_NAME` constants, including cross-class
references such as `OrderItemWithEmbeddedId.COLUMN_NAME_PRODUCT_ID`, so it depends on the
conventions in CLAUDE.md: every mapped column names itself through a constant, and every
entity declares `TABLE_NAME`.

## Rules the output follows

- **One node per database object, not per class.** The `WithEmbeddedId` / `WithIdClass` pairs
  map the same table and merge into a single node.
- **A view is drawn as a view**: rounded corners, a lighter header carrying a `VIEW` or
  `MATERIALIZED VIEW` tag, and a dashed arrow from each object it selects from, source to view.
  A derivation arrow attaches to no column, which is how it reads apart from a foreign key.
- **A view's identifier badge says `ID`, not `PK`.** A view has no primary key; what the badge
  marks is the `@Id` the entity chose, which JPA requires and the database does not enforce.
  A view mapped without an `@Id` simply shows no badge.
- **An edge per foreign key**, drawn child to parent: crow's foot on the child end, `tee`
  (exactly one) or `odot` (zero or one) on the parent end, by the FK's nullability. Each
  edge attaches to the port of the column it is about, which is why edges carry no label.
- **Every object, but only the mapped columns of a mapped object.** A column the entities do
  not map is absent from its node, and so is a foreign key between two mapped objects that no
  `@ManyToOne` owns. That absence is information; do not add it by hand -- add the mapping
  instead, and regenerate. An unmapped object is the one case the DDL speaks for directly,
  because there is no mapping whose silence could mean anything.
- **An upstream foreign key is drawn only when one of its ends is unmapped**, in a lighter
  grey. Between two mapped objects the edge comes from the `@ManyToOne` or not at all, so a
  greyed edge always means: this link exists in the database and nothing in Java carries it.
- The focus object of a per-object diagram wears the accent header; its neighbours are
  greyed and reduced to their keyed columns. A view's neighbours are the objects it selects
  from; a table's include every view that selects from it.

## After generating

1. Watch the run for Graphviz warnings -- an `unrecognized port` means a relationship names
   a column the table does not map, i.e. a broken mapping, not a broken diagram.
2. `python3 ... --dump <module> | grep '"sqlType": ""'` -- an empty SQL type means the
   column was not found in the DDL, usually a renamed or mistyped `COLUMN_NAME_*`
   constant. The diagram falls back to the Java type; check the mapping before accepting it.
   On a view, check the `SELECT` list first: a derived column legitimately has no SQL type,
   a projected one should have found its base column.
3. `git status <module>/doc/erd` -- a deleted diagram should correspond to an object the
   upstream schema genuinely stopped declaring, not to an entity that was merely unmapped: an
   unmapped object keeps its diagram, greyed.
4. Open `_<module>.svg` and confirm every object is connected -- a table by its foreign keys, a
   view by a dashed arrow from each source it selects from -- unless the schema genuinely
   declares a standalone table (`SH.SUPPLEMENTARY_DEMOGRAPHICS` has no upstream foreign key at
   all). A view with no dashed arrow means its `FROM` clause did not parse; check that before
   accepting it.
5. Check the `NOT MAPPED` nodes against intent. Each one is a table or view of the schema that
   no entity maps, so each is either deliberate -- no non-null unique column set to carry an
   `@Id` -- or a mapping still to write. Say which, per object, when reporting.
6. Report which modules changed and why -- a new entity, a changed FK, a renamed column.

If the mappings are right but a diagram is wrong, the bug is in
`scripts/generate_erd.py`; fix it there so the next regeneration stays correct.
