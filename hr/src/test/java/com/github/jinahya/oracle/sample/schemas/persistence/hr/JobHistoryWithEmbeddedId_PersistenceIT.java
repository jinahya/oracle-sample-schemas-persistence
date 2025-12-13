package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryWithEmbeddedId_;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@org.junit.jupiter.api.Disabled
@__Disable_PersistEntityInstance_Test
@Slf4j
class JobHistoryWithEmbeddedId_PersistenceIT
        extends MappedJobHistory_PersistenceIT<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_PersistenceIT() {
        super(JobHistoryWithEmbeddedId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void selectRandomEntityInstance(@Nonnull final JobHistoryWithEmbeddedId entityInstance) {
        super.selectRandomEntityInstance(entityInstance);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDate")
    @Nested
    class SelectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDate_Test {

        @Test
        void NamedQuery__() {
            // --------------------------------------------------------------------------------------------------- given
            final var employeeId = 101;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery(
                        "JobHistory.selectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDate",
                        JobHistoryWithEmbeddedId.class
                );
                query.setParameter("idEmployeeId", employeeId);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assumeThat(result).isNotEmpty();
            assertThat(result)
                    .allSatisfy(jh -> {
                        assertThat(jh.getId().getEmployeeId()).isEqualTo(employeeId);
                    })
                    .isSortedAccordingTo(JobHistoryWithEmbeddedId.COMPARING_ID_START_DATE);
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            // --------------------------------------------------------------------------------------------------- given
            final var employeeId = 101;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(JobHistoryWithEmbeddedId.class);
                final var root = query.from(JobHistoryWithEmbeddedId.class);
                query.select(root);
                query.where(builder.equal(
                        root.get(MappedJobHistoryWithEmbeddedId_.id).get(JobHistoryId_.employeeId),
                        employeeId
                ));
                query.orderBy(builder.asc(
                        root.get(JobHistoryWithEmbeddedId_.id).get(JobHistoryId_.startDate)
                ));
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assumeThat(result).isNotEmpty();
            assertThat(result)
                    .allSatisfy(jh -> {
                        assertThat(jh.getId().getEmployeeId()).isEqualTo(employeeId);
                    })
                    .isSortedAccordingTo(JobHistoryWithEmbeddedId.COMPARING_ID_START_DATE);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectList_WhereEmployeeEqualTo_OrderByStartDateDesc")
    @Nested
    class SelectList_WhereEmployeeEqualTo_OrderByStartDate_Test {

        @Test
        void NamedQuery__() {
            // --------------------------------------------------------------------------------------------------- given
            final var employee = Employee.builder().employeeId(101).build();
            applyEntityManagerInTransactionAndRollback(em -> {
                // ------------------------------------------------------------------------------------------------ when
                final var query = em.createNamedQuery(
                        "JobHistory.selectList_WhereEmployeeEqualTo_OrderByIdStartDate",
                        JobHistoryWithEmbeddedId.class
                );
                query.setParameter("employee", employee);
                final var result = query.getResultList();
                // ------------------------------------------------------------------------------------------------ then
                assumeThat(result).isNotEmpty();
                assertThat(result)
                        .allSatisfy(jh -> {
                            assertThat(jh.getId().getEmployeeId()).isEqualTo(employee.getEmployeeId());
                            assertThat(jh.getEmployee()).isEqualTo(employee);
                        })
                        .isSortedAccordingTo(JobHistoryWithEmbeddedId.COMPARING_ID_START_DATE);
                return null;
            });
        }

        @Test
        void QueryLanguage__() {
        }

        @Test
        void CriteriaApi__() {
            // --------------------------------------------------------------------------------------------------- given
            final var employee = Employee.builder().employeeId(101).build();
            applyEntityManagerInTransactionAndRollback(em -> {
                // ------------------------------------------------------------------------------------------------ when
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(JobHistoryWithEmbeddedId.class);
                final var root = query.from(JobHistoryWithEmbeddedId.class);
                query.select(root);
                query.where(builder.equal(
                        root.get(JobHistoryWithEmbeddedId_.employee),
                        employee
                ));
                query.orderBy(builder.asc(
                        root.get(JobHistoryWithEmbeddedId_.id).get(JobHistoryId_.startDate)
                ));
                final var result = em.createQuery(query).getResultList();
                // ------------------------------------------------------------------------------------------------ then
                assumeThat(result).isNotEmpty();
                assertThat(result)
                        .allSatisfy(jh -> {
                            assertThat(jh.getId().getEmployeeId()).isEqualTo(employee.getEmployeeId());
                            assertThat(jh.getEmployee()).isEqualTo(employee);
                        })
                        .isSortedAccordingTo(JobHistoryWithEmbeddedId.COMPARING_ID_START_DATE);
                return null;
            });
        }
    }
}
