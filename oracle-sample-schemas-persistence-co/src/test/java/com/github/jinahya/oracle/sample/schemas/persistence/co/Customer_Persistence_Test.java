package com.github.jinahya.oracle.sample.schemas.persistence.co;

import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*-
 * #%L
 * co
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

/**
 * Persists {@link Customer} instances against the in-memory database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Customer_Persistence_Test extends _DomainEntity_Persistence_Test<Customer, Long> {

    Customer_Persistence_Test() {
        super(Customer.class);
    }

    /**
     * A class for testing the selection of every {@link Customer}, ordered by its
     * {@value Customer#ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     * <p>
     * The same selection is written three ways -- as a query-language query, as the
     * {@code Customer.selectListOrderByCustomerIdAsc} named query, and through the criteria API -- and each asserts
     * that what comes back contains the instances just persisted, in ascending order of identifier.
     */
    @Nested
    class SelectListOrderByCustomerIdAsc_Test {

        @DisplayName("the named query selects the customers just persisted, ordered by identifier")
        @Test
        void __NamedQuery() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = List.of(
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass),
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass),
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass)
                );
                final var found = em
                        .createNamedQuery("Customer.selectListOrderByCustomerIdAsc", targetClass)
                        .getResultList();
                assertThat(found)
                        .as("the customers ordered by %s", Customer_.customerId.getName())
                        .containsAll(persisted)
                        .extracting(Customer::getCustomerId)
                        .isSorted();
                return found;
            });
        }

        @DisplayName("a query-language query selects the customers just persisted, ordered by identifier")
        @Test
        void __QueryLanguage() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = List.of(
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass),
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass),
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass)
                );
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Customer e
                                        ORDER BY e.customerId ASC""",
                                targetClass
                        )
                        .getResultList();
                assertThat(found)
                        .as("the customers ordered by %s", Customer_.customerId.getName())
                        .containsAll(persisted)
                        .extracting(Customer::getCustomerId)
                        .isSorted();
                return found;
            });
        }

        @DisplayName("a criteria query selects the customers just persisted, ordered by identifier")
        @Test
        void __CriteriaApi() {
            applyEntityManagerInTransactionAndRollback(em -> {
                final var persisted = List.of(
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass),
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass),
                        EntityPersisterUtils.newPersistedInstanceOf(em, targetClass)
                );
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Customer_.customerId)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("the customers ordered by %s", Customer_.customerId.getName())
                        .containsAll(persisted)
                        .extracting(Customer::getCustomerId)
                        .isSorted();
                return found;
            });
        }
    }

    /**
     * A class for testing the selection of a single {@link Customer} by its
     * {@value Customer#ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     * <p>
     * The same selection is written three ways -- as a query-language query, as the
     * {@code Customer.selectOneByEmailAddress} named query, and through the criteria API -- and each asserts that what
     * comes back is the very instance just persisted, which the persistence context still manages.
     */
    @Nested
    class SelectOneByEmailAddress_Test {

        @DisplayName("the named query selects the customer just persisted")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Customer.selectOneByEmailAddress", targetClass)
                        .setParameter("emailAddress", v.getEmailAddress())
                        .getSingleResult();
                assertThat(found)
                        .as("the customer selected by %s", v.getEmailAddress())
                        .isSameAs(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the customer just persisted")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Customer e
                                        WHERE e.emailAddress = :emailAddress""",
                                targetClass
                        )
                        .setParameter("emailAddress", v.getEmailAddress())
                        .getSingleResult();
                assertThat(found)
                        .as("the customer selected by %s", v.getEmailAddress())
                        .isSameAs(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the customer just persisted")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(
                        root.get(Customer_.emailAddress),
                        v.getEmailAddress()
                ));
                final var found = em.createQuery(query).getSingleResult();
                assertThat(found)
                        .as("the customer selected by %s", v.getEmailAddress())
                        .isSameAs(v);
                return found;
            });
        }
    }
}
