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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import com.github.jinahya.persistence.mapped.test.___Builder_TestUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
class OrderItem_Persister extends __MappedEntity_Persister<OrderItem, OrderItemId> {

    OrderItem_Persister() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final OrderItem entityInstance) {
        {
            final var order = __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Order.class);
            entityInstance.setOrder(order);
        }
        {
            final var product = __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
            entityInstance.setProduct(product);
        }
        {
            final var builder = ___Builder_TestUtils.newBuilderInstanceFromRandomizedInstanceOf(
                    ShipmentBuilder.class,
                    Shipment.class
            ).orElseThrow();
            builder.customer(entityInstance.getOrder().getCustomer());
            builder.store(entityInstance.getOrder().getStore());
            final var shipment = builder.build();
            entityManager.persist(shipment);
            entityInstance.setShipment(
                    ThreadLocalRandom.current().nextBoolean() ? null : shipment
            );
        }
        super.persist(entityManager, entityInstance);
    }
}
