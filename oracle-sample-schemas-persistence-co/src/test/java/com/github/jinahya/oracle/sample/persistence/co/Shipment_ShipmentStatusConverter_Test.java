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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment.ShipmentStatus;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment.ShipmentStatusConverter;
import com.github.jinahya.persistence.more.converter.test.AttributeEnumConverter_Test;

import java.util.stream.Stream;

/**
 * Tests {@link ShipmentStatusConverter}, and so {@link ShipmentStatus}, on the base of
 * {@code jinahya-persistence-more-test}, which checks the null conversions, the conversion of every
 * constant both ways, the rejection of the {@link #unknownAttributeValues() unknown values} and of a
 * value which is not a {@code String}, and the enum as its own base does; the enum's
 * expected values are checked by {@link Shipment_ShipmentStatus_Test}.
 */
class Shipment_ShipmentStatusConverter_Test
        extends AttributeEnumConverter_Test.OfStringTest<ShipmentStatusConverter, ShipmentStatus> {

    Shipment_ShipmentStatusConverter_Test() {
        super(ShipmentStatusConverter.class, ShipmentStatus.class);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote Adds, to the base's, the near misses of this column: the constant's name for a value
     *         which is not it, another case, and an empty text.
     */
    @Override
    protected Stream<String> unknownAttributeValues() {
        return Stream.concat(super.unknownAttributeValues(),
                             Stream.of("IN_TRANSIT", "in-transit", "Delivered", ""));
    }
}
