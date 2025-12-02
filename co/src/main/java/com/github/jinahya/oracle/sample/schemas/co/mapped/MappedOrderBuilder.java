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

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderBuilder<
        SELF extends MappedOrderBuilder<SELF, TARGET>,
        TARGET extends MappedOrder
        >
        extends _MappedCoEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderBuilder(final Class<TARGET> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------------------- orderId
    @Deprecated(forRemoval = true)
    public Long orderId() {
        return orderId;
    }

    @Deprecated(forRemoval = true)
    protected SELF orderId(Long orderId) {
        this.orderId = orderId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms
    public LocalDateTime orderTms() {
        return orderTms;
    }

    SELF orderTms(final LocalDateTime orderTms) {
        this.orderTms = orderTms;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    @Deprecated(forRemoval = true)
    public Long customerId() {
        return customerId;
    }

    @Deprecated(forRemoval = true)
    protected SELF customerId(final Long customerId) {
        this.customerId = customerId;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus
    public String orderStatus() {
        return orderStatus;
    }

    public SELF orderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Deprecated(forRemoval = true)
    public Long storeId() {
        return storeId;
    }

    @Deprecated(forRemoval = true)
    protected SELF storeId(final Long storeId) {
        this.storeId = storeId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long orderId;

    private LocalDateTime orderTms;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long customerId;

    // -----------------------------------------------------------------------------------------------------------------
    private String orderStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long storeId;
}
