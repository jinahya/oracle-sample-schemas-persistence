package com.github.jinahya.oracle.sample.schemas.co;

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

import com.github.jinahya.persistence.test.util.__Randomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Customer_Randomizer extends __Randomizer.___OfPodam<Customer> {

    Customer_Randomizer() {
        super(Customer.class, List.of(
                Customer.ATTRIBUTE_NAME_CUSTOMER_ID,
                Customer.ATTRIBUTE_NAME_ORDERS,
                Customer.ATTRIBUTE_NAME_SHIPMENTS
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Override
    public Customer get() {
        final var value = super.get();
        // Podam fills the attribute with an arbitrary string, which no @Email would accept; replace it with an
        // address which actually validates, and which is distinct enough to stand in for the business key that
        // Customer.equals(Object) compares.
        final var random = ThreadLocalRandom.current();
        value.setEmailAddress(
                "customer" + Long.toUnsignedString(random.nextLong(), Character.MAX_RADIX)
                + "@mail" + Long.toUnsignedString(random.nextLong(), Character.MAX_RADIX) + ".com"
        );
        return value;
    }
}
