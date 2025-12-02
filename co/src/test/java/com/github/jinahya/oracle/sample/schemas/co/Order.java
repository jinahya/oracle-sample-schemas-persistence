package com.github.jinahya.oracle.sample.schemas.co;

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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedCustomer;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrder;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrderItem;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedStore;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = MappedOrder.TABLE_NAME)
class Order extends MappedOrder {

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Order() {
        super();
    }

    Order(final OrderBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public boolean equals(final Object obj) {
        return equalsWithOrderId(obj);
    }

    @Override
    public int hashCode() {
        return hashCodeWithOrderIda();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // -------------------------------------------------------------------------------------------------------- customer
    @Nonnull
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
        setCustomerId(
                Optional.ofNullable(this.customer)
                        .map(MappedCustomer::getCustomerId)
                        .orElse(null)
        );
    }

    // ----------------------------------------------------------------------------------------------------------- store
    @Nonnull
    public Store getStore() {
        return store;
    }

    public void setStore(@Nonnull final Store stoer) {
        this.store = stoer;
        setStoreId(
                Optional.ofNullable(this.store)
                        .map(MappedStore::getStoreId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Customer customer;

    @Nonnull
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public BigDecimal getTotalPrice() {
        if (orderItems == null) {
            throw new IllegalArgumentException("orderItems is null");
        }
        return orderItems.values()
                .stream()
                .map(v -> v.getTotalPrice(null))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MapKeyColumn(name = MappedOrderItem.COLUMN_NAME_PRODUCT_ID)
    @OneToMany(mappedBy = MappedOrderItem.ATTRIBUTE_NAME_ORDER,
               fetch = FetchType.LAZY,
               cascade = {
                       // TODO: add, may be all?
               },
               orphanRemoval = true
    )
    private Map<Product, OrderItemWithEmbeddedId> orderItems;

    public @Nullable OrderItemWithEmbeddedId getOrderItem(@Nonnull final Product product) {
        Objects.requireNonNull(product, "product is null");
        if (orderItems == null) {
            throw new IllegalStateException("orderItems is null");
        }
        return orderItems.get(product);
    }

    void addOrderItem(@Nonnull final OrderItemWithEmbeddedId orderItem) {
        Objects.requireNonNull(orderItem, "orderItem is null");
        // TODO: check the orderStatus!!!
        final var previous = getOrderItem(orderItem.getProduct());
        if (previous != null) {
            previous.setQuantity(previous.getQuantity() + orderItem.getQuantity());
        } else {
            addOrderItem(orderItem);
        }
    }

    @Nullable
    OrderItemWithEmbeddedId removeOrderItem(@Nonnull final Product product) {
        Objects.requireNonNull(product, "product is null");
        if (orderItems == null) {
            throw new IllegalStateException("orderItems is null");
        }
        // TODO: check the orderStatus!!!
        return orderItems.remove(product);
    }
}
