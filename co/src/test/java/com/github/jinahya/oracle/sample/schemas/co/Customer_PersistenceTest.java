package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import com.github.jinahya.persistence.mapped.test.__Use_Cached_EntityManager;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@__Use_Cached_EntityManager
@Slf4j
class Customer_PersistenceTest extends __MappedEntity_PersistenceTest<Customer, Long> {

    Customer_PersistenceTest() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class SelectListOrderByCustomerIdAsc_Test {

        @Nested
        class NamedQuery_Test {

            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    IntStream.range(0, 128).forEach(i -> {
                        __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    });
                    final var count = ___JakartaPersistence_TestUtils.count(em, entityClass);
                    if (count == 0) {
                        log.info("table is empty: {}", getEntityTableName());
                        return null;
                    }
                    final var firstResult = ThreadLocalRandom.current().nextInt(Math.toIntExact(count));
                    final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                    log.debug("count: {}, firstResult: {}, maxResults: {}", count, firstResult, maxResults);
                    final var list = em
                            .createNamedQuery("Customer.selectListOrderByCustomerIdAsc", entityClass)
                            .setFirstResult(firstResult)
                            .setMaxResults(maxResults)
                            .getResultList();
                    log.debug("list.size: {}", list.size());
                    assertThat(list)
                            .hasSizeLessThanOrEqualTo(maxResults)
                            .isSortedAccordingTo(Comparator.comparing(MappedCustomer::getCustomerId));
                    return null;
                });
            }
        }

        @Nested
        class QueryLanguage_Test {

            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    IntStream.range(0, 128).forEach(i -> {
                        __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    });
                    final var count = ___JakartaPersistence_TestUtils.count(em, entityClass);
                    if (count == 0) {
                        log.info("table is empty: {}", getEntityTableName());
                        return null;
                    }
                    final var firstResult = ThreadLocalRandom.current().nextInt(Math.toIntExact(count));
                    final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                    log.debug("count: {}, firstResult: {}, maxResults: {}", count, firstResult, maxResults);
                    final var list = em
                            .createQuery(
                                    """
                                            SELECT e
                                            FROM Customer e
                                            ORDER By e.customerId ASC""",
                                    entityClass
                            )
                            .setFirstResult(firstResult)
                            .setMaxResults(maxResults)
                            .getResultList();
                    log.debug("list.size: {}", list.size());
                    assertThat(list)
                            .hasSizeLessThanOrEqualTo(maxResults)
                            .isSortedAccordingTo(Comparator.comparing(MappedCustomer::getCustomerId));
                    return null;
                });
            }
        }

        @Nested
        class CriteriaApi_Test {

            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    IntStream.range(0, 128).forEach(i -> {
                        __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    });
                    final var count = ___JakartaPersistence_TestUtils.count(em, entityClass);
                    if (count == 0) {
                        log.info("table is empty: {}", getEntityTableName());
                        return null;
                    }
                    final var firstResult = ThreadLocalRandom.current().nextInt(Math.toIntExact(count));
                    final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                    log.debug("count: {}, firstResult: {}, maxResults: {}", count, firstResult, maxResults);
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(entityClass);
                    final var root = query.from(entityClass);
                    query.select(root);
                    query.orderBy(builder.asc(root.get(MappedCustomer_.customerId)));
                    final var list = em
                            .createQuery(query)
                            .setFirstResult(firstResult)
                            .setMaxResults(maxResults)
                            .getResultList();
                    log.debug("list.size: {}", list.size());
                    assertThat(list)
                            .hasSizeLessThanOrEqualTo(maxResults)
                            .isSortedAccordingTo(Comparator.comparing(MappedCustomer::getCustomerId));
                    return null;
                });
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class SelectListOrderByCustomerIdAscCustomerIdGt_Test {

        @Nested
        class NamedQuery_Test {

            @Test
            void __() {
                applyEntityManagerInTransactionAndRollback(em -> {
                    IntStream.range(0, 128).forEach(i -> {
                        __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                    });
                    return ___JakartaPersistence_TestUtils.applyCountIndexAndEntity(
                            em,
                            entityClass,
                            c -> i -> e -> {
                                final var customerId = e.getCustomerId();
                                final var firstResult =
                                        ThreadLocalRandom.current().nextInt(Math.toIntExact(c - i));
                                final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                                log.debug("count: {}, firstResult: {}, maxResults: {}", c, firstResult,
                                          maxResults);
                                final var list = em
                                        .createNamedQuery(
                                                "Customer.selectListOrderByCustomerIdAscCustomerIdGt",
                                                entityClass
                                        )
                                        .setParameter("customerIdMinExclusive", customerId)
                                        .setFirstResult(firstResult)
                                        .setMaxResults(maxResults)
                                        .getResultList();
                                log.debug("list.size: {}", list.size());
                                assertThat(list)
                                        .hasSizeLessThanOrEqualTo(maxResults)
                                        .isSortedAccordingTo(
                                                Comparator.comparing(MappedCustomer::getCustomerId))
                                        .extracting(MappedCustomer::getCustomerId)
                                        .allSatisfy(ci -> {
                                            assertThat(ci).isGreaterThan(customerId);
                                        });
                                return null;
                            });
                });
            }

            @Nested
            class QueryLanguage_Test {

                @Test
                void __() {
                    applyEntityManagerInTransactionAndRollback(em -> {
                        IntStream.range(0, 128).forEach(i -> {
                            __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                        });
                        return ___JakartaPersistence_TestUtils.applyCountIndexAndEntity(
                                em,
                                entityClass,
                                c -> i -> e -> {
                                    final var customerId = e.getCustomerId();
                                    final var firstResult =
                                            ThreadLocalRandom.current().nextInt(Math.toIntExact(c - i));
                                    final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                                    log.debug("count: {}, firstResult: {}, maxResults: {}", c, firstResult,
                                              maxResults);
                                    final var list = em
                                            .createQuery(
                                                    """
                                                            
                                                                SELECT e
                                                            FROM Customer e
                                                            WHERE e.customerId > :customerIdMinExclusive
                                                            ORDER By e.customerId ASC""",
                                                    entityClass
                                            )
                                            .setParameter(
                                                    "customerIdMinExclusive", customerId)
                                            .setFirstResult(firstResult)
                                            .setMaxResults(
                                                    maxResults)
                                            .getResultList();
                                    log.debug("list.size: {}", list.size());
                                    assertThat(list)
                                            .
                                            hasSizeLessThanOrEqualTo(maxResults)
                                            .isSortedAccordingTo(
                                                    Comparator.comparing(MappedCustomer::getCustomerId))
                                            .extracting(MappedCustomer::getCustomerId
                                            )
                                            .allSatisfy(ci -> {
                                                assertThat(ci).isGreaterThan(customerId);
                                            });
                                    return null;
                                });
                    });
                }
            }

            @Nested
            class CriteriaApi_Test {

                @Test
                void __() {
                    applyEntityManagerInTransactionAndRollback(em -> {
                        IntStream.range(0, 128).forEach(i -> {
                            __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, entityClass);
                        });
                        return ___JakartaPersistence_TestUtils.applyCountIndexAndEntity(
                                em,
                                entityClass,
                                c -> i -> e -> {
                                    assert c >= 0L;
                                    assert i >= 0L;
                                    assert e != null;
                                    final var customerId = e.getCustomerId();
                                    final var firstResult =
                                            ThreadLocalRandom.current().nextInt(Math.toIntExact(c - i));
                                    final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                                    log.debug("count: {}, firstResult: {}, maxResults: {}", c, firstResult,
                                              maxResults);
                                    final var builder = em.getCriteriaBuilder();
                                    final var query = builder.createQuery(entityClass);
                                    final var root = query.from(entityClass);
                                    query.select(root);
                                    query.where(builder.gt(root.get(MappedCustomer_.customerId), customerId));
                                    query.orderBy(builder.asc(root.get(MappedCustomer_.customerId)));
                                    final var list = em
                                            .createQuery(query)
                                            .setFirstResult(firstResult)
                                            .setMaxResults(maxResults)
                                            .getResultList();
                                    log.debug("list.size: {}", list.size());
                                    assertThat(list)
                                            .hasSizeLessThanOrEqualTo(maxResults)
                                            .isSortedAccordingTo(
                                                    Comparator.comparing(MappedCustomer::getCustomerId))
                                            .extracting(MappedCustomer::getCustomerId)
                                            .allSatisfy(ci -> {
                                                assertThat(ci).isGreaterThan(customerId);
                                            });
                                    return null;
                                }
                        );
                    });
                }
            }
        }
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
                applyEntityManagerInTransaction(
                        em -> {
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
                        },
                        true
                );
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
