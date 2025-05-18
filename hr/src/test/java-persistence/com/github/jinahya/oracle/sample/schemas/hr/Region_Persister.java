package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import jakarta.persistence.EntityManager;

class Region_Persister extends __MappedEntity_Persister<Region> {

    Region_Persister() {
        super(Region.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected Region persist(EntityManager entityManager, Region entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}