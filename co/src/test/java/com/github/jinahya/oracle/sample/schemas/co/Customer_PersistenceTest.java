package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceTest;

class Customer_PersistenceTest extends __MappedEntityPersistenceTest<Customer, Long> {

    Customer_PersistenceTest() {
        super(Customer.class, Long.class);
    }
}
