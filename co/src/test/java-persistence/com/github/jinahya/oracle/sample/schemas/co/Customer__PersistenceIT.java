package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
class Customer__PersistenceIT extends __MappedEntity_PersistenceIT<Customer, Long> {

    Customer__PersistenceIT() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectSingleWhereEmailAddressEqual")
    @Nested
    class FindAllByEmailAddress_Test {

        @Test
        void __NamedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var emailAddress = "tammy.bryant@internalmail";
            // ---------------------------------------------------------------------------------------------------- when
            final var single = applyEntityManager(em -> {
                return em
                        .createNamedQuery("Customer.selectSingleWhereEmailAddressEqual", Customer.class)
                        .setParameter("emailAddress", emailAddress)
                        .getSingleResult(); // NoResultException, NonUniqueResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(single.getEmailAddress())
                    .isEqualTo(emailAddress);
        }

        @Test
        void __QueryLanguage() {
            // --------------------------------------------------------------------------------------------------- given
            final var emailAddress = "tammy.bryant@internalmail";
            // ---------------------------------------------------------------------------------------------------- when
            final var selected = applyEntityManager(em -> {
                return em.createQuery(
                                "SELECT e FROM Customer AS e WHERE e.emailAddress = :emailAddress",
                                Customer.class
                        )
                        .setParameter("emailAddress", emailAddress)
                        .getSingleResult(); // NoResultException, NonUniqueResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(selected.getEmailAddress())
                    .isEqualTo(emailAddress);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var emailAddress = "tammy.bryant@internalmail";
            // ---------------------------------------------------------------------------------------------------- when
            final var single = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var criteria = builder.createQuery(Customer.class);
                // FROM Customer AS e
                final var root = criteria.from(Customer.class);
                // SELECT e
                criteria.select(root);
                // WHERE e.emailAddress = :emailAddress
                criteria.where(builder.like(root.get(Customer_.emailAddress), emailAddress));
                return em.createQuery(criteria).getSingleResult(); // NoResultException, NonUniqueResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(single.getEmailAddress()).isEqualTo(emailAddress);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectListWhereEmailAddressLike")
    @Nested
    class selectListWhereEmailAddressLike_Test {

        @Test
        void __NativeQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var emailAddressPattern = "%a%";
            // ---------------------------------------------------------------------------------------------------- when
            final var list = applyEntityManager(em -> {
                return em.createNamedQuery("Customer.selectListWhereEmailAddressLike", Customer.class)
                        .setParameter("emailAddressPattern", emailAddressPattern)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).allSatisfy(e -> {
                assertThat(e.getEmailAddress()).contains("a");
            });
        }

        @Test
        void __QeeryLangauge() {
            // --------------------------------------------------------------------------------------------------- given
            final var emailAddressPattern = "%a%";
            // ---------------------------------------------------------------------------------------------------- when
            final var list = applyEntityManager(em -> {
                return em.createQuery(
                                "SELECT e FROM Customer e WHERE e.emailAddress LIKE :emailAddressPattern",
                                Customer.class
                        )
                        .setParameter("emailAddressPattern", emailAddressPattern)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).allSatisfy(e -> {
                assertThat(e.getEmailAddress()).contains("a");
            });
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var emailAddressPattern = "%a%";
            // ---------------------------------------------------------------------------------------------------- when
            final var list = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var criteria = builder.createQuery(Customer.class);
                // FROM Customer AS e
                final var root = criteria.from(Customer.class);
                // SELECT e
                criteria.select(root);
                // WHERE e.emailAddress LIKE :emailAddressPattern
                criteria.where(
                        builder.like(
                                root.get(Customer_.emailAddress), emailAddressPattern
                        )
                );
                return em.createQuery(criteria).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).allSatisfy(e -> {
                assertThat(e.getEmailAddress()).contains("a");
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectSingleWhereFullNameEqual")
    @Nested
    class SelectSingleWhereFullNameEqual_Test {

        @Test
        void __NativeQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var fullName = "Tammy Bryant";
            // ---------------------------------------------------------------------------------------------------- when
            final var single = applyEntityManager(em -> {
                return em.createNamedQuery("Customer.selectSingleWhereFullNameEqual", Customer.class)
                        .setParameter("fullName", fullName)
                        .getSingleResult(); // NoResultException, NonUniqueResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(single).isNotNull()
                    .extracting(Customer::getFullName)
                    .isEqualTo(fullName);
        }

        @Test
        void __DirectQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var fullName = "Tammy Bryant";
            // ---------------------------------------------------------------------------------------------------- when
            final var single = applyEntityManager(em -> {
                return em.createQuery(
                                "SELECT e FROM Customer e WHERE e.fullName = :fullName",
                                Customer.class
                        )
                        .setParameter("fullName", fullName)
                        .getSingleResult(); // NoResultException, NonUniqueResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(single).isNotNull();
            assertThat(single.getFullName()).isEqualTo(fullName);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var fullName = "Tammy Bryant";
            // ---------------------------------------------------------------------------------------------------- when
            final var single = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var criteria = builder.createQuery(Customer.class);
                // FROM Customer AS e
                final var root = criteria.from(Customer.class);
                // SELECT e
                criteria.select(root);
                // WHERE e.fullName = :fullName
                criteria.where(builder.equal(root.get(Customer_.fullName), fullName));
                return em.createQuery(criteria).getSingleResult(); // NoResultException, NonUniqueResultException
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(single).isNotNull();
            assertThat(single.getFullName()).isEqualTo(fullName);
        }
    }

    @DisplayName("selectListWhereFullNameLike")
    @Nested
    class SelectListWhereFullNameLike_Test {

        @Test
        void __NameQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var fullNamePattern = "%a%";
            // ---------------------------------------------------------------------------------------------------- when
            final var list = applyEntityManager(em -> {
                return em.createNamedQuery("Customer.selectListWhereFullNameLike", Customer.class)
                        .setParameter("fullNamePattern", fullNamePattern)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).satisfiesAnyOf(
                    f -> assertThat(f).isEmpty(),
                    f -> assertThat(f).allSatisfy(e -> {
                        assertThat(e.getFullName()).contains("a");
                    })
            );
        }

        @Test
        void __QueryLanguage() {
            // --------------------------------------------------------------------------------------------------- given
            final var fullNamePattern = "%a%";
            // ---------------------------------------------------------------------------------------------------- when
            final var list = applyEntityManager(em -> {
                return em.createQuery(
                                "SELECT e FROM Customer e WHERE e.fullName LIKE :fullNamePattern",
                                Customer.class
                        )
                        .setParameter("fullNamePattern", fullNamePattern)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).satisfiesAnyOf(
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
            final var list = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var criteria = builder.createQuery(Customer.class);
                // FROM Customer AS e
                final var root = criteria.from(Customer.class);
                // SELECT e
                criteria.select(root);
                // WHERE e.fullName LIKE :fullNamePattern
                criteria.where(builder.like(root.get(Customer_.fullName), fullNamePattern));
                return em.createQuery(criteria).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).satisfiesAnyOf(
                    f -> assertThat(f).isEmpty(),
                    f -> assertThat(f).allSatisfy(e -> {
                        assertThat(e.getFullName()).contains("a");
                    })
            );
        }
    }
}