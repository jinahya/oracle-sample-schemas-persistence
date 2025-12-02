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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedCustomer;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedShipment;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedStore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Entity
@Table(name = MappedShipment.TABLE_NAME)
class Shipment extends MappedShipment {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    static ShipmentBuilder builder() {
        return new ShipmentBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Shipment() {
        super();
    }

    Shipment(final ShipmentBuilder builder) {
        super(builder);
        if (getStoreId() == null) {
            setStore(builder.store());
        }
        if (getCustomerId() == null) {
            setCustomer(builder.customer());
        }
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final boolean equals(final Object obj) {
        return equalsWithShipmentId(obj);
    }

    @Override
    public final int hashCode() {
        return hashCodeWithShipmentId();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ----------------------------------------------------------------------------------------------------------- store
    @Nonnull
    public Store getStore() {
        return store;
    }

    public void setStore(@Nonnull final Store store) {
        this.store = store;
        setStoreId(
                Optional.ofNullable(this.store)
                        .map(MappedStore::getStoreId)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- product
    @Nonnull
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@Nonnull final Customer customer) {
        this.customer = customer;
        setCustomerId(
                Optional.ofNullable(this.customer)
                        .map(MappedCustomer::getCustomerId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Store store;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Customer customer;
}
