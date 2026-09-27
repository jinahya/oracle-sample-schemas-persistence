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
import com.github.jinahya.persistence.test.util.__RandomizerUtils;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.ManagedType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.test._Persistence_Test_Producer.__TestPU;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * An abstract base class for tests which need a persistence context, against the
 * {@value _Persistence_Test_Producer#PERSISTENCE_UNIT_NAME} persistence unit.
 * <p>
 * The container is started by weld-testing, with {@link _Persistence_Test_Producer} as its only bean class, so a
 * subclass gets an entity manager on an in-memory database whose schema the provider generates.
 * <p>
 * Nothing here reaches the Oracle database; a test which needs the real data extends {@link _Persistence_IT} and is
 * named {@code *_IT} so that failsafe, not surefire, runs it.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see _Persistence_Test_Producer
 */
@Slf4j
@AddBeanClasses(_Persistence_Test_Producer.class)
@EnableAutoWeld
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // "abstract" classes should not have "public" constructors
})
public abstract class _Persistence_Test<T> extends __Test<T> {

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param targetClass the class of the entity under test.
     */
    protected _Persistence_Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Aborts, rather than fails, when the persistence unit does not know the {@link #targetClass}.
     *
     * @implNote A class which is not listed in the {@value _Persistence_Test_Producer#PERSISTENCE_UNIT_NAME}
     * persistence unit is not a managed type, and every test here would fail against it with an unknown-entity error
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
    @Test
    protected void persist__() {
        final var persisted = __RandomizerUtils.newRandomizerInstanceOf(targetClass)
                .map(r -> {
                    return __PersisterUtils.newPersisterInstanceOf(targetClass)
                            .map(p -> {
                                return applyEntityManagerInRolledBackTransaction(em -> {
                                    final var v = p.apply(em, r.get());
                                    em.flush();
                                    return v;
                                });
                            });
                });
        log.debug("persisted: {}", persisted);
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
    protected <R> R applyEntityManagerInRolledBackTransaction(
            final Function<? super EntityManager, ? extends R> function) {
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
    protected void acceptEntityManagerInRolledBackTransaction(final Consumer<? super EntityManager> consumer) {
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
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

    /**
     * The one factory of the container; {@link jakarta.enterprise.context.ApplicationScoped ApplicationScoped}.
     */
    @__TestPU
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private EntityManagerFactory entityManagerFactory;

    /**
     * For obtaining entity managers by hand, rather than at an injection point.
     */
    @__TestPU
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<EntityManager> entityManagers;
}
