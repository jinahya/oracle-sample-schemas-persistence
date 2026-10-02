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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

/**
 * A randomizer which produces randomized {@link OrderItemWithIdClass} instances.
 * <p>
 * The {@code orderId} and {@code lineItemId} key attributes, and the {@code order}, {@code product} and
 * {@code shipment} associations, are excluded from randomization; {@link OrderItemWithIdClass_Persister} supplies all
 * of them.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class OrderItemWithIdClass_Randomizer extends PodamObjectRandomizer<OrderItemWithIdClass> {

    OrderItemWithIdClass_Randomizer() {
        super(OrderItemWithIdClass.class, List.of(
                OrderItemWithIdClass.ATTRIBUTE_NAME_ORDER_ID,
                OrderItemWithIdClass.ATTRIBUTE_NAME_ORDER,
                OrderItemWithIdClass.ATTRIBUTE_NAME_LINE_ITEM_ID,
                OrderItemWithIdClass.ATTRIBUTE_NAME_PRODUCT,
                OrderItemWithIdClass.ATTRIBUTE_NAME_SHIPMENT
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
    public OrderItemWithIdClass get() {
        return super.get();
    }
}
