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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedOrderItemId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;

/**
 * An id class for the {@value OrderItem#TABLE_NAME} table; the {@link jakarta.persistence.EmbeddedId @EmbeddedId} of
 * {@link OrderItem}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class OrderItemId extends MappedOrderItemId {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified order id and line item id.
     *
     * @param orderId    the {@value OrderItem#COLUMN_NAME_ORDER_ID} column value.
     * @param lineItemId the {@value OrderItem#COLUMN_NAME_LINE_ITEM_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static OrderItemId of(@Nonnull final Long orderId, @Nonnull final Long lineItemId) {
        final var instance = new OrderItemId();
        instance.setOrderId(orderId);
        instance.setLineItemId(lineItemId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItemId() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * {@inheritDoc}
     *
     * @implNote Overridden, unchanged, so that {@link OrderItem}, in this package, can set it.
     */
    @Override
    protected void setOrderId(@Nonnull final Long orderId) {
        super.setOrderId(orderId);
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId

    /**
     * {@inheritDoc}
     *
     * @implNote Overridden, unchanged, so that {@link OrderItem}, in this package, can set it.
     */
    @Override
    protected void setLineItemId(@Nonnull final Long lineItemId) {
        super.setLineItemId(lineItemId);
    }
}
