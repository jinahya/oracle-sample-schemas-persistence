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
 * A randomizer which produces randomized {@link OrderItemWithEmbeddedId} instances.
 * <p>
 * The embedded {@code id}, and the {@code order}, {@code product} and {@code shipment} associations, are excluded from
 * randomization; {@link OrderItemWithEmbeddedId_Persister} supplies all of them.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class OrderItemWithEmbeddedId_Randomizer extends PodamObjectRandomizer<OrderItemWithEmbeddedId> {

    OrderItemWithEmbeddedId_Randomizer() {
        super(OrderItemWithEmbeddedId.class, List.of(
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_ID,
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_ORDER,
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_PRODUCT,
                OrderItemWithEmbeddedId.ATTRIBUTE_NAME_SHIPMENT
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
    public OrderItemWithEmbeddedId get() {
        return super.get();
    }
}
