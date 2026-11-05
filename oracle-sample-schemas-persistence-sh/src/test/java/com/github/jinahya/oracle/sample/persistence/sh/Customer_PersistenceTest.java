package com.github.jinahya.oracle.sample.persistence.sh;

/*-
 * #%L
 * sh
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

import static com.github.jinahya.oracle.sample.persistence.sh.__PersistenceTest_Utils.applyEntityManagerFactory;
import static com.github.jinahya.persistence.test.util.EntityPersisterUtils.newPersistedInstanceOf;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Customer} against the schema generated into the in-memory
 * database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Customer_PersistenceTest
        extends _Entity_PersistenceTest<Customer, Long> {

    Customer_PersistenceTest() {
        super(Customer.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Tests {@code Customer.selectListPromotedByCustCityOrderByCustLastNameAsc}, which answers
     * exercise {@code SH_SCHEMA_I_10}.
     * <p>
     * Each test persists a new sale, which brings a customer and a promotion of its own; the
     * promotion's randomized category is not {@code NO PROMOTION}, so the customer bought under a
     * promotion.
     */
    @Nested
    class SelectListPromotedByCustCityOrderByCustLastNameAsc_Test {

        /**
         * Persists a new sale, and returns its customer.
         */
        private Customer promotedCustomer(final EntityManager em) {
            final var sales = EntityPersisterUtils.newPersistedInstanceOf(em, Sales.class);
            final var promotion = em.find(Promotion.class, sales.getId_().getPromoId());
            assertThat(promotion.getPromoCategory()).isNotEqualTo("NO PROMOTION");
            return em.find(Customer.class, sales.getId_().getCustId());
        }

        @DisplayName(
                "a query-language query selects the customer of the sale just persisted, by their" +
                " city")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var customer = promotedCustomer(em);
                        final var found = em
                                .createQuery(
                                        """
                                                SELECT c
                                                FROM Customer c
                                                WHERE c.custCity = :custCity
                                                  AND EXISTS (SELECT s
                                                              FROM Sales s
                                                              WHERE s.customer = c
                                                                AND s.promotion.promoCategory <> 'NO PROMOTION')
                                                ORDER BY c.custLastName ASC, c.custFirstName ASC, c.custId ASC""",
                                        targetClass
                                )
                                .setParameter("custCity", customer.getCustCity())
                                .getResultList();
                        assertThat(found)
                                .as("the customers of the city who bought under a promotion")
                                .containsExactly(customer);
                        return found;
                    })));
        }

        @DisplayName(
                "a criteria query selects the customer of the sale just persisted, by their city")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(
                    emf -> applyEntityManager(emf, m -> applyInTransactionAndRollback(m, em -> {
                        final var customer = promotedCustomer(em);
                        final var builder = em.getCriteriaBuilder();
                        final var query = builder.createQuery(targetClass);
                        final var root = query.from(targetClass);
                        final var subquery = query.subquery(Sales.class);
                        final var sales = subquery.from(Sales.class);
                        subquery.select(sales).where(
                                builder.equal(sales.get(Sales_.customer), root),
                                builder.notEqual(
                                        sales.get(Sales_.promotion).get(Promotion_.promoCategory),
                                        "NO PROMOTION")
                        );
                        query.select(root);
                        query.where(
                                builder.equal(root.get(Customer_.custCity), customer.getCustCity()),
                                builder.exists(subquery)
                        );
                        query.orderBy(
                                builder.asc(root.get(Customer_.custLastName)),
                                builder.asc(root.get(Customer_.custFirstName)),
                                builder.asc(root.get(Customer_.custId))
                        );
                        final var found = em.createQuery(query).getResultList();
                        assertThat(found)
                                .as("the customers of the city who bought under a promotion")
                                .containsExactly(customer);
                        return found;
                    })));
        }

    }
}
