package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import jakarta.persistence.EntityManager;

class CustomerPersister extends __MappedEntityPersister<Customer, Long> {

    CustomerPersister() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Customer entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
