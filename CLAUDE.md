# CLAUDE.md

Guidance for Claude Code when working in this repository.

## What this project is

Jakarta Persistence mappings for the
[Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas),
one Maven module per schema -- `oracle-sample-schemas-persistence-co`, `-hr` and `-sh`, each in a
directory of the same name and referred to below by its schema (`co`, `hr`, `sh`). The three depend on
`oracle-sample-schemas-persistence-common`, which holds what they share (as in the sibling
`sqlserver-samples-persistence`), and on nothing else of each other; its version is managed in the root
`<dependencyManagement>` as `${project.version}`. Their tests stay independent: each carries its own test bases
in its own test package.

`common` holds, in `common.annotation`, `SOURCE`-retention annotations that document what the DDL declares beyond
the mapping (`@OraTrigger`, `@OraStorage`, `@OraMaterializedView`, `@OraIdentity`), so the schema modules read it
`requires static`. Those whose subject Jakarta Persistence covers (`@OraComment`, `@OraCheck`, `@OraIndex`,
`@OraSequence`) are `@Deprecated(forRemoval = true)` and name the replacement, as the sibling's `Ms*` ones do.

**These are pure Jakarta Persistence modules.** Two rules follow from that:

- No main-scope dependency of `co`, `hr` or `sh` on `io.github.jinahya:jinahya-persistence-*`,
  or on any other persistence helper library. Everything the entities need comes from
  `jakarta.persistence`, `jakarta.validation` and `jakarta.annotation`. `common` is the
  exception: it depends on `jinahya-persistence-more` and `-utils` at `compile` scope, and each
  schema module redeclares both at `test` scope, which keeps them off its main classpath. The
  root `<dependencyManagement>` manages the versions of `jinahya-persistence-more`, `-utils` and
  `-test-utils` (`version.jinahya-persistence`), and no module declares a version of its own; `-test-utils` is a
  `test` dependency of the three schema modules only.
- **Main holds mapped superclasses; the entities are test code.** As in the sibling
  `postgresql-demodb-persistence`, each module's main sources are the package
  `<schema>.mapped` alone: one `@MappedSuperclass` `Mapped<Entity>` per table and view, one
  `<Entity>Id` per composite identifier, and the embeddables and converters that carry no
  association. A mapped superclass holds what the schema says -- names, types, lengths,
  nullability, check constraints as validation constraints, `toString`/`equals`/`hashCode` --
  and nothing an application decides: no association, no fetch type, no cascade, no named
  query. The entities (`Customer extends MappedCustomer`, ...) live in `src/test/java`, package
  `<schema>`, and add `@Entity`, `@Table`, the named queries and the associations.
- **A foreign-key column is read-only in the mapped superclass.** It is a basic attribute with
  `insertable = false, updatable = false`, a `public` getter and a `protected` setter; the
  entity owns its writable mapping through the association's `@JoinColumn`, and the
  association's setter keeps the id in step. Each column's writability is split, never changed.
- **An `@EmbeddedId` is declared by the entity, not by the mapped superclass.** Unlike postgres's
  `MappedRoute<T>`, no mapped superclass here holds an `@EmbeddedId T id`: EclipseLink 5.0 reads
  the type variable as `String` (EclipseLink-7246), or fails predeploy with a
  `ConcurrentModificationException`, depending on the unit's classes -- so `common`'s
  `MappedWithEmbeddedId<T>`, which the sibling `sqlserver-samples-persistence` extends, is not used
  here. The mapped superclass declares an abstract `protected` `getId_()` returning its
  `<Entity>Id`, which `equals`/`hashCode`/`toString` use, and the entity declares the field,
  `id_`.
- **An id class follows the sibling `sqlserver-samples-persistence`.** It is `<Entity>Id`, in
  `<schema>.mapped` of main, with no `Mapped` prefix, and nothing extends it: for an `@EmbeddedId`
  a plain `@Embeddable` which maps the key columns; for an `@IdClass` a plain class with no columns,
  whose `@NotNull` attributes match the mapped superclass's `@Id` attributes by name and by type.
  Its `equals`/`hashCode` are `final` and read through the getters. Its `<Entity>Id_Test` and
  `<Entity>Id_Randomizer` are in `<schema>.mapped` of the tests. Where `@MapsId` derives the whole
  key (`sh`'s `Sales`), the entity's `of(...)` takes the associations, the key is `null` until
  persist, `id_` carries no `@NotNull` (EclipseLink validates at `prePersist`, before the
  provider fills it), and `hashCode` is a constant. An `@IdClass` entity's `of(...)` takes the
  associations, and each `private` setter copies its id into the `@Id` attribute. An `@Id`
  attribute which is a foreign key is the exception to the read-only rule above: it writes its
  column, and its getter is `protected`, as is its setter where it has one (`sh`'s `MappedCost`;
  `hr`'s `MappedJobHistory`, which a trigger writes, has none); a value of the row's own keeps a
  `public` getter.
- **No shared superclass between tables.** Each `Mapped<Entity>` stands on its own: it holds
  its own column constants, fields, accessors, `toString`, and `equals`/`hashCode`. Do not add
  a common superclass to remove duplication between them -- the duplication keeps each mapping
  readable on its own.
- **Shared code has two homes per module.** Each module has a package-private `_Constants`
  and `_Utils` in `<schema>.mapped`, kept even when empty, and only what more than one class
  shares goes in them. There is no `__DomainConstants` or other per-domain catch-all.
- **`toString`, `equals` and `hashCode` use basic attributes and the `@EmbeddedId`
  only, never an association.** A class that cannot compare by an identifier --
  a view with no key, or the embeddable base `MappedBinary` -- omits `equals`/`hashCode`
  and keeps identity equality.

### Member order inside an entity

Every mapped superclass and every entity lays its members out in this order, and new members go
into the matching section rather than at the end of the file:

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

Section markers are line comments padded with dashes to column 100.

### Classes for views with no key

A view that no column, or combination of columns, identifies — `co`'s `PRODUCT_REVIEWS`
and `STORE_ORDERS` — cannot be an `@Entity`, so it is mapped by a plain class
(`ProductReview`, `StoreOrder`, in test, over `MappedProductReview` / `MappedStoreOrder`, whose
constructors are both `protected`). Each concrete one has exactly two constructors:

- a `protected` no-arg constructor, and
- a `public` all-args constructor, taking every attribute in field order.

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
exercised as the persistence provider — every `*_PersistenceTest` and `*_PersistenceIT` runs
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

There is no shared test module, and no CDI. Each module has, in its own test package, its own test
bases and the helpers `__PersistenceTest_Utils` / `__PersistenceIT_Utils`, `___Persistence_TestUtils`
and `___Validation_TestUtils`. The bases are one root and four siblings, none of which
extends another, plus one interface holding the checks:

| base | tests | for |
| --- | --- | --- |
| `__Test<T>` | nothing itself | the root: the target class and its factories |
| `__Object_Test<T>` (interface) | `toString`, `equals`/`hashCode`, accessors, as `@Test` default methods | mixed into the two bases below |
| `_NonEntity_Test<T>` | `toString`, `equals`/`hashCode`, accessors | a class with no identity: an id class, an embeddable, a mapped JSON document |
| `_Entity_Test<T>` | the same, and any check only such a class has | a class mapping a table or a view, keyless views included |
| `_Entity_PersistenceTest<T>` | the mapping, against the `<schema>-test` unit | an entity |
| `_Entity_PersistenceIT<T>` | the mapping, against the `<schema>-it` unit | an entity |

A class gets one of `_NonEntity_Test` / `_Entity_Test`, and an entity also gets the last two.

The checks live in `__Object_Test` as `@Test` default methods -- JUnit Jupiter discovers tests on
an interface's default methods -- and `_NonEntity_Test` and `_Entity_Test` implement it;
the persistence bases, which extend the same root, do not, and so do not run them again. Do not
move a `@Test` up into `__Test`, and do not add a base between the root and a sibling: a level
earns its place only with a check of its own.

Interface methods are `public`, so a subclass overriding one -- `equals_verifier_()` to suppress
a warning, `equals_verify_()` to skip a class with no value equality -- declares it `public`. An
override of a test method keeps its `@Test`; without it the override silently drops the test.

As in the sibling `sqlserver-samples-persistence`, each unit has a helper in place of a CDI producer:
`__PersistenceTest_Utils` for `<schema>-test` and `__PersistenceIT_Utils` for `<schema>-it`. Each holds
the unit name as its `PERSISTENCE_UNIT_NAME` constant and an `applyEntityManagerFactory(Function)` which
takes the factory from `JinahyaPersistenceTestUtils.applyCachedEntityManagerFactory`: opened once for the
whole run, closed when the virtual machine shuts down. The H2 schema of a `<schema>-test` unit is therefore
generated once per run, not once per test class, so a row a test commits is seen by the tests after it.

The persistence bases hold no entity manager, and no wrapper of their own. A test, in a base or in a
subclass, takes the factory from its unit's helper and goes through the jinahya library directly, one
entity manager per call:

```java
applyEntityManagerFactory(emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
    final var persisted = newPersistedInstanceOf(em, targetClass);
    ...
})));
```

That is `JinahyaEntityManagerFactoryUtils.applyEntityManager`, then
`JinahyaEntityManagerUtils.applyInTransactionAndRollback` when the work needs a transaction, and
`EntityPersisterUtils.newPersistedInstanceOf` for a persisted, randomized instance. A test which only reads
uses `applyEntityManager` alone, with the whole body inside the lambda, so the entity manager is still open
for the assertions. Each base runs `persistRandomizedEntityInstance__` and `selectRandomEntityInstance__`,
and hands the instance to the hooks `randomizedEntityInstancePersisted__` and
`randomEntityInstanceSelected__(EntityManager, T)`, which a subclass overrides to assert what its entity
promises; a view's test overrides `persistRandomizedEntityInstance__` with a `@Disabled` one. A test class
outside the bases (`sh`'s `*Utils_PersistenceTest` / `*Utils_PersistenceIT`) opens one entity manager per
test instance, and closes it in an `@AfterEach`.

`___Persistence_TestUtils` holds only what the library has no single call for:
`selectRandomEntityInstance(EntityManagerFactory, Class, BiFunction)`, a random selection inside a
rolled-back transaction. That needs `version.jinahya-persistence` 0.6.11 or later, and each module declares
`jinahya-persistence-more`, `-utils` and `-test-utils` itself, at `test` scope. `___Validation_TestUtils`
holds a validator factory, built once per run, and `validate` / `assertValid`; Hibernate Validator and
Expressly are each module's own `test` dependencies, not the root's.

The copies are kept in step by hand: a fix to one module's test base is carried to the other two,
unless it is specific to that module.

Every persistence unit carries `<exclude-unlisted-classes>true</exclude-unlisted-classes>`,
and it stays: the unit lists exactly the classes it means, and the flag keeps anything else out.

Every composite identifier is mapped once, in one style. The database declares two composite
primary keys, one per module, and each module shows one style on it: `co`'s `ORDER_ITEMS` is
`OrderItem` with an `@EmbeddedId` (`OrderItemId`, plus an `@MapsId` to `Order`), and `hr`'s
`JOB_HISTORY` is `JobHistory` with an `@IdClass` (`JobHistoryId`). Do not add the other style
alongside -- a class name carries no `WithEmbeddedId` / `WithIdClass` postfix because there is only
ever one. `co`'s `ProductOrder` (over a view) also uses an `@EmbeddedId`. `sh` declares no composite
key; the identifiers it chooses for its unkeyed tables and views use both styles, one of each per
kind of object -- of the tables, `Sales` uses an `@EmbeddedId` and `Cost` an `@IdClass`; of the
views, `FweekPscatSalesMv` uses an `@EmbeddedId` and `Profit` an `@IdClass`.

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
  (Hibernate Validator + Expressly for Jakarta Validation), pinned to
  its EE 11 aligned release in `<properties>`; there is nothing to choose, so it gets
  no profile. Neither provider profile picks a *version* either — both versions live
  in `<properties>`; a profile decides only which provider is on the test classpath
  and which metamodel generator the annotation processor path uses.
- Do not add a profile to test a second version of anything. Varying Hibernate
  Validator or Expressly tests those projects, not this one. Any profile that
  is added must carry the `jakarta-ee-11-` prefix and actually be certified for that
  generation.

Consequences:

- Never pin a `jakarta.*` API artifact (`jakarta.persistence-api`,
  `jakarta.validation-api`, `jakarta.el-api`, …) to an
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
| unit tests | `_mvn_jakarta_ee_11_test_hibernate_orm.sh` | `_mvn_jakarta_ee_11_test_eclipselink.sh` |
| unit tests and ITs | `_mvn_jakarta_ee_11_verify_hibernate_orm.sh` | `_mvn_jakarta_ee_11_verify_eclipselink.sh` |
| no test, but still compiles them | `_mvn_jakarta_ee_11_verify_notests_hibernate_orm.sh` | `_mvn_jakarta_ee_11_verify_notests_eclipselink.sh` |

The ITs want a live Oracle at `localhost:1521/freepdb1`; `./compose.sh` brings
one up. The `_verify_notests_` pair is the one to reach for when there is no database.

Which half of a `verify` run executes is chosen by two separate flags, because **as of
maven-failsafe-plugin 3.6.0 failsafe no longer binds its own `skipTests` parameter to the
`skipTests` user property**. So `-DskipTests` now reaches surefire alone — passing it to a
`_verify_` script leaves the ITs running — and `-DskipITs` is what drops the ITs. Under
3.5.x and earlier `-DskipTests` reached both, so this behaviour is tied to
`version.maven-failsafe`.
