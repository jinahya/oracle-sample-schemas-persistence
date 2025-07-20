package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OrderItemPersistenceTest extends __MappedEntityPersistenceTest<OrderItem, OrderItemId> {

    OrderItemPersistenceTest() {
        super(OrderItem.class, OrderItemId.class);
    }
}
