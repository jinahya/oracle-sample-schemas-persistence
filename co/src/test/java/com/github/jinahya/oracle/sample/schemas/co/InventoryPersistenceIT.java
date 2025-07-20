package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

class InventoryPersistenceIT extends __MappedEntityPersistenceIT<Inventory, Long> {

    InventoryPersistenceIT() {
        super(Inventory.class, Long.class);
    }
}
