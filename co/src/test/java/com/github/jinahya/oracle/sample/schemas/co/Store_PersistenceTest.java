package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Store_PersistenceTest extends __MappedEntityPersistenceTest<Store, Long> {

    Store_PersistenceTest() {
        super(Store.class, Long.class);
    }
}
