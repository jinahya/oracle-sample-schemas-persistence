package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;

class OrderItemTest extends __MappedEntityTest<OrderItem, OrderItemId> {

    OrderItemTest() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
