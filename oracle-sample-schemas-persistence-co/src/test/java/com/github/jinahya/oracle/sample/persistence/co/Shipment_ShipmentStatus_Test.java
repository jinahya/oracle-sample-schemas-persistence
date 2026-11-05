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
import com.github.jinahya.persistence.more.test.AttributeEnum_Test;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link ShipmentStatus}, the enum of the {@value MappedShipment#COLUMN_NAME_SHIPMENT_STATUS}
 * column, on the base of {@code jinahya-persistence-more-test}, which checks that no constant has a
 * {@code null} column value, that no two share one, that each is found by its own, and that they are
 * the {@link #expectedAttributeValues() expected ones}.
 */
class Shipment_ShipmentStatus_Test
        extends AttributeEnum_Test.OfStringTest<ShipmentStatus> {

    Shipment_ShipmentStatus_Test() {
        super(ShipmentStatus.class);
    }

    /**
     * {@inheritDoc}
     *
     * @return the values the {@code shipment_status_c} constraint allows.
     */
    @Override
    protected Set<String> expectedAttributeValues() {
        return Set.of(MappedShipment.COLUMN_VALUE_SHIPMENT_STATUS_CREATED,
                      MappedShipment.COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED,
                      MappedShipment.COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT,
                      MappedShipment.COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED);
    }

    /**
     * Verifies that the column values are the constraint's own text, not only the constants of
     * {@link MappedShipment} which {@link #expectedAttributeValues()} names.
     */
    @Test
    void attributeValue_ConstraintText_Each() {
        assertThat(ShipmentStatus.CREATED.attributeValue()).isEqualTo("CREATED");
        assertThat(ShipmentStatus.SHIPPED.attributeValue()).isEqualTo("SHIPPED");
        assertThat(ShipmentStatus.IN_TRANSIT.attributeValue()).isEqualTo("IN-TRANSIT");
        assertThat(ShipmentStatus.DELIVERED.attributeValue()).isEqualTo("DELIVERED");
    }

    /**
     * Verifies that {@link ShipmentStatus#IN_TRANSIT}'s column value is not its name, which is why
     * the enum is mapped with a converter rather than {@code @Enumerated(EnumType.STRING)}.
     */
    @Test
    void attributeValue_NotName_InTransit() {
        assertThat(ShipmentStatus.IN_TRANSIT.attributeValue())
                .isNotEqualTo(ShipmentStatus.IN_TRANSIT.name());
    }
}
