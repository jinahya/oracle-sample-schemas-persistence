package com.github.jinahya.oracle.sample.persistence.co;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

import com.github.jinahya.oracle.sample.persistence.co.mapped.OrderItemId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link OrderItem} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class OrderItem_Test
        extends _Entity_Test<OrderItem> {

    private static OrderItemId id(final int value) {
        final var id = new OrderItemId();
        id.setOrderId((long) value);
        id.setLineItemId((long) value);
        return id;
    }

    // ---------------------------------------------------------------------------------------------

    OrderItem_Test() {
        super(OrderItem.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link OrderItem#equals(Object) equals} compares only the embedded
     *         {@code @Id} {@code id} -- the composite key of {@code orderId} and {@code lineItemId}
     *         -- because every other attribute is mutable state.
     */
    @Override
    public SingleTypeEqualsVerifierApi<OrderItem> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValuesForField(OrderItem.ATTRIBUTE_NAME_ID_, id(1), id(2))
                .suppress(Warning.SURROGATE_KEY);
    }
}
