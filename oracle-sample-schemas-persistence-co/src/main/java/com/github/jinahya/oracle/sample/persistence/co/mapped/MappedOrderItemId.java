package com.github.jinahya.oracle.sample.persistence.co.mapped;

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

import java.util.Objects;

/**
 * A mapped superclass for the identifier of the {@value MappedOrderItem#TABLE_NAME} table; the
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId} of an entity which extends
 * {@link MappedOrderItem}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedOrderItemId {

    /**
     * The name of the attribute which maps the {@value MappedOrderItem#COLUMN_NAME_ORDER_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    /**
     * The name of the attribute which maps the {@value MappedOrderItem#COLUMN_NAME_LINE_ITEM_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_ITEM_ID = "lineItemId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItemId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "orderId=" + orderId +
               ",lineItemId=" + lineItemId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by both {@value #ATTRIBUTE_NAME_ORDER_ID} and
     *         {@value #ATTRIBUTE_NAME_LINE_ITEM_ID}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedOrderItemId that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId)
               && Objects.equals(lineItemId, that.lineItemId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over both {@value #ATTRIBUTE_NAME_ORDER_ID} and
     *         {@value #ATTRIBUTE_NAME_LINE_ITEM_ID}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(orderId, lineItemId);
    }

    // ------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    @Nonnull
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified
     * value.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(@Nonnull final Long orderId) {
        this.orderId = orderId;
    }

    // ---------------------------------------------------------------------------------- lineItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     */
    @Nonnull
    public Long getLineItemId() {
        return lineItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute with the specified
     * value.
     *
     * @param lineItemId new value for {@value #ATTRIBUTE_NAME_LINE_ITEM_ID} attribute.
     */
    protected void setLineItemId(@Nonnull final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    // ---------------------------------------------------------------------------------------------

    @Nonnull
    // no @NotNull: MappedOrderItem maps this through @MapsId, so the provider fills it at flush
    // -- later than
    // the prePersist at which EclipseLink validates. The column stays NOT NULL.
    @Basic(optional = false)
    @Column(name = MappedOrderItem.COLUMN_NAME_ORDER_ID, nullable = false, insertable = true,
            updatable = false,
            comment = "The order these products belong to")
    private Long orderId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedOrderItem.COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true,
            updatable = false,
            comment = "An incrementing number, starting at one for each order")
    private Long lineItemId;
}
