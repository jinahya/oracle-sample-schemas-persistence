package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;

class Product_PersistenceTest extends __MappedEntity_PersistenceTest<Product, Long> {

    Product_PersistenceTest() {
        super(Product.class, Long.class);
    }
}
