# oracle-sample-schemas-persistence

## dependencies alignments

|                                | jakarta.persistence-api | hibernate-core | querydsl-jpa |
|--------------------------------|-------------------------|----------------|--------------|
| [jakarta.jakartaee-bom:10.0.0] | `3.1.0`                 |                |              |
| [jakarta.jakartaee-bom:11.0.0] | `3.2.0`                 |                |              |
| [spring-boot 3.4]              | `3.1.0`                 | `6.6.15.Final` | `5.1.0`      |
| [spring-boot 3.5]              | `3.1.0`                 | `6.6.15.Final` | `5.1.0`      |

## profiles

|                         | eclipselink | hibernate | querydsl-5 | querydsl-6 | jakarta-ee | spring-boot |
|-------------------------|-------------|-----------|------------|------------|------------|-------------|
| persistence             | ✓           | ✓         |            |            |            |             |
| persistence-eclipselink | ✓           |           |            |            |            |             |
| persistence-hibernate   |             | ✓         |            |            |            |             |
| querydsl                |             |           | ✓          | ✓          |            |             |
| querydsl-5              |             |           | ✓          |            |            |             |
| querydsl-6              |             |           |            | ✓          |            |             |
| application             |             |           |            |            | ✓          | ✓           |
| application-jakarta-ee  |             |           |            |            | ✓          |             |
| application-spring-boot |             |           |            |            |            | ✓           |

`@MappedSuperclass`es and `@NoRepositoryBean`s interfaces for the SAMPLE database.

https://www.ibm.com/docs/en/iodgfdfz/11.7.0?topic=introduction-sample-database


[jakarta.jakartaee-bom:10.0.0]: https://central.sonatype.com/artifact/jakarta.platform/jakarta.jakartaee-bom/10.0.0

[jakarta.jakartaee-bom:11.0.0]: https://central.sonatype.com/artifact/jakarta.platform/jakarta.jakartaee-bom/11.0.0

[spring-boot 3.4]: https://docs.spring.io/spring-boot/3.4/appendix/dependency-versions/coordinates.html

[spring-boot 3.5]: https://docs.spring.io/spring-boot/3.5/appendix/dependency-versions/coordinates.html