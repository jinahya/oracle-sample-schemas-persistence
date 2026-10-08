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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Customer#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Customer.selectOneByEmailAddress",
        query = """
                SELECT e
                FROM Customer e
                WHERE e.emailAddress = :emailAddress"""
)
@NamedQuery(
        name = "Customer.selectListOrderByEmailAddressAsc",
        query = """
                SELECT e
                FROM Customer e
                ORDER BY e.emailAddress ASC"""
)
@NamedQuery(
        name = "Customer.selectListOrderByCustomerIdAscCustomerIdGt",
        query = """
                SELECT e
                FROM Customer e
                WHERE e.customerId > :customerIdMinExclusive
                ORDER BY e.customerId ASC"""
)
@NamedQuery(
        name = "Customer.selectListOrderByCustomerIdAsc",
        query = """
                SELECT e
                FROM Customer e
                ORDER BY e.customerId ASC"""
)
@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer implements __DomainEntity<Long> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_EMAIL_ADDRESS = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_NAME_EMAIL_ADDRESS = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_NAME_EMAIL_ADDRESS = COLUMN_LENGTH_NAME_EMAIL_ADDRESS;

    // -------------------------------------------------------------------------------------------- FULL_NAME / fullName

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FULL_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_FULL_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_FULL_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_NAME_FULL_NAME = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FULL_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_NAME_FULL_NAME = COLUMN_LENGTH_NAME_FULL_NAME;

    // ---------------------------------------------------------------------------------------------------------- ORDERS

    /**
     * The name of the attribute which maps the orders placed by this customer. The value is {@value}.
     *
     * @see Order#ATTRIBUTE_NAME_CUSTOMER
     */
    public static final String ATTRIBUTE_NAME_ORDERS = "orders";

    // ------------------------------------------------------------------------------------------------------- SHIPMENTS

    /**
     * The name of the attribute which maps the shipments dispatched to this customer. The value is {@value}.
     *
     * @see Shipment#ATTRIBUTE_NAME_CUSTOMER
     */
    public static final String ATTRIBUTE_NAME_SHIPMENTS = "shipments";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "customerId=" + customerId +
               ",emailAddress=" + emailAddress +
               ",fullName=" + fullName +
//               ",orders=" + orders +
//               ",shipments=" + shipments +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS}, which the table declares unique.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Customer that)) {
            return false;
        }
        return Objects.equals(emailAddress, that.emailAddress);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(emailAddress);
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
    protected void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    @Nonnull
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute with the specified value.
     *
     * @param emailAddress new value for {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public void setEmailAddress(@Nonnull final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------------- fullName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    @Nonnull
    public String getFullName() {
        return fullName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute with the specified value.
     *
     * @param fullName new value for {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    public void setFullName(@Nonnull final String fullName) {
        this.fullName = fullName;
    }

    // ---------------------------------------------------------------------------------------------------------- orders

    /**
     * Returns the orders placed by this customer.
     *
     * @return the orders placed by this customer.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Replaces the orders placed by this customer.
     *
     * @param orders new orders placed by this customer.
     */
    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

    // ------------------------------------------------------------------------------------------------------- shipments

    /**
     * Returns the shipments dispatched to this customer.
     *
     * @return the shipments dispatched to this customer.
     */
    public List<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Replaces the shipments dispatched to this customer.
     *
     * @param shipments new shipments dispatched to this customer.
     */
    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Positive // ???
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5.0.1.
            insertable = true,
            updatable = false
    )
    private Long customerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Email
    @Size(min = SIZE_MIN_NAME_EMAIL_ADDRESS, max = SIZE_MAX_NAME_EMAIL_ADDRESS)
    @NotNull
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_EMAIL_ADDRESS,
            unique = true
    )
    private String emailAddress;

    @Nonnull
    @Size(min = SIZE_MIN_NAME_FULL_NAME, max = SIZE_MAX_NAME_FULL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FULL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_FULL_NAME
    )
    private String fullName;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Order.ATTRIBUTE_NAME_CUSTOMER,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Order> orders;

    @OneToMany(mappedBy = Shipment.ATTRIBUTE_NAME_CUSTOMER,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Shipment> shipments;
}
