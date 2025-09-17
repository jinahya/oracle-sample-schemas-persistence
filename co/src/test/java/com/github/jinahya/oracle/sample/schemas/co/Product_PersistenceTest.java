package com.github.jinahya.oracle.sample.schemas.co;

class Product_PersistenceTest extends _MappedCoEntity_PersistenceTest<Product, Long> {

    Product_PersistenceTest() {
        super(Product.class, Long.class);
    }
}
