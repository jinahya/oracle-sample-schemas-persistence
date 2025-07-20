package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityRandomizer;
import com.github.jinahya.persistence.more.test.__AttributeEnumTestUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.Optional;

@Slf4j
class ShipmentRandomizer extends __MappedEntityRandomizer<Shipment, Long> {

    ShipmentRandomizer() {
        super(Shipment.class, Long.class, "shipmentId", "shipmentStatus");
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
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
                        __AttributeEnumTestUtils.getRandomAttributeValue(Shipment._ShipmentStatus.class)
                ).orElseThrow()
        );
        return value;
    }
}
