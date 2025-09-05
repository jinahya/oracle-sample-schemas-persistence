package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Store_PersistenceTest extends __MappedEntity_PersistenceTest<Store, Long> {

    Store_PersistenceTest() {
        super(Store.class, Long.class);
    }
}
