package com.github.jinahya.oracle.sample.schemas.co;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("findAllByFullNameLike")
class Customer_FindAllByFullNameLike_PersistenceIT extends Customer__PersistenceIT {

    @Test
    void __NativeQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var fullNamePattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createNamedQuery("Customer.findAllByFullNameLike", Customer.class)
                .setParameter("fullNamePattern", fullNamePattern)
                .getResultList();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).satisfiesAnyOf(
                f -> assertThat(f).isEmpty(),
                f -> assertThat(f).allSatisfy(e -> {
                    assertThat(e.getFullName()).contains("a");
                })
        );
    }

    @Test
    void __DirectQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var fullNamePattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createQuery(
                        """
                                SELECT e
                                FROM Customer AS e
                                WHERE e.fullName LIKE :fullNamePattern""",
                        Customer.class
                )
                .setParameter("fullNamePattern", fullNamePattern)
                .getResultList();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).satisfiesAnyOf(
                f -> assertThat(f).isEmpty(),
                f -> assertThat(f).allSatisfy(e -> {
                    assertThat(e.getFullName()).contains("a");
                })
        );
    }

    @Test
    void __CriteriaApi() {
        // --------------------------------------------------------------------------------------------------- given
        final var fullNamePattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = applyEntityManager(em -> {
            final var b = em.getCriteriaBuilder();
            final var q = b.createQuery(Customer.class);
            final var r = q.from(Customer.class);                        // FROM Customer AS e
            q.select(r);                                                 // SELECT e
            q.where(b.like(r.get(Customer_.fullName), fullNamePattern)); // WHERE e.fullName LIKE :fullNamePattern
            return em.createQuery(q).getResultList();
        });
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).satisfiesAnyOf(
                f -> assertThat(f).isEmpty(),
                f -> assertThat(f).allSatisfy(e -> {
                    assertThat(e.getFullName()).contains("a");
                })
        );
    }
}