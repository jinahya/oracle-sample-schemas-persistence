package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Country_Test extends __MappedEntity_Test<Country, String> {

    Country_Test() {
        super(Country.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Country> equalsVerifier() {
        return super.equalsVerifier();
    }
}