package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

//@AddBeanClasses({
//        _PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
abstract class ProductPersistenceIT extends __MappedEntityPersistenceIT<Product, Long> {

    ProductPersistenceIT() {
        super(Product.class, Long.class);
    }
}
