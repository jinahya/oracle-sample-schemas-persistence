package com.github.jinahya.oracle.sample.persistence.co.mapped;

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

import com.github.jinahya.oracle.sample.persistence.common.annotation.OraComment;
import com.github.jinahya.persistence.more.AttributeEnum;
import com.github.jinahya.persistence.more.converter.AttributeEnumConverter;
import jakarta.annotation.Nonnull;
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
 * A mapped superclass for mapping the {@value MappedShipment#TABLE_NAME} table.
 * <p>
 * The {@value #COLUMN_NAME_STORE_ID} and {@value #COLUMN_NAME_CUSTOMER_ID} columns are mapped
 * read-only ({@code insertable = false, updatable = false}), with a public getter and a
 * {@code protected} setter, for {@link #toString()} and for queries. How the relationship behind
 * each is mapped -- fetch type, cascade, whether there is an association at all -- is the extending
 * entity's decision, so the extending entity also owns their writable mapping, by an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}. <strong>An extending entity which maps none
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh, or by the
 * extending entity, which sets them through their {@code protected} setters in step with the
 * associations which write the columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@OraComment("Details of where ordered goods will be delivered")
public abstract class MappedShipment {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SHIPMENTS";

    // --------------------------------------------------------------------------------- SHIPMENT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_ID = "SHIPMENT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPMENT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_ID = "shipmentId";

    // ------------------------------------------------------------------------------------ STORE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column, read-only.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // --------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column, read-only.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ---------------------------------------------------------- DELIVERY_ADDRESS / deliveryAddress

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_ADDRESS = "DELIVERY_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_ADDRESS = 512;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_ADDRESS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_ADDRESS = "deliveryAddress";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_DELIVERY_ADDRESS = COLUMN_LENGTH_DELIVERY_ADDRESS;

    // ------------------------------------------------------------ SHIPMENT_STATUS / shipmentStatus

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIPMENT_STATUS = "SHIPMENT_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SHIPMENT_STATUS = 100;

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which has been
     * created. The value is {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_CREATED = "CREATED";

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which has been
     * shipped. The value is {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED = "SHIPPED";

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which is in
     * transit. The value is {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT = "IN-TRANSIT";

    /**
     * A value of the {@value #COLUMN_NAME_SHIPMENT_STATUS} column, for a shipment which has been
     * delivered. The value is {@value}.
     */
    public static final String COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED = "DELIVERED";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPMENT_STATUS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT_STATUS = "shipmentStatus";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute. The value is
     * {@value}.
     */
    public static final int SIZE_MIN_SHIPMENT_STATUS = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_SHIPMENT_STATUS = COLUMN_LENGTH_SHIPMENT_STATUS;

    /**
     * An enum for the {@value #COLUMN_NAME_SHIPMENT_STATUS} column; the values the
     * {@code shipment_status_c} constraint allows.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     * @see ShipmentStatusConverter
     */
    public enum ShipmentStatus
            implements AttributeEnum.OfString<ShipmentStatus> {

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_CREATED} value,
         * for a shipment which has been created.
         */
        // 준비 중?
        CREATED(COLUMN_VALUE_SHIPMENT_STATUS_CREATED),

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED} value,
         * for a shipment which has been shipped.
         */
        // 발송/출고?
        SHIPPED(COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED),

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT} value,
         * for a shipment which is in transit. Note that the column value is not this constant's
         * {@link Enum#name() name}.
         */
        // 배송 중?
        IN_TRANSIT(COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT),

        /**
         * A constant for the {@value MappedShipment#COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED} value,
         * for a shipment which has been delivered.
         */
        // 배송 왼료?
        DELIVERED(COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED);

        ShipmentStatus(final String attributeValue) {
            this.attributeValue = attributeValue;
        }

        /**
         * Returns the value of the {@value MappedShipment#COLUMN_NAME_SHIPMENT_STATUS} column which
         * this constant represents; not its {@link Enum#name() name()} for
         * {@link #IN_TRANSIT}, whose column value is not a Java identifier.
         *
         * @return the column value of this constant.
         */
        @Override
        public String attributeValue() {
            return attributeValue;
        }

        private final String attributeValue;
    }

    /**
     * Converts between a {@link ShipmentStatus} and its column value, its
     * {@link ShipmentStatus#attributeValue() attributeValue()}, through
     * {@link AttributeEnumConverter.OfString}, which looks a constant up by its value.
     * <p>
     * The converter is not {@link Converter#autoApply() auto-applied}; an attribute names it with
     * {@link Convert @Convert}.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    @Converter(autoApply = false)
    public static class ShipmentStatusConverter
            extends AttributeEnumConverter.OfString<ShipmentStatus>
            // redeclared with its type arguments: EclipseLink 5.0 reads them only from an
            // interface the converter class itself declares, and otherwise drops the @Convert
            // silently, reading IN-TRANSIT as a ShipmentStatus by name and failing
            implements AttributeConverter<ShipmentStatus, String> {

        /**
         * Creates a new instance.
         */
        public ShipmentStatusConverter() {
            super(ShipmentStatus.class);
        }
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedShipment() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the generated {@code @Id} alone, the other instance's read
     *         through its getter, which is what makes the comparison correct when the other
     *         instance is still a lazy proxy. An instance whose {@code @Id} is still {@code null}
     *         -- one not yet persisted -- equals itself only.
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
     * @implSpec The hash is constant so that it does not change when the generated
     *         {@code @Id} is assigned on persist, which would lose an instance already held in a
     *         hash-based collection. It is {@code MappedShipment}'s rather than
     *         {@link #getClass()}'s, because a lazy proxy's class is a generated subclass and must
     *         hash alike to the instance it stands for.
     */
    @Override
    public final int hashCode() {
        return MappedShipment.class.hashCode();
    }

    // ---------------------------------------------------------------------------------- shipmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    public Long getShipmentId() {
        return shipmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute with the specified
     * value.
     *
     * @param shipmentId new value for {@value #ATTRIBUTE_NAME_SHIPMENT_ID} attribute.
     */
    protected void setShipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    // ------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    @Nonnull
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified
     * value.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // ---------------------------------------------------------------------------------- customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    @Nonnull
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified
     * value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(@Nonnull final Long customerId) {
        this.customerId = customerId;
    }

    // ----------------------------------------------------------------------------- deliveryAddress

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute with the
     * specified value.
     *
     * @param deliveryAddress new value for {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS} attribute.
     */
    public void setDeliveryAddress(@Nonnull final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // ------------------------------------------------------------------------------ shipmentStatus

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute with the
     * specified value.
     *
     * @param shipmentStatus new value for {@value #ATTRIBUTE_NAME_SHIPMENT_STATUS} attribute.
     */
    public void setShipmentStatus(@Nonnull final ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    // ---------------------------------------------------------------------------------------------
    // no @NotNull: database-generated identity; the value is null when the provider validates at
    // pre-persist
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SHIPMENT_ID, nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5
            // .0.1.
            insertable = true,
            updatable = false,
            comment = "Auto-incrementing primary key")
    private Long shipmentId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false,
            comment = "Which location the goods will be transported from")
    private Long storeId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false,
            comment = "Who this shipment is for")
    private Long customerId;

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = SIZE_MAX_DELIVERY_ADDRESS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_ADDRESS,
            comment = "Where the goods will be transported to"
    )
    private String deliveryAddress;

    @Nonnull
    @NotNull
    @Convert(converter = ShipmentStatusConverter.class)
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHIPMENT_STATUS,
            comment = "The current status of the shipment. Valid values are:\n" +
                    "CREATED - the shipment is ready for order assignment\n" +
                    "SHIPPED - the goods have been dispatched\n" +
                    "IN-TRANSIT - the goods are en-route to their destination\n" +
                    "DELIVERED - the good have arrived at their destination"
    )
    private ShipmentStatus shipmentStatus;
}
