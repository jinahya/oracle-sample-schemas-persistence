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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedOrder;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value MappedOrder#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = MappedOrder.TABLE_NAME, comment = "Details of who made purchases where")
public class Order
        extends MappedOrder {

    // --------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on
     * the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ------------------------------------------------------------------------------------ STORE_ID

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on
     * the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // --------------------------------------------------------------------------------- ORDER_ITEMS

    /**
     * The name of the attribute which maps the order items of this order. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Order() {
        super();
    }

    // ------------------------------------------------------------------------------------ customer

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute with the specified
     * value, and current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified
     * value's identifier.
     *
     * @param customer new value for {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
        setCustomerId(Optional.ofNullable(customer).map(Customer::getCustomerId).orElse(null));
    }

    // --------------------------------------------------------------------------------------- store

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE} attribute with the specified value,
     * and current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified value's
     * identifier.
     *
     * @param store new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(@Nonnull final Store store) {
        this.store = store;
        setStoreId(Optional.ofNullable(store).map(Store::getStoreId).orElse(null));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Returns the total price of this order, summed over its order items.
     *
     * @return the total price of this order.
     * @throws IllegalArgumentException if the order items of this order are {@code null}.
     * @throws IllegalStateException    if the unit price or the quantity of any of its order items
     *                                  is {@code null}.
     * @see OrderItem#getTotalPrice(java.math.MathContext)
     */
    public BigDecimal getTotalPrice() {
        if (orderItems == null) {
            throw new IllegalArgumentException("orderItems is null");
        }
        return orderItems.values()
                .stream()
                .map(v -> v.getTotalPrice(null))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Returns the order item, of this order, which orders the specified product.
     *
     * @param product the product whose order item is returned.
     * @return the order item for the {@code product}; {@code null} when this order has none.
     * @throws NullPointerException  if {@code product} is {@code null}.
     * @throws IllegalStateException if the order items of this order are {@code null}.
     */
    public @jakarta.annotation.Nullable OrderItem getOrderItem(@Nonnull final Product product) {
        Objects.requireNonNull(product, "product is null");
        if (orderItems == null) {
            throw new IllegalStateException("orderItems is null");
        }
        return orderItems.get(product);
    }

    void addOrderItem(@Nonnull final OrderItem orderItem) {
        Objects.requireNonNull(orderItem, "orderItem is null");
        // TODO: check the orderStatus!!!
        final var previous = getOrderItem(orderItem.getProduct());
        if (previous != null) {
            previous.setQuantity(previous.getQuantity() + orderItem.getQuantity());
        } else {
            orderItems.put(orderItem.getProduct(), orderItem);
        }
    }

    @jakarta.annotation.Nullable
    OrderItem removeOrderItem(@Nonnull final Product product) {
        Objects.requireNonNull(product, "product is null");
        if (orderItems == null) {
            throw new IllegalStateException("orderItems is null");
        }
        // TODO: check the orderStatus!!!
        return orderItems.remove(product);
    }

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true,
                updatable = false)
    private Customer customer;

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    // ---------------------------------------------------------------------------------------------
    @MapKey(name = OrderItem.ATTRIBUTE_NAME_PRODUCT)
    @OneToMany(mappedBy = OrderItem.ATTRIBUTE_NAME_ORDER,
               fetch = FetchType.LAZY,
               cascade = {
                       // TODO: add, may be all?
               },
               orphanRemoval = true
    )
    private Map<Product, OrderItem> orderItems;
}
