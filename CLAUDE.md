# CLAUDE.md

Guidance for Claude Code when working in this repository.

## What this project is

Jakarta Persistence mappings for the
[Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas),
one Maven module per schema -- `oracle-sample-schemas-persistence-co`, `-hr` and `-sh`, each in a
directory of the same name and referred to below by its schema (`co`, `hr`, `sh`). The modules are
independent of each other, tests included: each carries its own test bases in its own test package.

**These are pure Jakarta Persistence modules.** Two rules follow from that:

- No main-scope dependency on `io.github.jinahya:jinahya-persistence-*`, or on any
  other persistence helper library. Everything the entities need comes from
  `jakarta.persistence`, `jakarta.validation` and `jakarta.annotation`. Tests may
  use `jinahya-persistence-*` (e.g. `jinahya-persistence-test-utils`, `test` scope).
- **Every entity is standalone.** No entity extends a superclass and there is no
  builder layer: one class holds its own column constants, fields, accessors,
  `toString`, and `equals`/`hashCode`. Do not introduce a shared entity superclass
  to remove duplication between entities — the duplication is the point, because
  it keeps each mapping readable on its own.
- **The `mapped` package is a parallel, independent mapping.** Each module also
  exports a `<schema>.mapped` package with one abstract `@MappedSuperclass` per
  table or view (`MappedCountry`, `MappedStoreOrder`, …), for readers who want to
  extend a mapping rather than use the concrete entity. The entities do **not**
  extend these classes; the two sets are kept in step by hand, so a change to an
  entity's columns, constants or `equals`/`hashCode` is made in its `Mapped*`
  counterpart too.
- **Each package has its own markers, and neither package refers to the other.**
  Every class that maps a table or a view implements its own package's marker:
  `__DomainEntity<T>` in the concrete package, `__MappedDomainEntity<T>` in
  `mapped`. `T` is the identifier type: `Void` for a view with no key, the
  `<T>` type variable for an `@EmbeddedId` `Mapped*`, and the `Mapped*Id` class
  for an `@IdClass` `Mapped*`. Id classes, embeddables and helpers implement
  neither. The two markers are unrelated -- `__DomainEntity` does not extend
  `__MappedDomainEntity` -- and the same goes for `__DomainConstants` and
  `__MappedDomainConstants`, two independent `final` classes. Nothing in the
  concrete package imports anything from `mapped`, and nothing in `mapped`
  imports anything from the concrete package. ArchUnit enforces both directions,
  on main classes: `___PackageSeparation_Test` in each module's test package, and
  `mapped.___MappedPackageSeparation_Test`. A constant the compiler inlines leaves
  no dependency behind, so the rule cannot see one; keep to it anyway.
- **Every `Mapped*` class's `toString`, `equals` and `hashCode` are `final`.**
  A class that cannot compare by an identifier -- a view with no key, or the
  embeddable base `_MappedBinary` -- omits `equals`/`hashCode` and keeps
  identity equality. In the concrete classes these methods use basic attributes
  and the `@EmbeddedId` only, never an association.

### The `mapped` branch -- this branch

**This checkout is the `mapped` branch.** The separation above is permanent on `develop`; here
the concrete classes take the other shape. Each one extends its `Mapped*` counterpart and keeps
only what the superclass cannot provide: associations, nested types, static factories,
constructors, `getIdValue()` for an `@EmbeddedId`, and overrides that widen a `protected` getter
to `public`. Its fields, its `toString` / `equals` / `hashCode` (all `final` in `Mapped*`) and its
other accessors go. The markers, constants classes and test bases change their declarations only:
`__DomainEntity` extends `__MappedDomainEntity`, and so on.

The tests and ITs are `develop`'s, unchanged; their passing here is what shows the two shapes
behave alike. Two files are this branch's own, kept through merges by `merge=ours` in
`.gitattributes`: this `CLAUDE.md`, and each module's `___PackageSeparation_Test`, whose rule is
turned around here once that module is converted. `mapped.___MappedPackageSeparation_Test` holds
on both branches and is not one of them. Run `git config merge.ours.driver true` once per clone,
or the attribute does nothing.

Merges go one way only: `develop` is merged into `mapped`, never the other way, and in small,
frequent steps, with `rerere` enabled. A conflict in a concrete class is resolved by carrying
`develop`'s change into the `Mapped*` class and keeping this branch's deletions. The branch builds
as `<version>-mapped-SNAPSHOT`, so its artifacts never overwrite `develop`'s; a conflict on a
`<version>` line keeps the `-mapped` suffix. Modules are converted one at a time, `co` first, with
the full verify under both providers after each.

### Member order inside an entity

Every entity lays its members out in this order, and new members go into the matching
section rather than at the end of the file:

1. constants — `TABLE_NAME`, then a `// ---- COLUMN_NAME` marker per column followed by
   that column's `COLUMN_NAME_*` / `COLUMN_LENGTH_*` / `ATTRIBUTE_NAME_*` / `SIZE_*`
   constants
2. nested types — attribute enums, their marker interfaces, and `AttributeConverter`
   implementations
3. `// ---- STATIC_FACTORY_METHODS`
4. `// ---- CONSTRUCTORS`
5. `// ---- java.lang.Object` — `toString`, then `equals`/`hashCode`
6. accessors, under a `// ---- attributeName` marker per attribute
7. the `private` fields, last, under a plain `// ----` separator

Section markers are line comments padded with dashes to column 120.

### Classes for views with no key

A view that no column, or combination of columns, identifies — `co`'s `PRODUCT_REVIEWS`
and `STORE_ORDERS` — cannot be an `@Entity`, so it is mapped by a plain class
(`ProductReview`, `StoreOrder`, and their abstract `Mapped*` counterparts). Each one has
exactly two constructors:

- a `protected` no-arg constructor, and
- an all-args constructor, taking every attribute in field order — `public` on a
  concrete class, `protected` on an abstract one.

The visibility split is load-bearing. A name-based row mapper (Spring's `JdbcClient` /
`DataClassRowMapper`) uses a class's single `public` constructor, which has to be the
all-args one, and matches its parameter names to the columns. Those names survive
compilation only because the root `pom.xml` sets `maven.compiler.parameters` (`javac
-parameters`); do not remove it. Jakarta Persistence itself does not need it —
`@ConstructorResult` and `SELECT NEW` pass arguments by position.

### The static metamodel, and `ATTRIBUTE_NAME_*`

The **generated static metamodel** (`Customer_`, `Employee_`, …) is available under both
providers, and is what persistence-context code uses: a criteria path is
`root.get(Customer_.emailAddress)`, not a string.

That covers criteria paths only. A **query parameter name is not an attribute name**: it is
whatever the query text declares, so `setParameter` takes the literal from the query —
`setParameter("storeToMatch", …)` for `WHERE e.store = :storeToMatch` — and never
`Inventory_.store.getName()`, which only happens to match while the two are spelled alike.

That works because **both profiles generate it with `hibernate-jpamodelgen`**. The EclipseLink
profile picks the provider and nothing else: it inherits `metamodel.generator.*` from the root
`<properties>` rather than overriding it, and EclipseLink's own processor sits commented out
beside a note saying why.

The reason is where a descriptor has to live. EclipseLink's processor reads a `persistence.xml`
at annotation-processing time and generates nothing without one, and the processor path sees
main resources, not test resources — so using it forces every module's descriptor into
`src/main/resources/META-INF`, which is also what gets packaged. The published jars would then
carry the test descriptor: the H2 datasource, the `sa` / `dmlonly` credentials,
`hbm2ddl.auto=create-drop`, and whichever `<provider>` the releasing profile baked in. Spring's
`DefaultPersistenceUnitManager` reads `classpath*:META-INF/persistence.xml` — every jar on the
classpath — so that descriptor reaches any consumer that wires an entity manager factory the
usual way.

`hibernate-jpamodelgen` needs no descriptor; it reads `@Entity` directly. And the canonical
static metamodel is defined by the specification, so the classes it emits are provider-neutral
and EclipseLink runs against them unchanged. Every module's `persistence.xml` and `orm-it.xml`
therefore live under **`src/test/resources/META-INF`**, and the jars carry entities and metamodel
only.

What this gives up is coverage of EclipseLink's metamodel processor. EclipseLink is still
exercised as the persistence provider — every `*_Persistence_Test` and `*_Persistence_IT` runs
against it — which is the behavioural difference the profile exists for.

Each module names its persistence units after its schema -- `co-test` / `co-it`, `hr-test` /
`hr-it`, `sh-test` / `sh-it` -- because a unit name is only required to be unique within the
archive that declares it, while the modules' test classpaths can still meet in one JVM: an IDE
running every test of the project (a JUnit configuration searching *In whole project*) does exactly
that. Two units sharing a name there is unspecified: the provider takes whichever descriptor it
finds first, silently, and every test of the other modules then aborts with "not a managed type" --
reported as skipped, not failed. Hibernate says so as `HHH008518`.

The same goes for any resource a unit names by path. `orm-it.xml`, which sets the IT unit's default
schema, lives at `META-INF/<schema>/orm-it.xml` -- `META-INF/co/orm-it.xml`, and so on -- and each
module's `persistence.xml` names its own. Were all three at `META-INF/orm-it.xml`, one JVM would
resolve every unit's `<mapping-file>` to the first copy found, and two of the three modules' ITs
would look for their tables in the wrong schema.

### Test bases, one copy per module

There is no shared test module. Each module has, in its own test package, its own test bases,
their CDI producers `_DomainEntity_Persistence_Test_Producer` /
`_DomainEntity_Persistence_IT_Producer`, and the helper `___Persistence_TestUtils`. The bases are
one root and four siblings, none of which extends another:

| base | tests | for |
| --- | --- | --- |
| `__Test<T>` | nothing itself | the root: the target class, its factories, and the checks below as plain methods |
| `_NonEntity_Test<T>` | `toString`, `equals`/`hashCode`, accessors | a class with no identity: an id class, an embeddable, a mapped JSON document |
| `_DomainEntity_Test<T extends __DomainEntity<U>, U>` | the same, and any check only such a class has | a class mapping a table or a view, keyless views included (`U` is `Void`) |
| `_DomainEntity_Persistence_Test<T extends __DomainEntity<U>, U>` | the mapping, against the `<schema>-test` unit | an entity |
| `_DomainEntity_Persistence_IT<T extends __DomainEntity<U>, U>` | the mapping, against the `<schema>-it` unit | an entity |

A class gets one of the first two, and an entity also gets the last two. `U` is the identifier
type its `__DomainEntity<U>` names, so a class without the marker cannot get a persistence test.

The checks live in `__Test` as plain methods, and `_NonEntity_Test` and `_DomainEntity_Test` each
re-declare them with `@Test`; that is what keeps the persistence bases, which extend the same root,
from running them again. Do not move a `@Test` up into `__Test`, and do not add a base between
the root and a sibling: a level earns its place only with a check of its own.

Each persistence base names its producer in its own `@AddBeanClasses`; the producer holds the unit
name as its `PERSISTENCE_UNIT_NAME` constant.

Keep the producers concrete. These classes used to live in a shared `test-base` module, with an
abstract producer that a subclass in each module completed by supplying the name -- and since CDI
does not inherit producer or disposer methods (an inherited `@Produces` is simply not seen, and the
injection point fails with `WELD-001408`), each subclass had to re-declare all four just to supply
it. As with the `Mapped*` classes, the copies are kept in step by hand: a fix to one module's test
base is carried to the other two, unless it is specific to that module.

Every persistence unit carries `<exclude-unlisted-classes>true</exclude-unlisted-classes>`,
and it stays: the unit lists exactly the classes it means, and the flag keeps anything else out.

Every composite identifier is mapped once, in one style. The database declares two composite
primary keys, one per module, and each module shows one style on it: `co`'s `ORDER_ITEMS` is
`OrderItem` with an `@EmbeddedId` (`OrderItemId`, plus an `@MapsId` to `Order`), and `hr`'s
`JOB_HISTORY` is `JobHistory` with an `@IdClass` (`JobHistoryId`). Do not add the other style
alongside -- a class name carries no `WithEmbeddedId` / `WithIdClass` postfix because there is only
ever one. `co`'s `ProductOrder` (over a view) also uses an `@EmbeddedId`. `sh` declares no composite
key; the identifiers it chooses for its unkeyed tables and views use both styles, one of each per
kind of object -- of the tables, `Sale` uses an `@EmbeddedId` and `Cost` an `@IdClass`; of the
views, `FweekPscatSalesMv` uses an `@EmbeddedId` and `Profit` an `@IdClass`.

The `Mapped*` counterparts split the same way. An `@IdClass` one (`MappedJobHistory`, `MappedCost`,
`MappedProfit`) maps the `@Id` attributes itself, as basic types, and compares by them, so it takes
no type parameter: an extending entity adds only `@IdClass(...)`. An `@EmbeddedId` one
(`MappedOrderItem`, `MappedProductOrder`, `MappedSale`, `MappedFweekPscatSalesMv`) cannot declare
the identifier, because EclipseLink rejects an `@EmbeddedId` typed by a type variable
(`EclipseLink-7246`); it takes the id type as `<T>`, and the extending entity declares the
`@EmbeddedId` and implements `getIdValue()`, by which the class compares.

The entity's own `ATTRIBUTE_NAME_*` constants stay, for the places a metamodel reference
cannot go: an annotation value must be a compile-time constant, so `mappedBy` takes
`@OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_JOB)` and never `Employee_.job.getName()`.

## Dependency convergence

Dependency convergence is **very important** in this project. A multi-module Maven
build that lets the same artifact resolve to different versions on different
classpaths produces failures that only show up at runtime, so treat any divergence as
a build defect, not a warning. `enforce-dependency-convergence` runs on every module
and is not skipped.

### Origin: the Jakarta EE umbrella platform

Versions originate from the **Jakarta EE platform umbrella BOM**, not from individual
artifacts:

- `jakarta.platform:jakarta.jakartaee-bom` is imported (`<type>pom</type>`,
  `<scope>import</scope>`) in the root `pom.xml` `<dependencyManagement>`, pinned by
  the `version.jakarta.jakartaee-bom` property (currently `11.0.0`).
- **Jakarta EE 11 is the only supported generation.** The platform values live in the
  root `<properties>` — `version.jakarta.jakartaee-bom`, `persistence.version` (3.2),
  `persistence.schemaLocation` — deliberately *not* in a profile, so no profile can
  move the platform out from under the implementations.
- **The persistence provider is the only implementation axis**, and it has exactly two
  profiles: `jakarta-ee-11-hibernate-orm` (`activeByDefault`) and
  `jakarta-ee-11-eclipselink`. Every other spec has a single implementation here
  (Hibernate Validator + Expressly for Jakarta Validation, Weld for CDI), pinned to
  its EE 11 aligned release in `<properties>`; there is nothing to choose, so it gets
  no profile. Neither provider profile picks a *version* either — both versions live
  in `<properties>`; a profile decides only which provider is on the test classpath
  and which metamodel generator the annotation processor path uses.
- Do not add a profile to test a second version of anything. Varying Hibernate
  Validator, Expressly or Weld tests those projects, not this one. Any profile that
  is added must carry the `jakarta-ee-11-` prefix and actually be certified for that
  generation.

Consequences:

- Never pin a `jakarta.*` API artifact (`jakarta.persistence-api`,
  `jakarta.validation-api`, `jakarta.enterprise.cdi-api`, `jakarta.el-api`, …) to an
  explicit version. Let the umbrella BOM decide, and change the platform version in
  one place instead.
- When a provider drags in a `jakarta.*` API newer or older than the umbrella BOM,
  align the provider with the platform generation — do not override the API version.
- Check convergence against both providers, not just the default:
  `./mvnw -q -Pjakarta-ee-11-hibernate-orm enforcer:enforce -Drules=dependencyConvergence`,
  then the same with `-Pjakarta-ee-11-eclipselink`.
- Naming any profile on the command line deactivates every `activeByDefault` profile.
  Harmless while the provider is the only axis — `-Pjakarta-ee-11-eclipselink` names
  everything that had to be chosen — but it is why a second axis must not be added
  casually.

### Keeping specs and implementations aligned

| Spec (API, from the umbrella BOM) | Implementation | Version property |
| --- | --- | --- |
| Jakarta Persistence (`jakarta.persistence-api`) | Hibernate ORM (`org.hibernate.orm:hibernate-core`) | `version.org.hibernate.orm` |
| Jakarta Persistence (`jakarta.persistence-api`) | EclipseLink (`org.eclipse.persistence:org.eclipse.persistence.jpa`) | `version.org.eclipse.persistence` |
| Jakarta Validation (`jakarta.validation-api`) | Hibernate Validator | `version.org.hibernate.validator` |
| Jakarta Expression Language (`jakarta.el-api`) | Expressly — required by Hibernate Validator | `version.org.glassfish.expressly` |
| Jakarta CDI (`jakarta.enterprise.cdi-api`) | Weld (`weld-junit5`) | `version.org.jboss.weld` |

Rules:

- The persistence provider is never hard-coded: `persistence-unit.provider` and
  `metamodel.generator.groupId/artifactId/version` default to Hibernate in
  `<properties>` and are overridden by `jakarta-ee-11-eclipselink`;
  `persistence.xml` and the annotation processor path read them. Change the profile,
  not the literal provider class.
- Both providers must stay buildable. A change made for Hibernate has to be checked
  against EclipseLink and vice versa.
- Hibernate Validator and Expressly move together: validator 9 ↔ expressly 6. Never
  bump one without the other.
- `version.org.jboss.weld` holds the **weld-junit5 (weld-testing)** version, not the
  Weld version — weld-junit5 5.0.x is the Weld 6 / CDI 4.1 line.

### Java release

`maven.compiler.release` is 17 for main sources and 25 for test sources, so the
published jars stay consumable on 17 while the tests use current language features.
**Building the tests therefore needs a JDK 25 or newer.**

### Detecting and fixing convergence problems

- `./mvnw -q dependency:tree -Dverbose` — `(version managed from ...)` / `omitted for
  conflict with ...` mark divergent transitive versions.
- `./mvnw enforcer:enforce -Drules=dependencyConvergence` — run it against the whole
  reactor, not one module; a conflict is a property of the aggregated graph.
- Do **not** silently accept Maven's "nearest wins". Resolve `jakarta.*` at the
  platform level; pin anything else in the root `<dependencyManagement>`, preferring
  the highest version any path requires. Use `<exclusions>` only when a dependency
  genuinely must not be on the classpath.
- If a conflict cannot be resolved without a behavioural change, stop and report it
  rather than choosing a version unilaterally.

### Adding or upgrading a dependency

- Part of Jakarta EE → take it from the imported BOM with no `<version>` at all.
- Otherwise declare the version in the root `pom.xml` (`<dependencyManagement>` /
  `version.*` property); child modules declare `groupId`/`artifactId` and scope only.
- Re-check convergence across the reactor before committing.

## Testing

There is one script per provider per depth, and every one of them prepends `clean`.
That `clean` is not optional: the annotation processor writes a metamodel per provider,
and without a clean in between the second provider compiles against the first's
leftovers.

| runs | Hibernate ORM | EclipseLink |
| --- | --- | --- |
| unit tests | `_mvn_jakarta_ee_11_test_hibernate-orm.sh` | `_mvn_jakarta_ee_11_test_eclipselink.sh` |
| unit tests and ITs | `_mvn_jakarta_ee_11_verify_hibernate-orm.sh` | `_mvn_jakarta_ee_11_verify_eclipselink.sh` |
| no test, but still compiles them | `_mvn_jakarta_ee_11_verify_notests_hibernate-orm.sh` | `_mvn_jakarta_ee_11_verify_notests_eclipselink.sh` |

The ITs want a live Oracle at `localhost:1521/freepdb1`; `./_docker-compose-up.sh` brings
one up. The `_verify_notests_` pair is the one to reach for when there is no database.

Which half of a `verify` run executes is chosen by two separate flags, because **as of
maven-failsafe-plugin 3.6.0 failsafe no longer binds its own `skipTests` parameter to the
`skipTests` user property**. So `-DskipTests` now reaches surefire alone — passing it to a
`_verify_` script leaves the ITs running — and `-DskipITs` is what drops the ITs. Under
3.5.x and earlier `-DskipTests` reached both, so this behaviour is tied to
`version.maven-failsafe`.
