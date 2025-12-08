package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegion;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Region_PersistenceTest extends _MappedHrEntity_PersistenceTest<Region, Long> {

    Region_PersistenceTest() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class Static_Test {

        @DisplayName("ATTRIBUTE_NAME_...")
        @Nested
        @SuppressWarnings({
                "java:S3415" // Assertion arguments should be passed in the correct order
        })
        class ATTRIBUTE_NAME_Test {

            @Test
            void __ATTRIBUTE_NAME_REGION_ID() {
                assertThat(MappedRegion.ATTRIBUTE_NAME_REGION_ID).isEqualTo(Region_.regionId.getName());
            }

            @Test
            void __ATTRIBUTE_NAME_REGION_NAME() {
                assertThat(MappedRegion.ATTRIBUTE_NAME_REGION_NAME).isEqualTo(Region_.regionName.getName());
            }
        }
    }
}
