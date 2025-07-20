package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProductPersister extends __MappedEntityPersister<Product, Long> {

    ProductPersister() {
        super(Product.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Product entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
