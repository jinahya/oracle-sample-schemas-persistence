package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Order_PersistenceIT extends _MappedCoEntity_PersistenceIT<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_PersistenceIT() {
        super(Order.class, Long.class);
    }
}
