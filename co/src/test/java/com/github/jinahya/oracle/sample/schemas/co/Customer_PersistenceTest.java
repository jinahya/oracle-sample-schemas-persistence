package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;

class Customer_PersistenceTest extends __MappedEntity_PersistenceTest<Customer, Long> {

    Customer_PersistenceTest() {
        super(Customer.class, Long.class);
    }
}
