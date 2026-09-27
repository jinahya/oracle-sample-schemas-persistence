package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
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

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.Getter;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.co._Persistence_IT_Producer.__ItPU;

/**
 * An abstract base class for integration tests which need the physical database, against the
 * {@value _Persistence_IT_Producer#PERSISTENCE_UNIT_NAME} persistence unit.
 * <p>
 * The container is started by weld-testing, with {@link _Persistence_IT_Producer} as its only bean class, so a subclass
 * gets an entity manager on the Oracle database the sample schemas were installed into, connecting as {@code dmlonly}.
 * <p>
 * These run under the {@code failsafe} profile only, and require the container from {@code docker-compose.yml} to be
 * up. Generating a schema is disabled for this unit: the tables are the ones Oracle's own installer created, and the
 * mappings are verified against them rather than the other way round.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see _Persistence_IT_Producer
 */
@AddBeanClasses(_Persistence_IT_Producer.class)
@EnableAutoWeld
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // "abstract" classes should not have "public" constructors
})
abstract class _Persistence_IT<T> extends __Test<T> {

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param persistenceClass the class of the entity under test.
     */
    _Persistence_IT(final Class<T> persistenceClass) {
        super(persistenceClass);
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
    void selectRandom() {
        acceptEntityManagerInRolledBackTransaction(
                em -> __Persistence___Utils.selectRandom(em, targetClass).ifPresent(this::randomSelected)
        );
    }

    /**
     * Called with the instance {@link #selectRandom()} selected, while it is still managed.
     *
     * @param instance the randomly selected instance.
     * @implSpec This class does nothing with it; a subclass overrides this method to assert whatever its entity
     * promises of a row which is actually in the database.
     */
    void randomSelected(final T instance) {
        // empty; overridden by a subclass which has something to assert
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Applies the entity manager, of this instance, to the specified function, within a transaction which is always
     * rolled back.
     *
     * @param function the function to apply the entity manager to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @implNote The rollback is what keeps one test from being visible to the next, and, here, what keeps the sample
     * data as the installer left it.
     */
    <R> R applyEntityManagerInRolledBackTransaction(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        final var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            return function.apply(entityManager);
        } finally {
            transaction.rollback();
        }
    }

    /**
     * Accepts the entity manager, of this instance, to the specified consumer, within a transaction which is always
     * rolled back.
     *
     * @param consumer the consumer to accept the entity manager.
     * @see #applyEntityManagerInRolledBackTransaction(Function)
     */
    void acceptEntityManagerInRolledBackTransaction(final Consumer<? super EntityManager> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        applyEntityManagerInRolledBackTransaction(em -> {
            consumer.accept(em);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A new entity manager, for each test instance; {@link jakarta.enterprise.context.Dependent Dependent}.
     */
    @__ItPU
    @Inject
    @Getter(AccessLevel.PACKAGE)
    private EntityManager entityManager;

    /**
     * The one factory of the container; {@link jakarta.enterprise.context.ApplicationScoped ApplicationScoped}.
     */
    @__ItPU
    @Inject
    @Getter(AccessLevel.PACKAGE)
    private EntityManagerFactory entityManagerFactory;

    /**
     * For obtaining entity managers by hand, rather than at an injection point.
     */
    @__ItPU
    @Inject
    @Getter(AccessLevel.PACKAGE)
    private Instance<EntityManager> entityManagers;
}
