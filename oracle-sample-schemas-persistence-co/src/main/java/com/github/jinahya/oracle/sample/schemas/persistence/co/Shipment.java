package com.github.jinahya.oracle.sample.schemas.persistence.co;

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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Shipment#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Shipment.TABLE_NAME)
public class Shipment {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
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
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ----------------------------------------------------------------------------- CUSTOMER_ID / customerId / customer

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUSTOMER} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
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
    public static final String ATTRIBUTE_DELIVERY_ADDRESS = "deliveryAddress";

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
         * A constant for the {@value Shipment#COLUMN_VALUE_SHIPMENT_STATUS_CREATED} value, for a shipment which has
         * been created.
         */
        // 준비 중?
        CREATED,

        /**
         * A constant for the {@value Shipment#COLUMN_VALUE_SHIPMENT_STATUS_SHIPPED} value, for a shipment which has
         * been shipped.
         */
        // 발송/출고?
        SHIPPED,

        /**
         * A constant for the {@value Shipment#COLUMN_VALUE_SHIPMENT_STATUS_IN_TRANSIT} value, for a shipment which is
         * in transit. It carries its column value explicitly, because the value is not this constant's
         * {@link Enum#name() name}.
         */
        // 배송/운송 중?
        IN_TRANSIT,

        /**
         * A constant for the {@value Shipment#COLUMN_VALUE_SHIPMENT_STATUS_DELIVERED} value, for a shipment which has
         * been delivered.
         */
        // 배달됨?
        DELIVERED;
    }
    // ----------------------------------------------------------------------------------------------------- ORDER_ITEMS

    /**
     * The name of the attribute which maps the order items carried by this shipment. The value is {@value}.
     *
     * @see OrderItemWithEmbeddedId#ATTRIBUTE_NAME_SHIPMENT
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String ATTRIBUTE_NAME_DELIVERY_ADDRESS = "deliveryAddress";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Shipment() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "shipmentId=" + shipmentId +
               ",storeId=" + storeId +
//               ",store=" + store +
               ",customerId=" + customerId +
//               ",customer=" + customer +
               ",deliveryAddress=" + deliveryAddress +
               ",shipmentStatus=" + shipmentStatus +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Shipment that)) {
            return false;
        }
        return Objects.equals(getShipmentId(), that.getShipmentId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getShipmentId());
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

    @Nonnull
    public Long getStoreId() {
        return storeId;
    }

    protected void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // ----------------------------------------------------------------------------------------------------------- store

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    @Nonnull
    public Store getStore() {
        return store;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE} attribute with the specified value.
     *
     * @param store new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(@Nonnull final Store store) {
        this.store = store;
        setStoreId(
                Optional.ofNullable(this.store).map(Store::getStoreId).orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    @Nonnull
    public Long getCustomerId() {
        return customerId;
    }

    protected void setCustomerId(@Nonnull final Long customerId) {
        this.customerId = customerId;
    }

    // -------------------------------------------------------------------------------------------------------- customer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    @Nonnull
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute with the specified value.
     *
     * @param customer new value for {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
        setCustomerId(
                Optional.ofNullable(this.customer).map(Customer::getCustomerId).orElse(null)
        );
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

    // ------------------------------------------------------------------------------------------------------ orderItems

    /**
     * Returns the order items carried by this shipment.
     *
     * @return the order items carried by this shipment.
     */
    public List<OrderItemWithEmbeddedId> getOrderItems() {
        return orderItems;
    }

    /**
     * Replaces the order items carried by this shipment.
     *
     * @param orderItems new order items carried by this shipment.
     */
    public void setOrderItems(final List<OrderItemWithEmbeddedId> orderItems) {
        this.orderItems = orderItems;
    }

    // -----------------------------------------------------------------------------------------------------------------
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
    @Nonnull
    @Valid
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Long storeId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Long customerId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Customer customer;

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
    @Enumerated(value = EnumType.STRING)
    @Column(name = COLUMN_NAME_SHIPMENT_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHIPMENT_STATUS
    )
    private ShipmentStatus shipmentStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = OrderItemWithEmbeddedId.ATTRIBUTE_NAME_SHIPMENT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull OrderItemWithEmbeddedId> orderItems;
}
