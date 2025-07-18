package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;
import lombok.extern.slf4j.Slf4j;

//@AddBeanClasses({
//        _PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
@Slf4j
class OrderPersistenceIT extends __MappedEntityPersistenceIT<Order, Long> {

    OrderPersistenceIT() {
        super(Order.class, Long.class);
    }

//    @Nested
//    class GetOrderItemsTotalPrice1_Test {
//
//        @Test
//        void _Zero_New() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // --------------------------------------------------------------------------------------------------- given
//                final var persisted =
//                        __MappedEntity_Persister_Utils.newPersistedInstanceOf(entityClass, em).orElseThrow();
//                // ---------------------------------------------------------------------------------------------------- when
//                final var orderItemsTotalPrice1 = persisted.getOrderItemsTotalPrice1();
//                // ---------------------------------------------------------------------------------------------------- then
//                assertThat(orderItemsTotalPrice1).isZero();
//            });
//        }
//
//        @Test
//        void __() {
//            // --------------------------------------------------------------------------------------------------- given
//            final var order = applyEntityCountAndRandomIndex(em -> c -> i -> {
//                return em.createQuery("SELECT e FROM Order AS e JOIN FETCH e.orderItems", entityClass)
//                        .setFirstResult(Math.toIntExact(i))
//                        .setMaxResults(1)
//                        .getSingleResult();
//            });
//            log.debug("order: {}", order);
//            // ---------------------------------------------------------------------------------------------------- when
//            final var orderItemsTotalPrice1 = order.getOrderItemsTotalPrice1();
//            log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
//            // ---------------------------------------------------------------------------------------------------- then
//            assertThat(orderItemsTotalPrice1).isNotNegative();
//        }
//    }
//
//    @DisplayName("getOrderItemsTotalPrice2(entityManager)BigDecimal")
//    @Nested
//    class GetOrderItemsTotalPrice2_Test {
//
//        @Test
//        void _Zero_New() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // --------------------------------------------------------------------------------------------------- given
//                final var persisted = newPersistedEntityInstance();
//                // ------------------------------------------------------------------------------------------------ when
//                final var orderItemsTotalPrice1 = persisted.getOrderItemsTotalPrice2(em);
//                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(orderItemsTotalPrice1).isNull();
//            });
//        }
//
//        @Test
//        void __() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var orderOptional = __Persistence_Test_Utils.selectRandom(em, entityClass);
//                assumeThat(orderOptional).isNotEmpty();
//                final var order = orderOptional.get();
//                log.debug("order: {}", order);
//                // ------------------------------------------------------------------------------------------------ when
//                final var orderItemsTotalPrice1 = order.getOrderItemsTotalPrice2(em);
//                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(orderItemsTotalPrice1).isNotNegative();
//            });
//        }
//
//        @Test
//        void __FetchOrderItems() {
//            acceptEntityCountAndRandomIndex(em -> c -> i -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var selected = em.createQuery("SELECT e FROM Order AS e JOIN FETCH e.orderItems", entityClass)
//                        .setFirstResult(Math.toIntExact(i))
//                        .setMaxResults(1)
//                        .getSingleResult();
//                assumeThat(selected).isNotNull();
//                log.debug("selected: {}", selected);
//                // ------------------------------------------------------------------------------------------------ when
//                final var orderItemsTotalPrice1 = selected.getOrderItemsTotalPrice1();
//                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(orderItemsTotalPrice1).isNotNegative();
//            });
//        }
//    }
//
//    @DisplayName("getOrderItemsTotalPrice3(entityManager)BigDecimal")
//    @Nested
//    class GetOrderItemsTotalPrice3_Test {
//
//        @Test
//        void _Zero_New() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // -------------------------------------------------------------------------------------------------- given
//                final var persisted = newPersistedEntityInstance();
//                // ------------------------------------------------------------------------------------------------ when
//                final var orderItemsTotalPrice1 = persisted.getOrderItemsTotalPrice3(em);
//                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(orderItemsTotalPrice1).isNull();
//            });
//        }
//
//        @Test
//        void __() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var orderOptional = __Persistence_Test_Utils.selectRandom(em, entityClass);
//                assumeThat(orderOptional).isNotEmpty();
//                final var order = orderOptional.get();
//                log.debug("order: {}", order);
//                // ------------------------------------------------------------------------------------------------ when
//                final var orderItemsTotalPrice1 = order.getOrderItemsTotalPrice3(em);
//                log.debug("orderItemsTotalPrice1: {}", orderItemsTotalPrice1);
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(orderItemsTotalPrice1).isNotNegative();
//            });
//        }
//    }
}
