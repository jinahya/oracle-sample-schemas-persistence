package com.github.jinahya.oracle.sample.persistence.shared;

/*-
 * #%L
 * oracle-sample-schemas-persistence
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

import com.github.jinahya.persistence.test.util.JinahyaPersistenceTestUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.persistence.shared.___Persistence_TestUtils.selectRandomEntityInstance;
import static com.github.jinahya.persistence.test.util.EntityPersisterUtils.newPersistedInstanceOf;
import static com.github.jinahya.persistence.test.util.JinahyaEntityManagerTestUtils.selectRandomEntityId;
import static com.github.jinahya.persistence.test.util.JinahyaEntityManagerTestUtils.selectRandomEntityReference;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerUtils.applyInTransactionAndRollback;

/**
 * An abstract base class for integration tests which need the physical database, against a module's
 * integration-test persistence unit.
 * <p>
 * The factory is the one of the unit named by the constructor, which each module passes from its own
 * {@code __PersistenceIT_Utils}, opened once for the whole run, through
 * {@link JinahyaPersistenceTestUtils#applyCachedEntityManagerFactory(String, Function)}; each test
 * gets an entity manager of its own on the Oracle database the sample schemas were installed into,
 * connecting as {@code dmlonly}.
 * <p>
 * These run under the {@code failsafe} profile only, and require the container from
 * {@code compose.yaml} to be up. Generating a schema is disabled for this unit: the tables are the
 * ones Oracle's own installer created, and the mappings are verified against them rather than the
 * other way round.
 *
 * @param <T> the type of the entity under test.
 * @param <U> the type of the identifier of the entity, or of its id class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
public abstract class _Entity_PersistenceIT<T, U>
        extends __Test<T> {

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param targetClass the class of the entity under test.
     * @param idClass             the class of the identifier of the entity, or its id class.
     * @param persistenceUnitName the name of the module's integration-test persistence unit.
     */
    protected _Entity_PersistenceIT(final Class<T> targetClass, final Class<U> idClass,
                                     final String persistenceUnitName) {
        super(targetClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
        this.persistenceUnitName =
                Objects.requireNonNull(persistenceUnitName, "persistenceUnitName is null");
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Applies the specified function to the factory of the {@link #persistenceUnitName} unit,
     * opening it on the first call.
     */
    private <R> R applyEntityManagerFactory(
            final Function<? super EntityManagerFactory, ? extends R> function) {
        return JinahyaPersistenceTestUtils.applyCachedEntityManagerFactory(persistenceUnitName,
                                                                           function);
    }

    /**
     * Applies the specified function to the factory of the {@link #persistenceUnitName} unit and
     * a new entity manager of it, which is closed when the function returns.
     */
    private <R> R applyEntityManager_(
            final Function<
                    ? super EntityManagerFactory,
                    ? extends Function<
                            ? super EntityManager,
                            ? extends R
                            >
                    >
                    function) {
        return applyEntityManagerFactory(
                emf -> applyEntityManager(
                        emf,
                        em -> function.apply(emf).apply(em)
                )
        );
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * Selects a reference to a randomly chosen instance of {@link #targetClass}, with an entity
     * manager of its own of the {@link #persistenceUnitName} unit,
     * through {@code JinahyaEntityManagerTestUtils.selectRandomEntityReference}.
     *
     * @return an optional of the reference selected; {@code empty} when there is no instance.
     * @apiNote The entity manager is closed when this method returns, so the reference is
     *         detached; only its identifier is safe to read, for its state may never have been
     *         loaded.
     */
    protected Optional<T> selectRandomEntityReference_() {
        return applyEntityManager_(
                emf -> em -> selectRandomEntityReference(em, targetClass)
        );
    }

    /**
     * Selects the identifier of a randomly chosen instance of {@link #targetClass}, as an instance
     * of {@link #idClass}, with an entity manager of its own of the
     * {@link #persistenceUnitName} unit, through
     * {@code JinahyaEntityManagerTestUtils.selectRandomEntityId}.
     *
     * @return an optional of the identifier selected; {@code empty} when there is no instance.
     */
    protected Optional<U> selectRandomEntityId_() {
        return applyEntityManager_(
                emf -> em -> selectRandomEntityId(em, targetClass, idClass)
        );
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Persists a new, randomized instance of {@link #targetClass}, through its persister, and
     * flushes it.
     *
     * @implNote The flush is what makes the {@code INSERT} reach the database at all: a
     *         provider may defer it until the flush or the commit -- one whose identifier is
     *         assigned rather than generated, or generated by the {@code INSERT} itself -- and the
     *         transaction is rolled back, so without it nothing would be written, and nothing
     *         tested.
     */
    @Test
    protected void persistRandomizedEntityInstance__() {
        applyEntityManagerFactory(emf -> applyEntityManager(
                emf,
                m -> applyInTransactionAndRollback(m, em -> {
                    final var persisted = newPersistedInstanceOf(em, targetClass);
//                    em.flush();
                    randomizedEntityInstancePersisted__(emf, em, persisted);
                    return null;
                })
        ));
    }

    protected void randomizedEntityInstancePersisted__(
            final EntityManagerFactory entityManagerFactory, final EntityManager entityManager,
            final T entityInstance) {
        // empty
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Selects a random instance of {@link #targetClass}, from the installed schema, and hands it to
     * {@link #randomEntityInstanceSelected__(EntityManagerFactory, EntityManager, Object)}.
     *
     * @implNote The selection and the callback both run inside a transaction which is
     *         rolled back, so the instance handed over is still managed -- a lazy association
     *         read in
     *         {@link #randomEntityInstanceSelected__(EntityManagerFactory, EntityManager, Object)}
     *         resolves rather than throwing -- and nothing the callback does reaches the sample
     *         data.
     * @implSpec Selecting from an empty table is not a failure: the table's content is the
     *         installer's, not this test's, so
     *         {@link #randomEntityInstanceSelected__(EntityManagerFactory, EntityManager, Object)}
     *         is simply not invoked.
     */
    @Test
    protected void selectRandomEntityInstance__() {
        applyEntityManagerFactory(
                emf -> selectRandomEntityInstance(
                        emf,
                        targetClass,
                        (em, v) -> {
                            randomEntityInstanceSelected__(emf, em, v);
                            return null;
                        }
                )
        );
    }

    /**
     * Called with the instance {@link #selectRandomEntityInstance__()} selected, while it is still
     * managed.
     *
     * @param entityManagerFactory the factory of the {@code entityManager}.
     * @param entityManager        the entity manager which selected the instance.
     * @param entityInstance       the randomly selected instance.
     * @implSpec This class does nothing with it; a subclass overrides this method to assert
     *         whatever its entity promises of a row which is actually in the database.
     */
    protected void randomEntityInstanceSelected__(final EntityManagerFactory entityManagerFactory,
                                                  final EntityManager entityManager,
                                                  final T entityInstance) {
        // empty; overridden by a subclass which has something to assert
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The class of the identifier of {@link #targetClass}; its id class, or the type of its
     * {@code @Id} or {@code @EmbeddedId} attribute.
     */
    protected final Class<U> idClass;

    /**
     * The name of the module's integration-test persistence unit, whose factory this test uses.
     */
    protected final String persistenceUnitName;
}
