package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * A class for producing/disposing entity manager factory and entity manager.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
public abstract class __PersistenceProducer {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for producing/disposing entity manager factory and entity manager.
     *
     * @param persistenceUnitName the name of the persistence unit.
     */
    protected __PersistenceProducer(@jakarta.annotation.Nonnull final String persistenceUnitName) {
        super();
        this.persistenceUnitName = Objects.requireNonNull(persistenceUnitName, "persistenceUnitName is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Produces an entity manager factory.
     *
     * @return an entity manager factory.
     */
    protected EntityManagerFactory produceEntityManagerFactory() {
        final var entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        log.debug("producing entityManagerFactory: {}", entityManagerFactory);
        return entityManagerFactory;
    }

    /**
     * Disposes specified entity manager factory.
     *
     * @param entityManagerFactory the entity manager factory to dispose.
     */
    protected void disposeEntityManagerFactory(final EntityManagerFactory entityManagerFactory) {
        log.debug("disposing entityManagerFactory: {}", entityManagerFactory);
        entityManagerFactory.close();
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected EntityManager produceEntityManager(final EntityManagerFactory entityManagerFactory) {
        final var entityManager = entityManagerFactory.createEntityManager();
        log.debug("producing entityManager: {}", entityManager);
        return entityManager;
    }

    protected void disposeEntityManager(final EntityManager entityManager) {
        log.debug("disposing entityManager: {}", entityManager);
        entityManager.close();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the persistence unit.
     */
    protected final String persistenceUnitName;
}
