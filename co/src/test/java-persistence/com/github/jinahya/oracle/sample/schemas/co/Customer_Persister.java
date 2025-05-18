package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import jakarta.persistence.EntityManager;

class Customer_Persister extends __MappedEntity_Persister<Customer> {

    Customer_Persister() {
        super(Customer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Customer persist(final EntityManager entityManager, final Customer entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}