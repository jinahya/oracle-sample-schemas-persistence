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
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedOrderBuilder;
import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedStore;

import java.util.Optional;

class OrderBuilder extends MappedOrderBuilder<OrderBuilder, Order> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    OrderBuilder() {
        super(Order.class);
    }

    // -------------------------------------------------------------------------------------------------------- customer
    public Customer customer() {
        return customer;
    }

    public OrderBuilder customer(final Customer customer) {
        this.customer = customer;
        return customerId(
                Optional.ofNullable(this.customer)
                        .map(MappedCustomer::getCustomerId)
                        .orElse(null)
        );
    }

    // ----------------------------------------------------------------------------------------------------------- store
    public Store stoer() {
        return stoer;
    }

    public OrderBuilder stoer(final Store stoer) {
        this.stoer = stoer;
        return storeId(
                Optional.ofNullable(this.stoer)
                        .map(MappedStore::getStoreId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Customer customer;

    private Store stoer;
}
