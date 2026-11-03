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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Customer} against the installed {@code SH} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Customer_PersistenceIT extends _Entity_PersistenceIT<Customer> {

    Customer_PersistenceIT() {
        super(Customer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@code Customer.selectListPromotedByCustCityOrderByCustLastNameAsc}, which answers exercise
     * {@code SH_SCHEMA_I_10}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectListPromotedByCustCityOrderByCustLastNameAsc_Test {

        /**
         * Returns some cities of the installed customers who bought under a promotion.
         */
        Stream<String> custCities() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT DISTINCT c.custCity
                                    FROM Sales s
                                             JOIN s.customer c
                                    WHERE s.promotion.promoCategory <> 'NO PROMOTION'
                                    ORDER BY c.custCity ASC""",
                            String.class
                    )
                    .setMaxResults(3)
                    .getResultList()
                    .stream();
        }

        private void verify(final String custCity, final List<Customer> found) {
            final var count = entityManager()
                    .createQuery(
                            """
                                    SELECT COUNT(DISTINCT c)
                                    FROM Sales s
                                             JOIN s.customer c
                                    WHERE c.custCity = :custCity
                                      AND s.promotion.promoCategory <> 'NO PROMOTION'""",
                            Long.class
                    )
                    .setParameter("custCity", custCity)
                    .getSingleResult();
            assertThat(found)
                    .as("the customers of %s who bought under a promotion", custCity)
                    .isNotEmpty()
                    .hasSize(count.intValue())
                    .allSatisfy(c -> assertThat(c.getCustCity()).isEqualTo(custCity))
                    .isSortedAccordingTo(Comparator.comparing(Customer::getCustLastName));
        }

        @DisplayName("the named query selects the city's customers who bought under a promotion")
        @MethodSource("custCities")
        @ParameterizedTest
        void __NamedQuery(final String custCity) {
            final var found = entityManager()
                    .createNamedQuery("Customer.selectListPromotedByCustCityOrderByCustLastNameAsc", targetClass)
                    .setParameter("custCity", custCity)
                    .getResultList();
            verify(custCity, found);
        }

        @DisplayName("a query-language query selects the city's customers who bought under a promotion")
        @MethodSource("custCities")
        @ParameterizedTest
        void __QueryLanguage(final String custCity) {
            final var found = entityManager()
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
                    .setParameter("custCity", custCity)
                    .getResultList();
            verify(custCity, found);
        }

        @DisplayName("a criteria query selects the city's customers who bought under a promotion")
        @MethodSource("custCities")
        @ParameterizedTest
        void __CriteriaApi(final String custCity) {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            final var subquery = query.subquery(Sales.class);
            final var sales = subquery.from(Sales.class);
            subquery.select(sales).where(
                    builder.equal(sales.get(Sales_.customer), root),
                    builder.notEqual(sales.get(Sales_.promotion).get(Promotion_.promoCategory), "NO PROMOTION")
            );
            query.select(root);
            query.where(
                    builder.equal(root.get(Customer_.custCity), custCity),
                    builder.exists(subquery)
            );
            query.orderBy(
                    builder.asc(root.get(Customer_.custLastName)),
                    builder.asc(root.get(Customer_.custFirstName)),
                    builder.asc(root.get(Customer_.custId))
            );
            final var found = entityManager.createQuery(query).getResultList();
            verify(custCity, found);
        }
    }
}
