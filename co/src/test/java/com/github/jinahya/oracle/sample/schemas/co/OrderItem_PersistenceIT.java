package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OrderItem_PersistenceIT extends __MappedEntityPersistenceIT<OrderItem, OrderItemId> {

    OrderItem_PersistenceIT() {
        super(OrderItem.class, OrderItemId.class);
    }
}
