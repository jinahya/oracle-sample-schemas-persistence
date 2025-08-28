package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Product_Persister extends __MappedEntityPersister<Product, Long> {

    Product_Persister() {
        super(Product.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Product entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
