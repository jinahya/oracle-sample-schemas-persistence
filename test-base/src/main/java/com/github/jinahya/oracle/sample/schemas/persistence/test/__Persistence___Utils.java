package com.github.jinahya.oracle.sample.schemas.persistence.test;

/*-
 * #%L
 * test-base
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.metamodel.EntityType;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * Persistence-unit-agnostic helpers, behind {@link _Persistence_Test_Utils}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote The methods which open their own entity manager take the persistence unit to work against; tests call
 * the named facade rather than this class.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class __Persistence___Utils {

    /**
     * Returns the entity manager factory for the specified persistence unit, creating it on first use.
     *
     * @param persistenceUnitName the name of the persistence unit.
     * @return the entity manager factory for {@code persistenceUnitName}.
     */
    public static EntityManagerFactory entityManagerFactory(final String persistenceUnitName) {
        Objects.requireNonNull(persistenceUnitName, "persistenceUnitName is null");
        return FACTORIES.computeIfAbsent(persistenceUnitName, n -> {
            final var factory = Persistence.createEntityManagerFactory(n);
            Runtime.getRuntime().addShutdownHook(new Thread(factory::close));
            return factory;
        });
    }

    /**
     * Applies a function to a new entity manager of the specified persistence unit, inside a transaction which is
     * always rolled back, so that nothing a test writes outlives it.
     *
     * @param persistenceUnitName the name of the persistence unit.
     * @param function            the function to apply.
     * @param <R>                 result type parameter.
     * @return the result of the {@code function}.
     */
    public static <R> R applyEntityManagerInRolledBackTransaction(
            final String persistenceUnitName,
            final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        try (final var entityManager = entityManagerFactory(persistenceUnitName).createEntityManager()) {
            final var transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                return function.apply(entityManager);
            } finally {
                transaction.rollback();
            }
        }
    }

    /**
     * Counts the rows mapped by the specified entity type.
     *
     * @param entityManager the entity manager to count with.
     * @param entityType    the entity type to count instances of.
     * @return the number of rows; {@code 0} when the table is empty.
     * @implNote The query is built from {@link EntityType#getName()}, the JPQL name, which
     * {@code @Entity(name = ...)} may set to something other than the simple class name.
     * <p>
     * {@code COUNT} with no {@code GROUP BY} is an aggregate over the whole table, so it always yields exactly one
     * row holding a number. It neither returns {@code null} nor throws
     * {@link jakarta.persistence.NoResultException}, hence no guard against either.
     */
    public static long count(final EntityManager entityManager, final EntityType<?> entityType) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityType, "entityType is null");
        return entityManager
                .createQuery("SELECT COUNT(e) FROM " + entityType.getName() + " e", Long.class)
                .getSingleResult();
    }

    /**
     * Selects a random instance of the specified entity class using the specified entity manager.
     *
     * @param entityManager the entity manager to select with.
     * @param entityClass   the entity class to select an instance of.
     * @param <T>           entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the class is not an
     * entity, or when its table is empty.
     * @implNote The entity type is resolved once, and both queries are built from it; an {@link
     * jakarta.persistence.Embeddable @Embeddable} has no table of its own, so it resolves to nothing and there is
     * nothing to select a random one from.
     */
    public static <T> Optional<T> selectRandom(final EntityManager entityManager, final Class<T> entityClass) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        final var entityType = entityManager.getMetamodel().getEntities().stream()
                .filter(t -> t.getJavaType() == entityClass)
                .findFirst()
                .orElse(null);
        if (entityType == null) {
            return Optional.empty();
        }
        final var count = count(entityManager, entityType);
        if (count == 0L) {
            return Optional.empty();
        }
        return entityManager
                .createQuery("SELECT e FROM " + entityType.getName() + " e", entityClass)
                .setFirstResult(Math.toIntExact(ThreadLocalRandom.current().nextLong(count)))
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private __Persistence___Utils() {
        throw new AssertionError("instantiation is not allowed");
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final Map<String, EntityManagerFactory> FACTORIES = new ConcurrentHashMap<>();
}
