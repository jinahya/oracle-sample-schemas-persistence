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

import jakarta.persistence.Transient;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * An abstract base class for testing an entity class without a persistence context.
 * <p>
 * It verifies what every entity class of this project has to satisfy on its own -- a usable {@code toString()}, an
 * {@code equals}/{@code hashCode} pair, and property accessors which round-trip.
 *
 * @param <T> the type of the target class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class _Test<T> extends __Test<T> {

    /**
     * Creates a new instance for the specified target class.
     *
     * @param targetClass the class to test.
     */
    protected _Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // -------------------------------------------------------------------------------------------------------- toString

    /**
     * Verifies that {@code toString()} of a new instance of {@link #targetClass} is not blank.
     */
    @Test
    protected void toString_NotBlank_NewInstance() {
        final var instance = newTargetInstance();
        final var string = instance.toString();
        assertThat(string).isNotBlank();
    }

    /**
     * Verifies that {@code toString()} of a new randomized instance of {@link #targetClass} is not blank.
     */
    @Test
    protected void toString_NotBlank_NewRandomizedInstance() {
        newRandomizedTargetInstance().map(Objects::toString).ifPresent(v -> {
            assertThat(v).isNotBlank();
        });
    }

    // ------------------------------------------------------------------------------------------------ equals/hashCode

    /**
     * Returns an equals-verifier for {@link #targetClass}, which subclasses may further configure.
     *
     * @return an equals-verifier for {@link #targetClass}.
     */
    protected SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return EqualsVerifier.simple().forClass(targetClass);
    }

    /**
     * Verifies the {@code equals}/{@code hashCode} contract of {@link #targetClass}, using the verifier which
     * {@link #equals_verifier_()} returns.
     */
    @Test
    protected void equals_verify_() {
        final var verifier = equals_verifier_();
        verifier.verify();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Verifies that every non-{@link jakarta.persistence.Transient @Transient} read/write property of
     * {@link #targetClass} accepts what its own reader returns.
     *
     * @throws IntrospectionException when {@link #targetClass} cannot be introspected.
     */
    @Test
    protected void propertyAccessors_DoNotThrow() throws IntrospectionException {
        final var instance = newTargetInstance();
        final java.beans.BeanInfo info = Introspector.getBeanInfo(targetClass);
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
