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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.metamodel.EntityType;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Helpers for tests which already have an entity manager.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote Every method here takes the entity manager it works with, so the same method serves either persistence unit
 * and nothing here has to know which one the caller is on. Obtaining an entity manager is not this class's business:
 * {@link _DomainEntity_Persistence_Test_Producer} and {@link _DomainEntity_Persistence_IT_Producer} produce them as CDI
 * beans, and a test which needs one injects it rather than opening one here.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class ___Persistence_TestUtils {

    /**
     * Applies the specified resultFunction to the specified entity manager, inside a transaction, and hands the
     * transaction to the specified transactionConsumer once the resultFunction is done with it.
     *
     * @param entityManager       the entity manager to work with.
     * @param resultFunction      the resultFunction to apply.
     * @param transactionConsumer the transactionConsumer to accept the transaction; invoked whether the
     *                            {@code resultFunction} returned or threw.
     * @param <R>                 result type parameter.
     * @return the result of the {@code resultFunction}.
     * @apiNote This is the general form the other methods here are built on, for a caller which wants to decide what
     * becomes of the transaction. Note that the {@code transactionConsumer} cannot tell a return from a throw, so
     * committing through it would commit a transaction whose resultFunction failed;
     * {@link #applyInTransactionAndCommit(EntityManager, Function)} exists for that reason.
     * @implNote The {@code transactionConsumer} should guard on {@link EntityTransaction#isActive()}: a
     * {@code resultFunction} which ended the transaction itself leaves nothing to end, and rolling an inactive
     * transaction back throws an {@link IllegalStateException} which would mask whatever the resultFunction did.
     */
    public static <R> R applyInTransaction(
            final EntityManager entityManager,
            final Function<? super EntityManager, ? extends R> resultFunction,
            final Consumer<? super EntityTransaction> transactionConsumer) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(resultFunction, "resultFunction is null");
        Objects.requireNonNull(transactionConsumer, "transactionConsumer is null");
        final var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            return resultFunction.apply(entityManager);
        } finally {
            transactionConsumer.accept(transaction);
        }
    }

    /**
     * Applies the specified resultFunction to the specified entity manager, inside a transaction which is always rolled
     * back.
     *
     * @param entityManager  the entity manager to work with.
     * @param resultFunction the resultFunction to apply.
     * @param <R>            result type parameter.
     * @return the result of the {@code resultFunction}.
     * @implNote The rollback is what keeps one test from being visible to the next, and, on the physical database, what
     * keeps the sample data as the installer left it. It happens whether the {@code resultFunction} returned or threw,
     * so a failing test leaves no more behind than a passing one.
     */
    public static <R> R applyInTransactionAndRollback(
            final EntityManager entityManager,
            final Function<? super EntityManager, ? extends R> resultFunction) {
        return applyInTransaction(entityManager, resultFunction, t -> {
            if (t.isActive()) {
                t.rollback();
            }
        });
    }

    /**
     * Applies the specified resultFunction to the specified entity manager, inside a transaction which is committed
     * when the resultFunction returns, and rolled back when it throws.
     *
     * @param entityManager  the entity manager to work with.
     * @param resultFunction the resultFunction to apply.
     * @param <R>            result type parameter.
     * @return the result of the {@code resultFunction}.
     * @apiNote Unlike {@link #applyInTransactionAndRollback(EntityManager, Function)}, what this writes outlives the
     * call. On the physical database that is the installed sample data being modified, so a test wants the rolled-back
     * one unless it means to leave something behind.
     * @implNote Not built on {@link #applyInTransaction(EntityManager, Function, Consumer)}: its transaction consumer
     * runs in a {@code finally}, which cannot tell a return from a throw, and committing a transaction whose
     * resultFunction threw is the one thing this must not do.
     */
    public static <R> R applyInTransactionAndCommit(
            final EntityManager entityManager,
            final Function<? super EntityManager, ? extends R> resultFunction) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(resultFunction, "resultFunction is null");
        final var transaction = entityManager.getTransaction();
        transaction.begin();
        final R result;
        try {
            result = resultFunction.apply(entityManager);
        } catch (final Throwable t) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw t;
        }
        if (transaction.isActive()) {
            transaction.commit();
        }
        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Counts the rows mapped by the specified entity type.
     *
     * @param entityManager the entity manager to count with.
     * @param entityClass   the entity type to count instances of.
     * @return the number of rows; {@code 0} when the table is empty.
     * @implNote The query is built from {@link EntityType#getName()}, the JPQL name, which {@code @Entity(name = ...)}
     * may set to something other than the simple class name.
     * <p>
     * {@code COUNT} with no {@code GROUP BY} is an aggregate over the whole table, so it always yields exactly one row
     * holding a number. It neither returns {@code null} nor throws {@link jakarta.persistence.NoResultException}, hence
     * no guard against either.
     */
    public static long count(final EntityManager entityManager, final EntityType<?> entityClass) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        return entityManager
                .createQuery("SELECT COUNT(e) FROM " + entityClass.getName() + " e", Long.class)
                .getSingleResult();
    }

    /**
     * Selects a random instance of the specified entity class using the specified entity manager.
     *
     * @param entityManager the entity manager to select with.
     * @param entityClass   the entity class to select an instance of.
     * @param <T>           entity type parameter.
     * @return a random instance of {@code entityClass}; {@link Optional#empty() empty} when the class is not an entity,
     * or when its table is empty.
     * @implNote The entity type is resolved once, and both queries are built from it; an
     * {@link jakarta.persistence.Embeddable @Embeddable} has no table of its own, so it resolves to nothing and there
     * is nothing to select a random one from.
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
    private ___Persistence_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
