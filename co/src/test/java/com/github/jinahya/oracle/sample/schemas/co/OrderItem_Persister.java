package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OrderItem_Persister extends __MappedEntityPersister<OrderItem, OrderItemId> {

    OrderItem_Persister() {
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
        entityInstance.setShipment(
                __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Shipment.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
