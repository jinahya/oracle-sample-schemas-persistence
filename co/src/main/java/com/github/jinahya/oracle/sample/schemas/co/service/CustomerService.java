package com.github.jinahya.oracle.sample.schemas.co.service;

/*-
 * #%L
 * co
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

import com.github.jinahya.oracle.sample.schemas.co.Customer;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An abstract service class for the {@link Customer} entity class.
 *
 * @param <ENTITY> the entity type.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class CustomerService<ENTITY extends Customer>
        extends EntityService<ENTITY, Long> {
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for the specified entity class, with the specified entity manager.
     *
     * @param entityClass   the entity class.
     * @param entityManager the entity manager to use.
     */
    protected CustomerService(final @Nonnull Class<ENTITY> entityClass,
                              final @Nonnull EntityManager entityManager) {
        super(entityClass, Long.class, entityManager);
    }

    /**
     * Finds the customer whose {@code emailAddress} attribute matches the specified value.
     *
     * @param emailAddress the email address to match.
     * @return an optional of the matched customer; {@link Optional#empty() empty} when there is none.
     */
    public Optional<ENTITY> findByEmailAddress(final String emailAddress) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return applyEntityManager(
                    em -> {
                        final var builder = em.getCriteriaBuilder();
                        final var query = builder.createQuery(entityClass);
                        final var root = query.from(entityClass);
                        query.select(root);
                        query.where(builder.equal(root.get(Customer.ATTRIBUTE_NAME_EMAIL_ADDRESS), emailAddress));
                        try {
                            return Optional.of(em.createQuery(query).getSingleResult());
                        } catch (final NoResultException nre) {
                            return Optional.empty();
                        }
                    },
                    false,
                    false
            );
        }
        return applyRoot(
                em -> b -> q -> r -> {
                    q.where(b.equal(r.get(Customer.ATTRIBUTE_NAME_EMAIL_ADDRESS), emailAddress));
                    try {
                        return Optional.of(em.createQuery(q).getSingleResult());
                    } catch (final NoResultException nre) {
                        return Optional.empty();
                    }
                },
                false, // <transactional>
                true   // <rollback>
        );
    }
}
