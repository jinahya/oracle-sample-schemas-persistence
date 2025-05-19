package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import jakarta.persistence.EntityManager;

class Country_Persister extends __MappedEntity_Persister<Country> {

    Country_Persister() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Country persist(final EntityManager entityManager, final Country entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}