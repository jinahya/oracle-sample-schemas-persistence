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

import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled // 왜 이 클래스가 test phase 에서 도는지 확인 필요!!!
@Slf4j
class Customer_PersistenceIT extends _MappedCoEntity_PersistenceIT<Customer, Long> {

    Customer_PersistenceIT() {
        super(Customer.class, Long.class);
    }

    @Nested
    class SelectListOrderByCustomerIdAsc_Test {

        @Nested
        class NamedQuery_Test {

            @Test
            void __() {
                applyEntityManager(em -> {
                    final var count = ___JakartaPersistence_TestUtils.count(em, entityClass);
                    if (count == 0) {
                        log.info("table is empty: {}", getEntityColumnNames());
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
                applyEntityManager(em -> {
                    final var count = ___JakartaPersistence_TestUtils.count(em, entityClass);
                    if (count == 0) {
                        log.info("table is empty: {}", getEntityColumnNames());
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
                applyEntityManager(em -> {
                    final var count = ___JakartaPersistence_TestUtils.count(em, entityClass);
                    if (count == 0) {
                        log.info("table is empty: {}", getEntityColumnNames());
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

    @Nested
    class SelectListOrderByCustomerIdAscCustomerIdGt_Test {

        @Nested
        class NamedQuery_Test {

            @Test
            void __() {
                applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyCountRandomIndexAndEntity(
                        em,
                        entityClass,
                        c -> i -> e -> {
                            final var customerId = e.getCustomerId();
                            final var firstResult = ThreadLocalRandom.current().nextInt(Math.toIntExact(c - i));
                            final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                            log.debug("count: {}, firstResult: {}, maxResults: {}", c, firstResult, maxResults);
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
                                    .isSortedAccordingTo(Comparator.comparing(MappedCustomer::getCustomerId))
                                    .extracting(MappedCustomer::getCustomerId)
                                    .allSatisfy(ci -> {
                                        assertThat(ci).isGreaterThan(customerId);
                                    });
                            return null;
                        })
                );
            }

            @Nested
            class QueryLanguage_Test {

                @Test
                void __() {
                    applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyCountRandomIndexAndEntity(
                            em,
                            entityClass,
                            c -> i -> e -> {
                                final var customerId = e.getCustomerId();
                                final var firstResult = ThreadLocalRandom.current().nextInt(Math.toIntExact(c - i));
                                final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                                log.debug("count: {}, firstResult: {}, maxResults: {}", c, firstResult, maxResults);
                                final var list = em
                                        .createQuery(
                                                """
                                                        SELECT e
                                                        FROM Customer e
                                                        WHERE e.customerId > :customerIdMinExclusive
                                                        ORDER By e.customerId ASC""",
                                                entityClass
                                        )
                                        .setParameter("customerIdMinExclusive", customerId)
                                        .setFirstResult(firstResult)
                                        .setMaxResults(maxResults)
                                        .getResultList();
                                log.debug("list.size: {}", list.size());
                                assertThat(list)
                                        .hasSizeLessThanOrEqualTo(maxResults)
                                        .isSortedAccordingTo(Comparator.comparing(MappedCustomer::getCustomerId))
                                        .extracting(MappedCustomer::getCustomerId)
                                        .allSatisfy(ci -> {
                                            assertThat(ci).isGreaterThan(customerId);
                                        });
                                return null;
                            })
                    );
                }
            }

            @Nested
            class CriteriaApi_Test {

                @Test
                void __() {
                    applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyCountRandomIndexAndEntity(
                            em,
                            entityClass,
                            c -> i -> e -> {
                                assert c >= 0L;
                                assert i >= 0L;
                                assert e != null;
                                final var customerId = e.getCustomerId();
                                final var firstResult = ThreadLocalRandom.current().nextInt(Math.toIntExact(c - i));
                                final var maxResults = ThreadLocalRandom.current().nextInt(128) + 1;
                                log.debug("count: {}, firstResult: {}, maxResults: {}", c, firstResult, maxResults);
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
                                        .isSortedAccordingTo(Comparator.comparing(MappedCustomer::getCustomerId))
                                        .extracting(MappedCustomer::getCustomerId)
                                        .allSatisfy(ci -> {
                                            assertThat(ci).isGreaterThan(customerId);
                                        });
                                return null;
                            }
                    ));
                }
            }
        }
    }
}
