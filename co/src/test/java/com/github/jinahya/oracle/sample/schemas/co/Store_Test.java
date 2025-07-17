package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Store_Test extends __MappedEntityTest<Store, Long> {

    Store_Test() {
        super(Store.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void equals_verify() {
        super.equals_verify();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Store> getEqualsVerifier() {
        return super.getEqualsVerifier();
    }
}
