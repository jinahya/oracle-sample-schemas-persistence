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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A persister which persists {@link OrderItemWithIdClass} instances.
 * <p>
 * Each instance is given a newly persisted {@link Order} and a newly persisted {@link Product} first, so that its
 * {@code order} and its {@code product} -- neither of which is nullable -- refer to rows which are already in the
 * database, and a newly persisted {@link Shipment} at random, because that column is nullable. The order is flushed
 * before it is set, so that the identifier which {@code setOrder} mirrors into the key column is there to copy. The
 * {@code lineItemId} is assigned here as well: it numbers the item within its order, and is not generated.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class OrderItemWithIdClass_Persister extends AbstractEntityPersister<OrderItemWithIdClass> {

    OrderItemWithIdClass_Persister() {
        super(OrderItemWithIdClass.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public OrderItemWithIdClass apply(final EntityManager entityManager, final OrderItemWithIdClass entityInstance) {
        final var order = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Order.class);
        final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        final Shipment shipment = ThreadLocalRandom.current().nextBoolean() ? null :
                                  EntityPersisterUtils.newPersistedInstanceOf(entityManager, Shipment.class);
        entityManager.flush();
        entityInstance.setOrder(order);
        entityInstance.setProduct(product);
        entityInstance.setShipment(shipment);
        // LINE_ITEM_ID is not generated -- it numbers the item within its order -- so nothing but this assigns it.
        entityInstance.setLineItemId(ThreadLocalRandom.current().nextLong(1, 100));
        return super.apply(entityManager, entityInstance);
    }
}
