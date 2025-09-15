package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Customer_PersistenceTest extends __MappedEntity_PersistenceTest<Customer, Long> {

    Customer_PersistenceTest() {
        super(Customer.class, Long.class);
    }

    @Nested
    class SelectSingleByEmailAddress_Test {

        @Nested
        class NamedQuery_Test {

            @Test
            void _NoResultException_EmailAddressUnknown() {
                applyEntityManager(em -> {
                    final var query = em
                            .createNamedQuery("Customer.selectSingleByEmailAddress", entityClass)
                            .setParameter("emailAddress", "");
                    assertThatThrownBy(query::getSingleResult)
                            .isInstanceOf(NoResultException.class);
                    return null;
                });
            }

            @Test
            void __EmailAddressKnown() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    final var persisted = __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    em.flush();
                    em.detach(persisted);
                    em.clear();
                    final var emailAddress = persisted.getEmailAddress();
                    final var found = em
                            .createNamedQuery("Customer.selectSingleByEmailAddress", entityClass)
                            .setParameter("emailAddress", emailAddress)
                            .getSingleResult();
                    assertThat(found.getEmailAddress()).isEqualTo(emailAddress);
                    return null;
                });
            }
        }

        @Nested
        class QueryLanguage_Test {

            @Test
            void _NoResultException_EmailAddressUnknown() {
                applyEntityManager(em -> {
                    final var query = em
                            .createQuery(
                                    """
                                            SELECT e
                                            FROM Customer e
                                            WHERE e.emailAddress = :emailAddress""",
                                    entityClass
                            )
                            .setParameter("emailAddress", "");
                    assertThatThrownBy(query::getSingleResult)
                            .isInstanceOf(NoResultException.class);
                    return null;
                });
            }

            @Test
            void __EmailAddressKnown() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    final var persisted = __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    em.flush();
                    em.detach(persisted);
                    em.clear();
                    final var emailAddress = persisted.getEmailAddress();
                    final var found = em
                            .createQuery(
                                    """
                                            SELECT e
                                            FROM Customer e
                                            WHERE e.emailAddress = :emailAddress""",
                                    entityClass
                            )
                            .setParameter("emailAddress", emailAddress)
                            .getSingleResult();
                    assertThat(found.getEmailAddress()).isEqualTo(emailAddress);
                    return null;
                });
            }
        }

        @Nested
        class CriteriaApi_Test {

            @Test
            void _NoResultException_EmailAddressUnknown() {
                applyEntityManager(em -> {
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(entityClass);
                    final var root = query.from(entityClass);
                    query.select(root);
                    query.where(builder.equal(root.get(MappedCustomer_.emailAddress), ""));
                    final var typed = em.createQuery(query);
                    assertThatThrownBy(typed::getSingleResult)
                            .isInstanceOf(NoResultException.class);
                    return null;
                });
            }

            @Test
            void __EmailAddressKnown() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    final var persisted = __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    em.flush();
                    em.detach(persisted);
                    em.clear();
                    final var emailAddress = persisted.getEmailAddress();
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(entityClass);
                    final var root = query.from(entityClass);
                    query.select(root);
                    query.where(builder.equal(root.get(MappedCustomer_.emailAddress), emailAddress));
                    final var found = em.createQuery(query).getSingleResult();
                    assertThat(found.getEmailAddress()).isEqualTo(emailAddress);
                    return null;
                });
            }
        }
    }
}
