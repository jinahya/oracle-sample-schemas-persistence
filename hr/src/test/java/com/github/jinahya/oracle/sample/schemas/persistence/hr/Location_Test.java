package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Location_Test extends __MappedEntity_Test<Location, Integer> {

    Location_Test() {
        super(Location.class, Integer.class);
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Location> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Location> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}