package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

class Customer_PersistenceIT extends __MappedEntityPersistenceIT<Customer, Long> {

    Customer_PersistenceIT() {
        super(Customer.class, Long.class);
    }
}
