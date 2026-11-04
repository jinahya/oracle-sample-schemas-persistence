package com.github.jinahya.oracle.sample.persistence.hr;


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

import static com.github.jinahya.persistence.test.util.EntityPersisterUtils.newPersistedInstanceOf;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerUtils.applyInTransactionAndRollback;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static com.github.jinahya.oracle.sample.persistence.hr.__PersistenceTest_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Employee} against the schema generated into the in-memory
 * database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Employee_PersistenceTest
        extends _Entity_PersistenceTest<Employee> {

    Employee_PersistenceTest() {
        super(Employee.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@code Employee.selectListByManagerOfManagerOrderByLastNameAsc}, which answers exercise
     * {@code HR_EMPLOYEES_I_06}.
     */
    @Nested
    class SelectListByManagerOfManagerOrderByLastNameAsc_Test {

        /**
         * Gives the specified employee a new manager, and that manager a new manager of their own,
         * and returns the latter.
         */
        private Employee managerOfManager(final EntityManager em, final Employee employee) {
            final var manager = EntityPersisterUtils.newPersistedInstanceOf(em, Employee.class);
            final var managerOfManager =
                    EntityPersisterUtils.newPersistedInstanceOf(em, Employee.class);
            employee.setManager(manager);
            manager.setManager(managerOfManager);
            return managerOfManager;
        }

        @DisplayName(
                "the named query selects the employee just persisted, by their manager's manager")
        @Test
        void __NamedQuery() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                final var v = newPersistedInstanceOf(em, targetClass);
                final var found = em
                        .createNamedQuery("Employee.selectListByManagerOfManagerOrderByLastNameAsc",
                                          targetClass)
                        .setParameter("manager", managerOfManager(em, v))
                        .getResultList();
                assertThat(found)
                        .as("the employees two levels below the manager")
                        .containsExactly(v);
                return found;
            })));
        }

        @DisplayName(
                "a query-language query selects the employee just persisted, by their manager's " +
                "manager")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                final var v = newPersistedInstanceOf(em, targetClass);
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Employee e
                                        WHERE e.manager.manager = :manager
                                        ORDER BY e.lastName ASC, e.firstName ASC""",
                                targetClass
                        )
                        .setParameter("manager", managerOfManager(em, v))
                        .getResultList();
                assertThat(found)
                        .as("the employees two levels below the manager")
                        .containsExactly(v);
                return found;
            })));
        }

        @DisplayName(
                "a criteria query selects the employee just persisted, by their manager's manager")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                final var v = newPersistedInstanceOf(em, targetClass);
                final var managerOfManager = managerOfManager(em, v);
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Employee_.manager).get(Employee_.manager),
                                          managerOfManager));
                query.orderBy(builder.asc(root.get(Employee_.lastName)),
                              builder.asc(root.get(Employee_.firstName)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("the employees two levels below the manager")
                        .containsExactly(v);
                return found;
            })));
        }
    }
}
