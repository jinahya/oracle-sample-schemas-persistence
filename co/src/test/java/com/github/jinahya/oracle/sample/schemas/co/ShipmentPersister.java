package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ShipmentPersister extends __MappedEntityPersister<Shipment, Long> {

    ShipmentPersister() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Shipment entityInstance) {
        entityInstance.setCustomer(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class)
        );
        entityInstance.setStore(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
