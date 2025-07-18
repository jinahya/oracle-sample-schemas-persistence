package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;

class InventoryPersister extends __MappedEntityPersister<Inventory, Long> {

    InventoryPersister() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void persist(final EntityManager entityManager, final Inventory entityInstance) {
        entityInstance.setStore(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setProduct(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
