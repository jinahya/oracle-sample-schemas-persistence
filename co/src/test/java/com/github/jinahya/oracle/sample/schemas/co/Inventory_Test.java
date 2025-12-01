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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedInventory;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class Inventory_Test extends __MappedEntity_Test<Inventory, Long> {

    Inventory_Test() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Inventory> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .withOnlyTheseFields(
                        MappedInventory.ATTRIBUTE_NAME_STORE_ID,
                        MappedInventory.ATTRIBUTE_NAME_PRODUCT_ID
                )
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("adjustProductInventory(delta)")
    @Nested
    class AdjustProductInventory_Test {

        @DisplayName("should invoke setProductInventory(getProductInventory() + delta)")
        @Test
        void _ShouldInvokeSetProductInventoryWithProductInventoryPlusDelta_() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newMappedInstanceSpy();
            final var productInventory = ThreadLocalRandom.current().nextLong();
            given(instance.getProductInventory()).willReturn(productInventory);
            final var delta = ThreadLocalRandom.current().nextInt();
            // ---------------------------------------------------------------------------------------------------- when
            // TODO: invoke
            // ---------------------------------------------------------------------------------------------------- then
            // TODO: verify
        }
    }

    @DisplayName("increaseProductInventory(delta)")
    @Nested
    class IncreaseProductInventory_Test {

        @DisplayName("should throw IllegalArgumentException when delta is negative)")
        @Test
        void _ShouldThrowIllegalArgumentException_DeltalIsNegative_() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newMappedInstance();
            final var delta = ThreadLocalRandom.current().nextInt() | Integer.MIN_VALUE;
            assert delta < 0;
            // --------------------------------------------------------------------------------------------- when / then
            assertThatThrownBy(() -> {
                instance.increaseProductInventoryBy(delta);
            }).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("should invoke adjustProductInventory(delta)")
        @Test
        void _ShouldInvokeAdjustProductInventoryWithDelta_() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newMappedInstanceSpy();
            final var productInventory = ThreadLocalRandom.current().nextLong();
            given(instance.getProductInventory()).willReturn(productInventory);
            final var delta = ThreadLocalRandom.current().nextInt() & Integer.MAX_VALUE;
            assert delta >= 0;
            // ---------------------------------------------------------------------------------------------------- when
            // TODO: invoke
            // ---------------------------------------------------------------------------------------------------- then
            // TODO: verify
        }
    }

    @DisplayName("decreaseProductInventory(delta)")
    @Nested
    class DecreaseProductInventory_Test {

        @DisplayName("should throw IllegalArgumentException when delta is negative)")
        @Test
        void _ShouldThrowIllegalArgumentException_DeltalIsNegative_() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newMappedInstance();
            final var delta = ThreadLocalRandom.current().nextInt() | Integer.MIN_VALUE;
            assert delta < 0;
            // --------------------------------------------------------------------------------------------- when / then
            // TODO: verify
        }

        @DisplayName("should invoke adjustProductInventory(-delta)")
        @Test
        void _ShouldInvokeAdjustProductInventoryWithMinusDelta_() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newMappedInstanceSpy();
            final var productInventory = ThreadLocalRandom.current().nextLong();
            given(instance.getProductInventory()).willReturn(productInventory);
            final var delta = ThreadLocalRandom.current().nextInt() & Integer.MAX_VALUE;
            assert delta >= 0;
            // ---------------------------------------------------------------------------------------------------- when
            assertThatCode(() -> {
                instance.decreaseProductInventoryBy(delta);
            }).doesNotThrowAnyException();
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).adjustProductInventory(-delta);
        }
    }
}
