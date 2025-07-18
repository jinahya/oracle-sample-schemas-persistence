package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import com.github.jinahya.persistence.more.__AttributeEnum;
import com.github.jinahya.persistence.more.__AttributeEnumConverter;
import com.github.jinahya.persistence.more.__AttributeEnumUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
public class Shipment extends __MappedEntity<Shipment, Long> {

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
        public String attributeValue() {
            return Optional.ofNullable(attributeValue)
                    .orElseGet(__ShipmentStatus.super::attributeValue);
        }

        // -------------------------------------------------------------------------------------------------------------
        private final String attributeValue;
    }

    @Converter(autoApply = true)
    public static class _ShipmentStatusConverter extends __AttributeEnumConverter.__OfString<_ShipmentStatus> {

        _ShipmentStatusConverter() {
            super(_ShipmentStatus.class);
        }
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
//    @Nonnull
//    static <
//            SHIPMENT extends Shipment<SHIPMENT>,
//            ORDER extends Order<ORDER, ?, ?>
//            >
//    SHIPMENT from(@Nonnull final Supplier<? extends SHIPMENT> instantiator,
//                  @Nonnull final ORDER order) {
//        Objects.requireNonNull(instantiator, "instantiator is null");
//        Objects.requireNonNull(order, "order is null");
//        final var instance = Objects.requireNonNull(instantiator.get(), "null supplied from " + instantiator);
//        instance.setCustomer(order.getCustomer());
//        instance.setStore(order.getStore());
//        return instance;
//    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Shipment() {
        super();
    }

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

    // ------------------------------------------------------------------------------------------------------ super.id__
    @Override
    protected Long getId__() {
        return getShipmentId();
    }

    @Override
    protected void setId__(final Long id__) {
        setShipmentId(id__);
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
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(@Nonnull final String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public <E extends Enum<E> & __ShipmentStatus<E>> E getShipmentStatusAsEnum(@Nonnull final Class<E> enumClass) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        return Optional.ofNullable(getShipmentStatus())
                .map(v -> __AttributeEnumUtils.valueOfAttributeValue(enumClass, v))
                .orElse(null);
    }

    public void setShipmentStatusAsEnum(final __ShipmentStatus<?> shipmentStatusAsEnum) {
        setShipmentStatus(
                Optional.ofNullable(shipmentStatusAsEnum)
                        .map(__AttributeEnum::attributeValue)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = false,
//            insertable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    @Nonnull
    @Size(max = 512)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS, nullable = false, insertable = true, updatable = true)
    private String deliveryAddress;

    @Nonnull
    @Size(max = 100)
    @NotNull
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS, nullable = false, insertable = true, updatable = true, length = 100)
    private String shipmentStatus;
}
