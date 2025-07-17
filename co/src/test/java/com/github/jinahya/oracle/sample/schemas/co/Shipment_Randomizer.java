package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityRandomizer;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class Shipment_Randomizer extends __MappedEntityRandomizer<Shipment, Long> {

    Shipment_Randomizer() {
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
        return super.get();
    }
}
