package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;

class OrderItemPersister extends __MappedEntityPersister<OrderItem, OrderItemId> {

    OrderItemPersister() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final OrderItem entityInstance) {
        entityInstance.setOrder(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Order.class)
        );
        entityInstance.setProduct(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class)
        );
//        entityInstance.setShipment(
//                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Shipment.class, entityManager).orElseThrow()
//        );
        entityInstance.setShipment(null);
        super.persist(entityManager, entityInstance);
    }
}
