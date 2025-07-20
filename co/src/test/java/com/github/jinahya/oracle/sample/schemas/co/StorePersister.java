package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class StorePersister extends __MappedEntityPersister<Store, Long> {

    StorePersister() {
        super(Store.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Store entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
