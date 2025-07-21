package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OrderItemPersistenceIT extends __MappedEntityPersistenceIT<OrderItem, OrderItemId> {

    OrderItemPersistenceIT() {
        super(OrderItem.class, OrderItemId.class);
    }
}
