package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;

@Slf4j
class Shipment_Persister extends __MappedEntityPersister<Shipment, Long> {

    Shipment_Persister() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Shipment entityInstance) {
        entityInstance.setStore(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setCustomer(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
