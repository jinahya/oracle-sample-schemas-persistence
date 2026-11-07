package com.github.jinahya.oracle.sample.persistence.co.co.customers;

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

import com.github.jinahya.oracle.sample.persistence.co.Customer;
import com.github.jinahya.oracle.sample.persistence.co.Customer_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_CUSTOMERS_B_01} of {@code src/test/sql-exercises/CUSTOMERS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_CUSTOMERS_B_01: Pull up a customer by email address")
@Slf4j
class CO_CUSTOMERS_B_01_PersistenceIT {

    private static final String EMAIL_ADDRESS = "tammy.bryant@internalmail";

    @Test
    void __QueryLanguage() {
        final var customer = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Customer c
                                        WHERE c.emailAddress = :emailAddress""",
                                Customer.class
                        )
                        .setParameter("emailAddress", EMAIL_ADDRESS)
                        .getSingleResult()
        );
        verify(customer);
    }

    @Test
    void __CriteriaApi() {
        final var customer = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Customer.class);
            final var c = cq.from(Customer.class);
            cq.select(c)
                    .where(cb.equal(c.get(Customer_.emailAddress), EMAIL_ADDRESS));
            return em.createQuery(cq).getSingleResult();
        });
        verify(customer);
    }

    private static void verify(final Customer customer) {
        log.debug("customer: {}", customer);
        assertThat(customer.getCustomerId()).isEqualTo(1L);
        assertThat(customer.getEmailAddress()).isEqualTo(EMAIL_ADDRESS);
        assertThat(customer.getFullName()).isEqualTo("Tammy Bryant");
    }
}
