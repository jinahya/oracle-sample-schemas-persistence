package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class Order_Persister extends __MappedEntity_Persister<Order> {

    Order_Persister() {
        super(Order.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Order persist(final EntityManager entityManager, final Order entityInstance) {
        entityInstance.setCustomer(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Customer.class, entityManager).orElseThrow()
        );
        entityInstance.setStore(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Store.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}