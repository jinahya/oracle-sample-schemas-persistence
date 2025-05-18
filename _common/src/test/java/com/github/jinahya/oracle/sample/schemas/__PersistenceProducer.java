package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public abstract class __PersistenceProducer {

    // -----------------------------------------------------------------------------------------------------------------
    protected __PersistenceProducer(final String persistenceUnitName) {
        super();
        this.persistenceUnitName = Objects.requireNonNull(persistenceUnitName, "persistenceUnitName is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected EntityManagerFactory produceEntityManagerFactory() {
        final var entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        log.debug("producing entityManageFactory: {}", entityManagerFactory);
        return entityManagerFactory;
    }

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
    protected final String persistenceUnitName;
}
