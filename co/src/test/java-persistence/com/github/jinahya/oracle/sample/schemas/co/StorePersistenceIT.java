package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityPersistenceIT;
import lombok.extern.slf4j.Slf4j;

//@AddBeanClasses({
//        _PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
@Slf4j
class StorePersistenceIT extends __MappedEntityPersistenceIT<Store, Long> {

    StorePersistenceIT() {
        super(Store.class, Long.class);
    }

//    // -----------------------------------------------------------------------------------------------------------------
//    @DisplayName("selectSingleWhereStoreNameEqual")
//    @Nested
//    class SelectSingleWhereStoreNameEqual_Test {
//
//        @Nested
//        class NamedQuery_Test {
//
//            @Test
//            void _NoResultException_Unknown() {
//                // --------------------------------------------------------------------------------------------------- given
//                final var storeName = "unknown";
//                // ----------------------------------------------------------------------------------------------- when/then
//                acceptEntityManagerInTransactionAndRollback(em -> {
//                    assertThatThrownBy(() -> {
//                        em.createNamedQuery("Store.selectSingleWhereStoreNameEqual", Store.class)
//                                .setParameter("storeName", storeName)
//                                .getSingleResult();
//                    }).isInstanceOf(NoResultException.class);
//                });
//            }
//
//            @Test
//            void __() {
//                // --------------------------------------------------------------------------------------------------- given
//                final var storeName = "Online";
//                // ---------------------------------------------------------------------------------------------------- when
//                final var single = applyEntityManagerInTransactionAndRollback(em -> {
//                    return em.createNamedQuery("Store.selectSingleWhereStoreNameEqual", Store.class)
//                            .setParameter("storeName", storeName)
//                            .getSingleResult();
//                });
//                // ---------------------------------------------------------------------------------------------------- then
//                assertThat(single.getStoreName()).isEqualTo(storeName);
//            }
//        }
//
//        @Nested
//        class QueryLanguage_Test {
//
//            @Test
//            void _NoResultException_Unknown() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeName = "unknown";
//                // ------------------------------------------------------------------------------------------------ when
//                acceptEntityManagerInTransactionAndRollback(em -> {
//                    assertThatThrownBy(() -> {
//                        em.createQuery("SELECT e FROM Store AS e WHERE e.storeName = :storeName", Store.class)
//                                .setParameter("storeName", storeName)
//                                .getSingleResult();
//                    }).isInstanceOf(NoResultException.class);
//                });
//            }
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeName = "Online";
//                // ------------------------------------------------------------------------------------------------ when
//                final var single = applyEntityManagerInTransactionAndRollback(em -> {
//                    return em.createQuery("SELECT e FROM Store AS e WHERE e.storeName = :storeName", Store.class)
//                            .setParameter("storeName", storeName)
//                            .getSingleResult();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(single.getStoreName()).isEqualTo(storeName);
//            }
//        }
//
//        @Nested
//        class CriteriaApi_Test {
//
//            @Test
//            void _NoResultException_Unknown() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeName = "unknown";
//                // -------------------------------------------------------------------------------------------- when/then
//                acceptEntityManagerInTransactionAndRollback(em -> {
//                    final var builder = em.getCriteriaBuilder();
//                    final var criteria = builder.createQuery(Store.class);
//                    final var root = criteria.from(Store.class);
//                    criteria.select(root);
//                    criteria.where(builder.equal(root.get(Store_.storeName), storeName));
//                    assertThatThrownBy(() -> {
//                        final var single = em.createQuery(criteria).getSingleResult();
//                    }).isInstanceOf(NoResultException.class);
//                });
//            }
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeName = "Online";
//                // ------------------------------------------------------------------------------------------------ when
//                final var single = applyEntityManagerInTransactionAndRollback(em -> {
//                    final var builder = em.getCriteriaBuilder();
//                    final var criteria = builder.createQuery(Store.class);
//                    final var root = criteria.from(Store.class);
//                    criteria.select(root);
//                    criteria.where(builder.equal(root.get(Store_.storeName), storeName));
//                    return em.createQuery(criteria).getSingleResult();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(single.getStoreName()).isEqualTo(storeName);
//            }
//        }
//    }
//
//    @Nested
//    class SelectSingleWhereStoreNameLike_Test {
//
//        @Nested
//        class NamedQuery_Test {
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeNamePattern = "%a%";
//                // ------------------------------------------------------------------------------------------------ when
//                final var list = applyEntityManagerInTransactionAndRollback(em -> {
//                    return em.createNamedQuery("Store.selectListWhereStoreNameLike", Store.class)
//                            .setParameter("storeNamePattern", storeNamePattern)
//                            .getResultList();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(list).satisfiesAnyOf(
//                        l -> {
//                            assertThat(l).isEmpty();
//                        },
//                        l -> {
//                            assertThat(l).extracting(Store::getStoreName).allSatisfy(sn -> {
//                                assertThat(sn).contains("a");
//                            });
//                        }
//                );
//            }
//        }
//
//        @Nested
//        class QueryLanguage_Test {
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeNamePattern = "%a%";
//                // ------------------------------------------------------------------------------------------------ when
//                final var list = applyEntityManagerInTransactionAndRollback(em -> {
//                    return em.createQuery("SELECT e FROM Store AS e WHERE e.storeName LIKE :storeNamePattern",
//                                          Store.class)
//                            .setParameter("storeNamePattern", storeNamePattern)
//                            .getResultList();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(list).satisfiesAnyOf(
//                        l -> {
//                            assertThat(l).isEmpty();
//                        },
//                        l -> {
//                            assertThat(l).extracting(Store::getStoreName).allSatisfy(sn -> {
//                                assertThat(sn).contains("a");
//                            });
//                        }
//                );
//            }
//        }
//
//        @Nested
//        class CriteriaApi_Test {
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeNamePattern = "%a%";
//                // ------------------------------------------------------------------------------------------------ when
//                final var list = applyEntityManagerInTransactionAndRollback(em -> {
//                    final var builder = em.getCriteriaBuilder();
//                    final var criteria = builder.createQuery(Store.class);
//                    final var root = criteria.from(Store.class);
//                    criteria.select(root);
//                    criteria.where(builder.like(root.get(Store_.storeName), storeNamePattern));
//                    return em.createQuery(criteria).getResultList();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(list).satisfiesAnyOf(
//                        l -> {
//                            assertThat(l).isEmpty();
//                        },
//                        l -> {
//                            assertThat(l).extracting(Store::getStoreName).allSatisfy(sn -> {
//                                assertThat(sn).contains("a");
//                            });
//                        }
//                );
//            }
//        }
//    }
//
//    @Nested
//    class SelectSingleWhereStoreNameIn_Test {
//
//        @Nested
//        class NamedQuery_Test {
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeNames = List.of(
//                        "Online",
//                        "San Francisco"
//                );
//                // ------------------------------------------------------------------------------------------------ when
//                final var list = applyEntityManagerInTransactionAndRollback(em -> {
//                    return em.createNamedQuery("Store.selectListWhereStoreNameIn", Store.class)
//                            .setParameter("storeNames", storeNames)
//                            .getResultList();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(list).extracting(Store::getStoreName).allSatisfy(sn -> {
//                    assertThat(sn).isIn(storeNames);
//                });
//            }
//        }
//
//        @Nested
//        class QueryLanguage_Test {
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeNames = List.of(
//                        "Online",
//                        "San Francisco"
//                );
//                // ------------------------------------------------------------------------------------------------ when
//                final var list = applyEntityManagerInTransactionAndRollback(em -> {
//                    return em.createQuery("SELECT e FROM Store AS e WHERE e.storeName IN :storeNames",
//                                          Store.class)
//                            .setParameter("storeNames", storeNames)
//                            .getResultList();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(list).extracting(Store::getStoreName).allSatisfy(sn -> {
//                    assertThat(sn).isIn(storeNames);
//                });
//            }
//        }
//
//        @Nested
//        class CriteriaApi_Test {
//
//            @Test
//            void __() {
//                // ----------------------------------------------------------------------------------------------- given
//                final var storeNames = List.of(
//                        "Online",
//                        "San Francisco"
//                );
//                // ------------------------------------------------------------------------------------------------ when
//                final var list = applyEntityManagerInTransactionAndRollback(em -> {
//                    final var builder = em.getCriteriaBuilder();
//                    final var criteria = builder.createQuery(Store.class);
//                    final var root = criteria.from(Store.class);
//                    criteria.select(root);
//                    criteria.where(root.get(Store_.storeName).in(storeNames));
//                    return em.createQuery(criteria).getResultList();
//                });
//                // ------------------------------------------------------------------------------------------------ then
//                assertThat(list).extracting(Store::getStoreName).allSatisfy(sn -> {
//                    assertThat(sn).isIn(storeNames);
//                });
//            }
//        }
//    }
}
