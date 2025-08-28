package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Order_Test extends __MappedEntityTest<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_Test() {
        super(Order.class, Long.class);
    }
}
