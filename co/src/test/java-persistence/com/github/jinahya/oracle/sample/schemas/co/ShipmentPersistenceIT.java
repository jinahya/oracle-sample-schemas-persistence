package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;

//@AddBeanClasses({
//        _PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
class ShipmentPersistenceIT extends __MappedEntityPersistenceIT<Shipment, Long> {

    ShipmentPersistenceIT() {
        super(Shipment.class, Long.class);
    }
}
