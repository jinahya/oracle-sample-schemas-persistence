package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Transient;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

abstract class _Test<T> extends __Test<T> {

    _Test(final Class<T> persistenceClass) {
        super(persistenceClass);
    }

    // -------------------------------------------------------------------------------------------------------- toString
    @Test
    void toString_NotBlank_NewInstance() {
        final var instance = newTargetInstance();
        final var string = instance.toString();
        assertThat(string).isNotBlank();
    }

    @Test
    void toString_NotBlank_NewRandomizedInstance() {
        newRandomizedTargetInstance().map(Objects::toString).ifPresent(v -> {
            assertThat(v).isNotBlank();
        });
    }

    // ------------------------------------------------------------------------------------------------ equals/hashCodee
    SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return EqualsVerifier.simple().forClass(targetClass);
    }

    @Test
    void equals_verify_() {
        final var verifier = equals_verifier_();
        verifier.verify();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void propertyAccessors_DoNotThrow() throws IntrospectionException {
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
