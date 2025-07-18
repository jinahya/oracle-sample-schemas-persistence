package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class InventoryPersistenceIT extends __MappedEntityPersistenceIT<Inventory, Long> {

    InventoryPersistenceIT() {
        super(Inventory.class, Long.class);
    }

//    // -----------------------------------------------------------------------------------------------------------------
//    @DisplayName("selectListByStore")
//    @Nested
//    class FindAllByStore_Test {
//
//        @Test
//        void __NamedQuery() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var randomStore = ___JakartaPersistenceTestUtils.selectRandom(em, Store.class);
//                assumeThat(randomStore).as("random store").isNotEmpty();
//                final var store = randomStore.get();
//                // ------------------------------------------------------------------------------------------------ when
//                final var result = em
//                        .createNamedQuery("Inventory.selectListWhereStoreEqual", Inventory.class)
//                        .setParameter("store", store)
//                        .setMaxResults(1)
//                        .getResultList();
//                // ------------------------------------------------------------------------------------------------ then
//                assumeThat(result).isNotEmpty();
//                assertThat(result)
//                        .extracting(Inventory::getStore)
//                        .containsOnly(store);
//            });
//        }
//
//        @Test
//        void __TypedQuery() {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var randomStore = __Persistence_Test_Utils.selectRandom(em, Store.class);
//                assumeThat(randomStore).as("random store").isNotEmpty();
//                final var store = randomStore.get();
//                // ------------------------------------------------------------------------------------------------ when
//                final var result = em.createQuery(
//                                """
//                                        SELECT e
//                                        FROM Inventory AS e
//                                        WHERE e.store = :store""",
//                                Inventory.class
//                        )
//                        .setParameter("store", store)
//                        .setMaxResults(1)
//                        .getResultList();
//                // ------------------------------------------------------------------------------------------------ then
//                assumeThat(result).isNotEmpty();
//                assertThat(result)
//                        .extracting(Inventory::getStore)
//                        .containsOnly(store);
//            });
//        }
//
//        @Test
//        void __CriteriaApi() {
//            // --------------------------------------------------------------------------------------------------- given
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                final var randomStore = __Persistence_Test_Utils.selectRandom(em, Store.class);
//                assumeThat(randomStore).as("random store").isNotEmpty();
//                final var store = randomStore.get();
//                // ---------------------------------------------------------------------------------------------------- when
//                final var builder = em.getCriteriaBuilder();
//                final var criteria = builder.createQuery(Inventory.class);
//                final var root = criteria.from(Inventory.class);
//                criteria.select(root);
//                criteria.where(builder.equal(root.get(Inventory_.store), store));
//                final var result = em
//                        .createQuery(criteria)
//                        .setMaxResults(1)
//                        .getResultList();
//                // ---------------------------------------------------------------------------------------------------- then
//                assumeThat(result).isNotEmpty();
//                assertThat(result)
//                        .extracting(Inventory::getStore)
//                        .containsOnly(store);
//            });
//        }
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//    @DisplayName("selectSingleByStoreAndProduct")
//    @Nested
//    class FindByStoreAndProduct_Test {
//
//        @Test
//        void __NamedQuery() throws NoResultException {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var randomInventory = __Persistence_Test_Utils.selectRandom(em, Inventory.class);
//                assumeThat(randomInventory).isNotEmpty();
//                final var inventory = randomInventory.get();
//                final var store = inventory.getStore();
//                final var product = inventory.getProduct();
//                // ------------------------------------------------------------------------------------------------ when
//                final var result = em
//                        .createNamedQuery("Inventory.selectSingleWhereStoreEqualAndProductEqual", Inventory.class)
//                        .setParameter("store", store)
//                        .setParameter("product", product)
//                        .getSingleResult();
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(result).isEqualTo(inventory);
//                assertThat(result.getStore()).isEqualTo(store);
//                assertThat(result.getProduct()).isEqualTo(product);
//            });
//        }
//
//        @Test
//        void __QueryLanguage() throws NoResultException {
//            // TODO: implement!
//        }
//
//        @Test
//        void __CriteriaApi() throws NoResultException {
//            acceptEntityManagerInTransactionAndRollback(em -> {
//                // ----------------------------------------------------------------------------------------------- given
//                final var randomInventory = __Persistence_Test_Utils.selectRandom(em, Inventory.class);
//                assumeThat(randomInventory).isNotEmpty();
//                final var inventory = randomInventory.get();
//                final var store = inventory.getStore();
//                final var product = inventory.getProduct();
//                // ------------------------------------------------------------------------------------------------ when
//                final var builder = em.getCriteriaBuilder();
//                final var criteria = builder.createQuery(Inventory.class);
//                // FROM Inventory AS e
//                final var root = criteria.from(Inventory.class);
//                // SELECT e
//                criteria.select(root);
//                // WHERE e.store = :store AND e.product = :product
//                criteria.where(builder.and(
//                        builder.equal(root.get(Inventory_.store), store),
//                        builder.equal(root.get(Inventory_.product), product)
//                ));
//                final var result = em.createQuery(criteria).getSingleResult();
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(result).isEqualTo(inventory);
//                assertThat(result.getStore()).isEqualTo(store);
//                assertThat(result.getProduct()).isEqualTo(product);
//            });
//        }
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//    @DisplayName("findAllByProduct")
//    @Nested
//    class FindAllByProduct_Test {
//        // TODO: do some!
//    }
}
