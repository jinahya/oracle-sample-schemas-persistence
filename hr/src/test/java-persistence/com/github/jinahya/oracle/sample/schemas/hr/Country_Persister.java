package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class Country_Persister extends __MappedEntity_Persister<Country> {

    Country_Persister() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected Country persist(EntityManager entityManager, Country entityInstance) {
        entityInstance.setRegion(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Region.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}