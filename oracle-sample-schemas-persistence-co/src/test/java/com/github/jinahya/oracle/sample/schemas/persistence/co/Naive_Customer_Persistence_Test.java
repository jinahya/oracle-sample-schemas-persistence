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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A naive persistence test of the {@link Customer} class, written out in full, with no test base and no CDI container.
 * <p>
 * The factory of the {@code co-test} unit is created once for the class and closed after it; a test takes an entity
 * manager from it, and works inside a transaction which it always rolls back.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Customer_Persistence_Test
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class Naive_Customer_Persistence_Test {

    private static final String PERSISTENCE_UNIT_NAME = "co-test";

    // -----------------------------------------------------------------------------------------------------------------
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        log.debug("created entity manager factory for {}: {}", PERSISTENCE_UNIT_NAME, entityManagerFactory);
    }

    @AfterAll
    static void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            log.debug("closing entity manager factory: {}", entityManagerFactory);
            entityManagerFactory.close();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("persist, commit, find, and remove")
    @Test
    void persist__() {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            final var customerId = new Long[1];
            final var transaction1 = entityManager.getTransaction();
            transaction1.begin();
            try {
                final var customer = ObjectRandomizerUtils.newRandomizedInstanceOf(Customer.class).orElseThrow();
                log.debug("persisting {}", customer);
                entityManager.persist(customer);
                entityManager.flush();
                log.debug("persisted; customerId: {}", customer.getCustomerId());
                assertThat(customer.getCustomerId()).isNotNull();
                customerId[0] = customer.getCustomerId();
                transaction1.commit();
                log.debug("committed");
            } catch (final Exception e) {
                log.error("failed to persist; rolling back", e);
                transaction1.rollback();
                return;
            }
            entityManager.clear();
            assert customerId[0] != null;
            final var found = entityManager.find(Customer.class, customerId[0]);
            log.debug("found by {}: {}", customerId[0], found);
            assertThat(found).isNotNull();
            log.debug("removing {}", found);
            entityManager.remove(found);
            final var transaction2 = entityManager.getTransaction();
            transaction2.begin();
            try {
                entityManager.flush();
                log.debug("flushed the removal");
            } catch (final Exception e) {
                log.error("failed to flush the removal; rolling back", e);
                transaction2.rollback();
            }
        }
    }
}
