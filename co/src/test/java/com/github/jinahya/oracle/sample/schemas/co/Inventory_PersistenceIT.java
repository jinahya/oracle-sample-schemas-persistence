package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

class Inventory_PersistenceIT extends __MappedEntityPersistenceIT<Inventory, Long> {

    Inventory_PersistenceIT() {
        super(Inventory.class, Long.class);
    }
}
