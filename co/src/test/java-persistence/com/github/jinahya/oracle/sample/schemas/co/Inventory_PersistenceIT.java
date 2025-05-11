package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.Persistence_TestUtils;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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
    @DisplayName("findAllByStore")
    @Nested
    class FindAllByStore_Test {

        @Test
        void __NamedQuery() {
            // ------------------------------------------------------------------------------------------------------- given
            final var randomStore = Persistence_TestUtils.findRandom(entityManager(), Store.class);
            assumeThat(randomStore).isNotEmpty();
            final var store = randomStore.get();
            // -------------------------------------------------------------------------------------------------------- when
            final var result = entityManager()
                    .createNamedQuery("Inventory.findAllByStore", Inventory.class)
                    .setParameter("store", store)
                    .getResultList();
            // -------------------------------------------------------------------------------------------------------- then
            assertThat(result).satisfiesAnyOf(
                    r -> assertThat(r).isEmpty(),
                    r -> assertThat(r).allSatisfy(e -> {
                        assertThat(e.getStore()).isEqualTo(store);
                    })
            );
            assertThat(result)
                    .extracting(Inventory::getStore)
                    .containsOnly(store);
        }

        @Test
        void __TypedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var randomStore = Persistence_TestUtils.findRandom(entityManager(), Store.class);
            assumeThat(randomStore).isNotEmpty();
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
                    .getResultList();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result).satisfiesAnyOf(
                    r -> assertThat(r).isEmpty(),
                    r -> assertThat(r).allSatisfy(e -> {
                        assertThat(e.getStore()).isEqualTo(store);
                    })
            );
            assertThat(result)
                    .extracting(Inventory::getStore)
                    .containsOnly(store);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var randomStore = Persistence_TestUtils.findRandom(entityManager(), Store.class);
            assumeThat(randomStore).isNotEmpty();
            final var store = randomStore.get();
            // ---------------------------------------------------------------------------------------------------- when
            final var b = entityManager().getCriteriaBuilder();
            final var q = b.createQuery(Inventory.class);
            final var r = q.from(Inventory.class);
            q.select(r);
            q.where(b.equal(r.get(Inventory_.store), store));
            final var result = entityManager().createQuery(q).getResultList();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .extracting(Inventory::getStore)
                    .containsOnly(store);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("findByStoreAndProduct")
    @Nested
    class FindByStoreAndProduct_Test {

        @Test
        void __NamedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            var randomStore = Persistence_TestUtils.findRandom(entityManager(), Store.class);
            assumeThat(randomStore).isNotEmpty();
            var randomProduct = Persistence_TestUtils.findRandom(entityManager(), Product.class);
            assumeThat(randomProduct).isNotEmpty();
            if (ThreadLocalRandom.current().nextBoolean()) {
                final var randomInventory = Persistence_TestUtils.findRandom(entityManager(), Inventory.class);
                assumeThat(randomInventory).isNotEmpty();
                randomStore = Optional.of(randomInventory.get().getStore());
                randomProduct = Optional.of(randomInventory.get().getProduct());
            }
            final var store = randomStore.get();
            final var product = randomProduct.get();
            // ---------------------------------------------------------------------------------------------------- when
            final Inventory result;
            try {
                result = entityManager()
                        .createNamedQuery("Inventory.findByStoreAndProduct", Inventory.class)
                        .setParameter("store", store)
                        .setParameter("product", product)
                        .getSingleResult();
                log.debug("result: {}", result);
            } catch (final NoResultException nre) {
                log.warn("no result for {}, {}", store, product);
                return;
            }
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result.getStore()).isEqualTo(store);
            assertThat(result.getProduct()).isEqualTo(product);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            var randomStore = Persistence_TestUtils.findRandom(entityManager(), Store.class);
            assumeThat(randomStore).isNotEmpty();
            var randomProduct = Persistence_TestUtils.findRandom(entityManager(), Product.class);
            assumeThat(randomProduct).isNotEmpty();
            if (ThreadLocalRandom.current().nextBoolean()) {
                final var randomInventory = Persistence_TestUtils.findRandom(entityManager(), Inventory.class);
                assumeThat(randomInventory).isNotEmpty();
                randomStore = Optional.of(randomInventory.get().getStore());
                randomProduct = Optional.of(randomInventory.get().getProduct());
            }
            final var store = randomStore.get();
            final var product = randomProduct.get();
            // ---------------------------------------------------------------------------------------------------- when
            final var b = entityManager().getCriteriaBuilder();
            final var q = b.createQuery(Inventory.class);
            final var r = q.from(Inventory.class);
            q.select(r);
            q.where(b.and(
                    b.equal(r.get(Inventory_.store), store),
                    b.equal(r.get(Inventory_.product), product)
            ));
            final Inventory result;
            try {
                result = entityManager().createQuery(q).getSingleResult();
            } catch (final NoResultException nre) {
                log.warn("no result for {}, {}", store, product);
                return;
            }
            // ---------------------------------------------------------------------------------------------------- then
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