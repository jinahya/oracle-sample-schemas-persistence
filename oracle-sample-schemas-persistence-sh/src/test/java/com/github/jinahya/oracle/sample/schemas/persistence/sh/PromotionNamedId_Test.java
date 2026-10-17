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

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract base class for testing the concrete pairs of {@link PromotionNamedId}: the standard checks of
 * {@link _NonEntity_Test}, and the one thing a pair adds that the verifier does not cover: a factory which sets both
 * halves.
 *
 * @param <T> the pair class under test.
 * @param <N> the type of the pair's id.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S118" // Abstract class names should comply with a naming convention
})
abstract class PromotionNamedId_Test<T extends PromotionNamedId<N>, N extends Number> extends _NonEntity_Test<T> {

    static <T extends PromotionNamedId<N>, N extends Number> Method of(final Class<T> targetClass,
                                                                       final Class<N> idClass)
            throws NoSuchMethodException {
        final var factory = targetClass.getMethod("of", String.class, idClass);
        assertThat(Modifier.isStatic(factory.getModifiers())).as("%s is static", factory).isTrue();
        assertThat(factory.getReturnType()).as("the return type of %s", factory).isSameAs(targetClass);
        return factory;
    }

    static <T extends PromotionNamedId<N>, N extends Number> T of(final Class<T> targetClass,
                                                                  final Class<N> idClass,
                                                                  final String name,
                                                                  final N id)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final var factory = of(targetClass, idClass);
        assertThat(Modifier.isStatic(factory.getModifiers())).as("%s is static", factory).isTrue();
        assertThat(factory.getReturnType()).as("the return type of %s", factory).isSameAs(targetClass);
        return targetClass.cast(factory.invoke(null, name, id));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for the specified pair class and id type.
     *
     * @param embeddableClass the pair class under test.
     * @param idClass         the type of the pair's id.
     */
    PromotionNamedId_Test(final Class<T> embeddableClass, final Class<N> idClass) {
        super(embeddableClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------------- of

    /**
     * Verifies that the pair class declares a public static factory {@code of(String, N)}, found reflectively through
     * {@code idClass}, and that the instance it returns carries the name and id it was given.
     *
     * @throws ReflectiveOperationException if the factory is missing, or cannot be called.
     */
    @DisplayName("of(name, id) returns an instance with that name and id")
    @Test
    void of_NameAndIdSet_() throws ReflectiveOperationException {
        final var randomized = newRandomizedTargetInstance();
        final var name = randomized.getName();
        final var id = randomized.getId();
        final var instance = of(targetClass(), idClass, name, id);
        assertThat(instance.getName()).isEqualTo(name);
        assertThat(instance.getId()).isEqualTo(id);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote The {@value PromotionNamedId#ATTRIBUTE_NAME_ID} field is declared as the type variable {@code N} in the
     * superclass, which EqualsVerifier cannot fabricate values for on its own, so two distinct ids of the concrete type
     * are given as prefab values for that field.
     */
    @Override
    public SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        final var red = Objects.requireNonNull(newRandomizedTargetInstance().getId(),
                                               "randomized id is null");
        N blue = null;
        for (int i = 0; i < 16 && (blue == null || blue.equals(red)); i++) {
            blue = newRandomizedTargetInstance().getId();
        }
        assertThat(blue).as("a second randomized id, different from the first").isNotNull().isNotEqualTo(red);
        return super.equals_verifier_()
                .withPrefabValuesForField(PromotionNamedId.ATTRIBUTE_NAME_ID, red, blue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<N> idClass;
}
