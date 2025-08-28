package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityBuilderTest;

class OrderItemBuilder_Test extends __MappedEntityBuilderTest<OrderItemBuilder, OrderItem, OrderItemId> {

    OrderItemBuilder_Test() {
        super(OrderItemBuilder.class, OrderItem.class, OrderItemId.class);
    }
}
