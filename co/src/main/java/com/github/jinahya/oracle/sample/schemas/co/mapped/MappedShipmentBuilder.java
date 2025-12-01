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

@MappedSuperclass
@SuppressWarnings({
        "unchecked",
        "java:S119", // Type parameter names should comply with a naming convention
        "java:S2637" // "" values should not be set to null
})
public abstract class MappedShipmentBuilder<
        SELF extends MappedShipmentBuilder<SELF, SHIPMENT, STORE, CUSTOMER>,
        SHIPMENT extends MappedShipment<STORE, CUSTOMER>,
        STORE extends MappedStore,
        CUSTOMER extends MappedCustomer
        >
        extends _MappedCoEntityBuilder<SELF, SHIPMENT> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedShipmentBuilder(final Class<SHIPMENT> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------------ shipmentId
    @Deprecated(forRemoval = true)
    Long shipmentId() {
        return shipmentId;
    }

    @Deprecated(forRemoval = true)
    SELF shipmentId(final Long shipmentId) {
        this.shipmentId = shipmentId;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Deprecated(forRemoval = true)
    Long storeId() {
        return storeId;
    }

    @Deprecated(forRemoval = true)
    SELF storeId(Long storeId) {
        this.storeId = storeId;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    public STORE store() {
        return store;
    }

    public SELF store(final STORE store) {
        this.store = store;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    @Deprecated(forRemoval = true)
    Long customerId() {
        return customerId;
    }

    @Deprecated(forRemoval = true)
    SELF customerId(Long customerId) {
        this.customerId = customerId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- customer
    public CUSTOMER customer() {
        return customer;
    }

    public SELF customer(final CUSTOMER customer) {
        this.customer = customer;
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
    private Long shipmentId;

    // -----------------------------------------------------------------------------------------------------------------
    private Long storeId;

    private STORE store;

    // -----------------------------------------------------------------------------------------------------------------
    private Long customerId;

    private CUSTOMER customer;

    // -----------------------------------------------------------------------------------------------------------------
    private String deliveryAddress;

    private String shipmentStatus;
}
