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

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.PluralAttribute;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.oracle.sample.schemas.persistence.co._Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the bidirectional association between {@link Customer} and {@link Order}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Persistence_Test_Producer.class)
@EnableAutoWeld
class Customer_Orders_Test {

    @DisplayName("Customer.orders is a one-to-many of Order")
    @Test
    void customerOrders_IsOneToManyOfOrder_() {
        final var customer = entityManagerFactory.getMetamodel().entity(Customer.class);
        final var orders = customer.getAttribute(Customer.ATTRIBUTE_NAME_ORDERS);
        assertThat(orders.getPersistentAttributeType())
                .isEqualTo(Attribute.PersistentAttributeType.ONE_TO_MANY);
        assertThat(((PluralAttribute<?, ?, ?>) orders).getElementType().getJavaType())
                .isEqualTo(Order.class);
    }

    @DisplayName("Order.customer is a many-to-one of Customer")
    @Test
    void orderCustomer_IsManyToOneOfCustomer_() {
        final var order = entityManagerFactory.getMetamodel().entity(Order.class);
        final var customer = order.getAttribute(Order.ATTRIBUTE_NAME_CUSTOMER);
        assertThat(customer.getPersistentAttributeType())
                .isEqualTo(Attribute.PersistentAttributeType.MANY_TO_ONE);
        assertThat(customer.getJavaType()).isEqualTo(Customer.class);
    }

    @DisplayName("a persisted customer's orders are reachable from the customer")
    @Test
    void orders_ReachableFromCustomer_() {
        ___Persistence_TestUtils.applyInTransactionAndRollback(entityManager, em -> {
            final var found = em.createQuery(
                            "SELECT c FROM Customer c LEFT JOIN FETCH c.orders", Customer.class)
                    .setMaxResults(1)
                    .getResultList();
            assertThat(found).as("customers joined with their orders").isNotNull();
            return found;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__TestPU
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @__TestPU
    @Inject
    private EntityManager entityManager;
}
