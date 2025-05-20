package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class Country_Test extends __MappedEntity_Test<Country, Long> {

    Country_Test() {
        super(Country.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_verify_() {
        super.equals_verify_();
    }

    @Override
    protected SingleTypeEqualsVerifierApi<Country> equalsVerifier() {
        return super.equalsVerifier();
    }
}