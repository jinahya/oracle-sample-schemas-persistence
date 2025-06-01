package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;

import static org.assertj.core.api.Assumptions.assumeThat;

@Slf4j
public final class __Persistence_Test_Utils {

    public static <R> R applyEntityManagerInTransaction(final EntityManager entityManager,
                                                        final Function<? super EntityManager, ? extends R> function,
                                                        final boolean rollback) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(function, "function is null");
        final var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            final R result = function.apply(entityManager);
            if (rollback) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
            return result;
        } catch (final Exception e) {
            transaction.rollback();
            throw new RuntimeException("failed to apply " + function + " and rollback: " + rollback, e);
        }
    }

    public static <R> R applyConnection(final EntityManager entityManager,
                                        final Function<? super Connection, ? extends R> function,
                                        final boolean rollback) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(function, "function is null");
        final var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            final var connection = entityManager.unwrap(Connection.class);
            final R result = function.apply(connection);
            if (rollback) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
            return result;
        } catch (final Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static long count(final EntityManager entityManager, final Class<?> entityClass) {
        final var builder = entityManager.getCriteriaBuilder();
        final var criteria = builder.createQuery(Long.class);
        final var root = criteria.from(entityClass);
        criteria.select(builder.count(root));
        final var typed = entityManager.createQuery(criteria);
        return typed.getSingleResult();
    }

    public static <R> R applyCountAndRandomIndex(final EntityManager entityManager, final Class<?> entityClass,
                                                 final LongFunction<? extends LongFunction<? extends R>> function) {
        final var count = count(entityManager, entityClass);
        assumeThat(count)
                .as("count of %1$s", entityClass)
                .isPositive();
        final var index = ThreadLocalRandom.current().nextLong(count);
        return function.apply(count).apply(index);
    }

    public static void acceptCountAndRandomIndex(final EntityManager entityManager, final Class<?> entityClass,
                                                 final LongFunction<? extends LongConsumer> function) {
        applyCountAndRandomIndex(entityManager, entityClass, c -> i -> {
            function.apply(c).accept(i);
            return null;
        });
    }

    public static <T> Optional<T> selectRandom(final EntityManager entityManager, final Class<T> entityClass) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        return applyCountAndRandomIndex(
                entityManager,
                entityClass,
                c -> i -> {
                    final var builder = entityManager.getCriteriaBuilder();
                    final var query = builder.createQuery(entityClass);
                    query.from(entityClass);
                    return Optional.of(
                            entityManager.createQuery(query)
                                    .setFirstResult(Math.toIntExact(i))
                                    .setMaxResults(1)
                                    .getSingleResult()
                    );
                }
        );
    }

    public static String entityName(final EntityManager entityManager, final Class<?> entityClass) {
        return entityManager.getMetamodel().entity(entityClass).getName();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __Persistence_Test_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
