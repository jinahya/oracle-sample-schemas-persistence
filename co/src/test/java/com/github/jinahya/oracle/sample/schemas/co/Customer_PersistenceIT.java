package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;

class Customer_PersistenceIT extends __MappedEntity_PersistenceIT<Customer, Long> {

    Customer_PersistenceIT() {
        super(Customer.class, Long.class);
    }
}
