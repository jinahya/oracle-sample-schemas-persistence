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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedCustomer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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
public class Customer extends MappedCustomer implements __DomainEntity<Long> {

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
