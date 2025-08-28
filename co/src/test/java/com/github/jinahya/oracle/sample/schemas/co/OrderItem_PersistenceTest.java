package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OrderItem_PersistenceTest extends __MappedEntityPersistenceTest<OrderItem, OrderItemId> {

    OrderItem_PersistenceTest() {
        super(OrderItem.class, OrderItemId.class);
    }
}
