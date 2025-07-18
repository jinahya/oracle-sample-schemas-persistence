package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;

class ShipmentPersister extends __MappedEntityPersister<Shipment, Long> {

    ShipmentPersister() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Shipment entityInstance) {
        entityInstance.setStore(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setCustomer(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
