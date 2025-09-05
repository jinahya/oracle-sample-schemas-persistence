package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityBuilder_Test;

class OrderBuilder_Test extends __MappedEntityBuilder_Test<OrderBuilder, Order, Long> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    OrderBuilder_Test() {
        super(OrderBuilder.class, Order.class, Long.class);
    }
}
