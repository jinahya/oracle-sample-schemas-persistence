package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Store_PersistenceIT extends __MappedEntityPersistenceIT<Store, Long> {

    Store_PersistenceIT() {
        super(Store.class, Long.class);
    }
}
