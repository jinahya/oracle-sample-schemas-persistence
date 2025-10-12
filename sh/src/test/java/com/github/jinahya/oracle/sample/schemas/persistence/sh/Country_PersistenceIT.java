package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped._MappedShEntity_PersistenceIT;

class Country_PersistenceIT extends _MappedShEntity_PersistenceIT<Country, Long> {

    Country_PersistenceIT() {
        super(Country.class, Long.class);
    }
}
