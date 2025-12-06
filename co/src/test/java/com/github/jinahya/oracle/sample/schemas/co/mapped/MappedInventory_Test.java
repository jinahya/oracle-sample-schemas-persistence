package com.github.jinahya.oracle.sample.schemas.co.mapped;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class MappedInventory_Test<ENTITY extends MappedInventory> extends _MappedCoEntity_Test<ENTITY, Long> {

    protected MappedInventory_Test(final Class<ENTITY> entityClass) {
        super(entityClass, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("increaseProductInventoryBy(quantity)V")
    @Nested
    class IncreaseProductInventoryBy_Test {

        @DisplayName("(negative)IllegalArgumentException")
        @Test
        void _ThrowIllegalArgumentException_QuantityNegative() {
            final var inventory = newInstantiatedTargetInstance();
            final var quantity = ThreadLocalRandom.current().nextInt() | Integer.MIN_VALUE;
            assertThatThrownBy(() -> {
                inventory.increaseProductInventoryBy(quantity);
            }).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
