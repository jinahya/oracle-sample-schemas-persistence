package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.Transient;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

@Slf4j
public abstract class __MappedEntity_Test<ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
        extends ___MappedEntity_TestBase<ENTITY, ID> {

    protected __MappedEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class ToStringTest {

        @Test
        void _NotBlank_NewEntityInstance() {
            // --------------------------------------------------------------------------------------------------- given
            final ENTITY entityInstance = newEntityInstance();
            // ---------------------------------------------------------------------------------------------------- when
            final var string = entityInstance.toString();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(string).isNotBlank();
        }

        @Test
        void _NotBlank_NewRandomizedEntityInstance() {
            // --------------------------------------------------------------------------------------------------- given
            final ENTITY randomizedEntityInstance = newRandomizedEntityInstance().orElse(null);
            assumeThat(randomizedEntityInstance).isNotNull();
            assumeFalse(
                    randomizedEntityInstance == null,
                    () -> String.format("randomizedEntityInstance(%s) is null", randomizedEntityInstance)
            );
            // ---------------------------------------------------------------------------------------------------- when
            final var string = randomizedEntityInstance.toString();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(string).isNotBlank();
        }
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode
    @DisplayName("equals/hashCode")
    @Test
    protected void equals_verify_() {
        equalsVerifier().verify();
    }

    protected SingleTypeEqualsVerifierApi<ENTITY> equalsVerifier() {
        final var verifier = EqualsVerifier.forClass(entityClass)
                .suppress(Warning.SURROGATE_KEY)
                .suppress(Warning.STRICT_HASHCODE);
        return verifier;
    }

    // ------------------------------------------------------------------------------------------------- getters/setters
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class AccessorsTest {

        private void accessors(final ENTITY entityInstance)
                throws IntrospectionException, InvocationTargetException, IllegalAccessException {
            final var info = Introspector.getBeanInfo(entityClass);
            for (final var descriptor : info.getPropertyDescriptors()) {
                final var reader = descriptor.getReadMethod();
                if (reader == null) {
                    continue;
                }
                if (!reader.canAccess(entityInstance)) {
                    reader.setAccessible(true);
                }
                if (reader.isAnnotationPresent(Transient.class)) {
                    continue;
                }
                final var value = reader.invoke(entityInstance);
                final var writer = descriptor.getWriteMethod();
                if (writer == null) {
                    continue;
                }
                if (!writer.canAccess(entityInstance)) {
                    writer.setAccessible(true);
                }
                if (writer.isAnnotationPresent(Transient.class)) {
                    continue;
                }
                assertThatCode(() -> {
                    writer.invoke(entityInstance, value);
                })
                        .as("%s(%s)", writer.getName(), value)
                        .doesNotThrowAnyException();
            }
        }

        @Test
        void accessors__NewEntityInstance()
                throws IntrospectionException, InvocationTargetException, IllegalAccessException {
            accessors(newEntityInstance());
        }

        @Test
        void accessors__NewRandomizedEntityInstance()
                throws IntrospectionException, InvocationTargetException, IllegalAccessException {
            final ENTITY randomizedEntityInstance = newRandomizedEntityInstance().orElse(null);
            assumeThat(randomizedEntityInstance).isNotNull();
            assumeFalse(
                    randomizedEntityInstance == null,
                    () -> String.format("randomizedEntityInstance(%s) is null", randomizedEntityInstance)
            );
            accessors(randomizedEntityInstance);
        }
    }

    // ----------------------------------------------------------------------------------------------- super.entityClass

    // --------------------------------------------------------------------------------------------------- super.idClass
}