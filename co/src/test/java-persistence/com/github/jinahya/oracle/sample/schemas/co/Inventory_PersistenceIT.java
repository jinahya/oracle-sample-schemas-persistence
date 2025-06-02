package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__Persistence_Test_Utils;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import jakarta.persistence.NoResultException;
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
class Inventory_PersistenceIT extends __MappedEntity_PersistenceIT<Inventory, Long> {

    Inventory_PersistenceIT() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectListByStore")
    @Nested
    class FindAllByStore_Test {

        @Test
        void __NamedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var randomStore = __Persistence_Test_Utils.selectRandom(entityManager(), Store.class);
            assumeThat(randomStore).as("random store").isNotEmpty();
            final var store = randomStore.get();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = entityManager()
                    .createNamedQuery("Inventory.selectListWhereStoreEqual", Inventory.class)
                    .setParameter("store", store)
                    .setMaxResults(1)
                    .getResultList();
            // ---------------------------------------------------------------------------------------------------- then
            assumeThat(result).isNotEmpty();
            assertThat(result)
                    .extracting(Inventory::getStore)
                    .containsOnly(store);
        }

        @Test
        void __TypedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var randomStore = __Persistence_Test_Utils.selectRandom(entityManager(), Store.class);
            assumeThat(randomStore).as("random store").isNotEmpty();
            final var store = randomStore.get();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = entityManager().createQuery(
                            """
                                    SELECT e
                                    FROM Inventory AS e
                                    WHERE e.store = :store""",
                            Inventory.class
                    )
                    .setParameter("store", store)
                    .setMaxResults(1)
                    .getResultList();
            // ---------------------------------------------------------------------------------------------------- then
            assumeThat(result).isNotEmpty();
            assertThat(result)
                    .extracting(Inventory::getStore)
                    .containsOnly(store);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var randomStore = __Persistence_Test_Utils.selectRandom(entityManager(), Store.class);
            assumeThat(randomStore).as("random store").isNotEmpty();
            final var store = randomStore.get();
            // ---------------------------------------------------------------------------------------------------- when
            final var builder = entityManager().getCriteriaBuilder();
            final var criteria = builder.createQuery(Inventory.class);
            final var root = criteria.from(Inventory.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Inventory_.store), store));
            final var result = entityManager()
                    .createQuery(criteria)
                    .setMaxResults(1)
                    .getResultList();
            // ---------------------------------------------------------------------------------------------------- then
            assumeThat(result).isNotEmpty();
            assertThat(result)
                    .extracting(Inventory::getStore)
                    .containsOnly(store);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectSingleByStoreAndProduct")
    @Nested
    class FindByStoreAndProduct_Test {

        @Test
        void __NamedQuery() throws NoResultException {
            // --------------------------------------------------------------------------------------------------- given
            final var randomInventory = __Persistence_Test_Utils.selectRandom(entityManager(), Inventory.class);
            assumeThat(randomInventory).isNotEmpty();
            final var inventory = randomInventory.get();
            final var store = inventory.getStore();
            final var product = inventory.getProduct();
            // ---------------------------------------------------------------------------------------------------- when
            final var result = entityManager()
                    .createNamedQuery("Inventory.selectSingleWhereStoreEqualAndProductEqual", Inventory.class)
                    .setParameter("store", store)
                    .setParameter("product", product)
                    .getSingleResult();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result).isEqualTo(inventory);
            assertThat(result.getStore()).isEqualTo(store);
            assertThat(result.getProduct()).isEqualTo(product);
        }

        @Test
        void __QueryLanguage() throws NoResultException {
            // TODO: implement!
        }

        @Test
        void __CriteriaApi() throws NoResultException {
            // --------------------------------------------------------------------------------------------------- given
            final var randomInventory = __Persistence_Test_Utils.selectRandom(entityManager(), Inventory.class);
            assumeThat(randomInventory).isNotEmpty();
            final var inventory = randomInventory.get();
            final var store = inventory.getStore();
            final var product = inventory.getProduct();
            // ---------------------------------------------------------------------------------------------------- when
            final var builder = entityManager().getCriteriaBuilder();
            final var criteria = builder.createQuery(Inventory.class);
            // FROM Inventory AS e
            final var root = criteria.from(Inventory.class);
            // SELECT e
            criteria.select(root);
            // WHERE e.store = :store AND e.product = :product
            criteria.where(builder.and(
                    builder.equal(root.get(Inventory_.store), store),
                    builder.equal(root.get(Inventory_.product), product)
            ));
            final var result = entityManager().createQuery(criteria).getSingleResult();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result).isEqualTo(inventory);
            assertThat(result.getStore()).isEqualTo(store);
            assertThat(result.getProduct()).isEqualTo(product);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("findAllByProduct")
    @Nested
    class FindAllByProduct_Test {
        // TODO: do some!
    }
}