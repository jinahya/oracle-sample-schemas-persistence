package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegion;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class Region_PersistenceTest extends _MappedHrEntity_PersistenceTest<Region, Long> {

    Region_PersistenceTest() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class Static_Test {

        @DisplayName("ATTRIBUTE_NAME_...")
        @Nested
        @SuppressWarnings({
                "java:S3415" // Assertion arguments should be passed in the correct order
        })
        class ATTRIBUTE_NAME_Test {

            @Test
            void __ATTRIBUTE_NAME_REGION_ID() {
                assertThat(MappedRegion.ATTRIBUTE_NAME_REGION_ID).isEqualTo(Region_.regionId.getName());
            }

            @Test
            void __ATTRIBUTE_NAME_REGION_NAME() {
                assertThat(MappedRegion.ATTRIBUTE_NAME_REGION_NAME).isEqualTo(Region_.regionName.getName());
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectOneWhereRegionNameEqual")
    @Nested
    class SelectOneByNameEqual_Test {

        @Nested
        class NamedQueryTest {

            @Test
            void _Empty_Unknown() {
                applyEntityManager(em -> {
                    final var query = em.createNamedQuery("Region.selectListWhereRegionNameEqual", Region.class);
                    query.setParameter("regionName", "Ἀτλαντίς");
                    final var result = query.getResultList();
                    assertThat(result).isEmpty(); // H2
                    return null;
                });
            }

            @Test
            void _Contains_Known() {
                final var persisted = applyEntityManager(em -> {
                    return ___JakartaPersistence_TestUtils.getInTransaction(
                            em,
                            () -> {
                                return __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, Region.class);
                            },
                            false
                    );
                });
                applyEntityManager(em -> {
                    final var query = em.createNamedQuery("Region.selectListWhereRegionNameEqual", Region.class);
                    query.setParameter("regionName", persisted.getRegionName());
                    final var result = query.getResultList();
                    assertThat(result).contains(persisted);
                    return null;
                });
            }
        }

        @Nested
        class QueryLanguageTest {

            @Test
            void _Empty_Unknown() {
                applyEntityManager(em -> {
                    final var query = em.createQuery(
                            """
                                    SELECT e
                                    FROM Region e
                                    WHERE e.regionName = :regionName"""
                    );
                    query.setParameter("regionName", "Ἀτλαντίς");
                    final var result = query.getResultList();
                    assertThat(result).isEmpty(); // H2
                    return null;
                });
            }

            @Test
            void _Contains_Known() {
                final var persisted = applyEntityManager(em -> {
                    return ___JakartaPersistence_TestUtils.getInTransaction(
                            em,
                            () -> {
                                return __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, Region.class);
                            },
                            false
                    );
                });
                applyEntityManager(em -> {
                    final var query = em.createQuery(
                            """
                                    SELECT e
                                    FROM Region e
                                    WHERE e.regionName = :regionName"""
                    );
                    query.setParameter("regionName", persisted.getRegionName());
                    final var result = query.getResultList();
                    assertThat(result).contains(persisted);
                    return null;
                });
            }
        }

        @Nested
        class CriteriaApiTest {

            @Test
            void _Empty_Unknown() {
                applyEntityManager(em -> {
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(Region.class);
                    // FROM Region e
                    final var root = query.from(Region.class);
                    // SELECT e
                    query.select(root);
                    // WHERE e.regionName = :regionName
                    query.where(
                            builder.equal(root.get(Region_.regionName), "Ἀτλαντίς")
                    );
                    final var result = em.createQuery(query).getResultList();
                    assertThat(result).isEmpty(); // H2
                    return null;
                });
            }

            @Test
            void _Contains_Known() {
                final var persisted = applyEntityManager(em -> {
                    return ___JakartaPersistence_TestUtils.getInTransaction(
                            em,
                            () -> {
                                return __MappedEntity_PersisterUtils.newPersistedInstanceOf(em, Region.class);
                            },
                            false
                    );
                });
                applyEntityManager(em -> {
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(Region.class);
                    // FROM Region e
                    final var root = query.from(Region.class);
                    // SELECT e
                    query.select(root);
                    // WHERE e.regionName = :regionName
                    query.where(
                            builder.equal(root.get(Region_.regionName), persisted.getRegionName())
                    );
                    final var result = em.createQuery(query).getResultList();
                    assertThat(result).contains(persisted);
                    return null;
                });
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectListWhereRegionNameLike")
    @Nested
    class SelectListByNameLike_Test {

        @Test
        void __() {
            applyEntityManager(em -> {
                final var query = em.createNamedQuery("Region.selectListWhereRegionNameLike", Region.class);
                query.setParameter("regionNamePattern", "%a%");
                assertThatCode(() -> {
                    final var result = query.getResultList();
                    assertThat(result).isEmpty(); // H2
                }).doesNotThrowAnyException();
                return null;
            });
        }
    }
}
