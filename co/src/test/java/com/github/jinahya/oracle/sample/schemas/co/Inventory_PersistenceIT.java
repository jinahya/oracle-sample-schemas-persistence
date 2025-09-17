package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;

class Inventory_PersistenceIT extends _MappedCoEntity_PersistenceIT<Inventory, Long> {

    Inventory_PersistenceIT() {
        super(Inventory.class, Long.class);
    }
}
