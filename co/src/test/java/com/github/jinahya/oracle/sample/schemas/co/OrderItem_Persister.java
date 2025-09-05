package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Builder_TestUtils;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
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
            final var builder = __Builder_TestUtils.newBuilderInstanceFromRandomizedInstanceOf(
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
