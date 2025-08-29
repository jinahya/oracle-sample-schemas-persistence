package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class Region_Test extends __MappedEntityTest<Region, Long> {

    Region_Test() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Region> createEqualsVerifier() {
        return super.createEqualsVerifier();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Region> configureEqualsVerifier(
            @Nonnull SingleTypeEqualsVerifierApi<Region> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
