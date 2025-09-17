package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Store_Test extends _MappedCoEntity_Test<Store, Long> {

    Store_Test() {
        super(Store.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Store> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Store> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .withOnlyTheseFields(MappedStore.ATTRIBUTE_NAME_STORE_NAME)
                ;
    }
}
