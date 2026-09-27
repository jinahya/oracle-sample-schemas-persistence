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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * Persistence-unit-agnostic helpers shared by {@link _Persistence_Test_Utils} and {@link _Persistence_IT_Utils}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote Every method takes the persistence unit to work against, which is the only thing that differs between the
 * two. Tests call the named facades rather than this class.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class __Persistence___Utils {

    /**
     * Returns the entity manager factory for the specified persistence unit, creating it on first use.
     *
     * @param persistenceUnitName the name of the persistence unit.
     * @return the entity manager factory for {@code persistenceUnitName}.
     */
    static EntityManagerFactory entityManagerFactory(final String persistenceUnitName) {
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
    static <R> R applyEntityManagerInRolledBackTransaction(
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
     * Counts the rows mapped by the specified entity class.
     *
     * @param entityManager the entity manager to count with.
     * @param entityClass   the entity class to count instances of.
     * @return the number of rows; {@code 0} when the table is empty.
     * @implNote {@code COUNT} with no {@code GROUP BY} is an aggregate over the whole table, so it always yields
     * exactly one row holding a number. It neither returns {@code null} nor throws
     * {@link jakarta.persistence.NoResultException}, hence no guard against either.
     */
    static long count(final EntityManager entityManager, final Class<?> entityClass) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        // the JPQL name, which @Entity(name = ...) may set to something other than the simple class name
        final var name = entityManager.getMetamodel().entity(entityClass).getName();
        return entityManager
                .createQuery("SELECT COUNT(e) FROM " + name + " e", Long.class)
                .getSingleResult();
    }

    /**
     * Selects a random instance of the specified entity class using the specified entity manager.
     *
     * @param entityManager the entity manager to select with.
     * @param entityClass   the entity class to select an instance of.
     * @param <T>           entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the table is empty.
     */
    static <T> Optional<T> selectRandom(final EntityManager entityManager, final Class<T> entityClass) {
        if (entityManager.getMetamodel().getEntities().stream()
                .noneMatch(t -> t.getJavaType() == entityClass)) {
            // an @Embeddable has no table of its own, so there is nothing to select a random one from
            return Optional.empty();
        }
        final var count = count(entityManager, entityClass);
        if (count == 0L) {
            return Optional.empty();
        }
        // the JPQL name, which @Entity(name = ...) may set to something other than the simple class name
        final var name = entityManager.getMetamodel().entity(entityClass).getName();
        return entityManager
                .createQuery("SELECT e FROM " + name + " e", entityClass)
                .setFirstResult(Math.toIntExact(ThreadLocalRandom.current().nextLong(count)))
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }

    /**
     * Selects a random instance of the specified entity class from the specified persistence unit.
     *
     * @param persistenceUnitName the name of the persistence unit.
     * @param entityClass         the entity class to select an instance of.
     * @param <T>                 entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the table is empty.
     * @apiNote The instance is detached by the time it is returned -- the transaction is rolled back and the entity
     * manager closed -- so reading a lazy association off it throws. Use {@link #selectRandom(EntityManager, Class)}
     * inside {@link #applyEntityManagerInRolledBackTransaction(String, Function)} when the instance has to stay
     * managed.
     */
    static <T> Optional<T> selectRandom(final String persistenceUnitName, final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return applyEntityManagerInRolledBackTransaction(
                persistenceUnitName, em -> selectRandom(em, entityClass));
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private __Persistence___Utils() {
        throw new AssertionError("instantiation is not allowed");
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final Map<String, EntityManagerFactory> FACTORIES = new ConcurrentHashMap<>();
}
