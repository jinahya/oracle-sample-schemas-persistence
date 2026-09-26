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

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Objects;
import java.util.function.Function;

/**
 * An abstract service class for an entity class and its identifier class.
 *
 * @param <ENTITY> the entity type.
 * @param <ID>     the type of the entity's identifier.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class EntityService<ENTITY, ID> {
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for the specified entity class and identifier class.
     *
     * @param entityClass   the entity class.
     * @param idClass       the class of the entity's identifier.
     * @param entityManager the entity manager to use.
     */
    protected EntityService(final @Nonnull Class<ENTITY> entityClass, final @Nonnull Class<ID> idClass,
                            final @Nonnull EntityManager entityManager) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
        this.entityManager = Objects.requireNonNull(entityManager, "entityManager is null");
    }

    // ----------------------------------------------------------------------------------------------------- entityClass
    // --------------------------------------------------------------------------------------------------------- idClass
    // --------------------------------------------------------------------------------------------------- entityManager

    /**
     * Applies the entity manager, of this service, to the specified function, optionally within a transaction.
     *
     * @param <R>           the type of the result.
     * @param function      the function to apply the entity manager to.
     * @param transactional {@code true} to run within a transaction; {@code false} otherwise.
     * @param rollback      {@code true} to roll the transaction back; {@code false} to commit it.
     * @return the result of the {@code function}.
     */
    protected <R> R applyEntityManager(final @Nonnull Function<EntityManager, R> function, final boolean transactional,
                                       final boolean rollback) {
        Objects.requireNonNull(function, "function is null");
        if (!transactional) {
            return function.apply(entityManager);
        }
        if (entityManager.isJoinedToTransaction()) {
            throw new IllegalStateException("entityManager is already joined to a transaction");
        }
        final var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            final var result = function.apply(entityManager);
            if (rollback) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
            return result;
        } catch (final RuntimeException re) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw re;
        }
    }

    /**
     * Applies the entity manager, and its criteria builder, to the specified function, optionally within a
     * transaction.
     *
     * @param <R>           the type of the result.
     * @param function      the function to apply the entity manager and the criteria builder to.
     * @param transactional {@code true} to run within a transaction; {@code false} otherwise.
     * @param rollback      {@code true} to roll the transaction back; {@code false} to commit it.
     * @return the result of the {@code function}.
     */
    protected <R> R applyCriteriaBuilder(final Function<
                                                 ? super EntityManager,
                                                 ? extends Function<
                                                         ? super CriteriaBuilder,
                                                         ? extends R
                                                         >
                                                 > function,
                                         final boolean transactional,
                                         final boolean rollback) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManager(
                em -> function.apply(em).apply(em.getCriteriaBuilder()),
                transactional,
                rollback
        );
    }

    /**
     * Applies the entity manager, its criteria builder, and a criteria query for the entity class, to the specified
     * function, optionally within a transaction.
     *
     * @param <R>           the type of the result.
     * @param function      the function to apply.
     * @param transactional {@code true} to run within a transaction; {@code false} otherwise.
     * @param rollback      {@code true} to roll the transaction back; {@code false} to commit it.
     * @return the result of the {@code function}.
     */
    protected <R> R applyCriteriaQuery(final Function<
                                               ? super EntityManager,
                                               ? extends Function<
                                                       ? super CriteriaBuilder,
                                                       ? extends Function<
                                                               ? super CriteriaQuery<ENTITY>,
                                                               ? extends R
                                                               >
                                                       >> function,
                                       final boolean transactional,
                                       final boolean rollback) {
        return applyCriteriaBuilder(
                em -> b -> function.apply(em).apply(b).apply(b.createQuery(entityClass)),
                transactional,
                rollback
        );
    }

    /**
     * Applies the entity manager, its criteria builder, a criteria query for the entity class, and the query's root, to
     * the specified function, optionally within a transaction.
     *
     * @param <R>           the type of the result.
     * @param function      the function to apply.
     * @param transactional {@code true} to run within a transaction; {@code false} otherwise.
     * @param rollback      {@code true} to roll the transaction back; {@code false} to commit it.
     * @return the result of the {@code function}.
     */
    protected <R> R applyRoot(final Function<
                                      ? super EntityManager,
                                      ? extends Function<
                                              ? super CriteriaBuilder,
                                              ? extends Function<
                                                      ? super CriteriaQuery<ENTITY>,
                                                      ? extends Function<
                                                              ? super Root<ENTITY>,
                                                              ? extends R
                                                              >
                                                      >
                                              >
                                      > function,
                              final boolean transactional,
                              final boolean rollback) {
        return applyCriteriaQuery(
                em -> b -> q -> function.apply(em).apply(b).apply(q).apply(q.from(entityClass)),
                transactional,
                rollback
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The entity class of this service.
     */
    protected final Class<ENTITY> entityClass;

    /**
     * The class of the identifier of {@link #entityClass}.
     */
    protected final Class<ID> idClass;

    private EntityManager entityManager;
}
