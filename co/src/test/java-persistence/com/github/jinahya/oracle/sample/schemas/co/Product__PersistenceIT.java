package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

//@AddBeanClasses({
//        _PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
abstract class Product__PersistenceIT extends __MappedEntity_PersistenceIT<Product, Long> {

    Product__PersistenceIT() {
        super(Product.class, Long.class);
    }
}