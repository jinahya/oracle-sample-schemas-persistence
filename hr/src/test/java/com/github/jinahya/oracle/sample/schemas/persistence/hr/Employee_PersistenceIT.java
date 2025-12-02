package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Employee_PersistenceIT extends _MappedHrEntity_PersistenceIT<Employee, Integer> {

    private static final Function<
            EntityManager,
            ? extends Function<
                    ? super Integer,
                    ? extends List<JobHistoryWithEmbeddedId>
                    >
            >
            JOB_HISTORY_SELECTOR = em -> employeeId -> em.createQuery(
            """
                    SELECT e
                    FROM JobHistoryWithEmbeddedId e
                    WHERE e.id.employeeId = :employeeId
                    ORDER BY e.id.startDate ASC
                    """,
            JobHistoryWithEmbeddedId.class
    ).setParameter("employeeId", employeeId).getResultList();

    // -----------------------------------------------------------------------------------------------------------------
    Employee_PersistenceIT() {
        super(Employee.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Disabled("run manually")
    @DisplayName("Job updated -> JobHistory added")
    @Test
    void _JobHistoryAdded_JobUpdated() {
        applyEntityManagerInTransactionAndRollback(em -> {
            // --------------------------------------------------------------------------------------------------- given
            final var employee = ___JakartaPersistence_TestUtils.selectRandom(em, Employee.class).orElseThrow();
            final var jobHistories1 = JOB_HISTORY_SELECTOR.apply(em).apply(employee.getEmployeeId());
            // ---------------------------------------------------------------------------------------------------- when
            final Job newJob;
            while (true) {
                final var job = ___JakartaPersistence_TestUtils.selectRandom(em, Job.class).orElseThrow();
                if (!job.equals(employee.getJob())) {
                    newJob = job;
                    break;
                }
            }
            employee.setJob(newJob);
            em.flush();
            // ---------------------------------------------------------------------------------------------------- when
            final var jobHistories2 = JOB_HISTORY_SELECTOR.apply(em).apply(employee.getEmployeeId());
            assertThat(jobHistories2).hasSize(jobHistories1.size() + 1);
            return null;
        });
    }

    @Disabled("run manually")
    @DisplayName("Department updated -> JobHistory added")
    @Test
    void _JobHistoryAdded_DepartmentUpdated() {
        applyEntityManagerInTransactionAndRollback(em -> {
            // --------------------------------------------------------------------------------------------------- given
            final var employee = ___JakartaPersistence_TestUtils.selectRandom(em, Employee.class).orElseThrow();
            final var departmentHistories1 = JOB_HISTORY_SELECTOR.apply(em).apply(employee.getEmployeeId());
            // ---------------------------------------------------------------------------------------------------- when
            final Department newDepartment;
            while (true) {
                final var department = ___JakartaPersistence_TestUtils.selectRandom(em, Department.class).orElseThrow();
                if (!department.equals(employee.getDepartment())) {
                    newDepartment = department;
                    break;
                }
            }
            employee.setDepartment(newDepartment);
            em.flush();
            // ---------------------------------------------------------------------------------------------------- when
            final var departmentHistories2 = JOB_HISTORY_SELECTOR.apply(em).apply(employee.getEmployeeId());
            assertThat(departmentHistories2).hasSize(departmentHistories1.size() + 1);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectManagersAndNumberOfSubordinates__OrderByNumberOfSubordinatesAsc")
    @Nested
    class SelectManagersAndNumberOfSubordinates__OrderByNumberOfSubordinatesAsc_Test {

        @Test
        void NamedQuery__() {
            // --------------------------------------------------------------------------------------------------- given
            final var firstResult = 0;
            final var maxResults = 16;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery(
                        "Employee.selectManagersAndNumberOfSubordinates__OrderByNumberOfSubordinatesAsc",
//                        Tuple.class // https://github.com/eclipse-ee4j/eclipselink/issues/778
                        Object[].class
                );
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return query.getResultList();
            });
            result.forEach(t -> {
                final var manager = (Employee) t[0];
                final var numberOfSubordinates = (Integer) t[1];
                log.debug("number of subordinates: {}, manager: {}", numberOfSubordinates, manager);
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotNull()
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .extracting(t -> (Integer) t[1])
                    .isSortedAccordingTo(Comparator.naturalOrder());
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            // --------------------------------------------------------------------------------------------------- given
            final var firstResult = 0;
            final var maxResults = 16;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(Employee.class);
                final var subordinates = root.get(Employee_.subordinates);
                final var numberOfSubordinates = builder.size(subordinates);
//                final var numberOfSubordinates = builder.count(subordinates);
                query.select(builder.array(
                        root,
                        numberOfSubordinates
                ));
                query.orderBy(
                        builder.asc(
                                numberOfSubordinates
                        )
                );
                return em.createQuery(query)
                        .setFirstResult(firstResult)
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            result.forEach(t -> {
                final var manager = (Employee) t[0];
                final var numberOfSubordinates = (Integer) t[1];
                log.debug("number of subordinates: {}, manager: {}", numberOfSubordinates, manager);
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotNull()
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .extracting(t -> (Integer) t[1])
                    .isSortedAccordingTo(Comparator.naturalOrder());
        }
    }
}
