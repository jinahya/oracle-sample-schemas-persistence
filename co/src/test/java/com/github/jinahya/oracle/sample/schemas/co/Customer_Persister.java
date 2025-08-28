package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Customer_Persister extends __MappedEntityPersister<Customer, Long> {

    Customer_Persister() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Customer entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
