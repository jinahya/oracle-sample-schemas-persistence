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

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedShipment.TABLE_NAME)
class Shipment extends MappedShipment<Store, Customer> {

    static MappedShipmentBuilder<?, Shipment, ?, ?> builder() {
        return new ShipmentBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Shipment() {
        super();
    }

    Shipment(final ShipmentBuilder builder) {
        super(builder);
    }
}
