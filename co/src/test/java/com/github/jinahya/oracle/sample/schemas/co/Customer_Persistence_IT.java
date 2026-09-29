package com.github.jinahya.oracle.sample.schemas.co;


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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Customer} against the installed {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Customer_Persistence_IT extends _Persistence_IT<Customer> {

    /**
     * Some customers of the installed table, ordered by identifier.
     *
     * @implNote Static, and read once, because the argument sources of the nested classes are static methods -- which
     * is what {@link MethodSource} resolves by a bare name -- and because there is no reason for each test to select
     * them again.
     */
    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Reads some customers of the installed table into {@link #CUSTOMERS}.
     *
     * @implNote {@link TestInstance.Lifecycle#PER_CLASS} is what lets this be an instance method, which it has to be:
     * the entity manager arrives at an injection point of the test instance, and a {@code static} callback would run
     * with nothing injected.
     * <p>
     * No transaction, and none is needed: this only reads, and a query runs outside one. Nor does the
     * persistence context have to be cleared afterwards -- each nested class is injected with an entity manager of its
     * own, so what this leaves managed is gone by the time a test runs, and every {@code find} below really does reach
     * the database.
     * @implSpec What lands in {@link #CUSTOMERS} is therefore detached: its identifier and its email address are
     * loaded and safe to read, a lazy association of it is not.
     */
    @BeforeAll
    void selectSome() {
        final var entityManager = getEntityManager();
        CUSTOMERS.clear();
        CUSTOMERS.addAll(
                entityManager
                        .createNamedQuery("Customer.selectListOrderByCustomerIdAsc", Customer.class)
                        .setMaxResults(5)
                        .getResultList()
        );
        assertThat(CUSTOMERS).isNotEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    Customer_Persistence_IT() {
        super(Customer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class Find_Test {

        static LongStream customerIds() {
            return CUSTOMERS.stream().mapToLong(Customer::getCustomerId);
        }

        @MethodSource("customerIds")
        @ParameterizedTest
        void __(final long customerId) {
            final var found = getEntityManager().find(targetClass, customerId);
            assertThat(found).isNotNull().extracting(Customer::getCustomerId).isEqualTo(customerId);
        }
    }

    @Nested
    class SelectOneByEmailAddress_Test {

        static Stream<String> emailAddresses() {
            return CUSTOMERS.stream().map(Customer::getEmailAddress);
        }

        @DisplayName("a query-language query selects the installed customer")
        @MethodSource("emailAddresses")
        @ParameterizedTest
        void __QueryLanguage(final String emailAddress) {
            final var found = getEntityManager()
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Customer e
                                    WHERE e.emailAddress = :emailAddress""",
                            targetClass
                    )
                    .setParameter(Customer_.emailAddress.getName(), emailAddress)
                    .getSingleResult();
            assertThat(found)
                    .as("the customer selected by %s", emailAddress)
                    .extracting(Customer::getEmailAddress)
                    .isEqualTo(emailAddress);
        }

        @DisplayName("the named query selects the installed customer")
        @MethodSource("emailAddresses")
        @ParameterizedTest
        void __NamedQuery(final String emailAddress) {
            final var found = getEntityManager()
                    .createNamedQuery("Customer.selectOneByEmailAddress", targetClass)
                    .setParameter(Customer_.emailAddress.getName(), emailAddress)
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
            final var entityManager = getEntityManager();
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
}
