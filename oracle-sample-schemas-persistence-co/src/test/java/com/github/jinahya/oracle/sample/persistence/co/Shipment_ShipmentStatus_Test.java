package com.github.jinahya.oracle.sample.persistence.co;

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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment.ShipmentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests {@link ShipmentStatus}, the enum of the {@value MappedShipment#COLUMN_NAME_SHIPMENT_STATUS}
 * column.
 */
class Shipment_ShipmentStatus_Test {

    /**
     * Verifies that the column values are the constraint's own text.
     */
    @Test
    void columnValue_ConstraintText_Each() {
        assertThat(ShipmentStatus.CREATED.columnValue()).isEqualTo("CREATED");
        assertThat(ShipmentStatus.SHIPPED.columnValue()).isEqualTo("SHIPPED");
        assertThat(ShipmentStatus.IN_TRANSIT.columnValue()).isEqualTo("IN-TRANSIT");
        assertThat(ShipmentStatus.DELIVERED.columnValue()).isEqualTo("DELIVERED");
    }

    /**
     * Verifies that {@link ShipmentStatus#IN_TRANSIT}'s column value is not its name, which is why
     * each constant carries its column value as an {@code @EnumeratedValue}.
     */
    @Test
    void columnValue_NotName_InTransit() {
        assertThat(ShipmentStatus.IN_TRANSIT.columnValue())
                .isNotEqualTo(ShipmentStatus.IN_TRANSIT.name());
    }

    /**
     * Verifies that no two constants share a column value.
     */
    @Test
    void columnValue_Distinct_Constants() {
        assertThat(Arrays.stream(ShipmentStatus.values()).map(ShipmentStatus::columnValue))
                .doesNotHaveDuplicates();
    }

    @ParameterizedTest
    @EnumSource(ShipmentStatus.class)
    void valueOfColumnValue_Same_ColumnValue(final ShipmentStatus constant) {
        assertThat(ShipmentStatus.valueOfColumnValue(constant.columnValue())).isSameAs(constant);
    }

    /**
     * Verifies that the near misses of this column are rejected: the constant's name for a value
     * which is not it, another case, and an empty text.
     */
    @ParameterizedTest
    @ValueSource(strings = {"IN_TRANSIT", "in-transit", "Delivered", ""})
    void valueOfColumnValue_IllegalArgumentException_Unknown(final String columnValue) {
        assertThatThrownBy(() -> ShipmentStatus.valueOfColumnValue(columnValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
