package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Channel_Test extends __MappedEntity_Test<Channel, Long> {

    Channel_Test() {
        super(Channel.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Channel> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Channel> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
        ;
    }
}
