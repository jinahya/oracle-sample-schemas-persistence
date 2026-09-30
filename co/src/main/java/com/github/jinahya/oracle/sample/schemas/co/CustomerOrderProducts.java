package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An entity class for mapping the {@value CustomerOrderProducts#TABLE_NAME} view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = CustomerOrderProducts.TABLE_NAME)
public class CustomerOrderProducts {

    /**
     * The name of the database view to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMER_ORDER_PRODUCTS";

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "ORDER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ------------------------------------------------------------------------------------------------------- ORDER_TMS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_TMS = "ORDER_TMS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_TMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_TMS = "orderTms";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL_ADDRESS = 255;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_EMAIL_ADDRESS = COLUMN_LENGTH_EMAIL_ADDRESS;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    // ------------------------------------------------------------------------------------------------------- FULL_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_FULL_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FULL_NAME = 255;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FULL_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FULL_NAME = COLUMN_LENGTH_FULL_NAME;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    // ----------------------------------------------------------------------------------------------------- ORDER_TOTAL

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_TOTAL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_TOTAL = "ORDER_TOTAL";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_TOTAL = "orderTotal";

    // ----------------------------------------------------------------------------------------------------------- ITEMS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ITEMS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEMS = "ITEMS";

    /**
     * The length of the {@value #COLUMN_NAME_ITEMS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEMS = 4000;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ITEMS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ITEMS = COLUMN_LENGTH_ITEMS;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEMS = "items";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CustomerOrderProducts() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",orderTms=" + orderTms +
               ",orderStatus=" + orderStatus +
               ",customerId=" + customerId +
               ",emailAddress=" + emailAddress +
               ",fullName=" + fullName +
               ",orderTotal=" + orderTotal +
               ",items=" + items +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof CustomerOrderProducts that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(orderId);
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     */
    public LocalDateTime getOrderTms() {
        return orderTms;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified value.
     *
     * @param orderTms new value for {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     */
    public void setOrderTms(final LocalDateTime orderTms) {
        this.orderTms = orderTms;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute with the specified value.
     *
     * @param orderStatus new value for {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute with the specified value.
     *
     * @param emailAddress new value for {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------------- fullName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute with the specified value.
     *
     * @param fullName new value for {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    // ------------------------------------------------------------------------------------------------------ orderTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_TOTAL} attribute.
     */
    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TOTAL} attribute with the specified value.
     *
     * @param orderTotal new value for {@value #ATTRIBUTE_NAME_ORDER_TOTAL} attribute.
     */
    public void setOrderTotal(final BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    // ----------------------------------------------------------------------------------------------------------- items

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEMS} attribute.
     */
    public String getItems() {
        return items;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEMS} attribute with the specified value.
     *
     * @param items new value for {@value #ATTRIBUTE_NAME_ITEMS} attribute.
     */
    public void setItems(final String items) {
        this.items = items;
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    @Id
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_TMS, nullable = false, insertable = false, updatable = false)
    private LocalDateTime orderTms;

    @Size(max = SIZE_MAX_ORDER_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ORDER_STATUS)
    private String orderStatus;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Long customerId;

    @Size(max = SIZE_MAX_EMAIL_ADDRESS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_EMAIL_ADDRESS)
    private String emailAddress;

    @Size(max = SIZE_MAX_FULL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FULL_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FULL_NAME)
    private String fullName;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_TOTAL, nullable = true, insertable = false, updatable = false)
    private BigDecimal orderTotal;

    @Size(max = SIZE_MAX_ITEMS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ITEMS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEMS)
    private String items;
}
