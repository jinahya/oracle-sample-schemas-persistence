package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;

class Region_PersistenceTest extends __MappedEntityPersistenceTest<Region, Long> {

    Region_PersistenceTest() {
        super(Region.class, Long.class);
    }
}
