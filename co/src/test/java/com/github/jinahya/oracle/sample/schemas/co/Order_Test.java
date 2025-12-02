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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrder;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Disabled
@Slf4j
class Order_Test extends __MappedEntity_Test<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_Test() {
        super(Order.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Order> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Order> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class OrderTms_Test {

    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class OrderStatus_Test {

        @DisplayName("getOrderStatusAsMapped(mapper)")
        @Nested
        class GetOrderStatusAsMapped_Test {

            @Test
            void _Null_Null() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstanceSpy();
                given(instance.getOrderStatus()).willReturn(null);
                // ------------------------------------------------------------------------------------------------ when
                assertThatCode(() -> {
                    final var result = instance.getOrderStatusAsMapped(null);
                    // -------------------------------------------------------------------------------------------- then
                    assertThat(result).isNull();
                }).doesNotThrowAnyException();
            }

            @Test
            void _NullPointerException_ValueIsNotNullMapperIsNull() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstance();
                instance.setOrderStatus("");
                // ------------------------------------------------------------------------------------------------ when
                assertThatThrownBy(() -> {
                    instance.getOrderStatusAsMapped(null);
                }).isInstanceOf(NullPointerException.class);
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        @DisplayName("getOrderStatusFromMapped(orderStatus, mapper)")
        @Nested
        class GetOrderStatusFromMapped_Test {

            @Test
            void _Null_Null() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstance();
                // ------------------------------------------------------------------------------------------------ when
                assertThatCode(() -> {
                    instance.setOrderStatusFromMapped(null, null);
                }).doesNotThrowAnyException();
            }

            @Test
            void _NullPointerException_ValueIsNotNullMapperIsNull() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstance();
                // ------------------------------------------------------------------------------------------------ when
                assertThatThrownBy(() -> {
                    instance.setOrderStatusFromMapped("", null);
                }).isInstanceOf(NullPointerException.class);
            }

            @Test
            void _InvokeSetOrderStatus_() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstanceSpy();
                final var orderStatus = "";
                final var mapper = Mockito.mock(Function.class);
                given(mapper.apply(any())).willAnswer(i -> i.getArguments()[0]);
                // ------------------------------------------------------------------------------------------------ when
                instance.setOrderStatusFromMapped(orderStatus, mapper);
                // ------------------------------------------------------------------------------------------------ then
                verify(mapper, times(1)).apply(orderStatus);
                verify(instance, times(1)).setOrderStatus(orderStatus);
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        @DisplayName("getOrderStatusAsEnum(enumClass)")
        @Nested
        class GetOrderStatusAsEnum_Test {

            @Test
            void _Null_Null() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstanceSpy();
                given(instance.getOrderStatus()).willReturn(null);
                // ------------------------------------------------------------------------------------------------ when
                final var result = instance.getOrderStatusAsEnum(MappedOrder._OrderStatus.class);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(result).isNull();
            }

            @EnumSource(MappedOrder._OrderStatus.class)
            @ParameterizedTest
            void _NotNull_NotNull(final MappedOrder._OrderStatus orderStatus) {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstanceSpy();
                given(instance.getOrderStatus()).willReturn(orderStatus.attributeValue());
                // ------------------------------------------------------------------------------------------------ when
                final var result = instance.getOrderStatusAsEnum(MappedOrder._OrderStatus.class);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(result).isSameAs(orderStatus);
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        @DisplayName("setOrderStatusFromEnum(enumValue)")
        @Nested
        class SetOrderStatusAsEnum_Test {

            @Test
            void _Null_Null() {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstanceSpy();
                // ------------------------------------------------------------------------------------------------ when
                instance.setOrderStatusFromEnum(null);
                // ------------------------------------------------------------------------------------------------ then
                verify(instance, times(1)).setOrderStatusFromMapped(isNull(), isNotNull());
            }

            @EnumSource(MappedOrder._OrderStatus.class)
            @ParameterizedTest
            void _NotNull_NotNull(final MappedOrder._OrderStatus orderStatus) {
                // ----------------------------------------------------------------------------------------------- given
                final var instance = newMappedInstanceSpy();
                // ------------------------------------------------------------------------------------------------ when
                instance.setOrderStatusFromEnum(orderStatus);
                // ------------------------------------------------------------------------------------------------ then
                verify(instance, times(1)).setOrderStatusFromMapped(same(orderStatus), isNotNull());
            }
        }
    }
}
