package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import jakarta.persistence.EntityManager;

class OrderItem_Persister extends __MappedEntity_Persister<OrderItem> {

    OrderItem_Persister() {
        super(OrderItem.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected OrderItem persist(final EntityManager entityManager, final OrderItem entityInstance) {
//        entityInstance.getId(OrderItemId::new).setLineItemId(1L);
        entityInstance.setOrder(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Order.class, entityManager).orElseThrow()
        );
        entityInstance.setProduct(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Product.class, entityManager).orElseThrow()
        );
        entityInstance.setShipment(
                __MappedEntity_Persister_Utils.newPersistedInstanceOf(Shipment.class, entityManager).orElseThrow()
        );
        return super.persist(entityManager, entityInstance);
    }
}