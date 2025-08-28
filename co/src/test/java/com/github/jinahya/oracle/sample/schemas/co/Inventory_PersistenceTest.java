package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;

class Inventory_PersistenceTest extends __MappedEntityPersistenceTest<Inventory, Long> {

    Inventory_PersistenceTest() {
        super(Inventory.class, Long.class);
    }
}
