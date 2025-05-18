package com.github.jinahya.oracle.sample.schemas;

import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EnumType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public abstract class __MappedEntity_PersistenceIT<ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
        extends ___MappedEntity_TestBase<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected __MappedEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Persists a randomized instance of {@link #entityClass}.
     */
    @DisplayName("persist a random entity instance")
    @Test
    protected void persistRandom() {
        applyEntityManagerInTransactionAndRollback(em -> {
            __MappedEntity_Persister_Utils.newPersistedInstanceOf(entityClass, entityManager()).ifPresent(e -> {
                log.debug("persisted entity: {}", e);
                em.flush();
                log.debug("e._id_: {}", e._id_());
                final var found = entityManager.find(entityClass, e._id_());
                assertThat(found).isEqualTo(e);
            });
            return null;
        });
    }

    /**
     * Finds an entity from the database.
     */
    @DisplayName("find a random entity")
    @Test
    protected void findRandom() {
        Persistence_TestUtils.findRandom(entityManager(), entityClass)
                .ifPresent(this::randomFound__);
    }

    /**
     * Reports an instance of {@link #entityClass} found in the database.
     *
     * @param entity the instance of {@link #entityClass} found in the database
     */
    protected void randomFound__(@Nonnull final ENTITY entity) {
        log.debug("found entity: {}", entity);
        Validation_TestUtils.requireValid(entity);
    }

    // ----------------------------------------------------------------------------------------------- super.entityClass

    // --------------------------------------------------------------------------------------------------- super.idClass

    // ------------------------------------------------------------------------------------------------------ entityName

    /**
     * Returns {@link EnumType#name() name} of the {@link #entityClass}.
     *
     * @return {@link EnumType#name() name} of the {@link #entityClass}.
     */
    protected String entityName() {
        var entityName_ = entityName;
        if (entityName_ == null) {
            entityName_ = entityName = Persistence_TestUtils.entityName(entityManager(), entityClass);
        }
        return entityName_;
    }

    // -------------------------------------------------------------------------------------------- entityManagerFactory

    /**
     * Returns an instance of {@link EntityManagerFactory}, injected to this object.
     *
     * @return an instance of {@link EntityManagerFactory}.
     * @see #applyEntityManagerFactory(Function)
     */
    protected EntityManagerFactory entityManagerFactory() {
        return entityManagerFactoryProxy();
    }

    /**
     * Applies an instance of {@link EntityManagerFactory}, injected to this object, to specified function, and returns
     * the result.
     *
     * @param function the function.
     * @return the result of the function.
     * @see #entityManagerFactory()
     */
    protected <R> R applyEntityManagerFactory(final Function<? super EntityManagerFactory, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(entityManagerFactory());
    }

    // --------------------------------------------------------------------------------------------------- entityManager
    protected EntityManager entityManager() {
        return entityManagerProxy();
    }

    protected <R> R applyEntityManager(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(entityManager());
    }

    protected <R> R applyEntityManagerInTransaction(final Function<? super EntityManager, ? extends R> function,
                                                    final boolean rollback) {
        return Persistence_TestUtils.applyEntityManagerInTransaction(entityManager(), function, rollback);
    }

    protected <R> R applyEntityManagerInTransactionAndRollback(
            final Function<? super EntityManager, ? extends R> function) {
        return applyEntityManagerInTransaction(function, true);
    }

    // --------------------------------------------------------------------------------------- entityManagerFactoryProxy
    private EntityManagerFactory entityManagerFactoryProxy() {
        var proxy = entityManagerFactoryProxy;
        if (proxy == null) {
            proxy = entityManagerFactoryProxy =
                    Lang_TestUtils.unclosable(EntityManagerFactory.class, entityManagerFactory);
        }
        return proxy;
    }

    // ---------------------------------------------------------------------------------------------- entityManagerProxy
    private EntityManager entityManagerProxy() {
        var proxy = entityManagerProxy;
        if (proxy == null) {
            proxy = entityManagerProxy = Lang_TestUtils.unclosable(EntityManager.class, entityManager);
        }
        return proxy;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String entityName;

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private EntityManager entityManager;

    // -----------------------------------------------------------------------------------------------------------------
    private EntityManagerFactory entityManagerFactoryProxy;

    private EntityManager entityManagerProxy;
}