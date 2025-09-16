package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;

class Product_PersistenceIT extends __MappedEntity_PersistenceIT<Product, Long> {

    Product_PersistenceIT() {
        super(Product.class, Long.class);
    }
}
