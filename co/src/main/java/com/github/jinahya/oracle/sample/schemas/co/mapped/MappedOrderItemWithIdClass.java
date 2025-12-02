package com.github.jinahya.oracle.sample.schemas.co.mapped;

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

import com.github.jinahya.oracle.sample.schemas.co.OrderItemId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@IdClass(OrderItemId.class)
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderItemWithIdClass extends MappedOrderItem {

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItemWithIdClass() {
        super();
    }

    protected MappedOrderItemWithIdClass(final MappedOrderItemWithIdClassBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",lineItemId=" + lineItemId +
               '}';
    }

//    @Override
//    public final boolean equals(final Object obj) {
//        if (!(obj instanceof MappedOrderItemWithIdClass that)) {
//            return false;
//        }
//        return Objects.equals(orderId, that.orderId)
//               && Objects.equals(lineItemId, that.lineItemId);
//    }
//
//    @Override
//    public final int hashCode() {
//        return Objects.hash(orderId, lineItemId);
//    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // -------------------------------------------------------------------------------------------------------------- id
    @Override
    public final OrderItemId getId() {
        return OrderItemId.of(getOrderId(), getLineItemId());
    }

    @Override
    protected final void setId(final OrderItemId id) {
        setOrderId(
                Optional.ofNullable(id)
                        .map(OrderItemId::getOrderId)
                        .orElse(null)
        );
        setLineItemId(
                Optional.ofNullable(id)
                        .map(OrderItemId::getLineItemId)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    @Nonnull
    public Long getOrderId() {
        return orderId;
    }

    protected void setOrderId(@Nonnull final Long orderId) {
        this.orderId = orderId;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId
    @Nonnull
    public Long getLineItemId() {
        return lineItemId;
    }

    protected void setLineItemId(@Nonnull final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @Nonnull
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true, updatable = false)
    private Long lineItemId;
}
