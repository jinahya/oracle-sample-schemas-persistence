package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class Location_Persister extends __MappedEntity_Persister<Location> {

    Location_Persister() {
        super(Location.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected Location persist(EntityManager entityManager, Location entityInstance) {
        entityInstance.setCountry(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Country.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}