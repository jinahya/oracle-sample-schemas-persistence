package com.github.jinahya.oracle.sample.schemas.co;

class ShipmentBuilder extends MappedShipmentBuilder<ShipmentBuilder, Shipment, Store, Customer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    ShipmentBuilder() {
        super(Shipment.class);
    }
}
