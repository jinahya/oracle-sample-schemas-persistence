package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.Persistence_TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@DisplayName("findByEmailAddress")
class Customer_FindByEmailAddress_PersistenceIT extends Customer__PersistenceIT {

    @Test
    void __NativeQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var emailAddress =
                Persistence_TestUtils.selectRandom(entityManager(), entityClass)
                        .map(Customer::getEmailAddress)
                        .orElse(null);
        assumeThat(emailAddress)
                .as("emailAddress mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createNamedQuery("Customer.findByEmailAddress", Customer.class)
                .setParameter("emailAddress", emailAddress)
                .getSingleResult();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getEmailAddress()).isEqualTo(emailAddress);
    }

    @Test
    void __DirectQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var emailAddress =
                Persistence_TestUtils.selectRandom(entityManager(), entityClass)
                        .map(Customer::getEmailAddress)
                        .orElse(null);
        assumeThat(emailAddress)
                .as("emailAddress mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createQuery(
                        """
                                SELECT e
                                FROM Customer AS e
                                WHERE e.emailAddress = :emailAddress""",
                        Customer.class
                )
                .setParameter("emailAddress", emailAddress)
                .getSingleResult();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getEmailAddress()).isEqualTo(emailAddress);
    }

    @Test
    void __CriteriaApi() {
        // --------------------------------------------------------------------------------------------------- given
        final var emailAddress =
                Persistence_TestUtils.selectRandom(entityManager(), entityClass)
                        .map(Customer::getEmailAddress)
                        .orElse(null);
        assumeThat(emailAddress)
                .as("emailAddress mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = super.<Customer>applyEntityManager(em -> {
            final var b = em.getCriteriaBuilder();
            final var q = b.createQuery(Customer.class);
            final var r = q.from(Customer.class);                          // FROM Customer AS e
            q.select(r);                                                   // SELECT e
            q.where(b.equal(r.get(Customer_.emailAddress), emailAddress)); // WHERE e.emailAddress = :emailAddress
            return em.createQuery(q).getSingleResult();
        });
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getEmailAddress()).isEqualTo(emailAddress);
    }
}