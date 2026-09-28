# CLAUDE.md

Guidance for Claude Code when working in this repository.

## What this project is

Jakarta Persistence mappings for the
[Oracle Database Sample Schemas](https://github.com/oracle-samples/db-sample-schemas),
one Maven module per schema (`co`, `hr`, `sh`), plus `test-base` for the shared test
base classes.

**These are pure Jakarta Persistence modules.** Two rules follow from that:

- No dependency on `io.github.jinahya:jinahya-persistence-*`, or on any other
  persistence helper library. Everything the entities need comes from
  `jakarta.persistence`, `jakarta.validation` and `jakarta.annotation`.
- **Every entity is standalone.** There is no `mapped` package, no
  `@MappedSuperclass` hierarchy and no builder layer: one class holds its own
  column constants, fields, accessors, `toString`, and `equals`/`hashCode`. Do not
  reintroduce a shared entity superclass to remove duplication between entities —
  the duplication is the point, because it keeps each mapping readable on its own.

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

### The static metamodel, and `ATTRIBUTE_NAME_*`

The **generated static metamodel** (`Customer_`, `Employee_`, …) is available under both
providers, and is what persistence-context code uses: a criteria path is
`root.get(Customer_.emailAddress)`, not a string.

That works because each module's `persistence.xml` lives in **`src/main/resources/META-INF`**,
not in test resources. EclipseLink's metamodel processor reads a `persistence.xml` at
annotation-processing time and generates nothing without one, so moving it is what makes
the metamodel exist under the EclipseLink profile at all. Do not move it back.

Each module names its persistence units after itself -- `__co_testPU` / `__co_itPU`, and so on --
because a unit name is only required to be unique within the archive that declares it, while every
module of this build lands on one classpath whenever they are built or run together (an IDE running
the whole project, or a reader depending on two modules at once). Two units sharing a name there is
unspecified: the provider takes whichever descriptor it finds first, silently, and every test of the
other modules then aborts with "not a managed type". Hibernate says so as `HHH008518`.

That name lives in a per-module producer -- `_Persistence_Test_Producer` and its three siblings --
which `_Persistence_Test` names in its `@AddBeanClasses`, and which every `*_Persistence_Test` of
the module extends. The shared `__Persistence_Test_Producer` is abstract and supplies everything but
the name. Note that its four `@Produces`/`@Disposes` methods **are overridden in each module
producer**: CDI does not inherit producer or disposer methods, and an inherited `@Produces` is simply
not seen -- the injection point fails with `WELD-001408`.

Every persistence unit carries `<exclude-unlisted-classes>true</exclude-unlisted-classes>`,
and that is load-bearing for the same reason: EclipseLink's processor otherwise attaches
every `@Entity` in the compilation to the unit, including the ones a module deliberately
leaves out — `hr` maps `JobHistory` twice (`JobHistoryWithEmbeddedId`, `JobHistoryWithIdClass`)
under one entity name and lists one of them, and without the flag the `hr` compile fails with
`EclipseLink-7237`, entity name not unique.

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
  `./_mvn_jakarta_ee_11.sh -q enforcer:enforce -Drules=dependencyConvergence`.
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

`maven.compiler.release` is 21 for main sources and 25 for test sources, so the
published jars stay consumable on 21 while the tests use current language features.
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

`./_mvn_jakarta_ee_11.sh test` runs the build against both providers. `clean` is
prepended and is not optional: the annotation processor writes a metamodel per
provider, and without a clean in between the second provider compiles against the
first's leftovers.
