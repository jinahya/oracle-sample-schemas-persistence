package com.github.jinahya.oracle.sample.persistence.co.mapped;

/*-
 * #%L
 * co
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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment.ShipmentStatus;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment.ShipmentStatusConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShipmentStatusConverter_Test {

    /**
     * Verifies that every value the {@code shipment_status_c} constraint allows has a constant.
     */
    @ParameterizedTest
    @ValueSource(strings = {"CREATED", "SHIPPED", "IN-TRANSIT", "DELIVERED"})
    void convertToEntityAttribute_Constant_ConstraintValue(final String text) {
        assertThat(new ShipmentStatusConverter().convertToEntityAttribute(text).attributeValue())
                .isEqualTo(text);
    }

    @ParameterizedTest
    @EnumSource(ShipmentStatus.class)
    void convertToEntityAttribute_SameConstant_RoundTrip(final ShipmentStatus constant) {
        final var converter = new ShipmentStatusConverter();
        assertThat(converter.convertToEntityAttribute(converter.convertToDatabaseColumn(constant)))
                .isSameAs(constant);
    }

    @Test
    void convertToDatabaseColumn_ColumnValue_InTransit() {
        assertThat(new ShipmentStatusConverter().convertToDatabaseColumn(ShipmentStatus.IN_TRANSIT))
                .isEqualTo("IN-TRANSIT");
    }

    @ParameterizedTest
    @ValueSource(strings = {"IN_TRANSIT", "in-transit", "Delivered", ""})
    void convertToEntityAttribute_IllegalArgumentException_Unknown(final String text) {
        assertThatThrownBy(() -> new ShipmentStatusConverter().convertToEntityAttribute(text))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void convertBoth_Null_Null() {
        final var converter = new ShipmentStatusConverter();
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }
}
