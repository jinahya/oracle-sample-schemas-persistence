package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
class Country_PersistentIT extends __MappedEntity_PersistenceIT<Country, String> {

    Country_PersistentIT() {
        super(Country.class, String.class);
    }

    @Deprecated(forRemoval = true)
    @DisplayName("findAllByRegionId(regionId)")
    @Nested
    class FindAllByRegionId_Test {

    }

    @DisplayName("findAllByRegion(region)")
    @Nested
    class FindAllByRegion_Test {

    }
}