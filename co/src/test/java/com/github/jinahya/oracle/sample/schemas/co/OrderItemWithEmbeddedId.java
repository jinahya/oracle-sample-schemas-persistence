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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrder;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrderItem;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrderItemWithEmbeddedId;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedProduct;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Entity
@Table(name = MappedOrderItem.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               MappedOrderItem.COLUMN_NAME_ORDER_ID,
                               MappedOrderItem.COLUMN_NAME_PRODUCT_ID,
                       }
               )
       }
)
class OrderItemWithEmbeddedId extends MappedOrderItemWithEmbeddedId {

    static OrderItemWithEmbeddedIdBuilder builder() {
        return new OrderItemWithEmbeddedIdBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItemWithEmbeddedId() {
        super();
    }

    OrderItemWithEmbeddedId(final OrderItemWithEmbeddedIdBuilder builder) {
        super(builder);
    }

    // ----------------------------------------------------------------------------------------------------------- order
    @Nonnull
    public Order getOrder() {
        return order;
    }

    protected void setOrder(@Nonnull final Order order) {
        this.order = order;
        Optional.ofNullable(getId())
                .orElseGet(() -> {
                    setId(OrderItemId.of(null, null));
                    return getId();
                })
                .setOrderId(Optional.ofNullable(this.order).map(MappedOrder::getOrderId).orElse(null));
    }

    // --------------------------------------------------------------------------------------------------------- product
    @Nonnull
    public Product getProduct() {
        return product;
    }

    protected void setProduct(@Nonnull final Product product) {
        this.product = product;
        setProductId(
                Optional.ofNullable(this.product)
                        .map(MappedProduct::getProductId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID,
                referencedColumnName = MappedOrder.COLUMN_NAME_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Order order;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;
}
