package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;

@Slf4j
class Shipment_Persister extends __MappedEntity_Persister<Shipment, Long> {

    Shipment_Persister() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Shipment entityInstance) {
        entityInstance.setStore(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setCustomer(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Customer.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
