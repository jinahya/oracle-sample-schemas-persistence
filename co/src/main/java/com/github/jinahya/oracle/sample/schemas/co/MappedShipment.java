package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import com.github.jinahya.persistence.more.__AttributeEnum;
import com.github.jinahya.persistence.more.__AttributeEnumConverter;
import com.github.jinahya.persistence.more.__AttributeEnumUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
@SuppressWarnings({
        "java:S2637" // "@NonNull" values should not be set to null
})
public abstract class MappedShipment<STORE extends MappedStore, CUSTOMER extends MappedCustomer>
        extends __MappedEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SHIPMENTS";

    // ----------------------------------------------------------------------------------------------------- SHIPMENT_ID

    /**
     * The name of the table column to which the {@link MappedShipment_#shipmentId shipmentId} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    public static final String ATTRIBUTE_NAME_SHIPMENT_ID = "shipmentId";

    // -------------------------------------------------------------------------------------- STORE_ID / storeId / store
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ----------------------------------------------------------------------------- CUSTOMER_ID / customerId / customer
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ------------------------------------------------------------------------------ DELIVERY_ADDRESS / deliveryAddress
    public static final String COLUMN_NAME_DELIVERY_ADDRESS = "DELIVERY_ADDRESS";

    public static final int COLUMN_LENGTH_DELIVERY_ADDRESS = 512;

    public static final String ATTRIBUTE_DELIVERY_ADDRESS = "deliveryAddress";

    public static final int SIZE_MAX_DELIVERY_ADDRESS = COLUMN_LENGTH_DELIVERY_ADDRESS;

    // -------------------------------------------------------------------------------- SHIPMENT_STATUS / shipmentStatus
    public static final String COLUMN_NAME_SHIPMENT_STATUS = "SHIPMENT_STATUS";

    public static final int COLUMN_LENGTH_SHIPMENT_STATUS = 100;

    public static final String ATTRIBUTE_NAME_SHIPMENT_STATUS = "shipmentStatus";

    public static final int SIZE_MAX_SHIPMENT_STATUS = COLUMN_LENGTH_SHIPMENT_STATUS;

    public interface __ShipmentStatus<E extends Enum<E> & __ShipmentStatus<E>>
            extends __AttributeEnum.__OfString<E> {

    }

    public enum _ShipmentStatus implements __ShipmentStatus<MappedShipment._ShipmentStatus> {

        /**
         * .
         */
        // 준비 중
        CREATED,

        /**
         * .
         */
        // 발송/출고
        SHIPPED,

        /**
         * .
         */
        // 배송/운송 중
        IN_TRANSIT("IN-TRANSIT"),

        /**
         * .
         */
        // 배달됨
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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedShipment() {
        super();
    }

    protected MappedShipment(final MappedShipmentBuilder<?, ?, STORE, CUSTOMER> builder) {
        super(builder);
        storeId = builder.storeId();
        setStore(builder.store()); // TODO: set directly
        customerId = builder.customerId();
        setCustomer(builder.customer()); // TODO: set directly
        deliveryAddress = builder.deliveryAddress();
        shipmentStatus = builder.shipmentStatus();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "shipmentId=" + shipmentId +
                ",storeId=" + storeId +
//                ",store=" + store + // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                ",customerId=" + customerId +
//                ",customer=" + customer + // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                ",deliveryAddress=" + deliveryAddress +
                ",shipmentStatus=" + shipmentStatus +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedShipment<?, ?> that)) {
            return false;
        }
        return Objects.equals(getShipmentId(), that.getShipmentId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getShipmentId());
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
    public STORE getStore() {
        return store;
    }

    void setStore(@Nonnull final STORE store) {
        this.store = store;
        storeId = Optional.ofNullable(this.store)
                .map(MappedStore::getStoreId)
                .orElse(null);
    }

    // -------------------------------------------------------------------------------------------------------- customer
    @Nonnull
    public CUSTOMER getCustomer() {
        return customer;
    }

    void setCustomer(@Nonnull final CUSTOMER customer) {
        this.customer = customer;
        customerId = Optional.ofNullable(this.customer)
                .map(MappedCustomer::getCustomerId)
                .orElse(null);
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

    @Transient
    public void setShipmentStatusFromEnum(final __ShipmentStatus<?> shipmentStatusEnum) {
        setShipmentStatus(
                Optional.ofNullable(shipmentStatusEnum)
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
    @Deprecated(forRemoval = true)
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Long storeId; // TODO: remove

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private STORE store;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Long customerId; // TODO: remove

    @Nonnull
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private CUSTOMER customer;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = SIZE_MAX_DELIVERY_ADDRESS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_ADDRESS
    )
    private String deliveryAddress;

    @Nonnull
    @Size(max = SIZE_MAX_SHIPMENT_STATUS)
    @NotNull
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHIPMENT_STATUS
    )
    private String shipmentStatus;
}
