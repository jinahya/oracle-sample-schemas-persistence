package com.github.jinahya.oracle.sample.schemas.co;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("findByEmailAddressLike")
class Customer_FindAllByEmailAddressLike_PersistenceIT extends Customer__PersistenceIT {

    @Test
    void __NativeQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var emailAddressPattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createNamedQuery("Customer.findAllByEmailAddressLike", Customer.class)
                .setParameter("emailAddressPattern", emailAddressPattern)
                .getResultList();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).allSatisfy(e -> {
            assertThat(e.getEmailAddress()).contains("a");
        });
    }

    @Test
    void __DirectQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var emailAddressPattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createQuery(
                        """
                                SELECT e
                                FROM Customer AS e
                                WHERE e.emailAddress LIKE :emailAddressPattern""",
                        Customer.class
                )
                .setParameter("emailAddressPattern", emailAddressPattern)
                .getResultList();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).allSatisfy(e -> {
            assertThat(e.getEmailAddress()).contains("a");
        });
    }

    @Test
    void __CriteriaApi() {
        // --------------------------------------------------------------------------------------------------- given
        final var emailAddressPattern = "%a%";
        // ---------------------------------------------------------------------------------------------------- when
        final var found = super.<List<Customer>>applyEntityManager(em -> {
            final var b = em.getCriteriaBuilder();
            final var q = b.createQuery(Customer.class);
            // ---------------------------------------------------------------------------------- FROM Customer AS e
            final var r = q.from(Customer.class);
            // -------------------------------------------------------------------------------------------- SELECT e
            q.select(r);
            // ------------------------------------------------------ WHERE e.emailAddress LIKE :emailAddressPattern
            q.where(b.like(r.get(Customer_.emailAddress), emailAddressPattern));
            return em.createQuery(q).getResultList();
        });
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).allSatisfy(e -> {
            assertThat(e.getEmailAddress()).contains("a");
        });
    }
}