package com.github.jinahya.oracle.sample.schemas.persistence.co;


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

import com.github.jinahya.persistence.test.util.JinahyaPersistenceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Verifies the mappings of {@link Customer} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Customer_Persistence_IT extends _Persistence_IT<Customer> {

    // -----------------------------------------------------------------------------------------------------------------
    Customer_Persistence_IT() {
        super(Customer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Find_Test {

        /**
         * Returns some identifiers of the installed table.
         *
         * @implNote Selects the identifiers, not the entities, so nothing is left managed and every {@code find} really
         * does reach the database.
         */
        LongStream customerIds() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT e.customerId
                                    FROM Customer e
                                    ORDER BY e.customerId ASC""",
                            Long.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream()
                    .mapToLong(Long::longValue);
        }

        @MethodSource("customerIds")
        @ParameterizedTest
        void __(final long customerId) {
            final var found = entityManager().find(targetClass, customerId);
            assertThat(found).isNotNull().extracting(Customer::getCustomerId).isEqualTo(customerId);
        }
    }

    @Nested
    class SelectListOrderByCustomerIdAsc_Test {

        @DisplayName("the named query selects the installed customers, ordered by identifier")
        @Test
        void __NamedQuery() {
            final var found = entityManager()
                    .createNamedQuery("Customer.selectListOrderByCustomerIdAsc", targetClass)
                    .getResultList();
            assertThat(found)
                    .as("the customers ordered by %s", Customer_.customerId.getName())
                    .isNotEmpty()
                    .extracting(Customer::getCustomerId)
                    .isSorted();
        }

        @DisplayName("a query-language query selects the installed customers, ordered by identifier")
        @Test
        void __QueryLanguage() {
            final var found = entityManager()
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
                    .isNotEmpty()
                    .extracting(Customer::getCustomerId)
                    .isSorted();
        }

        @DisplayName("a criteria query selects the installed customers, ordered by identifier")
        @Test
        void __CriteriaApi() {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.orderBy(builder.asc(root.get(Customer_.customerId)));
            final var found = entityManager.createQuery(query).getResultList();
            assertThat(found)
                    .as("the customers ordered by %s", Customer_.customerId.getName())
                    .isNotEmpty()
                    .extracting(Customer::getCustomerId)
                    .isSorted();
        }
    }

    @Nested
    class SelectPagesOrderByCustomerIdAsc_Test {

        /**
         * The maximum number of results of each page.
         */
        private static final int MAX_RESULTS = 32;

        private void verify(final List<Customer> page) {
            assertThat(page)
                    .as("a page ordered by %s", Customer_.customerId.getName())
                    .hasSizeLessThanOrEqualTo(MAX_RESULTS)
                    .extracting(Customer::getCustomerId)
                    .isSorted();
        }

        @DisplayName("the named query selects all installed customers, page by page, ordered by identifier")
        @Test
        void __NamedQuery() {
            final var query = entityManager()
                    .createNamedQuery("Customer.selectListOrderByCustomerIdAsc", targetClass)
                    .setMaxResults(MAX_RESULTS);
            for (var firstResult = 0; ; firstResult += MAX_RESULTS) {
                final var page = query.setFirstResult(firstResult).getResultList();
                verify(page);
                if (page.size() < MAX_RESULTS) {
                    break;
                }
            }
        }

        @DisplayName("a query-language query selects all installed customers, page by page, ordered by identifier")
        @Test
        void __QueryLanguage() {
            final var query = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Customer e
                                    ORDER BY e.customerId ASC""",
                            targetClass
                    )
                    .setMaxResults(MAX_RESULTS);
            for (var firstResult = 0; ; firstResult += MAX_RESULTS) {
                final var page = query.setFirstResult(firstResult).getResultList();
                verify(page);
                if (page.size() < MAX_RESULTS) {
                    break;
                }
            }
        }

        @DisplayName("a criteria query selects all installed customers, page by page, ordered by identifier")
        @Test
        void __CriteriaApi() {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var criteria = builder.createQuery(targetClass);
            final var root = criteria.from(targetClass);
            criteria.select(root);
            criteria.orderBy(builder.asc(root.get(Customer_.customerId)));
            final var query = entityManager.createQuery(criteria)
                    .setMaxResults(MAX_RESULTS);
            for (var firstResult = 0; ; firstResult += MAX_RESULTS) {
                final var page = query.setFirstResult(firstResult).getResultList();
                verify(page);
                if (page.size() < MAX_RESULTS) {
                    break;
                }
            }
        }
    }

    @Nested
    class SelectListOrderByCustomerIdAscCustomerIdGt_Test {

        /**
         * The maximum number of results of a page.
         */
        private static final int MAX_RESULTS = 32;

        /**
         * Returns the value of {@code customerIdMinExclusive}: the identifier of a random customer, which the page
         * follows.
         *
         * @implNote Assumes the installed table is not empty; the test is aborted, not failed, if it is.
         */
        private long customerIdMinExclusive() {
            final var previous = JinahyaPersistenceTestUtils.selectRandom(entityManager(), targetClass);
            assumeTrue(previous.isPresent(), "no customer to start a page after");
            return previous.get().getCustomerId();
        }

        private void verify(final List<Customer> found, final long customerIdMinExclusive) {
            assertThat(found)
                    .as("at most %d customers after %d, ordered by %s", MAX_RESULTS, customerIdMinExclusive,
                        Customer_.customerId.getName())
                    .hasSizeLessThanOrEqualTo(MAX_RESULTS)
                    .extracting(Customer::getCustomerId)
                    .isSorted()
                    .allSatisfy(v -> assertThat(v).isGreaterThan(customerIdMinExclusive));
        }

        @DisplayName("the named query selects a page of the installed customers, after the previous one")
        @Test
        void __NamedQuery() {
            final var customerIdMinExclusive = customerIdMinExclusive();
            final var found = entityManager()
                    .createNamedQuery("Customer.selectListOrderByCustomerIdAscCustomerIdGt", targetClass)
                    .setParameter("customerIdMinExclusive", customerIdMinExclusive)
                    .setMaxResults(MAX_RESULTS)
                    .getResultList();
            verify(found, customerIdMinExclusive);
        }

        @DisplayName("a query-language query selects a page of the installed customers, after the previous one")
        @Test
        void __QueryLanguage() {
            final var customerIdMinExclusive = customerIdMinExclusive();
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Customer e
                                    WHERE e.customerId > :customerIdMinExclusive
                                    ORDER BY e.customerId ASC""",
                            targetClass
                    )
                    .setParameter("customerIdMinExclusive", customerIdMinExclusive)
                    .setMaxResults(MAX_RESULTS)
                    .getResultList();
            verify(found, customerIdMinExclusive);
        }

        @DisplayName("a criteria query selects a page of the installed customers, after the previous one")
        @Test
        void __CriteriaApi() {
            final var customerIdMinExclusive = customerIdMinExclusive();
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.greaterThan(root.get(Customer_.customerId), customerIdMinExclusive));
            query.orderBy(builder.asc(root.get(Customer_.customerId)));
            final var found = entityManager.createQuery(query)
                    .setMaxResults(MAX_RESULTS)
                    .getResultList();
            verify(found, customerIdMinExclusive);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectOneByEmailAddress_Test {

        /**
         * Returns some email addresses of the installed table.
         *
         * @implNote Selects the email addresses, not the entities, so nothing is left managed.
         */
        Stream<String> emailAddresses() {
            return entityManager()
                    .createQuery(
                            """
                                    SELECT e.emailAddress
                                    FROM Customer e
                                    ORDER BY e.customerId ASC""",
                            String.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }

        @DisplayName("the named query selects the installed customer")
        @MethodSource("emailAddresses")
        @ParameterizedTest
        void __NamedQuery(final String emailAddress) {
            final var found = entityManager()
                    .createNamedQuery("Customer.selectOneByEmailAddress", targetClass)
                    .setParameter("emailAddress", emailAddress)
                    .getSingleResult();
            assertThat(found)
                    .as("the customer selected by %s", emailAddress)
                    .extracting(Customer::getEmailAddress)
                    .isEqualTo(emailAddress);
        }

        @DisplayName("a query-language query selects the installed customer")
        @MethodSource("emailAddresses")
        @ParameterizedTest
        void __QueryLanguage(final String emailAddress) {
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Customer e
                                    WHERE e.emailAddress = :emailAddress""",
                            targetClass
                    )
                    .setParameter("emailAddress", emailAddress)
                    .getSingleResult();
            assertThat(found)
                    .as("the customer selected by %s", emailAddress)
                    .extracting(Customer::getEmailAddress)
                    .isEqualTo(emailAddress);
        }

        @DisplayName("a criteria query selects the installed customer")
        @MethodSource("emailAddresses")
        @ParameterizedTest
        void __CriteriaApi(final String emailAddress) {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.where(builder.equal(root.get(Customer_.emailAddress), emailAddress));
            final var found = entityManager.createQuery(query).getSingleResult();
            assertThat(found)
                    .as("the customer selected by %s", emailAddress)
                    .extracting(Customer::getEmailAddress)
                    .isEqualTo(emailAddress);
        }
    }

    @Nested
    class SelectListOrderByEmailAddress_Test {

        @DisplayName("the named query selects the installed customers, ordered by email address")
        @Test
        void __NamedQuery() {
            final var found = entityManager()
                    .createNamedQuery("Customer.selectListOrderByEmailAddressAsc", targetClass)
                    .getResultList();
            assertThat(found)
                    .as("the customers ordered by %s", Customer_.emailAddress.getName())
                    .isNotEmpty()
                    .extracting(Customer::getEmailAddress)
                    .isSorted();
        }

        @DisplayName("a query-language query selects the installed customers, ordered by email address")
        @Test
        void __QueryLanguage() {
            final var found = entityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Customer e
                                    ORDER BY e.emailAddress ASC""",
                            targetClass
                    )
                    .getResultList();
            assertThat(found)
                    .as("the customers ordered by %s", Customer_.emailAddress.getName())
                    .isNotEmpty()
                    .extracting(Customer::getEmailAddress)
                    .isSorted();
        }

        @DisplayName("a criteria query selects the installed customers, ordered by email address")
        @Test
        void __CriteriaApi() {
            final var entityManager = entityManager();
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(targetClass);
            final var root = query.from(targetClass);
            query.select(root);
            query.orderBy(builder.asc(root.get(Customer_.emailAddress)));
            final var found = entityManager.createQuery(query).getResultList();
            assertThat(found)
                    .as("the customers ordered by %s", Customer_.emailAddress.getName())
                    .isNotEmpty()
                    .extracting(Customer::getEmailAddress)
                    .isSorted();
        }
    }
}
