package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

class Region_PersistenceIT extends __MappedEntityPersistenceIT<Region, Long> {

    Region_PersistenceIT() {
        super(Region.class, Long.class);
    }
}
