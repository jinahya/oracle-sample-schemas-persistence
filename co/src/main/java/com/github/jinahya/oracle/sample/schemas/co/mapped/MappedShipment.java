package com.github.jinahya.oracle.sample.schemas.co.mapped;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.persistence.more.__AttributeEnum;
import com.github.jinahya.persistence.more.__AttributeEnumConverter;
import com.github.jinahya.persistence.more.__AttributeEnumUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S114", // Interface names should comply with a naming convention
        "java:S119", // Type parameter names should comply with a naming convention
        "java:S2637" // "@NonNull" values should not be set to null
})
public abstract class MappedShipment
        extends _MappedCoEntity<Long> {

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

    public static final String COLUMN_VALUE_SHIPMENT_STATUS_CREATED = "CREATED";

    public static final String COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED = "SHIPPED";

    public static final String COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT = "IN-TRANSIT";

    public static final String COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED = "DELIVERED";

    public static final String ATTRIBUTE_NAME_SHIPMENT_STATUS = "shipmentStatus";

    public static final int ATTRIBUTE_SIZE_MIN_SHIPMENT_STATUS = 0;

    public static final int ATTRIBUTE_SIZE_MAX_SHIPMENT_STATUS = COLUMN_LENGTH_SHIPMENT_STATUS;

    public interface __ShipmentStatus<E extends Enum<E> & __ShipmentStatus<E>>
            extends __AttributeEnum.__OfString<E> {

    }

    public enum _ShipmentStatus implements __ShipmentStatus<MappedShipment._ShipmentStatus> {

        /**
         * .
         */
        // 준비 중?
        CREATED,

        /**
         * .
         */
        // 발송/출고?
        SHIPPED,

        /**
         * .
         */
        // 배송/운송 중?
        IN_TRANSIT(COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT),

        /**
         * .
         */
        // 배달됨?
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
        private final @Nullable String attributeValue;
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

    protected MappedShipment(final MappedShipmentBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "shipmentId=" + shipmentId +
               ",storeId=" + storeId +
               ",customerId=" + customerId +
               ",deliveryAddress=" + deliveryAddress +
               ",shipmentStatus=" + shipmentStatus +
               '}';
    }

    protected final boolean equalsWithShipmentId(final Object obj) {
        if (!(obj instanceof MappedShipment that)) {
            return false;
        }
        return Objects.equals(getShipmentId(), that.getShipmentId());
    }

    protected final int hashCodeWithShipmentId() {
        return Objects.hashCode(getShipmentId());
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    public Long getShipmentId() {
        return shipmentId;
    }

    protected void setShipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Nonnull
    public Long getStoreId() {
        return storeId;
    }

    protected void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    @Nonnull
    public Long getCustomerId() {
        return customerId;
    }

    protected void setCustomerId(@Nonnull final Long customerId) {
        this.customerId = customerId;
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
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Long storeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Long customerId;

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
    @Size(max = ATTRIBUTE_SIZE_MAX_SHIPMENT_STATUS)
    @NotNull
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHIPMENT_STATUS
    )
    private String shipmentStatus;
}
