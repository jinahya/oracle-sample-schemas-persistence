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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import jakarta.persistence.EntityManager;
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

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void __persistEntityInstance(final EntityManager entityManager, final Employee persisted) {
        super.__persistEntityInstance(entityManager, persisted);
    }

    @Override
    protected void __persistEntityInstance(final Employee persisted) {
        super.__persistEntityInstance(persisted);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("""
            Select\
            One_\
            Where\
            EmailEqual_""")
    @Nested
    class SelectOne_WhereEmailEqual__Test {

        @Test
        void NamedQuery__() {
            // TODO: implement!
        }

        @Test
        void QueryLanguage__() {

        }

        @Test
        void CriteriaApi__() {
            // TODO: implement!
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("""
            SelectList_\
            Where\
            JobEqual_\
            OrderBy\
            HireDateAsc"""
    )
    @Nested
    class SelectList_WhereJobEqual_OrderByHireDateAsc_Test {

        @Test
        void NamedQuery__() {
            // --------------------------------------------------------------------------------------------------- given
            final var job = Job.builder().jobId("jobId").build();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery(
                        """
                                Employee.\
                                SelectList_\
                                Where\
                                JobEqual_\
                                OrderBy\
                                HireDateAsc""",
                        Employee.class
                );
                query.setParameter("job", job);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotNull()
                    .isSortedAccordingTo(MappedEmployee.COMPARING_HIRE_DATE);
        }

        @Test
        void QueryLanguage__() {

        }

        @Test
        void CriteriaApi__() {
            // --------------------------------------------------------------------------------------------------- given
            final var job = Job.builder().jobId("jobId").build();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Employee.class);
                final var root = query.from(Employee.class);              // FROM Employee e
                query.select(root);                                       // SELECT e
                query.where(builder.equal(root.get(Employee_.job), job)); // WHERE e.job = :job
                query.orderBy(builder.asc(root.get(Employee_.hireDate))); // ORDER BY e.hireDate ASC
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .isNotNull()
                    .isSortedAccordingTo(MappedEmployee.COMPARING_HIRE_DATE);
        }
    }

    @DisplayName("selectList_WhereDepartmentEqual_OrderByLastNameAscFirstNameAscNullsFirst")
    @Nested
    class SelectList_WhereDepartmentEqual_OrderByLastNameAscFirstNameAscNullsFirst_Test {

        @Test
        void NamedQuery__() {
            // --------------------------------------------------------------------------------------------------- given
            final var department = Department.builder().departmentId(0).build();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var query = em.createNamedQuery(
                        "Employee.SelectList_WhereDepartmentEqual_OrderByLastNameAscFirstNameAscNullsFirst",
                        Employee.class
                );
                query.setParameter("department", department);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result).isNotNull()
                    .isSortedAccordingTo(
                            MappedEmployee.COMPARING_LAST_NAME
                                    .thenComparing(MappedEmployee.COMPARING_FIRST_NAME_NULLS_FIRST)
                    );
        }

        @Test
        void QueryLanguage__() {

        }

        @Test
        void CriteriaApi__() {
            // --------------------------------------------------------------------------------------------------- given
            final var department = Department.builder().departmentId(0).build();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Employee.class);
                final var root = query.from(Employee.class);
                query.select(root);
                query.where(builder.equal(root.get(Employee_.department), department));
                query.orderBy(
                        builder.asc(root.get(Employee_.lastName)),
                        builder.asc(builder.coalesce(root.get(Employee_.firstName), ""))
                );
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result).isNotNull()
                    .isSortedAccordingTo(
                            MappedEmployee.COMPARING_LAST_NAME
                                    .thenComparing(MappedEmployee.COMPARING_FIRST_NAME_NULLS_FIRST)
                    );
        }
    }
}
