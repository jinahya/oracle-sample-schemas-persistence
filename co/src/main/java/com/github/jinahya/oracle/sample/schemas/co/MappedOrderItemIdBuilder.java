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

import com.github.jinahya.persistence.mapped.__Builder;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderItemIdBuilder<
        SELF extends MappedOrderItemIdBuilder<SELF, ORDER_ITEM_ID>,
        ORDER_ITEM_ID extends MappedOrderItemId
        >
        extends __Builder<SELF, ORDER_ITEM_ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItemIdBuilder(final Class<ORDER_ITEM_ID> targetClass) {
        super(targetClass);
    }

    // --------------------------------------------------=--------------------------------------------- java.lang.String

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long orderId() {
        return orderId;
    }

    public SELF orderId(final Long orderId) {
        this.orderId = orderId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId
    public Long lineItemId() {
        return lineItemId;
    }

    public SELF lineItemId(final Long lineItemId) {
        this.lineItemId = lineItemId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Long orderId;

    private Long lineItemId;
}
