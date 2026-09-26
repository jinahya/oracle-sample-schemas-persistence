package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._TestPersistenceProducer.__TestPU;

/**
 * An abstract base class for tests which need a persistence context, against the
 * {@value _TestPersistenceProducer#PERSISTENCE_UNIT_NAME} persistence unit.
 * <p>
 * The container is started by weld-testing, with {@link _TestPersistenceProducer} as its only bean class, so a subclass
 * gets an entity manager on an in-memory database whose schema the provider generates.
 * <p>
 * Nothing here reaches the Oracle database; a test which needs the real data extends {@link _Persistence_IT} and is
 * named {@code *_IT} so that failsafe, not surefire, runs it.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see _TestPersistenceProducer
 */
@AddBeanClasses(_TestPersistenceProducer.class)
@EnableAutoWeld
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // "abstract" classes should not have "public" constructors
})
abstract class _Persistence_Test<T> extends __Test<T> {

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param persistenceClass the class of the entity under test.
     */
    _Persistence_Test(final Class<T> persistenceClass) {
        super(persistenceClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Applies the entity manager, of this instance, to the specified function, within a transaction which is always
     * rolled back.
     *
     * @param function the function to apply the entity manager to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @implNote The rollback is what keeps one test from being visible to the next, on a schema which is recreated per
     * container anyway.
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
    @__TestPU
    @Inject
    @Getter(AccessLevel.PACKAGE)
    private EntityManager entityManager;

    /**
     * The one factory of the container; {@link jakarta.enterprise.context.ApplicationScoped ApplicationScoped}.
     */
    @__TestPU
    @Inject
    @Getter(AccessLevel.PACKAGE)
    private EntityManagerFactory entityManagerFactory;

    /**
     * For obtaining entity managers by hand, rather than at an injection point.
     */
    @__TestPU
    @Inject
    @Getter(AccessLevel.PACKAGE)
    private Instance<EntityManager> entityManagers;
}
