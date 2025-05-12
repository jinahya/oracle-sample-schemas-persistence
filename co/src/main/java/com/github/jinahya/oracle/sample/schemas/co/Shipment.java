package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__AttributeEnum;
import com.github.jinahya.oracle.sample.schemas.__AttributeEnumConverter;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping {@value Shipment#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Store
 * @see Customer
 */
@Entity
@Table(name = Shipment.TABLE_NAME)
public class Shipment extends __MappedEntity<Shipment, Long> {

    @Serial
    private static final long serialVersionUID = -9090927980604439764L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SHIPMENTS";

    // ----------------------------------------------------------------------------------------------------- SHIPMENT_ID

    /**
     * The name of the table column to which the {@link Shipment_#shipmentId shipmentId} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    // -------------------------------------------------------------------------------------------------------- STORE_ID
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // ------------------------------------------------------------------------------------------------ DELIVERY_ADDRESS
    public static final String COLUMN_NAME_DELIVERY_ADDRESS = "DELIVERY_ADDRESS";

    // ------------------------------------------------------------------------------------------------- SHIPMENT_STATUS
    public static final String COLUMN_NAME_SHIPMENT_STATUS = "SHIPMENT_STATUS";

    public interface __ShipmentStatus<E extends Enum<E> & __ShipmentStatus<E>> extends __AttributeEnum.__OfString<E> {

    }

    public enum _ShipmentStatus implements __ShipmentStatus<Shipment._ShipmentStatus> {

        /**
         * .
         */
        // 준비 중
        CREATED,

        /**
         * .
         */
        // 출고
        SHIPPED,

        /**
         * .
         */
        // 배송 중
        IN_TRANSIT("IN-TRANSIT"),

        /**
         * .
         */
        // 완료
        DELIVERED;

        // -------------------------------------------------------------------------------------------------------------
        _ShipmentStatus(final String attributeValue) {
            this.attributeValue = attributeValue;
        }

        _ShipmentStatus() {
            this(null);
        }

        // ---------------------------------------------------------------------------------------------- attributeValue
        @Override
        public String getAttributeValue() {
            return Optional.ofNullable(attributeValue)
                    .orElseGet(__ShipmentStatus.super::getAttributeValue);
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
    public static Shipment from(final Order order) {
        Objects.requireNonNull(order, "order is null");
        final var instance = new Shipment();
        instance.setCustomer(order.getCustomer());
        instance.setStore(order.getStore());
        return instance;
    }

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
        return getShipmentId();
    }

    @Override
    protected final void _id_(final Long _id_) {
        setShipmentId(_id_);
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    public Long getShipmentId() {
        return shipmentId;
    }

    void setShipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    @Nonnull
    public Store getStore() {
        return store;
    }

    public void setStore(@Nonnull final Store store) {
        this.store = store;
    }

    // -------------------------------------------------------------------------------------------------------- customer
    @Nonnull
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
    }

    // ------------------------------------------------------------------------------------------------- deliveryAddress
    @Nonnull
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(@Nonnull final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // -------------------------------------------------------------------------------------------------- shipmentStatus
    @Nonnull
    public _ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(@Nonnull final _ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

//    public <E extends Enum<E> & __ShipmentStatus<E>> E getShipmentStatusAsEnum(final Class<E> enumClass) {
//        Objects.requireNonNull(enumClass, "enumClass is null");
//        return Optional.ofNullable(getShipmentStatus())
//                .map(v -> __AttributeEnum.valueOfAttributeValue(enumClass, v))
//                .orElse(null);
//    }
//
//    public void setShipmentStatusAsEnum(final __ShipmentStatus<?> shipmentStatusAsEnum) {
//        setShipmentStatus(
//                Optional.ofNullable(shipmentStatusAsEnum)
//                        .map(__AttributeEnum::getAttributeValue)
//                        .orElse(null)
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = false,
//            insertable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    @jakarta.annotation.Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @jakarta.annotation.Nonnull
    @Size(max = 512)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS, nullable = false, insertable = true, updatable = true)
    private String deliveryAddress;

    @jakarta.annotation.Nonnull
//    @Size(max = 100)
    @NotNull
    @Convert(converter = _ShipmentAttributeConverter.class)
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS, nullable = false, insertable = true, updatable = true)
    private _ShipmentStatus shipmentStatus;
}