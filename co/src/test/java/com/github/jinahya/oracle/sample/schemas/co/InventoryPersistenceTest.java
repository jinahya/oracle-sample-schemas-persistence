package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;

class InventoryPersistenceTest extends __MappedEntityPersistenceTest<Inventory, Long> {

    InventoryPersistenceTest() {
        super(Inventory.class, Long.class);
    }
}
