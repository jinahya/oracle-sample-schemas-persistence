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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedShipment;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import com.github.jinahya.persistence.more.test.__AttributeEnum_TestUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;
import java.util.Optional;

@Slf4j
class Shipment_Randomizer extends __MappedEntity_Randomizer<Shipment, Long> {

    Shipment_Randomizer() {
        super(Shipment.class, Long.class, List.of(
                MappedShipment.ATTRIBUTE_NAME_STORE_ID,
                MappedShipment.ATTRIBUTE_NAME_STORE,
                MappedShipment.ATTRIBUTE_NAME_CUSTOMER_ID,
                MappedShipment.ATTRIBUTE_NAME_CUSTOMER
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Nonnull
    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    public Shipment get() {
        final var value = super.get();
        value.setShipmentStatus(
                Optional.ofNullable(
                        __AttributeEnum_TestUtils.getRandomAttributeValue(Shipment._ShipmentStatus.class)
                ).orElseThrow()
        );
        return value;
    }
}
