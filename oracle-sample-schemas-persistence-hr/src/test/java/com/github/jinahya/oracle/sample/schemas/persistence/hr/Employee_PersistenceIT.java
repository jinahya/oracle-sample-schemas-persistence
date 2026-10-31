package com.github.jinahya.oracle.sample.schemas.persistence.hr;


/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Employee} against the installed {@code HR} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Employee_PersistenceIT extends _Entity_Persistence_IT<Employee> {

    Employee_PersistenceIT() {
        super(Employee.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class JobHistory_Test {

        private List<JobHistory> selectJobHistories(final EntityManager em, final Employee employee) {
            return em.createQuery(
                            "SELECT h FROM " + JobHistory.ENTITY_NAME + " h"
                            + " WHERE h." + JobHistory.ATTRIBUTE_NAME_EMPLOYEE_ID + " = :employeeId",
                            JobHistory.class)
                    .setParameter("employeeId", employee.getEmployeeId())
                    .getResultList();
        }

        /**
         * Changes the job of a new employee, and checks that the {@code UPDATE_JOB_HISTORY} trigger inserted a
         * {@code JOB_HISTORY} row for them.
         * <p>
         * The trigger fires on the flushed {@code UPDATE} of {@code JOB_ID}, within this transaction, so its row is
         * visible to a query in it until the rollback. Nothing else writes {@code JOB_HISTORY} here, so any row for the
         * employee is the trigger's.
         *
         * @implNote The hire date is left as {@link Employee_Randomizer} set it, a year in the past, rather than set
         * here: changing it would be an {@code UPDATE} of its own, and Hibernate ORM, which writes every column by
         * default, would include {@code JOB_ID} in it and fire the trigger with the persisted hire date.
         */
        @Test
        void _NewJobHistoryPopulated_JobChanged() {
            applyNewPersistedTargetInstanceAndRollback((em, e) -> {
                // the INSERT, on its own; otherwise EclipseLink writes the new value into it, and no UPDATE fires
                em.flush();
                final var oldJob = e.getJob();
                final var oldDepartment = e.getDepartment();
                e.setJob(EntityPersisterUtils.newPersistedInstanceOf(em, Job.class));
                em.flush();
                assertThat(selectJobHistories(em, e))
                        .isNotEmpty()
                        // every row records the job and the department the employee had before the change
                        .allSatisfy(h -> {
                            assertThat(h.getJob()).isEqualTo(oldJob);
                            assertThat(h.getDepartment()).isEqualTo(oldDepartment);
                        });
                return null;
            });
        }

        /**
         * Changes the department of a new employee, and checks that the {@code UPDATE_JOB_HISTORY} trigger inserted a
         * {@code JOB_HISTORY} row for them, recording the job and the department they had before the change.
         */
        @Test
        void _NewJobHistoryPopulated_DepartmentChanged() {
            applyNewPersistedTargetInstanceAndRollback((em, e) -> {
                // the INSERT, on its own; otherwise EclipseLink writes the new value into it, and no UPDATE fires
                em.flush();
                final var oldJob = e.getJob();
                final var oldDepartment = e.getDepartment();
                e.setDepartment(EntityPersisterUtils.newPersistedInstanceOf(em, Department.class));
                em.flush();
                assertThat(selectJobHistories(em, e))
                        .isNotEmpty()
                        // every row records the job and the department the employee had before the change
                        .allSatisfy(h -> {
                            assertThat(h.getJob()).isEqualTo(oldJob);
                            assertThat(h.getDepartment()).isEqualTo(oldDepartment);
                        });
                return null;
            });
        }

        /**
         * Changes both the job and the department of a new employee, in one flush, and checks that the
         * {@code UPDATE_JOB_HISTORY} trigger inserted a {@code JOB_HISTORY} row for them, recording the job and the
         * department they had before the change.
         *
         * @implNote Both go in one flush, so one {@code UPDATE}, on which the row trigger fires once. Flushed apart,
         * the trigger would fire twice with the same {@code START_DATE} -- the unchanged {@code HIRE_DATE} -- and the
         * second insert would violate {@code JHIST_EMP_ID_ST_DATE_PK}.
         */
        @Test
        void _NewJobHistoryPopulated_BothChanged() {
            applyNewPersistedTargetInstanceAndRollback((em, e) -> {
                // the INSERT, on its own; otherwise EclipseLink writes the new value into it, and no UPDATE fires
                em.flush();
                final var oldJob = e.getJob();
                final var oldDepartment = e.getDepartment();
                e.setJob(EntityPersisterUtils.newPersistedInstanceOf(em, Job.class));
                e.setDepartment(EntityPersisterUtils.newPersistedInstanceOf(em, Department.class));
                em.flush();
                assertThat(selectJobHistories(em, e))
                        .isNotEmpty()
                        // every row records the job and the department the employee had before the change
                        .allSatisfy(h -> {
                            assertThat(h.getJob()).isEqualTo(oldJob);
                            assertThat(h.getDepartment()).isEqualTo(oldDepartment);
                        });
                return null;
            });
        }
    }

    /**
     * Tests {@code Employee.selectListByManagerOfManagerOrderByLastNameAsc}, which answers exercise
     * {@code HR_EMPLOYEES_I_06}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectListByManagerOfManagerOrderByLastNameAsc_Test {

        /**
         * Returns the ids of the installed employees who have someone two levels below them.
         */
        Stream<Integer> managerIds() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT DISTINCT g.employeeId
                                    FROM Employee e
                                             JOIN e.manager m
                                             JOIN m.manager g
                                    ORDER BY g.employeeId ASC""",
                            Integer.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }

        private void verify(final Integer managerId, final List<Employee> found) {
            final var count = entityManager()
                    .createQuery(
                            """
                                    SELECT COUNT(e)
                                    FROM Employee e
                                             JOIN e.manager m
                                    WHERE m.manager.employeeId = :managerId""",
                            Long.class
                    )
                    .setParameter("managerId", managerId)
                    .getSingleResult();
            assertThat(found)
                    .as("the employees two levels below %s", managerId)
                    .isNotEmpty()
                    .hasSize(count.intValue())
                    .allSatisfy(e -> assertThat(e.getManager().getManager().getEmployeeId()).isEqualTo(managerId))
                    .isSortedAccordingTo(Comparator.comparing(Employee::getLastName));
        }

        @DisplayName("the named query selects the employees two levels below the manager, by name")
        @MethodSource("managerIds")
        @ParameterizedTest
        void __NamedQuery(final Integer managerId) {
            final var found = entityManager()
                    .createNamedQuery("Employee.selectListByManagerOfManagerOrderByLastNameAsc", targetClass)
                    .setParameter("manager", entityManager().getReference(Employee.class, managerId))
                    .getResultList();
            verify(managerId, found);
        }

        @DisplayName("a query-language query selects the employees two levels below the manager, by name")
        @MethodSource("managerIds")
        @ParameterizedTest
        void __QueryLanguage(final Integer managerId) {
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Employee e
                                    WHERE e.manager.manager = :manager
                                    ORDER BY e.lastName ASC, e.firstName ASC""",
                            targetClass
                    )
                    .setParameter("manager", entityManager().getReference(Employee.class, managerId))
                    .getResultList();
            verify(managerId, found);
        }

        @DisplayName("a criteria query selects the employees two levels below the manager, by name")
        @MethodSource("managerIds")
        @ParameterizedTest
        void __CriteriaApi(final Integer managerId) {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.equal(root.get(Employee_.manager).get(Employee_.manager),
                                      entityManager.getReference(Employee.class, managerId)));
            query.orderBy(builder.asc(root.get(Employee_.lastName)), builder.asc(root.get(Employee_.firstName)));
            final var found = entityManager.createQuery(query).getResultList();
            verify(managerId, found);
        }
    }
}
