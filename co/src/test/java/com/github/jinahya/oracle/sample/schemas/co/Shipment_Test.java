package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class Shipment_Test extends __MappedEntityTest<Shipment, Long> {

    Shipment_Test() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> createEqualsVerifier() {
        return super.createEqualsVerifier();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> configureEqualsVerifier(
            @Nonnull final SingleTypeEqualsVerifierApi<Shipment> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier)
//                .withOnlyTheseFields(MappedShipment.ATTRIBUTE_NAME_SHIPMENT_ID)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
