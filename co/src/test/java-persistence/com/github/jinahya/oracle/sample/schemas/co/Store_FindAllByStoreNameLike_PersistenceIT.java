package com.github.jinahya.oracle.sample.schemas.co;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("selectListWhereStoreNameLink")
class Store_FindAllByStoreNameLike_PersistenceIT extends Store__PersistenceIT {

    @Test
    void __NativeQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var storeNamePattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createNamedQuery("Store.findAllByStoreNameLike", Store.class)
                .setParameter("storeNamePattern", storeNamePattern)
                .getResultList();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).allSatisfy(e -> {
            assertThat(e.getStoreName()).contains("a");
        });
    }

    @Test
    void __DirectQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var storeNamePattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createQuery("SELECT e FROM Store AS e WHERE e.storeName LIKE :storeNamePattern", Store.class)
                .setParameter("storeNamePattern", storeNamePattern)
                .getResultList();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).allSatisfy(e -> {
            assertThat(e.getStoreName()).contains("a");
        });
    }

    @Test
    void __CriteriaApi() {
        // --------------------------------------------------------------------------------------------------- given
        final var storeNamePattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = super.<List<Store>>applyEntityManager(em -> {
            final var b = em.getCriteriaBuilder();
            final var q = b.createQuery(Store.class);
            final var r = q.from(Store.class);                          // FROM Store AS e
            q.select(r);                                                // SELECT e
            q.where(b.like(r.get(Store_.storeName), storeNamePattern)); // WHERE e.storeName LIKE :storeNamePattern
            return em.createQuery(q).getResultList();
        });
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).allSatisfy(e -> {
            assertThat(e.getStoreName()).contains("a");
        });
    }
}