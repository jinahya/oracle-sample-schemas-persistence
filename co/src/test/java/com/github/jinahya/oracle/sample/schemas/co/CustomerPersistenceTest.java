package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;

class CustomerPersistenceTest extends __MappedEntityPersistenceTest<Customer, Long> {

    CustomerPersistenceTest() {
        super(Customer.class, Long.class);
    }
}
