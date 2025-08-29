package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Region_Persister extends __MappedEntityPersister<Region, Long> {

    Region_Persister() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Region entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
