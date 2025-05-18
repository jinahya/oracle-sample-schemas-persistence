package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class Shipment_Persister extends __MappedEntity_Persister<Shipment> {

    Shipment_Persister() {
        super(Shipment.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Shipment persist(final EntityManager entityManager, final Shipment entityInstance) {
        entityInstance.setStore(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Store.class, entityManager).orElseThrow()
        );
        entityInstance.setCustomer(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Customer.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}