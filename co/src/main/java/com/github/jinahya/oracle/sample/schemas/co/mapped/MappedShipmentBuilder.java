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
        "java:S119", // Type parameter names should comply with a naming convention
        "java:S2637" // "" values should not be set to null
})
public abstract class MappedShipmentBuilder<
        SELF extends MappedShipmentBuilder<SELF, TARGET>,
        TARGET extends MappedShipment
        >
        extends _MappedCoEntityBuilder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedShipmentBuilder(final Class<TARGET> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    @Deprecated(forRemoval = true)
    public Long shipmentId() {
        return shipmentId;
    }

    @Deprecated(forRemoval = true)
    protected SELF shipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
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

    // ------------------------------------------------------------------------------------------------- deliveryAddress
    public String deliveryAddress() {
        return deliveryAddress;
    }

    public SELF deliveryAddress(final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------- shipmentStatus
    public String shipmentStatus() {
        return shipmentStatus;
    }

    public SELF shipmentStatus(final String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long storeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long customerId;

    // -----------------------------------------------------------------------------------------------------------------
    private String deliveryAddress;

    private String shipmentStatus;
}
