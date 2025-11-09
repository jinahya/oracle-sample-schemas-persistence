package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Location_Persister extends __MappedEntity_Persister<Location, Integer> {

    Location_Persister() {
        super(Location.class, Integer.class);
    }

    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Location entityInstance) {
        entityInstance.setCountry(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Country.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
