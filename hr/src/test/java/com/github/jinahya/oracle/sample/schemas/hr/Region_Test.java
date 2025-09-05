package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Region_Test extends __MappedEntity_Test<Region, Long> {

    Region_Test() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Region> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Region> equals_Verify_Configure(
            @Nonnull final SingleTypeEqualsVerifierApi<Region> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
