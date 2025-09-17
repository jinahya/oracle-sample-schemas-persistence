package com.github.jinahya.oracle.sample.schemas.co;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Store_PersistenceIT extends _MappedCoEntity_PersistenceIT<Store, Long> {

    Store_PersistenceIT() {
        super(Store.class, Long.class);
    }
}
