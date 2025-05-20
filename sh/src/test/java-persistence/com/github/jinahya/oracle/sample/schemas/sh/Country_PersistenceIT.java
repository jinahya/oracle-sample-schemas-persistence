package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
class Country_PersistenceIT extends __MappedEntity_PersistenceIT<Country, Long> {

    Country_PersistenceIT() {
        super(Country.class, Long.class);
    }
}