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
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An id class for {@link OrderItemWithEmbeddedId} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class OrderItemId {

    /**
     * The name of the attribute which maps the {@code ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    /**
     * The name of the attribute which maps the {@code LINE_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_ITEM_ID = "lineItemId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItemId() {
        super();
    }

    /**
     * Creates a new instance with the specified order id and line item id.
     *
     * @param orderId    the {@value OrderItemWithEmbeddedId#COLUMN_NAME_ORDER_ID} column value.
     * @param lineItemId the {@value OrderItemWithEmbeddedId#COLUMN_NAME_LINE_ITEM_ID} column value.
     */
    public OrderItemId(@Nonnull final Long orderId, @Nonnull final Long lineItemId) {
        this();
        setOrderId(orderId);
        setLineItemId(lineItemId);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",lineItemId=" + lineItemId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof OrderItemId that)) {
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

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    @Nonnull
    public Long getOrderId() {
        return orderId;
    }

    void setOrderId(@Nonnull final Long orderId) {
        this.orderId = orderId;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     */
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
    @Column(name = OrderItemWithEmbeddedId.COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = OrderItemWithEmbeddedId.COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true,
            updatable = false)
    private Long lineItemId;
}
