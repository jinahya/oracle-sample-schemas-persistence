package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;

//@AddBeanClasses({
//        _PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
abstract class Product__PersistenceIT extends __MappedEntity_PersistenceIT<Product, Long> {

    Product__PersistenceIT() {
        super(Product.class, Long.class);
    }
}