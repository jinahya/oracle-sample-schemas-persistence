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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ProductDetails_Json_Test {

    @Test
    void __() throws IOException {
        try (var resource = getClass().getResourceAsStream("PRODUCTS_PRODUCT_DETAILS_LIST.json")) {
            assertThat(resource).isNotNull();
            final var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNEXPECTED_VIEW_PROPERTIES, true);
            final var tree = objectMapper.readTree(resource);
            for (int i = 0; i < tree.size(); i++) {
                final var node = tree.get(i);
                final var json = node.get("PRODUCT_DETAILS").textValue();
                if (json == null) {
                    continue;
                }
                final var value = objectMapper.readValue(json, ProductDetails.class);
                log.debug("value: {}", value);
//                assertThat(value.unknownProperties()).isEmpty();
//                Optional.ofNullable(value.getReviews()).ifPresent(l -> {
//                    l.forEach(e -> {
//                        assertThat(e.unknownProperties()).isEmpty();
//                    });
//                });
            }
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
