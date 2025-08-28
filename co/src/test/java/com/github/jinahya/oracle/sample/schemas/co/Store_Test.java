package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class Store_Test extends __MappedEntityTest<Store, Long> {

    Store_Test() {
        super(Store.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Store> createEqualsVerifier() {
        return super.createEqualsVerifier();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Store> configureEqualsVerifier(
            @Nonnull SingleTypeEqualsVerifierApi<Store> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier)
                .withOnlyTheseFields(MappedStore.ATTRIBUTE_NAME_STORE_NAME)
                ;
    }
}
