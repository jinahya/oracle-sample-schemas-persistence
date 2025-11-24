package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Employee_PersistenceTest extends _MappedHrEntity_PersistenceTest<Employee, Integer> {

    Employee_PersistenceTest() {
        super(Employee.class, Integer.class);
    }

    @DisplayName("SelectListByJobId")
    @Nested
    class SelectListByJobId_Test {

        @Nested
        class NamedQuery_Test {

            @DisplayName("(persisted.)[persisted]")
            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    // ------------------------------------------------------------------------------------------- given
                    final var persisted = __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    final var jobId = persisted.getJobId();
                    // -------------------------------------------------------------------------------------------- when
                    final var query = em.createNamedQuery("Employee.selectListWhereJobIdEqualTo", Employee.class);
                    query.setParameter("jobId", jobId);
                    final var result = query.getResultList();
                    // -------------------------------------------------------------------------------------------- then
                    assertThat(result).contains(persisted);
                    return null;
                });
            }
        }

        @Nested
        class QueryLanguage_Test {

            @DisplayName("(persisted.)[persisted]")
            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    // ------------------------------------------------------------------------------------------- given
                    final var persisted = __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    final var jobId = persisted.getJobId();
                    // -------------------------------------------------------------------------------------------- when
                    final var query = em.createQuery(
                            """
                                    SELECT e
                                    FROM Employee e
                                    WHERE e.jobId = :jobId
                                    """,
                            Employee.class
                    );
                    query.setParameter("jobId", jobId);
                    final var result = query.getResultList();
                    // -------------------------------------------------------------------------------------------- then
                    assertThat(result).contains(persisted);
                    return null;
                });
            }
        }

        @Nested
        class CriteriaApi_Test {

            @DisplayName("(persisted.)[persisted]")
            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    // ------------------------------------------------------------------------------------------- given
                    final var persisted = __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    final var jobId = persisted.getJobId();
                    // -------------------------------------------------------------------------------------------- when
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(Employee.class);
                    final var root = query.from(Employee.class);                  // FROM Employee e
                    query.select(root);                                           // SELECT e
                    query.where(builder.equal(root.get(Employee_.jobId), jobId)); // WHERE e.jobId = :jobId
                    final var result = em.createQuery(query).getResultList();
                    // -------------------------------------------------------------------------------------------- then
                    assertThat(result).contains(persisted);
                    return null;
                });
            }
        }
    }
}
