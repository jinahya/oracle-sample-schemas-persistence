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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedOrderItem;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * An entity class for mapping the {@value MappedOrderItem#TABLE_NAME} table, whose composite primary key is
 * mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "OrderItem.selectOneByOrderIdAndLineItemId",
            query = """
                    SELECT e
                    FROM OrderItem e
                    WHERE e.id.orderId = :orderId
                      AND e.id.lineItemId = :lineItemId"""
)
@NamedQuery(name = "OrderItem.selectListOrderByOrderIdAscLineItemIdAsc",
            query = """
                    SELECT e
                    FROM OrderItem e
                    ORDER BY e.id.orderId ASC, e.id.lineItemId ASC"""
)
@NamedQuery(name = "OrderItem.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM OrderItem e"""
)
@NamedQuery(name = "OrderItem.selectListByOrderOrderByLineItemIdAsc",
            query = """
                    SELECT e
                    FROM OrderItem e
                    WHERE e.order = :order
                    ORDER BY e.id.lineItemId ASC"""
)
@NamedQuery(name = "OrderItem.countByOrder",
            query = """
                    SELECT COUNT(e)
                    FROM OrderItem e
                    WHERE e.order = :order"""
)
@NamedQuery(name = "OrderItem.selectListByProductOrderByOrderIdAscLineItemIdAsc",
            query = """
                    SELECT e
                    FROM OrderItem e
                    WHERE e.product = :product
                    ORDER BY e.id.orderId ASC, e.id.lineItemId ASC"""
)
@NamedQuery(name = "OrderItem.countByProduct",
            query = """
                    SELECT COUNT(e)
                    FROM OrderItem e
                    WHERE e.product = :product"""
)
@NamedQuery(name = "OrderItem.selectListByShipmentOrderByOrderIdAscLineItemIdAsc",
            query = """
                    SELECT e
                    FROM OrderItem e
                    WHERE e.shipment = :shipment
                    ORDER BY e.id.orderId ASC, e.id.lineItemId ASC"""
)
@NamedQuery(name = "OrderItem.countByShipment",
            query = """
                    SELECT COUNT(e)
                    FROM OrderItem e
                    WHERE e.shipment = :shipment"""
)
@Entity
@Table(name = MappedOrderItem.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               OrderItem.COLUMN_NAME_ORDER_ID,
                               OrderItem.COLUMN_NAME_PRODUCT_ID,
                       }
               )
       }
)
public class OrderItem
        extends MappedOrderItem<OrderItemId> {

    // -------------------------------------------------------------------------------------------------------- ORDER_ID

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which maps the
     * {@value #COLUMN_NAME_ORDER_ID} column. The association is {@link jakarta.persistence.MapsId @MapsId} to the
     * {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute, so it is the association which writes the column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER = "order";

    // -------------------------------------------------------------------------------------------- PRODUCT_ID / product

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ------------------------------------------------------------------------------------------ SHIPMENT_ID / shipment

    /**
     * The name of the {@link jakarta.persistence.ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_SHIPMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPMENT = "shipment";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItem() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    @Override
    public OrderItemId getId() {
        return id;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    @Override
    protected void setId(final OrderItemId id) {
        this.id = id;
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Replaces current value of {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute with the specified value, creating the
     * {@value #ATTRIBUTE_NAME_ID} attribute first when it is {@code null}.
     *
     * @param orderId new value for {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute.
     */
    protected void setOrderId(final Long orderId) {
        Optional.ofNullable(getId())
                .orElseGet(() -> {
                    setId(new OrderItemId());
                    return getId();
                })
                .setOrderId(orderId);
    }

    // ----------------------------------------------------------------------------------------------------------- order

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER} attribute.
     */
    @Nonnull
    public Order getOrder() {
        return order;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER} attribute with the specified value, and current value of
     * {@value #ATTRIBUTE_PATH_ID_ORDER_ID} attribute with the specified value's identifier.
     *
     * @param order new value for {@value #ATTRIBUTE_NAME_ORDER} attribute.
     */
    protected void setOrder(@Nonnull final Order order) {
        this.order = order;
        setOrderId(
                Optional.ofNullable(this.order).map(Order::getOrderId).orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    @Nonnull
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute with the specified value, and current value
     * of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value's identifier.
     *
     * @param product new value for {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    protected void setProduct(@Nonnull final Product product) {
        this.product = product;
        setProductId(Optional.ofNullable(product).map(Product::getProductId).orElse(null));
    }

    // -------------------------------------------------------------------------------------------------------- shipment

    /**
     * Returns the shipment which carries this order item.
     *
     * @return the shipment which carries this order item; {@code null} when not yet shipped.
     */
    @Nullable
    public Shipment getShipment() {
        return shipment;
    }

    /**
     * Replaces the shipment which carries this order item.
     *
     * @param shipment new shipment which carries this order item; {@code null} for an item not yet shipped.
     */
    public void setShipment(@Nullable final Shipment shipment) {
        this.shipment = shipment;
        setShipmentId(Optional.ofNullable(shipment).map(Shipment::getShipmentId).orElse(null));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private OrderItemId id;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    // the association owns ORDER_ID, and the provider copies the order's id into the embedded id; @MapsId is what
    // ties the two together, so the column is written from here rather than from a mirroring attribute
    @MapsId(OrderItemId.ATTRIBUTE_NAME_ORDER_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID, referencedColumnName = Order.COLUMN_NAME_ORDER_ID, nullable = false)
    private Order order;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = Product.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Product product;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIPMENT_ID,
                referencedColumnName = Shipment.COLUMN_NAME_SHIPMENT_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Shipment shipment;
}
