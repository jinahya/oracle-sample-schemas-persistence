package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__AttributeEnum;
import com.github.jinahya.oracle.sample.schemas.__AttributeEnumConverter;
import com.github.jinahya.oracle.sample.schemas.__MappedSuperclass;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.Optional;

@Entity
@Table(name = Shipment.TABLE_NAME)
public class Shipment extends __MappedSuperclass<Shipment, Long> {

    @Serial
    private static final long serialVersionUID = -9090927980604439764L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "SHIPMENTS";

    // ----------------------------------------------------------------------------------------------------- SHIPMENT_ID
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    // ------------------------------------------------------------------------------------------------ STORE_ID
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ------------------------------------------------------------------------------------------------ CUSTOMER_ID
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // ------------------------------------------------------------------------------------------------ DELIVERY_ADDRESS
    public static final String COLUMN_NAME_DELIVERY_ADDRESS = "DELIVERY_ADDRESS";

    // ------------------------------------------------------------------------------------------------ SHIPMENT_STATUS
    public static final String COLUMN_NAME_SHIPMENT_STATUS = "SHIPMENT_STATUS";

    public interface __ShipmentStatus<E extends Enum<E> & __ShipmentStatus<E>> extends __AttributeEnum.__OfString<E> {

    }

    public enum _ShipmentStatus implements __ShipmentStatus<Shipment._ShipmentStatus> {

        CREATED,

        SHIPPED,

        IN_TRANSIT("IN-TRANSIT"),

        DELIVERED;

        // -------------------------------------------------------------------------------------------------------------
        _ShipmentStatus(final String attributeValue) {
            this.attributeValue = attributeValue;
        }

        _ShipmentStatus() {
            this(null);
        }

        // --------------------------------------------------------------------------------------------------- textValue
        @Override
        public String getAttributeValue() {
            return Optional.ofNullable(attributeValue).orElseGet(__ShipmentStatus.super::getAttributeValue);
        }

        // -------------------------------------------------------------------------------------------------------------
        private final String attributeValue;
    }

    @Converter(autoApply = true)
    static class _ShipmentAttributeConverter extends __AttributeEnumConverter.__OfString<_ShipmentStatus> {

        _ShipmentAttributeConverter() {
            super(_ShipmentStatus.class);
        }
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "shipmentId=" + shipmentId +
//                ",store=" + store +
//                ",customer=" + customer +
                ",deliveryAddress=" + deliveryAddress +
                ",shipmentStatus=" + shipmentStatus +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return shipmentId;
    }

    @Override
    protected final void _id_(final Long _id_) {
        shipmentId = _id_;
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    public Long getShipmentId() {
        return shipmentId;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    // -------------------------------------------------------------------------------------------------------- customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    // ------------------------------------------------------------------------------------------------- deliveryAddress

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // -------------------------------------------------------------------------------------------------- shipmentStatus
    public _ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(final _ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = false, insertable = false, updatable = false)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @Size(max = 512)
    @NotNull
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS, nullable = false, insertable = true, updatable = true, length = 512)
    private String deliveryAddress;

    @Size(max = 100)
    @NotNull
//    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS, nullable = false, insertable = true, updatable = true, length = 100)
    private _ShipmentStatus shipmentStatus;
}