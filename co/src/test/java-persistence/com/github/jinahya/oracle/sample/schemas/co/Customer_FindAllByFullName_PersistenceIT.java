package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.Persistence_TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@DisplayName("findAllByFullName")
class Customer_FindAllByFullName_PersistenceIT extends Customer__PersistenceIT {

    @Test
    void __NativeQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var fullName =
                Persistence_TestUtils.findRandom(entityManager(), entityClass)
                        .map(Customer::getFullName)
                        .orElse(null);
        assumeThat(fullName)
                .as("fullName mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createNamedQuery("Customer.findAllByFullName", Customer.class)
                .setParameter("fullName", fullName)
                .getSingleResult();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getFullName()).isEqualTo(fullName);
    }

    @Test
    void __DirectQuery() {
        // --------------------------------------------------------------------------------------------------- given
        final var fullName =
                Persistence_TestUtils.findRandom(entityManager(), entityClass)
                        .map(Customer::getFullName)
                        .orElse(null);
        assumeThat(fullName)
                .as("fullName mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = entityManager()
                .createQuery(
                        """
                                SELECT e
                                FROM Customer AS e
                                WHERE e.fullName = :fullName""",
                        Customer.class
                )
                .setParameter("fullName", fullName)
                .getSingleResult();
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getFullName()).isEqualTo(fullName);
    }

    @Test
    void __CriteriaApi() {
        // --------------------------------------------------------------------------------------------------- given
        final var fullName =
                Persistence_TestUtils.findRandom(entityManager(), entityClass)
                        .map(Customer::getFullName)
                        .orElse(null);
        assumeThat(fullName)
                .as("fullName mapped from a randomly selected entity")
                .isNotNull();
        // ---------------------------------------------------------------------------------------------------- when
        final var found = super.<Customer>applyEntityManager(em -> {
            final var b = em.getCriteriaBuilder();
            final var q = b.createQuery(Customer.class);
            final var r = q.from(Customer.class);                  // FROM Customer AS e
            q.select(r);                                           // SELECT e
            q.where(b.equal(r.get(Customer_.fullName), fullName)); // WHERE e.fullName = :fullName
            return em.createQuery(q).getSingleResult();
        });
        // ---------------------------------------------------------------------------------------------------- then
        assertThat(found).isNotNull();
        assertThat(found.getFullName()).isEqualTo(fullName);
    }
}