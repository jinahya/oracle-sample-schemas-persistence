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

public abstract class _Test<T> extends __Test<T> {

    protected _Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // -------------------------------------------------------------------------------------------------------- toString
    @Test
    protected void toString_NotBlank_NewInstance() {
        final var instance = newTargetInstance();
        final var string = instance.toString();
        assertThat(string).isNotBlank();
    }

    @Test
    protected void toString_NotBlank_NewRandomizedInstance() {
        newRandomizedTargetInstance().map(Objects::toString).ifPresent(v -> {
            assertThat(v).isNotBlank();
        });
    }

    // ------------------------------------------------------------------------------------------------ equals/hashCode
    protected SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return EqualsVerifier.simple().forClass(targetClass);
    }

    @Test
    protected void equals_verify_() {
        final var verifier = equals_verifier_();
        verifier.verify();
    }

    // -----------------------------------------------------------------------------------------------------------------
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
