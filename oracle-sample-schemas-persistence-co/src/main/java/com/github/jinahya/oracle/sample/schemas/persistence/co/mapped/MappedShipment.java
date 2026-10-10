package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * A mapped superclass which holds the mappings of the {@value MappedShipment#TABLE_NAME} table.
 * <p>
 * The {@value MappedShipment#COLUMN_NAME_CUSTOMER_ID} and {@value MappedShipment#COLUMN_NAME_STORE_ID} columns are
 * mapped read-only ({@code insertable = false, updatable = false}), with a {@code protected} getter and no setter, for
 * {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether there
 * is an association at all -- is the extending entity's decision, so the extending entity also owns their writable
 * mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedShipment {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SHIPMENTS";

    // ----------------------------------------------------------------------------------------------------- SHIPMENT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_ID = "shipmentId";

    // -------------------------------------------------------------------------------------- STORE_ID / storeId / store

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    /**
     * The name of an attribute, which this class does not declare, joining on the {@value #COLUMN_NAME_STORE_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ----------------------------------------------------------------------------- CUSTOMER_ID / customerId / customer

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    /**
     * The name of an attribute, which this class does not declare, joining on the {@value #COLUMN_NAME_CUSTOMER_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ------------------------------------------------------------------------------ DELIVERY_ADDRESS / deliveryAddress

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_ADDRESS = "DELIVERY_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_ADDRESS = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_ADDRESS = "deliveryAddress";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_DELIVERY_ADDRESS = COLUMN_LENGTH_DELIVERY_ADDRESS;

    // -------------------------------------------------------------------------------- SHIPMENT_STATUS / shipmentStatus

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_STATUS = "SHIPMENT_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SHIPMENT_STATUS = 100;

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which has been created. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_CREATED = "CREATED";

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which has been shipped. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED = "SHIPPED";

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which is in transit. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT = "IN-TRANSIT";

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which has been delivered. The value
     * is {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED = "DELIVERED";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPMENT_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_STATUS = "shipmentStatus";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_SHIPMENT_STATUS = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_SHIPMENT_STATUS = COLUMN_LENGTH_SHIPMENT_STATUS;

    /**
     * An enum for the {@value #COLUMN_NAME_SHIPMENT_STATUS} column.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum ShipmentStatus {

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_CREATED} value, for a shipment which
         * has been created.
         */
        // 준비 중?
        CREATED,

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED} value, for a shipment which
         * has been shipped.
         */
        // 발송/출고?
        SHIPPED,

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT} value, for a shipment
         * which is in transit. Note that the column value is not this constant's {@link Enum#name() name}.
         */
        // 배송 중?
        IN_TRANSIT(COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT),

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED} value, for a shipment which
         * has been delivered.
         */
        // 배송 왼료?
        DELIVERED;

        // -------------------------------------------------------------------------------------------------------------
        ShipmentStatus(final String columnValue) {
            this.columnValue = columnValue;
        }

        ShipmentStatus() {
            this(null);
        }

        // ------------------------------------------------------------------------------------------------------------- columnValue

        /**
         * Returns the value of the {@value MappedShipment#COLUMN_NAME_SHIPMENT_STATUS} column which this constant
         * represents.
         *
         * @return the column value of this constant; the constant's {@link Enum#name() name} unless it declares its
         * own.
         */
        public String columnValue() {
            if (columnValue != null) {
                return columnValue;
            }
            return name();
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The column value this constant declares; {@code null} for a constant whose column value is its
         * {@link Enum#name() name}.
         *
         * @see #columnValue()
         */
        public final String columnValue;
    }

    /**
     * A converter between {@link ShipmentStatus} and the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, by
     * {@link ShipmentStatus#columnValue()} rather than {@link Enum#name()}.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    @Converter(autoApply = false)
    public static final class ShipmentStatusConverter implements AttributeConverter<ShipmentStatus, String> {

        /**
         * Creates a new instance.
         */
        public ShipmentStatusConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final ShipmentStatus attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.columnValue();
        }

        /**
         * {@inheritDoc}
         *
         * @param dbData {@inheritDoc}
         * @return {@inheritDoc}
         * @throws IllegalArgumentException if {@code dbData} is the column value of no {@link ShipmentStatus}
         *                                  constant.
         */
        @Override
        public ShipmentStatus convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            for (final var value : ShipmentStatus.values()) {
                if (value.columnValue().equals(dbData)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no shipment status for '" + dbData + "'");
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedShipment() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "shipmentId=" + shipmentId +
               ",storeId=" + storeId +
               ",customerId=" + customerId +
               ",deliveryAddress=" + deliveryAddress +
               ",shipmentStatus=" + shipmentStatus +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the generated {@code @Id} alone, the other instance's read through its getter, which is
     * what makes the comparison correct when the other instance is still a lazy proxy. An instance whose {@code @Id} is
     * still {@code null} -- one not yet persisted -- equals itself only.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedShipment that)) {
            return false;
        }
        final var shipmentId = getShipmentId();
        return shipmentId != null && shipmentId.equals(that.getShipmentId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is constant so that it does not change when the generated {@code @Id} is assigned on persist,
     * which would lose an instance already held in a hash-based collection. It is {@code MappedShipment}'s rather than
     * {@link #getClass()}'s, because a lazy proxy's class is a generated subclass and must hash alike to the instance
     * it stands for.
     */
    @Override
    public final int hashCode() {
        return MappedShipment.class.hashCode();
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    public Long getShipmentId() {
        return shipmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute with the specified value.
     *
     * @param shipmentId new value for {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    protected void setShipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // --------------------------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getStoreId() {
        return storeId;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute, which is {@code null} until this
     * instance is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getCustomerId() {
        return customerId;
    }

    // ------------------------------------------------------------------------------------------------- deliveryAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute.
     */
    @Nonnull
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute with the specified value.
     *
     * @param deliveryAddress new value for {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute.
     */
    public void setDeliveryAddress(@Nonnull final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // -------------------------------------------------------------------------------------------------- shipmentStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute.
     */
    @Nonnull
    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute with the specified value.
     *
     * @param shipmentStatus new value for {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute.
     */
    public void setShipmentStatus(@Nonnull final ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // no @NotNull: database-generated identity; the value is null when the provider validates at pre-persist
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5.0.1.
            insertable = true,
            updatable = false)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // no @NotNull: read-only duplicate of a column the extending entity writes; null until a load
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Long storeId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    // no @NotNull: read-only duplicate of a column the extending entity writes; null until a load
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
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
    @NotNull
    @Convert(converter = ShipmentStatusConverter.class)
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHIPMENT_STATUS
    )
    private ShipmentStatus shipmentStatus;
}
