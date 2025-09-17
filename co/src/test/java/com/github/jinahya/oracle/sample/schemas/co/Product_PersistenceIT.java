package com.github.jinahya.oracle.sample.schemas.co;

class Product_PersistenceIT extends _MappedCoEntity_PersistenceIT<Product, Long> {

    Product_PersistenceIT() {
        super(Product.class, Long.class);
    }
}
