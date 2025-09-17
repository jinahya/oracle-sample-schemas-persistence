package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;

class Inventory_PersistenceTest extends __MappedEntity_PersistenceTest<Inventory, Long> {

    Inventory_PersistenceTest() {
        super(Inventory.class, Long.class);
    }
}
