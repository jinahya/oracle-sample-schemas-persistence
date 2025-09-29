package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;

class Country_PersistenceTest extends __MappedEntity_PersistenceTest<Country, String> {

    Country_PersistenceTest() {
        super(Country.class, String.class);
    }
}
