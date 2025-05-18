package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.Persistence_TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@DisplayName("findByStoreName")
class Store_FindByStoreName_PersistenceIT extends Store__PersistenceIT {

    @Test
    void __NativeQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var storeName =
                Persistence_TestUtils.findRandom(entityManager(), entityClass)
                        .map(Store::getStoreName)
                        .orElse(null);
        assumeThat(storeName)
                .as("storeName mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createNamedQuery("Store.findByStoreName", Store.class)
                .setParameter("storeName", storeName)
                .getSingleResult();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getStoreName()).isEqualTo(storeName);
    }

    @Test
    void __DirectQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var storeName =
                Persistence_TestUtils.findRandom(entityManager(), entityClass)
                        .map(Store::getStoreName)
                        .orElse(null);
        assumeThat(storeName)
                .as("storeName mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createQuery("SELECT e FROM Store AS e WHERE e.storeName = :storeName", Store.class)
                .setParameter("storeName", storeName)
                .getSingleResult();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getStoreName()).isEqualTo(storeName);
    }

    @Test
    void __CriteriaApi() {
        // --------------------------------------------------------------------------------------------------- given
        final var storeName =
                Persistence_TestUtils.findRandom(entityManager(), entityClass)
                        .map(Store::getStoreName)
                        .orElse(null);
        assumeThat(storeName)
                .as("storeName mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = applyEntityManager(em -> {
            final var b = em.getCriteriaBuilder();
            final var q = b.createQuery(Store.class);
            final var r = q.from(Store.class);                    // FROM Store AS e
            q.select(r);                                          // SELECT e
            q.where(b.equal(r.get(Store_.storeName), storeName)); // WHERE e.storeName = :storeName
            return em.createQuery(q).getSingleResult();
        });
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getStoreName()).isEqualTo(storeName);
    }
}