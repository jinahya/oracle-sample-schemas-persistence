package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link OrderItem} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class OrderItem_Test extends _DomainEntity_Test<OrderItem, OrderItemId> {

    OrderItem_Test() {
        super(OrderItem.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link OrderItem#equals(Object) equals} compares only the embedded {@code @Id} {@code id} -- the
     * composite key of {@code orderId} and {@code lineItemId} -- because every other attribute is mutable state.
     */
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY);
    }
}
