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

import jakarta.persistence.Transient;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * The checks a class has to pass on its own -- a usable {@code toString()}, an {@code equals}/{@code hashCode} pair,
 * and property accessors which round-trip -- as tests a base class takes on by implementing this interface.
 * <p>
 * {@link _NonEntity_Test} and {@link _Entity_Test} implement it; {@link _Entity_Persistence_Test} and
 * {@link _Entity_Persistence_IT} do not, so they do not run these again.
 *
 * @param <T> the type of the target class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see __Test
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S114"  // Interface names should comply with a naming convention
})
interface __Object_Test<T> {

    /**
     * Returns the class which this test targets.
     *
     * @return the class which this test targets.
     */
    Class<T> targetClass();

    /**
     * Returns a new, uninitialized instance of {@link #targetClass()}.
     *
     * @return a new instance of {@link #targetClass()}.
     */
    T newTargetInstance();

    /**
     * Returns a new instance of {@link #targetClass()} with randomized property values.
     *
     * @return a new randomized instance of {@link #targetClass()}.
     */
    T newRandomizedTargetInstance();

    // -------------------------------------------------------------------------------------------------------- toString

    /**
     * Verifies that {@code toString()} of a new instance of {@link #targetClass()} is not blank.
     */
    @Test
    default void toString_NotBlank_NewInstance() {
        final var instance = newTargetInstance();
        final var string = instance.toString();
        assertThat(string).isNotBlank();
    }

    /**
     * Verifies that {@code toString()} of a new randomized instance of {@link #targetClass()} is not blank.
     */
    @Test
    default void toString_NotBlank_NewRandomizedInstance() {
        assertThat(newRandomizedTargetInstance().toString()).isNotBlank();
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * Returns an equals-verifier for {@link #targetClass()}, which implementations may further configure.
     *
     * @return an equals-verifier for {@link #targetClass()}.
     */
    default SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return EqualsVerifier.simple().forClass(targetClass());
    }

    /**
     * Verifies the {@code equals}/{@code hashCode} contract of {@link #targetClass()}, using the verifier which
     * {@link #equals_verifier_()} returns.
     */
    @Test
    default void equals_verify_() {
        final var verifier = equals_verifier_();
        verifier.verify();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verifies that every non-{@link Transient @Transient} read/write property of {@link #targetClass()} accepts what
     * its own reader returns.
     *
     * @throws IntrospectionException when {@link #targetClass()} cannot be introspected.
     */
    @Test
    default void propertyAccessors_DoNotThrow() throws IntrospectionException {
        final var targetClass = targetClass();
        final var instance = newTargetInstance();
        final var info = Introspector.getBeanInfo(targetClass);
        for (final var descriptor : info.getPropertyDescriptors()) {
            final var reader = descriptor.getReadMethod();
            final var writer = descriptor.getWriteMethod();
            if (reader == null || writer == null
                || reader.isAnnotationPresent(Transient.class)
                || writer.isAnnotationPresent(Transient.class)) {
                continue;
            }
            reader.setAccessible(true);
            writer.setAccessible(true);
            assertThatCode(() -> writer.invoke(instance, reader.invoke(instance)))
                    .as("%s.%s(%s())", targetClass.getSimpleName(), writer.getName(), reader.getName())
                    .doesNotThrowAnyException();
        }
    }
}
