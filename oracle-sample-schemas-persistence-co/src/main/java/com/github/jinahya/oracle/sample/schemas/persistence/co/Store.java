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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedStore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * An entity class for mapping the {@value Store#TABLE_NAME} table.
 * <p>
 * The {@value #ATTRIBUTE_NAME_STORE_NAME} attribute is unique, and is the natural key on which {@link #equals(Object)}
 * and {@link #hashCode()} are based; the {@code Store.selectSingleByStoreName} named query selects the single store of
 * a given {@code storeName}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Store.selectSingleByStoreName",
            query = """
                    SELECT e
                    FROM Store AS e
                    WHERE e.storeName = :storeName""")
@Entity
@Table(name = Store.TABLE_NAME)
public class Store extends MappedStore implements __DomainEntity<Long> {

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute, as a decimal string.
     * <p>
     * The attribute takes geographic degrees, {@code -90} to {@code +90}, which is narrower than what the
     * {@value #COLUMN_NAME_LATITUDE} column can hold, {@value #COLUMN_MIN_LATITUDE} to {@value #COLUMN_MAX_LATITUDE}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LATITUDE = __DomainConstants.DECIMAL_MIN_LATITUDE;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute, as a decimal string.
     *
     * @see #ATTRIBUTE_DECIMAL_MIN_LATITUDE
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LATITUDE = __DomainConstants.DECIMAL_MAX_LATITUDE;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MIN_LATITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MIN_LATITUDE);

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MAX_LATITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MAX_LATITUDE);

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute, as a decimal string.
     * <p>
     * The attribute takes geographic degrees, {@code -180} to {@code +180}, which is narrower than what the
     * {@value #COLUMN_NAME_LONGITUDE} column can hold, {@value #COLUMN_MIN_LONGITUDE} to
     * {@value #COLUMN_MAX_LONGITUDE}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LONGITUDE = __DomainConstants.DECIMAL_MIN_LONGITUDE;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute, as a decimal string.
     *
     * @see #ATTRIBUTE_DECIMAL_MIN_LONGITUDE
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LONGITUDE = __DomainConstants.DECIMAL_MAX_LONGITUDE;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MIN_LONGITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MIN_LONGITUDE);

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     */
    public static final BigDecimal ATTRIBUTE_MAX_LONGITUDE = new BigDecimal(ATTRIBUTE_DECIMAL_MAX_LONGITUDE);

    // ---------------------------------------------------------------------------------------------------------- ORDERS

    /**
     * The name of the attribute which maps the orders placed at this store. The value is {@value}.
     *
     * @see Order#ATTRIBUTE_NAME_STORE
     */
    public static final String ATTRIBUTE_NAME_ORDERS = "orders";

    // ------------------------------------------------------------------------------------------------------- SHIPMENTS

    /**
     * The name of the attribute which maps the shipments dispatched from this store. The value is {@value}.
     *
     * @see Shipment#ATTRIBUTE_NAME_STORE
     */
    public static final String ATTRIBUTE_NAME_SHIPMENTS = "shipments";

    // ----------------------------------------------------------------------------------------------------- INVENTORIES

    /**
     * The name of the attribute which maps the inventories held by this store. The value is {@value}.
     *
     * @see Inventory#ATTRIBUTE_NAME_STORE
     */
    public static final String ATTRIBUTE_NAME_INVENTORIES = "inventories";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Store() {
        super();
    }

    // ---------------------------------------------------------------------------------------------------------- orders

    /**
     * Returns the orders placed at this store.
     *
     * @return the orders placed at this store.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Replaces the orders placed at this store.
     *
     * @param orders new orders placed at this store.
     */
    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

    // ------------------------------------------------------------------------------------------------------- shipments

    /**
     * Returns the shipments dispatched from this store.
     *
     * @return the shipments dispatched from this store.
     */
    public List<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Replaces the shipments dispatched from this store.
     *
     * @param shipments new shipments dispatched from this store.
     */
    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    // ----------------------------------------------------------------------------------------------------- inventories

    /**
     * Returns the inventories held by this store.
     *
     * @return the inventories held by this store.
     */
    public List<Inventory> getInventories() {
        return inventories;
    }

    /**
     * Replaces the inventories held by this store.
     *
     * @param inventories new inventories held by this store.
     */
    public void setInventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    @OneToMany(mappedBy = Order.ATTRIBUTE_NAME_STORE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Order> orders;

    @OneToMany(mappedBy = Shipment.ATTRIBUTE_NAME_STORE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Shipment> shipments;

    @OneToMany(mappedBy = Inventory.ATTRIBUTE_NAME_STORE,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Inventory> inventories;
}
