package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

class CustomerPersistenceIT extends __MappedEntityPersistenceIT<Customer, Long> {

    CustomerPersistenceIT() {
        super(Customer.class, Long.class);
    }
}
