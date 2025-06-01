package com.github.jinahya.oracle.sample.schemas;

import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;
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
    @DisplayName("persist an entity with randomized attributes")
    @Test
    protected void persistRandom() {
        acceptEntityManagerInTransactionAndRollback(em -> {
            __MappedEntity_Persister_Utils.newPersistedInstanceOf(entityClass, em).ifPresent(e -> {
                log.debug("persisted entity: {}", e);
                em.flush();
                final var _id_ = e._id_();
                log.debug("_id_: {}", _id_);
                final var found = em.find(entityClass, _id_);
                assertThat(found).isEqualTo(e);
            });
        });
    }

    /**
     * Finds an entity from the database.
     */
    @DisplayName("select an entity of random index")
    @Test
    protected void selectRandom() {
        __Persistence_Test_Utils.selectRandom(entityManager(), entityClass)
                .ifPresent(this::randomSelected__);
    }

    /**
     * Reports an instance of {@link #entityClass} found in the database.
     *
     * @param entity the instance of {@link #entityClass} found in the database
     */
    protected void randomSelected__(@Nonnull final ENTITY entity) {
        log.debug("selected entity: {}", entity);
        __Validation_Test_Utils.requireValid(entity);
    }

    // ----------------------------------------------------------------------------------------------- super.entityClass

    // --------------------------------------------------------------------------------------------------- super.idClass

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

    protected void acceptEntityManagerFactory(final Consumer<? super EntityManagerFactory> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManagerFactory(emf -> {
            consumer.accept(emf);
            return null;
        });
    }

    // --------------------------------------------------------------------------------------------------- entityManager
    protected EntityManager entityManager() {
        return entityManagerProxy();
    }

    protected <R> R applyEntityManager(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(entityManager());
    }

    protected void acceptEntityManager(final Consumer<? super EntityManager> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManager(em -> {
            consumer.accept(em);
            return null;
        });
    }

    protected <R> R applyEntityManagerInTransaction(final Function<? super EntityManager, ? extends R> function,
                                                    final boolean rollback) {
        return __Persistence_Test_Utils.applyEntityManagerInTransaction(entityManager(), function, rollback);
    }

    protected void acceptEntityManagerInTransaction(final Consumer<? super EntityManager> consumer,
                                                    final boolean rollback) {
        applyEntityManagerInTransaction(
                em -> {
                    consumer.accept(em);
                    return null;
                },
                rollback
        );
    }

    protected <R> R applyEntityManagerInTransactionAndRollback(
            final Function<? super EntityManager, ? extends R> function) {
        return applyEntityManagerInTransaction(function, true);
    }

    protected void acceptEntityManagerInTransactionAndRollback(
            final Consumer<? super EntityManager> consumer) {
        applyEntityManagerInTransactionAndRollback(rm -> {
            consumer.accept(rm);
            return null;
        });
    }

    // --------------------------------------------------------------------------------------- entityManagerFactoryProxy
    private EntityManagerFactory entityManagerFactoryProxy() {
        var proxy = entityManagerFactoryProxy;
        if (proxy == null) {
            proxy = entityManagerFactoryProxy =
                    __Lang_Test_Utils.unclosable(EntityManagerFactory.class, entityManagerFactory);
        }
        return proxy;
    }

    // ---------------------------------------------------------------------------------------------- entityManagerProxy
    private EntityManager entityManagerProxy() {
        var proxy = entityManagerProxy;
        if (proxy == null) {
            proxy = entityManagerProxy = __Lang_Test_Utils.unclosable(EntityManager.class, entityManager);
        }
        return proxy;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private EntityManager entityManager;

    // -----------------------------------------------------------------------------------------------------------------
    private EntityManagerFactory entityManagerFactoryProxy;

    private EntityManager entityManagerProxy;
}