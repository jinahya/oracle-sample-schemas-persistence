package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__BuilderTestUtils;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersister;
import com.github.jinahya.persistence.mapped.test.__MappedEntityPersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
class OrderItem_Persister extends __MappedEntityPersister<OrderItem, OrderItemId> {

    OrderItem_Persister() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final OrderItem entityInstance) {
        {
            final var order = __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Order.class);
            entityInstance.setOrder(order);
        }
        {
            final var product = __MappedEntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
            entityInstance.setProduct(product);
        }
        {
            final var builder = __BuilderTestUtils.newBuilderInstanceFromRandomizedInstanceOf(
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
