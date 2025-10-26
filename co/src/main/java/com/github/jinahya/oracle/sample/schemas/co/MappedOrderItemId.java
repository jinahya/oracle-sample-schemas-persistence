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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class MappedOrderItemId implements Serializable {

    @Serial
    private static final long serialVersionUID = 2149145063300672899L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    public static final String ATTRIBUTE_NAME_LINE_ITEM_ID = "lineItemId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItemId() {
        super();
    }

    protected MappedOrderItemId(final MappedOrderItemIdBuilder<?, ?> builder) {
        super();
        this.orderId = builder.orderId();
        this.lineItemId = builder.lineItemId();
    }

    // --------------------------------------------------=--------------------------------------------- java.lang.String
    @Override
    public String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",lineItemId=" + lineItemId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedOrderItemId that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId)
               && Objects.equals(lineItemId, that.lineItemId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(orderId, lineItemId);
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    @Nonnull
    public Long getOrderId() {
        return orderId;
    }

    void setOrderId(@Nonnull final Long orderId) {
        this.orderId = orderId;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId
    @Nonnull
    public Long getLineItemId() {
        return lineItemId;
    }

    void setLineItemId(@Nonnull final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedOrderItem.COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedOrderItem.COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true, updatable = false)
    private Long lineItemId;
}
