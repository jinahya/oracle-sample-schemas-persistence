package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas._MappedEntity_Persister;
import jakarta.persistence.EntityManager;

class Store_Persister extends _MappedEntity_Persister<Store> {

    Store_Persister() {
        super(Store.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Store persist(final EntityManager entityManager, final Store entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}