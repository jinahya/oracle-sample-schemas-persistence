package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Inventory_Persister extends __MappedEntityPersister<Inventory, Long> {

    Inventory_Persister() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Inventory entityInstance) {
        entityInstance.setStore(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setProduct(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
