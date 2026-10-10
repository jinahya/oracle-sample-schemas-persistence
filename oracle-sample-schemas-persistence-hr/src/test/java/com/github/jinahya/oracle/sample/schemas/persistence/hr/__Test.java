package com.github.jinahya.oracle.sample.schemas.persistence.hr;

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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import jakarta.persistence.Transient;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.platform.commons.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * The root of every test base of this package; it holds the target class, and what a test of any kind does with it.
 * <p>
 * The checks a class has to pass on its own -- a usable {@code toString()}, an {@code equals}/{@code hashCode} pair,
 * and property accessors which round-trip -- are plain methods here, not tests. {@link _NonEntity_Test} and
 * {@link _DomainEntity_Test} declare them as tests; {@link _DomainEntity_Persistence_Test} and
 * {@link _DomainEntity_Persistence_IT} do not, so they do not run them again.
 *
 * @param <T> the type of the target class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
abstract class __Test<T> {

    /**
     * Creates a new instance for the specified target class.
     *
     * @param targetClass the class to test.
     */
    protected __Test(final Class<T> targetClass) {
        super();
        this.targetClass = Objects.requireNonNull(targetClass, "targetClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a new, uninitialized instance of {@link #targetClass}.
     *
     * @return a new instance of {@link #targetClass}.
     */
    public T newTargetInstance() {
        return ReflectionUtils.newInstance(targetClass);
    }

    /**
     * Returns a new instance of {@link #targetClass} with randomized property values.
     *
     * @return a new randomized instance of {@link #targetClass}; empty when no randomizer is registered for
     * {@link #targetClass}.
     */
    public Optional<T> newRandomizedTargetInstance() {
        return ObjectRandomizerUtils.newRandomizedInstanceOf(targetClass);
    }

    // -------------------------------------------------------------------------------------------------------- toString

    /**
     * Verifies that {@code toString()} of a new instance of {@link #targetClass} is not blank.
     */
    protected void toString_NotBlank_NewInstance() {
        final var instance = newTargetInstance();
        final var string = instance.toString();
        assertThat(string).isNotBlank();
    }

    /**
     * Verifies that {@code toString()} of a new randomized instance of {@link #targetClass} is not blank.
     */
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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The class which this test targets.
     */
    protected final Class<T> targetClass;
}
