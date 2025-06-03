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
import java.util.function.LongConsumer;
import java.util.function.LongFunction;

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

    /**
     * Returns new persisted instance of {@link #entityClass}.
     *
     * @return new persisted instance of {@link #entityClass}.
     * @see __MappedEntity_Persister_Utils#newPersistedInstanceOf(Class, EntityManager)
     */
    protected ENTITY newPersistedEntityInstance() {
        return __MappedEntity_Persister_Utils.newPersistedInstanceOf(entityClass, entityManager()).orElseThrow();
    }

//    /**
//     * Returns new persisted instance of {@link #entityClass}.
//     *
//     * @return new persisted instance of {@link #entityClass}.
//     * @see __MappedEntity_Persister_Utils#newPersistedInstanceOf(Class, EntityManager)
//     */
//    protected Optional<ENTITY> selectRandomEntityInstance() {
//        return applyEntityManagerInTransactionAndRollback(em -> {
//            return __Persistence_Test_Utils.selectRandom(em, entityClass);
//        });
//    }

    protected <R> R applyEntityCountAndRandomIndex(
            final Function<? super EntityManager, ? extends LongFunction<? extends LongFunction<? extends R>>> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerInTransactionAndRollback(em -> {
            return __Persistence_Test_Utils.applyCountAndRandomIndex(
                    em,
                    entityClass,
                    c -> i -> {
                        return function.apply(em).apply(c).apply(i);
                    }
            );
        });
    }

    protected void acceptEntityCountAndRandomIndex(
            final Function<? super EntityManager, ? extends LongFunction<? extends LongConsumer>> function) {
        applyEntityCountAndRandomIndex(em -> c -> i -> {
            function.apply(em).apply(c).accept(i);
            return null;
        });
    }

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

    private void acceptEntityManagerFactory(final Consumer<? super EntityManagerFactory> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManagerFactory(emf -> {
            consumer.accept(emf);
            return null;
        });
    }

    // --------------------------------------------------------------------------------------------------- entityManager
    private EntityManager entityManager() {
        return entityManagerProxy();
    }

    //    protected
    private <R> R applyEntityManager(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(entityManager());
    }

    //    protected
    private void acceptEntityManager(final Consumer<? super EntityManager> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManager(em -> {
            consumer.accept(em);
            return null;
        });
    }

    //    protected
    private <R> R applyEntityManagerInTransaction(final Function<? super EntityManager, ? extends R> function,
                                                  final boolean rollback) {
        return __Persistence_Test_Utils.applyEntityManagerInTransaction(entityManager(), function, rollback);
    }

    //    protected
    private void acceptEntityManagerInTransaction(final Consumer<? super EntityManager> consumer,
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