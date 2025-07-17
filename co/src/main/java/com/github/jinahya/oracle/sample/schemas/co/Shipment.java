package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity class for mapping {@value _MappedShipment#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Store
 * @see Customer
 */
@Entity
@Table(name = _MappedShipment.TABLE_NAME)
public class Shipment extends _MappedShipment<Shipment> {

    protected Shipment() {
        super();
    }
}
