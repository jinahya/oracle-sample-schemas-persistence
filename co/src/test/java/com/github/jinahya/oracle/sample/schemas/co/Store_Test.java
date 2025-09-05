package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Store_Test extends __MappedEntity_Test<Store, Long> {

    Store_Test() {
        super(Store.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Store> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Store> equals_Verify_Configure(
            @Nonnull SingleTypeEqualsVerifierApi<Store> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier)
                .withOnlyTheseFields(MappedStore.ATTRIBUTE_NAME_STORE_NAME)
                ;
    }
}
