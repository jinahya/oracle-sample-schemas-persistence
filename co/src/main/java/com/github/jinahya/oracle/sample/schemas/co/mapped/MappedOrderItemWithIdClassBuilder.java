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

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderItemWithIdClassBuilder<
        SELF extends MappedOrderItemWithIdClassBuilder<SELF, TARGET>,
        TARGET extends MappedOrderItemWithIdClass
        >
        extends MappedOrderItemBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedOrderItemWithIdClassBuilder(final Class<TARGET> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long getOrderId() {
        return orderId;
    }

    protected SELF setOrderId(final Long orderId) {
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
