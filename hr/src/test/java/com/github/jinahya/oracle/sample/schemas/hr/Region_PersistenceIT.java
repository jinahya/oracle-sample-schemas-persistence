package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;

class Region_PersistenceIT extends __MappedEntity_PersistenceIT<Region, Long> {

    Region_PersistenceIT() {
        super(Region.class, Long.class);
    }
}
