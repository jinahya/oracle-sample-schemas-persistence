package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Order_Persister extends __MappedEntityPersister<Order, Long> {

    Order_Persister() {
        super(Order.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Order entityInstance) {
        entityInstance.setCustomer(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class)
        );
        entityInstance.setStore(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
