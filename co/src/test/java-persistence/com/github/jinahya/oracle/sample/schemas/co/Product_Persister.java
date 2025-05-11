package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import jakarta.persistence.EntityManager;

/**
 * .
 *
 * @see Product_Randomizer
 */
class Product_Persister extends __MappedEntity_Persister<Product> {

    Product_Persister() {
        super(Product.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Product persist(final EntityManager entityManager, final Product entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}