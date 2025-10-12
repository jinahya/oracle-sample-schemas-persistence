package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped._MappedShEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Country_Persister extends _MappedShEntity_Persister<Country, Long> {

    Country_Persister() {
        super(Country.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Country entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
