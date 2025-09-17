package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Inventory_Persister extends __MappedEntity_Persister<Inventory, Long> {

    Inventory_Persister() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Inventory entityInstance) {
        entityInstance.setStore(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setProduct(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Product.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
