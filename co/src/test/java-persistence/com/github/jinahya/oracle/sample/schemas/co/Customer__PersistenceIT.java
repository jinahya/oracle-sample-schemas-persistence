package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
abstract class Customer__PersistenceIT extends __MappedEntity_PersistenceIT<Customer, Long> {

    Customer__PersistenceIT() {
        super(Customer.class, Long.class);
    }
}