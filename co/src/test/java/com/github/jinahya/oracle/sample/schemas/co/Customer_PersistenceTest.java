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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Persists {@link Customer} instances against the in-memory database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Customer_PersistenceTest {

    private static Customer newRandomizedInstance() {
        final var random = ThreadLocalRandom.current();
        final var instance = new Customer();
        instance.setEmailAddress(random.nextLong(Long.MAX_VALUE) + "@" + random.nextLong(Long.MAX_VALUE) + ".com");
        instance.setFullName("name-" + random.nextLong(Long.MAX_VALUE));
        return instance;
    }

    @DisplayName("persist() assigns the identity-generated customerId")
    @Test
    void persist_AssignsGeneratedId_() {
        final var persisted = _Persistence_Test_Utils.applyEntityManagerInRolledBackTransaction(em -> {
            final var instance = newRandomizedInstance();
            assertThat(instance.getCustomerId()).as("customerId before persist").isNull();
            em.persist(instance);
            em.flush();
            return instance;
        });
        assertThat(persisted.getCustomerId())
                .as("customerId assigned by %s", Customer.COLUMN_NAME_CUSTOMER_ID)
                .isNotNull()
                .isPositive();
    }

    @DisplayName("a persisted Customer reads back with the values it was given")
    @Test
    void persist_RoundTrips_() {
        _Persistence_Test_Utils.applyEntityManagerInRolledBackTransaction(em -> {
            final var instance = newRandomizedInstance();
            em.persist(instance);
            em.flush();
            em.clear();
            final var found = em.find(Customer.class, instance.getCustomerId());
            assertThat(found).isNotNull();
            assertThat(found.getEmailAddress()).isEqualTo(instance.getEmailAddress());
            assertThat(found.getFullName()).isEqualTo(instance.getFullName());
            return found;
        });
    }
}
