package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.co._Entity_Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * An abstract base class for tests which need a persistence context, against a module's unit-test persistence unit.
 * <p>
 * The container is started by weld-testing, with {@link _Entity_Persistence_Test_Producer} as its bean class; a test
 * then gets an entity manager on an in-memory database whose schema the provider generates.
 * <p>
 * Nothing here reaches the Oracle database; a test which needs the real data extends {@link _Entity_Persistence_IT} and
 * is named {@code *_IT} so that failsafe, not surefire, runs it.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see _Entity_Persistence_Test_Producer
 */
@AddBeanClasses(_Entity_Persistence_Test_Producer.class)
@EnableAutoWeld
@Slf4j
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
abstract class _Entity_Persistence_Test<T> extends __Test<T> {

    /**
     * The prefix of the constants {@link #_SameValue_ATTRIBUTE_NAME_()} checks. The value is {@value}.
     */
    private static final String ATTRIBUTE_NAME_CONSTANT_PREFIX = "ATTRIBUTE_NAME_";

    /**
     * The prefix of the constants {@link #_Resolvable_ATTRIBUTE_PATH_()} checks. The value is {@value}.
     */
    private static final String ATTRIBUTE_PATH_CONSTANT_PREFIX = "ATTRIBUTE_PATH_";

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
    protected _Entity_Persistence_Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns every {@code static final String} field, declared by {@link #targetClass} or inherited by it, whose name
     * starts with the specified prefix, with its value.
     */
    private Map<String, String> constantsPrefixed(final String prefix) {
        final var constants = new TreeMap<String, String>();
        for (Class<?> c = targetClass; c != null; c = c.getSuperclass()) {
            for (final var field : c.getDeclaredFields()) {
                if (!field.getName().startsWith(prefix) || constants.containsKey(field.getName())) {
                    continue;
                }
                final var modifiers = field.getModifiers();
                assertThat(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)
                           && field.getType() == String.class)
                        .as("%s.%s is a static final String", c.getSimpleName(), field.getName())
                        .isTrue();
                field.setAccessible(true);
                try {
                    constants.put(field.getName(), (String) field.get(null));
                } catch (final IllegalAccessException iae) {
                    throw new AssertionError("failed to read " + c.getSimpleName() + '.' + field.getName(), iae);
                }
            }
        }
        return constants;
    }

    /**
     * Verifies the {@code ATTRIBUTE_NAME_*} constants of {@link #targetClass} both ways: every managed attribute whose
     * constant exists finds that constant holding the attribute's name, and every such constant names a managed
     * attribute of the type.
     *
     * @implNote The first direction runs from the attribute to the constant, so an attribute with no constant is
     * skipped; whether every attribute ought to have one is a different question. The second runs from the constant
     * to the attribute, so a constant holding a name the provider does not manage the type by -- one left behind by
     * a renamed field, or one naming an attribute of some other class -- fails. A path into an embedded attribute is
     * not an attribute name; it is declared as an {@code ATTRIBUTE_PATH_*} constant and checked by
     * {@link #_Resolvable_ATTRIBUTE_PATH_()}.
     */
    @DisplayName("every ATTRIBUTE_NAME_* constant names a managed attribute, and vice versa")
    @Test
    protected void _SameValue_ATTRIBUTE_NAME_() {
        final var managedType = entityManagerFactory.getMetamodel().managedType(targetClass);
        final var constants = constantsPrefixed(ATTRIBUTE_NAME_CONSTANT_PREFIX);
        for (final var attribute : managedType.getAttributes()) {
            final var attributeName = attribute.getName();
            final var constantName = ATTRIBUTE_NAME_CONSTANT_PREFIX + toUpperSnakeCase(attributeName);
            if (!constants.containsKey(constantName)) {
                continue; // existence is not this direction's concern
            }
            assertThat(constants.get(constantName))
                    .as("%s.%s, for the managed attribute %s", targetClass.getSimpleName(), constantName,
                        attributeName)
                    .isEqualTo(attributeName);
        }
        constants.forEach((constantName, value) -> assertThatCode(() -> managedType.getAttribute(value))
                .as("%s.%s = \"%s\" names a managed attribute of %s", targetClass.getSimpleName(), constantName, value,
                    managedType.getJavaType().getSimpleName())
                .doesNotThrowAnyException());
    }

    /**
     * Verifies that every {@code ATTRIBUTE_PATH_*} constant of {@link #targetClass} holds a dotted path which resolves
     * through the metamodel, segment by segment, to an attribute whose name is the path's last segment.
     *
     * @see ___Persistence_TestUtils#resolveAttributePath(ManagedType, String)
     */
    @DisplayName("every ATTRIBUTE_PATH_* constant resolves through the metamodel")
    @Test
    protected void _Resolvable_ATTRIBUTE_PATH_() {
        final var managedType = entityManagerFactory.getMetamodel().managedType(targetClass);
        constantsPrefixed(ATTRIBUTE_PATH_CONSTANT_PREFIX).forEach((constantName, path) -> {
            assertThat(path)
                    .as("%s.%s is a dotted path", targetClass.getSimpleName(), constantName)
                    .contains(".");
            final var resolved = ___Persistence_TestUtils.resolveAttributePath(managedType, path);
            assertThat(resolved.getName())
                    .as("%s.%s = \"%s\" resolves to its last segment", targetClass.getSimpleName(), constantName,
                        path)
                    .isEqualTo(path.substring(path.lastIndexOf('.') + 1));
        });
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
     * Verifies {@link EntityManager#find(Class, Object) find} against a newly persisted instance of
     * {@link #targetClass}.
     */
    @Nested
    class Find_Test {

        /**
         * Aborts, rather than fails, when {@link #targetClass} cannot be randomized, cannot be persisted, or does not
         * carry the shape of identifier these tests address.
         *
         * @implNote Not every entity has a randomizer and a persister -- a view has no use for one -- and
         * {@link #applyNewPersistedEntityInstanceAndRollback(BiFunction)} throws for those, with a message about the
         * missing class rather than about {@code find}. This is the same forgiveness
         * {@link #_persist_RandomizedInstance()} shows, said out loud so the tests are reported as skipped.
         * <p>
         * The identifier has to be a single basic attribute. An {@link jakarta.persistence.IdClass IdClass} is left out
         * because assembling one generically is more than this base class knows how to do. An
         * {@link jakarta.persistence.EmbeddedId EmbeddedId} is left out because an entity may derive a component of it
         * through {@link jakarta.persistence.MapsId @MapsId} -- {@code CO}'s {@code OrderItem} does -- and the two
         * providers disagree about that component: Hibernate copies the association's identifier into the embedded one
         * as it flushes, EclipseLink writes the column but leaves the component of the managed instance {@code null},
         * so there is no identifier to hand {@code find} which is right under both. The row itself is written correctly
         * either way, and {@link #_persist_RandomizedInstance()} is what covers that.
         */
        @BeforeEach
        void assumeTargetClassIsPersistableAndAddressable() {
            assumeTrue(
                    ObjectRandomizerUtils.newRandomizerInstanceOf(targetClass).isPresent(),
                    () -> targetClass + " has no usable randomizer"
            );
            assumeTrue(
                    EntityPersisterUtils.newPersisterInstanceOf(targetClass).isPresent(),
                    () -> targetClass + " has no usable persister"
            );
            final var entityType = entityManagerFactory.getMetamodel().entity(targetClass);
            assumeTrue(
                    entityType.hasSingleIdAttribute(),
                    () -> targetClass + " is mapped with an id class"
            );
            assumeTrue(
                    entityType.getIdType().getPersistenceType() == Type.PersistenceType.BASIC,
                    () -> targetClass + " is mapped with an embedded identifier"
            );
        }

        /**
         * Returns the identifier of the specified instance, read from the field the metamodel says maps it.
         *
         * @param instance the instance whose identifier to read.
         * @return the identifier of the {@code instance}.
         * @implNote {@link jakarta.persistence.PersistenceUnitUtil#getIdentifier(Object)} is the API for this, and is
         * not what this uses. EclipseLink answers it by instantiating the identifier class through
         * {@link Class#getConstructor(Class[])}, which sees public constructors only, and the identifier classes here
         * declare the no-arg constructor {@code protected} -- which is what the specification asks of an embeddable.
         * Reading the mapped field instead keeps this test off that difference, and off the question of which provider
         * is right.
         * @implSpec An entity which maps its identifier by property rather than by field aborts rather than fails; none
         * here does.
         */
        private Object identifierOf(final T instance) {
            final var entityType = entityManagerFactory.getMetamodel().entity(targetClass);
            final var member = entityType.getId(entityType.getIdType().getJavaType()).getJavaMember();
            assumeTrue(
                    member instanceof Field,
                    () -> targetClass + " maps its identifier by property, not by field"
            );
            final var field = (Field) member;
            field.setAccessible(true);
            try {
                return field.get(instance);
            } catch (final IllegalAccessException iae) {
                throw new AssertionError("failed to read " + field, iae);
            }
        }

        /**
         * Verifies that {@code find} answers the very instance just persisted, while it is still managed.
         *
         * @implNote The flush is what makes the identifier readable at all: Hibernate writes a row whose identifier
         * comes from an {@code IDENTITY} column as {@code persist} is called, and EclipseLink does not, so without it
         * the identifier is still {@code null} under one provider and not the other.
         */
        @DisplayName("find returns the very instance just persisted")
        @Test
        void __() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                em.flush();
                final var found = em.find(targetClass, identifierOf(v));
                assertThat(found)
                        .as("the %s found by its identifier", targetClass.getSimpleName())
                        .isSameAs(v);
                return found;
            });
        }

        /**
         * Verifies that {@code find} still reaches the persisted row once the persistence context no longer holds it.
         *
         * @implNote What comes back is compared by identifier, not with {@link Object#equals(Object)}. An entity here
         * is free to base its equality on an association -- {@code Inventory} compares its store and its product -- and
         * the reloaded instance holds proxies for those, which do not compare equal to the instances the randomizer
         * built. That is a property of the entity, not a fault in {@code find}, and asserting the identifier is what
         * this test can promise of every entity alike.
         * @implSpec {@link EntityManager#clear() clear} drops what is still pending, so the flush above it is not
         * decoration: without it the row is never written and {@code find} answers {@code null}.
         */
        @DisplayName("find reaches the persisted row once the context is cleared")
        @Test
        void __Clear() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                em.flush();
                final var identifier = identifierOf(v);
                em.clear();
                final var found = em.find(targetClass, identifier);
                assertThat(found)
                        .as("the %s found by its identifier, from the database", targetClass.getSimpleName())
                        .isNotNull()
                        .isNotSameAs(v);
                assertThat(identifierOf(found))
                        .as("the identifier of the %s found", targetClass.getSimpleName())
                        .isEqualTo(identifier);
                return found;
            });
        }
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
    protected <R> R applyNewPersistedEntityInstanceAndRollback(
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
    protected <R> R applyNewPersistedEntityInstanceAndCommit(
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
     * @see ___Persistence_TestUtils#applyInTransactionAndRollback(EntityManager, Function)
     */
    protected <R> R applyEntityManagerInTransactionAndRollback(
            final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndRollback(entityManager, function);
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
     * @see ___Persistence_TestUtils#applyInTransactionAndCommit(EntityManager, Function)
     */
    protected <R> R applyEntityManagerInTransactionAndCommit(
            final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndCommit(entityManager, function);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A new entity manager, for each test instance; {@link jakarta.enterprise.context.Dependent Dependent}.
     */
    @__TestPU
    @Inject
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

    /**
     * The one factory of the container; {@link jakarta.enterprise.context.ApplicationScoped ApplicationScoped}.
     */
    @__TestPU
    @Inject
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private EntityManagerFactory entityManagerFactory;

    /**
     * For obtaining entity managers by hand, rather than at an injection point.
     */
    @__TestPU
    @Inject
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private Instance<EntityManager> entityManagers;
}
