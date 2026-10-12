package com.github.jinahya.oracle.sample.schemas.persistence.co;

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Naive_Customer_Persistence_IT {

    private static final String PERSISTENCE_UNIT_NAME = "co-it";

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

    static <R> R applyEntityManager(final Function<EntityManager, ? extends R> function) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            return function.apply(entityManager);
        }
    }

    static <R> R applyEntityManagerInTransactionAndRollback(final Function<EntityManager, ? extends R> function) {
        return applyEntityManager(em -> {
            final var transaction = em.getTransaction();
            transaction.begin();
            try {
                return function.apply(em);
            } finally {
                if (transaction.isActive()) {
                    log.debug("rolling back");
                    transaction.rollback();
                }
            }
        });
    }

    static <R> R applyEntityManagerInTransactionAndCommit(final Function<EntityManager, ? extends R> function) {
        return applyEntityManager(em -> {
            final var transaction = em.getTransaction();
            transaction.begin();
            final R result;
            try {
                result = function.apply(em);
            } catch (final Throwable t) {
                log.error("failed in transaction; rolling back", t);
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw t;
            }
            log.debug("committing");
            transaction.commit();
            return result;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("persist, commit, find, and remove")
    @Test
    void __() {
        // persist and commit
        final var customerId = applyEntityManagerInTransactionAndCommit(em -> {
            final var customer = ObjectRandomizerUtils.newRandomizedInstanceOf(Customer.class).orElseThrow();
            log.debug("persisting {}", customer);
            em.persist(customer);
            em.flush();
            log.debug("persisted; customerId: {}", customer.getCustomerId());
            assertThat(customer.getCustomerId()).isNotNull();
            return customer.getCustomerId();
        });
        try {
            applyEntityManager(em -> {
                final var found = em.find(Customer.class, customerId);
                log.debug("found by {}: {}", customerId, found);
                assertThat(found).isNotNull();
                assertThat(found.getCustomerId()).isEqualTo(customerId);
                return null;
            });
        } finally {
            applyEntityManagerInTransactionAndCommit(em -> {
                log.debug("finding reference for {}", customerId);
                final var reference = em.getReference(Customer.class, customerId);
                log.debug("removing the reference...");
                em.remove(reference);
                return null;
            });
        }
    }
}
