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

import com.github.jinahya.persistence.test.util.__Persister;
import com.github.jinahya.persistence.test.util.__PersisterUtils;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

class OrderItemWithEmbeddedId_Persister extends __Persister<OrderItemWithEmbeddedId> {

    OrderItemWithEmbeddedId_Persister() {
        super(OrderItemWithEmbeddedId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public OrderItemWithEmbeddedId apply(final EntityManager entityManager,
                                         final OrderItemWithEmbeddedId entityInstance) {
        entityInstance.setOrder(__PersisterUtils.newPersistedInstanceOf(entityManager, Order.class));
        entityInstance.setProduct(__PersisterUtils.newPersistedInstanceOf(entityManager, Product.class));
        if (ThreadLocalRandom.current().nextBoolean()) {
            entityInstance.setShipment(__PersisterUtils.newPersistedInstanceOf(entityManager, Shipment.class));
        }
        // LINE_ITEM_ID is not generated -- it numbers the item within its order -- so nothing but this assigns it.
        entityInstance.getId().setLineItemId(ThreadLocalRandom.current().nextLong(1, 100));
        return super.apply(entityManager, entityInstance);
    }
}
