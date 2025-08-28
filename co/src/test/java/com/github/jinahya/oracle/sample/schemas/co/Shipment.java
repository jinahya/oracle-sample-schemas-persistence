package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedShipment.TABLE_NAME)
public class Shipment extends MappedShipment<Store, Customer> {

    public static MappedShipmentBuilder<?, Shipment, ?, ?> builder() {
        return new ShipmentBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Shipment() {
        super();
    }

    Shipment(final ShipmentBuilder builder) {
        super(builder);
    }
}
