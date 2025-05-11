package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class Inventory_Persister extends __MappedEntity_Persister<Inventory> {

    Inventory_Persister() {
        super(Inventory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Inventory persist(final EntityManager entityManager, final Inventory entityInstance) {
        entityInstance.setStore(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Store.class, entityManager).orElseThrow()
        );
        entityInstance.setProduct(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Product.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}