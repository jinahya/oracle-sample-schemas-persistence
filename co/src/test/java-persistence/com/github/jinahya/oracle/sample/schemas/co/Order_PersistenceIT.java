package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister_Utils;
import com.github.jinahya.oracle.sample.schemas.__Persistence_Test_Utils;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@Slf4j
class Order_PersistenceIT extends __MappedEntity_PersistenceIT<Order, Long> {

    Order_PersistenceIT() {
        super(Order.class, Long.class);
    }

    @Nested
    class GetOrderItemsTotalPrice1_Test {

        @Test
        void _Zero_New() {
            acceptEntityManagerInTransactionAndRollback(em -> {
                // --------------------------------------------------------------------------------------------------- given
                final var persisted =
                        __MappedEntity_Persister_Utils.newPersistedInstanceOf(entityClass, em).orElseThrow();
                // ---------------------------------------------------------------------------------------------------- when
                final var orderItemsTotalPrice1 = persisted.getOrderItemsTotalPrice1();
                // ---------------------------------------------------------------------------------------------------- then
                assertThat(orderItemsTotalPrice1).isZero();
            });
        }

        @Test
        void __() {
            // --------------------------------------------------------------------------------------------------- given
            final var order = applyEntityCountAndRandomIndex(em -> c -> i -> {
                return em.createQuery("SELECT e FROM Order AS e JOIN FETCH e.orderItems", entityClass)
                        .setFirstResult(Math.toIntExact(i))
                        .setMaxResults(1)
                        .getSingleResult();
            });
            log.debug("order: {}", order);
            // ---------------------------------------------------------------------------------------------------- when
            final var orderItemsTotalPrice1 = order.getOrderItemsTotalPrice1();
            log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(orderItemsTotalPrice1).isNotNegative();
        }
    }

    @DisplayName("getOrderItemsTotalPrice2(entityManager)BigDecimal")
    @Nested
    class GetOrderItemsTotalPrice2_Test {

        @Test
        void _Zero_New() {
            acceptEntityManagerInTransactionAndRollback(em -> {
                // --------------------------------------------------------------------------------------------------- given
                final var persisted = newPersistedEntityInstance();
                // ------------------------------------------------------------------------------------------------ when
                final var orderItemsTotalPrice1 = persisted.getOrderItemsTotalPrice2(em);
                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(orderItemsTotalPrice1).isNull();
            });
        }

        @Test
        void __() {
            acceptEntityManagerInTransactionAndRollback(em -> {
                // ----------------------------------------------------------------------------------------------- given
                final var orderOptional = __Persistence_Test_Utils.selectRandom(em, entityClass);
                assumeThat(orderOptional).isNotEmpty();
                final var order = orderOptional.get();
                log.debug("order: {}", order);
                // ------------------------------------------------------------------------------------------------ when
                final var orderItemsTotalPrice1 = order.getOrderItemsTotalPrice2(em);
                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(orderItemsTotalPrice1).isNotNegative();
            });
        }

        @Test
        void __FetchOrderItems() {
            acceptEntityCountAndRandomIndex(em -> c -> i -> {
                // ----------------------------------------------------------------------------------------------- given
                final var selected = em.createQuery("SELECT e FROM Order AS e JOIN FETCH e.orderItems", entityClass)
                        .setFirstResult(Math.toIntExact(i))
                        .setMaxResults(1)
                        .getSingleResult();
                assumeThat(selected).isNotNull();
                log.debug("selected: {}", selected);
                // ------------------------------------------------------------------------------------------------ when
                final var orderItemsTotalPrice1 = selected.getOrderItemsTotalPrice1();
                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(orderItemsTotalPrice1).isNotNegative();
            });
        }
    }

    @DisplayName("getOrderItemsTotalPrice3(entityManager)BigDecimal")
    @Nested
    class GetOrderItemsTotalPrice3_Test {

        @Test
        void _Zero_New() {
            acceptEntityManagerInTransactionAndRollback(em -> {
                // -------------------------------------------------------------------------------------------------- given
                final var persisted = newPersistedEntityInstance();
                // ------------------------------------------------------------------------------------------------ when
                final var orderItemsTotalPrice1 = persisted.getOrderItemsTotalPrice3(em);
                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(orderItemsTotalPrice1).isNull();
            });
        }

        @Test
        void __() {
            acceptEntityManagerInTransactionAndRollback(em -> {
                // ----------------------------------------------------------------------------------------------- given
                final var orderOptional = __Persistence_Test_Utils.selectRandom(em, entityClass);
                assumeThat(orderOptional).isNotEmpty();
                final var order = orderOptional.get();
                log.debug("order: {}", order);
                // ------------------------------------------------------------------------------------------------ when
                final var orderItemsTotalPrice1 = order.getOrderItemsTotalPrice3(em);
                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
                // ------------------------------------------------------------------------------------------------ then
                assertThat(orderItemsTotalPrice1).isNotNegative();
            });
        }
    }
}