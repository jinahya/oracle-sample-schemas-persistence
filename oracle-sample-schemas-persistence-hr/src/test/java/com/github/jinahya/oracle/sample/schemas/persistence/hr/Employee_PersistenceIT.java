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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Employee} against the installed {@code HR} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Employee_PersistenceIT extends _Persistence_IT<Employee> {

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
         * @implNote The trigger also fires on an {@code UPDATE} which only sets {@code JOB_ID} to the value it already
         * has. Hibernate ORM, which updates every column by default, does that when the hire date alone is flushed, so
         * there may be a second row: it, too, records the old job and department.
         */
        @Test
        void _NewJobHistoryPopulated_JobChanged() {
            applyNewPersistedTargetInstanceAndRollback((em, e) -> {
                // well in the past, so the trigger's END_DATE (SYSDATE) is after its START_DATE, as
                // JHIST_DATE_INTERVAL requires; the randomized hire date may be today, or later
                e.setHireDate(LocalDateTime.now().minusYears(1L));
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
                // well in the past, so the trigger's END_DATE (SYSDATE) is after its START_DATE, as
                // JHIST_DATE_INTERVAL requires; the randomized hire date may be today, or later
                e.setHireDate(LocalDateTime.now().minusYears(1L));
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
                // well in the past, so the trigger's END_DATE (SYSDATE) is after its START_DATE, as
                // JHIST_DATE_INTERVAL requires; the randomized hire date may be today, or later
                e.setHireDate(LocalDateTime.now().minusYears(1L));
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
}
