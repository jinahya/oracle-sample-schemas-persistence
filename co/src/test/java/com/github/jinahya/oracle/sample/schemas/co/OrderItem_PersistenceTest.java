package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OrderItem_PersistenceTest extends __MappedEntity_PersistenceTest<OrderItem, OrderItemId> {

    OrderItem_PersistenceTest() {
        super(OrderItem.class, OrderItemId.class);
    }
}
