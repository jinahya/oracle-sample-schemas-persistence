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

import jakarta.persistence.Transient;
import org.junit.jupiter.api.Assertions;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Checks shared by every entity test in the {@code SH} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class EntityTestUtils {

    /**
     * Verifies that the specified class can be instantiated through the no-arg constructor Jakarta Persistence
     * requires, and returns the instance.
     *
     * @param entityClass the entity class.
     * @param <T>         entity type parameter.
     * @return a new instance of {@code entityClass}.
     */
    static <T> T newInstance(final Class<T> entityClass) {
        try {
            final var constructor = entityClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (final NoSuchMethodException nsme) {
            return Assertions.fail(entityClass + " has no no-arg constructor", nsme);
        } catch (final ReflectiveOperationException roe) {
            final var cause = roe instanceof InvocationTargetException ite ? ite.getCause() : roe;
            return Assertions.fail("failed to instantiate " + entityClass, cause);
        }
    }

    /**
     * Verifies that {@link Object#toString() toString()} of a new instance of the specified class is not blank.
     *
     * @param entityClass the entity class.
     */
    static void assertToStringIsNotBlank(final Class<?> entityClass) {
        assertThat(newInstance(entityClass).toString())
                .as("%s.toString()", entityClass.getSimpleName())
                .isNotBlank();
    }

    /**
     * Verifies that every readable/writable property of the specified class survives a read-then-write round trip.
     *
     * @param entityClass the entity class.
     */
    static void assertPropertyAccessorsDoNotThrow(final Class<?> entityClass) {
        final var instance = newInstance(entityClass);
        final java.beans.BeanInfo info;
        try {
            info = Introspector.getBeanInfo(entityClass);
        } catch (final IntrospectionException ie) {
            Assertions.fail("failed to introspect " + entityClass, ie);
            return;
        }
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
                    .as("%s.%s(%s())", entityClass.getSimpleName(), writer.getName(), reader.getName())
                    .doesNotThrowAnyException();
        }
    }

    private EntityTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
