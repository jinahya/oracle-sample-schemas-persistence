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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

/**
 * A randomizer which produces randomized {@link Order} instances.
 * <p>
 * The generated {@code orderId}, the {@code customer} and {@code store} associations, and the {@code orderItems}
 * collection, are excluded from randomization; {@link Order_Persister} supplies the {@code customer} and the
 * {@code store}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Order_Randomizer extends PodamObjectRandomizer<Order> {

    Order_Randomizer() {
        super(Order.class, List.of(
                Order.ATTRIBUTE_NAME_ORDER_ID,
                Order.ATTRIBUTE_NAME_CUSTOMER,
                Order.ATTRIBUTE_NAME_STORE,
                Order.ATTRIBUTE_NAME_ORDER_ITEMS
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
    public Order get() {
        return super.get();
    }
}
