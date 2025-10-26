package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.persistence.mapped.test.___RandomizerUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

@Slf4j
class ProductDetails_Test {

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class ToStringTest {

        @Test
        void _NotBlank_NewEntityInstance() {
            // --------------------------------------------------------------------------------------------------- given
            final var newInstance = new ProductDetails();
            // ---------------------------------------------------------------------------------------------------- when
            final var string = newInstance.toString();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(string).isNotBlank();
        }

        @Test
        void _NotBlank_NewRandomizedEntityInstance() {
            // --------------------------------------------------------------------------------------------------- given
            final var randomizedInstance = ___RandomizerUtils.newRandomizedInstanceOf(ProductDetails.class);
            assumeThat(randomizedInstance).isNotNull();
            assumeFalse(
                    randomizedInstance == null,
                    () -> String.format("randomizedInstance(%s) is null", randomizedInstance)
            );
            // ---------------------------------------------------------------------------------------------------- when
            final var string = randomizedInstance.toString();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(string).isNotBlank();
        }
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- getters/setters
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class AccessorsTest {

        private void accessors(final ProductDetails instance)
                throws IntrospectionException, InvocationTargetException, IllegalAccessException {
            final var info = Introspector.getBeanInfo(ProductDetails.class);
            for (final var descriptor : info.getPropertyDescriptors()) {
                final var reader = descriptor.getReadMethod();
                if (reader == null) {
                    continue;
                }
                if (!reader.canAccess(instance)) {
                    reader.setAccessible(true);
                }
                final var value = reader.invoke(instance);
                final var writer = descriptor.getWriteMethod();
                if (writer == null) {
                    continue;
                }
                if (!writer.canAccess(instance)) {
                    writer.setAccessible(true);
                }
                assertThatCode(() -> writer.invoke(instance, value))
                        .as("%s(%s)", writer.getName(), value)
                        .doesNotThrowAnyException();
            }
        }

        @Test
        void accessors__NewEntityInstance()
                throws IntrospectionException, InvocationTargetException, IllegalAccessException {
            accessors(new ProductDetails());
        }

        @Test
        void accessors__NewRandomizedEntityInstance()
                throws IntrospectionException, InvocationTargetException, IllegalAccessException {
            final var randomizedInstance = ___RandomizerUtils.newRandomizedInstanceOf(ProductDetails.class);
            assumeThat(randomizedInstance).isNotNull();
            assumeFalse(
                    randomizedInstance == null,
                    () -> String.format("randomizedInstance(%s) is null", randomizedInstance)
            );
            accessors(randomizedInstance.orElseThrow());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class ProductDetails_JsonTest {

        @ValueSource(strings = {
                "PRODUCTS_PRODUCT_DETAILS_1.json",
                "PRODUCTS_PRODUCT_DETAILS_2.json"
        })
        @ParameterizedTest
        void __(final String name) throws IOException {
            log.debug("package: {}", getClass().getPackageName());
            try (var resource = getClass().getResourceAsStream(name)) {
                assertThat(resource)
                        .as("resource for '%1$s'", name)
                        .isNotNull();
                final var value = ProductDetails_TestUtils.from(resource, new ObjectMapper());
                log.debug("value: {}", value);
            }
        }
    }
}
