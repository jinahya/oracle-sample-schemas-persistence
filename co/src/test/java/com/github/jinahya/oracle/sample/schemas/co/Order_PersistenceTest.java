package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Order_PersistenceTest extends __MappedEntityPersistenceTest<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_PersistenceTest() {
        super(Order.class, Long.class);
    }
}
