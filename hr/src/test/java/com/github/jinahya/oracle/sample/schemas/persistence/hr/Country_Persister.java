package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Country_Persister extends __MappedEntity_Persister<Country, String> {

    Country_Persister() {
        super(Country.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Country entityInstance) {
        entityInstance.setRegion(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Region.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
