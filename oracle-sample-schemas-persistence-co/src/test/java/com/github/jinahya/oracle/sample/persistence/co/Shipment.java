package com.github.jinahya.oracle.sample.persistence.co;

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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedShipment;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * An entity class for mapping the {@value MappedShipment#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Shipment.selectOneByShipmentId",
            query = """
                    SELECT e
                    FROM Shipment e
                    WHERE e.shipmentId = :shipmentId"""
)
@NamedQuery(name = "Shipment.selectListOrderByShipmentIdAsc",
            query = """
                    SELECT e
                    FROM Shipment e
                    ORDER BY e.shipmentId ASC"""
)
@NamedQuery(name = "Shipment.selectListOrderByShipmentIdAscShipmentIdGt",
            query = """
                    SELECT e
                    FROM Shipment e
                    WHERE e.shipmentId > :shipmentIdMinExclusive
                    ORDER BY e.shipmentId ASC"""
)
@NamedQuery(name = "Shipment.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Shipment e"""
)
@NamedQuery(name = "Shipment.selectListByStoreOrderByShipmentIdAsc",
            query = """
                    SELECT e
                    FROM Shipment e
                    WHERE e.store = :store
                    ORDER BY e.shipmentId ASC"""
)
@NamedQuery(name = "Shipment.countByStore",
            query = """
                    SELECT COUNT(e)
                    FROM Shipment e
                    WHERE e.store = :store"""
)
@NamedQuery(name = "Shipment.selectListByCustomerOrderByShipmentIdAsc",
            query = """
                    SELECT e
                    FROM Shipment e
                    WHERE e.customer = :customer
                    ORDER BY e.shipmentId ASC"""
)
@NamedQuery(name = "Shipment.countByCustomer",
            query = """
                    SELECT COUNT(e)
                    FROM Shipment e
                    WHERE e.customer = :customer"""
)
@Entity
@Table(name = MappedShipment.TABLE_NAME)
public class Shipment
        extends MappedShipment {

    // -------------------------------------------------------------------------------------- STORE_ID / storeId / store

    /**
     * The name of the {@link ManyToOne @ManyToOne} association which joins on the {@value #COLUMN_NAME_STORE_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ----------------------------------------------------------------------------- CUSTOMER_ID / customerId / customer

    /**
     * The name of the {@link ManyToOne @ManyToOne} association which joins on the {@value #COLUMN_NAME_CUSTOMER_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ----------------------------------------------------------------------------------------------------- ORDER_ITEMS

    /**
     * The name of the attribute which maps the order items carried by this shipment. The value is {@value}.
     *
     * @see OrderItem#ATTRIBUTE_NAME_SHIPMENT
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Shipment() {
        super();
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE} attribute with the specified value, and current value
     * of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified value's identifier.
     *
     * @param store new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(@Nonnull final Store store) {
        this.store = store;
        setStoreId(Optional.ofNullable(store).map(Store::getStoreId).orElse(null));
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute with the specified value, and current value
     * of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value's identifier.
     *
     * @param customer new value for {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
        setCustomerId(Optional.ofNullable(customer).map(Customer::getCustomerId).orElse(null));
    }

    // ------------------------------------------------------------------------------------------------------ orderItems

    /**
     * Returns the order items carried by this shipment.
     *
     * @return the order items carried by this shipment.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Replaces the order items carried by this shipment.
     *
     * @param orderItems new order items carried by this shipment.
     */
    public void setOrderItems(final List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = OrderItem.ATTRIBUTE_NAME_SHIPMENT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull OrderItem> orderItems;
}
