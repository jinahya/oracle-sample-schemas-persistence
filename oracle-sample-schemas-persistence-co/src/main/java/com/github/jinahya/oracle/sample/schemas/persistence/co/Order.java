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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedOrder;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * An entity class for mapping the {@value Order#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Order.selectListByCustomerOrderIdGtOrderByOrderTmsDesc",
            query = """
                    SELECT e
                    FROM Order AS e
                    WHERE e.customer = :customer
                      AND e.orderId > :orderIdMinExclusive
                    ORDER BY e.orderTms DESC"""
)
@NamedQuery(name = "Order.selectListByCustomerOrderByOrderTmsDesc",
            query = """
                    SELECT e
                    FROM Order AS e
                    WHERE e.customer = :customer
                    ORDER BY e.orderTms DESC"""
)
@NamedQuery(name = "Order.countByCustomerOrderByOrderTmsDesc",
            query = """
                    SELECT COUNT(e)
                    FROM Order AS e
                    WHERE e.customer = :customer"""
)
@Entity
@Table(name = Order.TABLE_NAME)
public class Order extends MappedOrder implements __DomainEntity<Long> {

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    /**
     * The name of the attribute which maps the order items of this order. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Order() {
        super();
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute, mapped by the specified function.
     *
     * @param <R>    the type of the mapped value.
     * @param mapper the function to apply to current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute.
     * @return the mapped value; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is {@code null}.
     * @throws NullPointerException if {@code mapper} is {@code null} while the {@value #ATTRIBUTE_NAME_ORDER_TMS}
     *                              attribute is not {@code null}.
     */
    public <R> R getOrderTmsAsMapped(final Function<? super LocalDateTime, ? extends R> mapper) {
        return Optional.ofNullable(getOrderTms())
                .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                .orElse(null);
    }

    /**
     * Replaces current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified value, mapped by
     * the specified function.
     *
     * @param <T>      the type of the specified value.
     * @param orderTms the value to map and set.
     * @param mapper   the function which maps the specified value.
     * @throws NullPointerException if {@code mapper} is {@code null} while {@code orderTms} is not {@code null}.
     */
    public <T> void setOrderTmsFromMapped(final T orderTms, final Function<? super T, LocalDateTime> mapper) {
        setOrderTms(
                Optional.ofNullable(orderTms)
                        .map(v -> Objects.requireNonNull(mapper, "mapper is null").apply(v))
                        .orElse(null)
        );
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute, at the specified zone.
     *
     * @param zone the zone to apply.
     * @return a zoned date-time; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is {@code null}.
     * @throws NullPointerException if {@code zone} is {@code null} while the {@value #ATTRIBUTE_NAME_ORDER_TMS}
     *                              attribute is not {@code null}.
     */
    public ZonedDateTime getOrderTmsAsZonedDatetime(final ZoneId zone) {
        return getOrderTmsAsMapped(
                v -> v.atZone(Objects.requireNonNull(zone, "zone is null"))
        );
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the local date-time of the specified
     * zoned date-time.
     *
     * @param orderTms the zoned date-time whose local date-time is set; may be {@code null}.
     */
    @Transient
    public void setOrderTmsFromZonedDateTime(final ZonedDateTime orderTms) {
        setOrderTmsFromMapped(orderTms, ZonedDateTime::toLocalDateTime);
    }

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute, at the specified offset.
     *
     * @param offset the offset to apply.
     * @return an offset date-time; {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is {@code null}.
     * @throws NullPointerException if {@code offset} is {@code null} while the {@value #ATTRIBUTE_NAME_ORDER_TMS}
     *                              attribute is not {@code null}.
     */
    public OffsetDateTime getOrderTmsAsOffsetDatetime(final ZoneOffset offset) {
        return getOrderTmsAsMapped(
                v -> v.atOffset(Objects.requireNonNull(offset, "offset is null"))
        );
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the local date-time of the specified
     * offset date-time.
     *
     * @param orderTms the offset date-time whose local date-time is set; may be {@code null}.
     */
    @Transient
    public void setOrderTmsFromOffsetDateTime(final OffsetDateTime orderTms) {
        setOrderTmsFromMapped(
                orderTms,
                OffsetDateTime::toLocalDateTime
        );
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an {@link Instant instant} at specified
     * zone.
     *
     * @param zone the zone.
     * @return {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an {@link Instant instant} at {@code zone}; {@code null}
     * when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is {@code null}.
     * @throws NullPointerException if {@code zone} is {@code null} while the {@value #ATTRIBUTE_NAME_ORDER_TMS}
     *                              attribute is not {@code null}.
     */
    public Instant getOrderTmsAsInstant(final ZoneId zone) {
        return Optional.ofNullable(getOrderTmsAsZonedDatetime(zone))
                .map(ZonedDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified instant at specified
     * zone.
     *
     * @param instant the instant; may be {@code null}.
     * @param zone    the zone.
     * @throws NullPointerException if {@code zone} is {@code null} while {@code instant} is not {@code null}.
     */
    public void setOrderTmsFromInstant(final Instant instant, final ZoneId zone) {
        setOrderTmsFromZonedDateTime(
                Optional.ofNullable(instant)
                        .map(v -> v.atZone(Objects.requireNonNull(zone, "zone is null")))
                        .orElse(null)
        );
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an {@link Instant instant} at specified
     * offset.
     *
     * @param offset the offset.
     * @return {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute as an {@link Instant instant} at {@code offset};
     * {@code null} when the {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute is {@code null}.
     * @throws NullPointerException if {@code offset} is {@code null} while the {@value #ATTRIBUTE_NAME_ORDER_TMS}
     *                              attribute is not {@code null}.
     */
    public Instant getOrderTmsAsInstant(final ZoneOffset offset) {
        return Optional.ofNullable(getOrderTmsAsOffsetDatetime(offset))
                .map(OffsetDateTime::toInstant)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_TMS} attribute with the specified instant at specified
     * offset.
     *
     * @param orderTms the instant; may be {@code null}.
     * @param offset   the offset.
     * @throws NullPointerException if {@code offset} is {@code null} while {@code orderTms} is not {@code null}.
     */
    public void setOrderTmsFromInstant(final Instant orderTms, final ZoneOffset offset) {
        setOrderTmsFromOffsetDateTime(
                Optional.ofNullable(orderTms)
                        .map(v -> v.atOffset(Objects.requireNonNull(offset, "offset is null")))
                        .orElse(null)
        );
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
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the total price of this order, summed over its order items.
     *
     * @return the total price of this order.
     * @throws IllegalArgumentException if the order items of this order are {@code null}.
     * @throws IllegalStateException    if the unit price or the quantity of any of its order items is {@code null}.
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

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    private Customer customer;

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    // -----------------------------------------------------------------------------------------------------------------
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
