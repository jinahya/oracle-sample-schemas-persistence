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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.ManagedType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.test.__Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * An abstract base class for tests which need a persistence context, against the persistence unit.
 * <p>
 * The container is started by weld-testing, with {@link __Persistence_Test_Producer} as its only bean class, so a
 * subclass gets an entity manager on an in-memory database whose schema the provider generates.
 * <p>
 * Nothing here reaches the Oracle database; a test which needs the real data extends {@link __Persistence_IT} and is
 * named {@code *_IT} so that failsafe, not surefire, runs it.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see __Persistence_Test_Producer
 */
@Slf4j
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // "abstract" classes should not have "public" constructors
})
public abstract class __Persistence_Test<T> extends __Test<T> {

    /**
     * The prefix of the constants {@link #_SameValue_ATTRIBUTE_NAME_()} looks up. The value is {@value}.
     */
    private static final String ATTRIBUTE_NAME_CONSTANT_PREFIX = "ATTRIBUTE_NAME_";

    /**
     * Returns the {@code UPPER_SNAKE_CASE} form of the specified attribute name.
     *
     * @param attributeName the attribute name to convert; {@code countryIsoCode}, say.
     * @return the converted name; {@code COUNTRY_ISO_CODE} for the example above.
     * @implNote An underscore goes wherever a lower-case letter or a digit is followed by an upper-case one, which is
     * the boundary the constants in this project are spelled with.
     */
    private static String toUpperSnakeCase(final String attributeName) {
        return attributeName.replaceAll("(?<=[a-z0-9])(?=[A-Z])", "_").toUpperCase(Locale.ROOT);
    }

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param targetClass the class of the entity under test.
     */
    protected __Persistence_Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verifies that every {@code ATTRIBUTE_NAME_*} constant of {@link #targetClass} carries the name the provider
     * actually manages that attribute by.
     *
     * @implNote The lookup runs from the attribute to the constant, never the other way round: an attribute with no
     * matching constant is skipped, because whether every attribute ought to have one is a different question than
     * whether the constants which do exist are right. The constant is read reflectively rather than compared against a
     * hand-written list, so an entity gains this check by declaring the constant and nothing else.
     * <p>
     * Absence is the only thing this forgives. A field which is there under the constant's name has to be a constant --
     * {@code static final String} -- and is a failure when it is not, rather than something to skip past: skipping it
     * would let a field named like a constant, but not usable as one, pass for a checked attribute name.
     * <p>
     * The name compared against is {@link Attribute#getName()} -- what the provider resolved from the mapping -- so
     * this catches a constant left behind by a renamed field, which the compiler cannot.
     */
    @DisplayName("every ATTRIBUTE_NAME_* constant matches the managed attribute name")
    @Test
    protected void _SameValue_ATTRIBUTE_NAME_() {
        final var managedType = entityManagerFactory.getMetamodel().managedType(targetClass);
        for (final var attribute : managedType.getAttributes()) {
            final var attributeName = attribute.getName();
            final var constantName = ATTRIBUTE_NAME_CONSTANT_PREFIX + toUpperSnakeCase(attributeName);
            final Field constant;
            try {
                constant = targetClass.getDeclaredField(constantName);
            } catch (final NoSuchFieldException nsfe) {
                continue; // existence is not this test's concern
            }
            final var modifiers = constant.getModifiers();
            assertThat(Modifier.isStatic(modifiers))
                    .as("%s.%s is static", targetClass.getSimpleName(), constantName)
                    .isTrue();
            assertThat(Modifier.isFinal(modifiers))
                    .as("%s.%s is final", targetClass.getSimpleName(), constantName)
                    .isTrue();
            assertThat(constant.getType())
                    .as("the type of %s.%s", targetClass.getSimpleName(), constantName)
                    .isSameAs(String.class);
            constant.setAccessible(true);
            final Object value;
            try {
                value = constant.get(null);
            } catch (final IllegalAccessException iae) {
                throw new AssertionError("failed to read " + targetClass.getSimpleName() + '.' + constantName, iae);
            }
            assertThat(value)
                    .as("%s.%s, for the managed attribute %s", targetClass.getSimpleName(), constantName,
                        attributeName)
                    .isEqualTo(attributeName);
        }
    }

    /**
     * Aborts, rather than fails, when the persistence unit does not know the {@link #targetClass}.
     *
     * @implNote A class which is not listed in the persistence unit is not a managed type, and every test here would
     * fail against it with an unknown-entity error which says nothing about the mapping. Aborting keeps the class
     * reported as skipped, so listing it in {@code persistence.xml} is all it takes to bring the test back.
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
     * Verifies that a randomized instance of {@link #targetClass} can be persisted and flushed.
     *
     * @implNote The work happens in a transaction which is always rolled back, so the row never survives the test.
     * Absence is forgiven at both steps: a {@link #targetClass} with no registered randomizer, or none with a
     * registered persister, leaves the chain empty and the test passes without having persisted anything.
     */
    @Test
    protected void _persist_RandomizedInstance() {
        final var persisted = ObjectRandomizerUtils.newRandomizerInstanceOf(targetClass)
                .map(r -> {
                    return EntityPersisterUtils.newPersisterInstanceOf(targetClass)
                            .map(p -> {
                                return applyEntityManagerInTransactionAndRollback(em -> {
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
     * Persists a new randomized instance of {@link #targetClass}, and applies it, along with the entity manager, to the
     * specified function, within a transaction which is always rolled back.
     *
     * @param function the function to apply the entity manager and the persisted instance to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException when {@link #targetClass} has no usable randomizer or no usable persister; see
     *                                  {@link EntityPersisterUtils#newPersistedInstanceOf(EntityManager, Class)}.
     * @implNote The instance is persisted inside the same transaction the {@code function} runs in, so it is still
     * managed when the function sees it, and it is gone when the transaction rolls back.
     */
    protected <R> R applyNewPersistedTargetInstanceAndRollback(
            final BiFunction<? super EntityManager, ? super T, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerInTransactionAndRollback(em -> {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(em, targetClass);
            return function.apply(em, persisted);
        });
    }

    /**
     * Persists a new randomized instance of {@link #targetClass}, and applies it, along with the entity manager, to the
     * specified function, within a transaction which is committed when the function returns, and rolled back when it
     * throws.
     *
     * @param function the function to apply the entity manager and the persisted instance to.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     * @throws IllegalArgumentException when {@link #targetClass} has no usable randomizer or no usable persister; see
     *                                  {@link EntityPersisterUtils#newPersistedInstanceOf(EntityManager, Class)}.
     * @implNote The persisted row outlives the call, on a database which is thrown away with the container; a test
     * which only needs the instance for the length of the {@code function} wants the rolled-back one.
     */
    protected <R> R applyNewPersistedTargetInstanceAndCommit(
            final BiFunction<? super EntityManager, ? super T, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManagerInTransactionAndCommit(em -> {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(em, targetClass);
            return function.apply(em, persisted);
        });
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
     * @implNote What this writes survives the transaction, but not the container: the schema is generated per container
     * and the in-memory database goes with it. Still the exception -- a test whose writes nothing later has to read
     * wants the rolled-back one.
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
