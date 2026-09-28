package com.github.jinahya.oracle.sample.schemas.persistence.test;

/*-
 * #%L
 * test-base
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

import com.github.jinahya.persistence.test.util.__PersisterUtils;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.ManagedType;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.test.__Persistence_IT_Producer.__ItPU;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * An abstract base class for integration tests which need the physical database, against the
 * persistence unit.
 * <p>
 * The container is started by weld-testing, with {@link __Persistence_IT_Producer} as its only bean class, so a subclass
 * gets an entity manager on the Oracle database the sample schemas were installed into, connecting as {@code dmlonly}.
 * <p>
 * These run under the {@code failsafe} profile only, and require the container from {@code docker-compose.yml} to be
 * up. Generating a schema is disabled for this unit: the tables are the ones Oracle's own installer created, and the
 * mappings are verified against them rather than the other way round.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see __Persistence_IT_Producer
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // "abstract" classes should not have "public" constructors
})
public abstract class __Persistence_IT<T> extends __Test<T> {

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param targetClass the class of the entity under test.
     */
    protected __Persistence_IT(final Class<T> targetClass) {
        super(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Aborts, rather than fails, when the persistence unit does not know the {@link #targetClass}.
     *
     * @implNote A class which is commented out of the persistence unit is not a managed type, and every test here would fail against it with an unknown-entity error
     * which says nothing about the mapping. Aborting keeps the class reported as skipped, so listing it in
     * {@code persistence.xml} is all it takes to bring the test back.
     */
    @BeforeEach
    protected void assumeTargetClassIsManaged() {
        assumeTrue(
                entityManagerFactory.getMetamodel().getManagedTypes().stream()
                        .map(ManagedType::getJavaType)
                        .anyMatch(c -> c == targetClass),
                () -> targetClass + " is not a managed type of the persistence unit"
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Selects a random instance of {@link #targetClass}, from the installed schema, and hands it to
     * {@link #randomSelected(Object)}.
     *
     * @implNote The selection and the callback both run inside a transaction which is rolled back, so the instance
     * handed over is still managed -- a lazy association read in {@link #randomSelected(Object)} resolves rather than
     * throwing -- and nothing the callback does reaches the sample data.
     * @implSpec Selecting from an empty table is not a failure: the table's content is the installer's, not this
     * test's, so {@link #randomSelected(Object)} is simply not invoked.
     */
    @Test
    protected void selectRandom() {
        applyEntityManagerInTransactionAndRollback(em -> {
            __Persistence_TestUtils.selectRandom(em, targetClass).ifPresent(this::randomSelected);
            return null;
        });
    }

    /**
     * Called with the instance {@link #selectRandom()} selected, while it is still managed.
     *
     * @param instance the randomly selected instance.
     * @implSpec This class does nothing with it; a subclass overrides this method to assert whatever its entity
     * promises of a row which is actually in the database.
     */
    protected void randomSelected(final T instance) {
        // empty; overridden by a subclass which has something to assert
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Persists a new randomized instance of {@link #targetClass}, and applies it, along with the entity manager, to
     * the specified function, within a transaction which is always rolled back.
     *
     * @param function the function to apply the entity manager and the persisted instance to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException when {@link #targetClass} has no usable randomizer or no usable persister; see
     *                                  {@link __PersisterUtils#newPersistedInstanceOf(EntityManager, Class)}.
     * @apiNote Unlike {@link #selectRandom()}, which reads a row the installer put there, this one writes a row of its
     * own -- against the physical schema, with whatever constraints and triggers it carries, which is a good part of
     * what an integration test is for. The rollback is what keeps it from being a change to the sample data.
     * @implNote The instance is persisted inside the same transaction the {@code function} runs in, so it is still
     * managed when the function sees it, and it is gone when the transaction rolls back.
     */
    protected <R> R applyNewPersistedTargetInstanceAndRollback(
            final BiFunction<? super EntityManager, ? super T, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerInTransactionAndRollback(em -> {
            final var persisted = __PersisterUtils.newPersistedInstanceOf(em, targetClass);
            return function.apply(em, persisted);
        });
    }

    /**
     * Persists a new randomized instance of {@link #targetClass}, and applies it, along with the entity manager, to
     * the specified function, within a transaction which is committed when the function returns, and rolled back when
     * it throws.
     *
     * @param function the function to apply the entity manager and the persisted instance to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException when {@link #targetClass} has no usable randomizer or no usable persister; see
     *                                  {@link __PersisterUtils#newPersistedInstanceOf(EntityManager, Class)}.
     * @apiNote The committed row stays in the installed sample data, which the next run of this same test will not
     * undo. Use it only for a row the test cannot do without, and delete it afterwards; otherwise take the rolled-back
     * one.
     * @implNote Randomized values are the ones the persister produced, so a committed row is also a row nothing else
     * in the schema expects; a foreign key pointing at it later is the caller's problem to clean up.
     */
    protected <R> R applyNewPersistedTargetInstanceAndCommit(
            final BiFunction<? super EntityManager, ? super T, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerInTransactionAndCommit(em -> {
            final var persisted = __PersisterUtils.newPersistedInstanceOf(em, targetClass);
            return function.apply(em, persisted);
        });
    }

    /**
     * Applies the entity manager, of this instance, to the specified function, within a transaction which is always
     * rolled back.
     *
     * @param function the function to apply the entity manager to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @implNote The rollback is what keeps one test from being visible to the next, and, here, what keeps the sample
     * data as the installer left it.
     * @see __Persistence_TestUtils#applyInTransactionAndRollback(EntityManager, Function)
     */
    protected <R> R applyEntityManagerInTransactionAndRollback(
            final Function<? super EntityManager, ? extends R> function) {
        return __Persistence_TestUtils.applyInTransactionAndRollback(entityManager, function);
    }

    /**
     * Applies the entity manager, of this instance, to the specified function, within a transaction which is committed
     * when the function returns, and rolled back when it throws.
     *
     * @param function the function to apply the entity manager to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @implNote What this writes reaches the installed sample data and stays there, which no test here wants by accident.
     * Use it only for a row the test cannot do without, and clean it up; otherwise take the rolled-back one.
     * @see __Persistence_TestUtils#applyInTransactionAndCommit(EntityManager, Function)
     */
    protected <R> R applyEntityManagerInTransactionAndCommit(
            final Function<? super EntityManager, ? extends R> function) {
        return __Persistence_TestUtils.applyInTransactionAndCommit(entityManager, function);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A new entity manager, for each test instance; {@link jakarta.enterprise.context.Dependent Dependent}.
     */
    @__ItPU
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

    /**
     * The one factory of the container; {@link jakarta.enterprise.context.ApplicationScoped ApplicationScoped}.
     */
    @__ItPU
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private EntityManagerFactory entityManagerFactory;

    /**
     * For obtaining entity managers by hand, rather than at an injection point.
     */
    @__ItPU
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<EntityManager> entityManagers;
}
