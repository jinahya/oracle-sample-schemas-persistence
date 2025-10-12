package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped._MappedShEntity_PersistenceTest;

class Country_PersistenceTest extends _MappedShEntity_PersistenceTest<Country, Long> {

    Country_PersistenceTest() {
        super(Country.class, Long.class);
    }
}
