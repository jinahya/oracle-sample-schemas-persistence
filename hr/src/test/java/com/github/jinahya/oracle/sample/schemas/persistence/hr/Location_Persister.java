package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

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