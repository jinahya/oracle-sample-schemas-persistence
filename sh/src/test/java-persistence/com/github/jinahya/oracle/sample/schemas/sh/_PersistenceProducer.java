package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__PersistenceProducer;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class _PersistenceProducer extends __PersistenceProducer {

    private static final String PERSISTENCE_UNIT_NAME = "shPU";

    // -----------------------------------------------------------------------------------------------------------------
    _PersistenceProducer() {
        super(PERSISTENCE_UNIT_NAME);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Produces
    @Override
    protected EntityManagerFactory produceEntityManagerFactory() {
        return super.produceEntityManagerFactory();
    }

    @Override
    protected void disposeEntityManagerFactory(@Disposes final EntityManagerFactory entityManagerFactory) {
        super.disposeEntityManagerFactory(entityManagerFactory);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Produces
    @Override
    protected EntityManager produceEntityManager(final EntityManagerFactory entityManagerFactory) {
        return super.produceEntityManager(entityManagerFactory);
    }

    @Override
    protected void disposeEntityManager(@Disposes final EntityManager entityManager) {
        super.disposeEntityManager(entityManager);
    }
}
