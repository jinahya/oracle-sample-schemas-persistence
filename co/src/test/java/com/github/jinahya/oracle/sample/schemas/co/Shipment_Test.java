package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Shipment_Test extends __MappedEntity_Test<Shipment, Long> {

    Shipment_Test() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> equals_Verify_Configure(
            @Nonnull final SingleTypeEqualsVerifierApi<Shipment> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier)
//                .withOnlyTheseFields(MappedShipment.ATTRIBUTE_NAME_SHIPMENT_ID)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
