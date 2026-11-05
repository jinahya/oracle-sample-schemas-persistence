package com.github.jinahya.oracle.sample.persistence.hr;

/*-
 * #%L
 * hr
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

import com.github.jinahya.persistence.metamodel.util.JinayaManagedTypeUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.ManagedType;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import static com.github.jinahya.oracle.sample.persistence.hr.__PersistenceTest_Utils.applyEntityManagerFactory;
import static com.github.jinahya.oracle.sample.persistence.hr.___Persistence_TestUtils.selectRandomEntityInstance;
import static com.github.jinahya.persistence.test.util.EntityPersisterUtils.newPersistedInstanceOf;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * An abstract base class for tests which need a persistence context, against a module's unit-test
 * persistence unit.
 * <p>
 * The factory comes from {@link __PersistenceTest_Utils}, opened once for the whole run; each test
 * gets an entity manager of its own on an in-memory database whose schema the provider generates.
 * <p>
 * Nothing here reaches the Oracle database; a test which needs the real data extends
 * {@link _Entity_PersistenceIT} and is named {@code *_PersistenceIT} so that failsafe, not
 * surefire, runs it.
 *
 * @param <T> the type of the entity under test.
 * @param <U> the type of the identifier of the entity, or of its id class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see __PersistenceTest_Utils
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
abstract class _Entity_PersistenceTest<T, U>
        extends __Test<T> {

    /**
     * The prefix of the constants {@link #_SameValue_ATTRIBUTE_NAME_()} checks. The value is
     * {@value}.
     */
    private static final String ATTRIBUTE_NAME_CONSTANT_PREFIX = "ATTRIBUTE_NAME_";

    /**
     * The prefix of the constants {@link #_Resolvable_ATTRIBUTE_PATH_()} checks. The value is
     * {@value}.
     */
    private static final String ATTRIBUTE_PATH_CONSTANT_PREFIX = "ATTRIBUTE_PATH_";

    /**
     * Returns the {@code UPPER_SNAKE_CASE} form of the specified attribute name.
     *
     * @param attributeName the attribute name to convert; {@code countryIsoCode}, say.
     * @return the converted name; {@code COUNTRY_ISO_CODE} for the example above.
     * @implNote An underscore goes wherever a lower-case letter or a digit is followed by
     *         an upper-case one, which is the boundary the constants in this project are spelled
     *         with.
     */
    private static String toUpperSnakeCase(final String attributeName) {
        return attributeName.replaceAll("(?<=[a-z0-9])(?=[A-Z])", "_").toUpperCase(Locale.ROOT);
    }

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param targetClass the class of the entity under test.
     * @param idClass     the class of the identifier of the entity, or its id class.
     */
    protected _Entity_PersistenceTest(final Class<T> targetClass, final Class<U> idClass) {
        super(targetClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Returns every {@code static final String} field, declared by {@link #targetClass} or
     * inherited by it, whose name starts with the specified prefix, with its value.
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
                    throw new AssertionError(
                            "failed to read " + c.getSimpleName() + '.' + field.getName(), iae);
                }
            }
        }
        return constants;
    }

    /**
     * Verifies the {@code ATTRIBUTE_NAME_*} constants of {@link #targetClass} both ways: every
     * managed attribute whose constant exists finds that constant holding the attribute's name, and
     * every such constant names a managed attribute of the type.
     *
     * @implNote The first direction runs from the attribute to the constant, so an
     *         attribute with no constant is skipped; whether every attribute ought to have one is a
     *         different question. The second runs from the constant to the attribute, so a constant
     *         holding a name the provider does not manage the type by -- one left behind by a
     *         renamed field, or one naming an attribute of some other class -- fails. A path into
     *         an embedded attribute is not an attribute name; it is declared as an
     *         {@code ATTRIBUTE_PATH_*} constant and checked by
     *         {@link #_Resolvable_ATTRIBUTE_PATH_()}.
     */
    @DisplayName("every ATTRIBUTE_NAME_* constant names a managed attribute, and vice versa")
    @Test
    protected void _SameValue_ATTRIBUTE_NAME_() {
        final var managedType =
                applyEntityManagerFactory(EntityManagerFactory::getMetamodel).managedType(
                        targetClass);
        final var constants = constantsPrefixed(ATTRIBUTE_NAME_CONSTANT_PREFIX);
        for (final var attribute : managedType.getAttributes()) {
            final var attributeName = attribute.getName();
            final var constantName =
                    ATTRIBUTE_NAME_CONSTANT_PREFIX + toUpperSnakeCase(attributeName);
            if (!constants.containsKey(constantName)) {
                continue; // existence is not this direction's concern
            }
            assertThat(constants.get(constantName))
                    .as("%s.%s, for the managed attribute %s", targetClass.getSimpleName(),
                        constantName,
                        attributeName)
                    .isEqualTo(attributeName);
        }
        constants.forEach(
                (constantName, value) -> assertThatCode(() -> managedType.getAttribute(value))
                        .as("%s.%s = \"%s\" names a managed attribute of %s",
                            targetClass.getSimpleName(), constantName, value,
                            managedType.getJavaType().getSimpleName())
                        .doesNotThrowAnyException());
    }

    /**
     * Verifies that every {@code ATTRIBUTE_PATH_*} constant of {@link #targetClass} holds a dotted
     * path which resolves through the metamodel, segment by segment, to an attribute whose name is
     * the path's last segment.
     *
     * @see JinayaManagedTypeUtils#resolveAttributePath(ManagedType, String)
     */
    @DisplayName("every ATTRIBUTE_PATH_* constant resolves through the metamodel")
    @Test
    protected void _Resolvable_ATTRIBUTE_PATH_() {
        final var managedType =
                applyEntityManagerFactory(EntityManagerFactory::getMetamodel).managedType(
                        targetClass);
        constantsPrefixed(ATTRIBUTE_PATH_CONSTANT_PREFIX).forEach((constantName, path) -> {
            assertThat(path)
                    .as("%s.%s is a dotted path", targetClass.getSimpleName(), constantName)
                    .contains(".");
            final var resolved = JinayaManagedTypeUtils.resolveAttributePath(managedType, path);
            assertThat(resolved.getName())
                    .as("%s.%s = \"%s\" resolves to its last segment", targetClass.getSimpleName(),
                        constantName,
                        path)
                    .isEqualTo(path.substring(path.lastIndexOf('.') + 1));
        });
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Persists a new, randomized instance of {@link #targetClass}, through its persister, and hands
     * it to {@link #randomizedEntityInstancePersisted__(EntityManager, Object)}.
     *
     * @implNote The work happens in a transaction which is always rolled back, so the row
     *         never survives the test.
     */
    @Test
    protected void persistRandomizedEntityInstance__() {
        applyEntityManagerFactory(emf -> applyEntityManager(
                emf,
                m -> applyInTransactionAndRollback(m, em -> {
                    final var persisted = newPersistedInstanceOf(em, targetClass);
                    randomizedEntityInstancePersisted__(em, persisted);
                    return null;
                })
        ));
    }

    void randomizedEntityInstancePersisted__(final EntityManager entityManager,
                                             final T entityInstance) {
        // empty
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Selects a random instance of {@link #targetClass} and hands it to
     * {@link #randomEntityInstanceSelected__(EntityManager, Object)}.
     *
     * @implNote The selection and the callback both run inside a transaction which is
     *         rolled back, so the instance handed over is still managed, and nothing the callback
     *         does survives the test.
     * @implSpec Selecting from an empty table is not a failure: the schema is generated
     *         empty, so {@link #randomEntityInstanceSelected__(EntityManager, Object)} is invoked
     *         only when an earlier test of the run committed a row.
     */
    @Test
    protected void selectRandomEntityInstance__() {
        applyEntityManagerFactory(
                emf -> selectRandomEntityInstance(
                        emf,
                        targetClass,
                        (em, v) -> {
                            randomEntityInstanceSelected__(em, v);
                            return null;
                        }
                )
        );
    }

    /**
     * Called with the instance {@link #selectRandomEntityInstance__()} selected, while it is still
     * managed.
     *
     * @param entityInstance the randomly selected instance.
     * @implSpec This class does nothing with it; a subclass overrides this method to assert
     *         whatever its entity promises of a row which is in the database.
     */
    protected void randomEntityInstanceSelected__(final EntityManager entityManager,
                                                  final T entityInstance) {
        // empty; overridden by a subclass which has something to assert
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The class of the identifier of {@link #targetClass}; its id class, or the type of its
     * {@code @Id} or {@code @EmbeddedId} attribute.
     */
    protected final Class<U> idClass;
}
