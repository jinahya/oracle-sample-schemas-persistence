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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class Region_PersistenceIT extends _MappedHrEntity_PersistenceIT<Region, Long> {

    Region_PersistenceIT() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectOneWhereRegionNameEqual")
    @Nested
    class SelectOneByNameEqual_Test {

        @Nested
        class NamedQueryTest {

            @Test
            void __() {
                applyEntityManager(em -> {
                    final var query = em.createNamedQuery("Region.selectListWhereRegionNameEqual", Region.class);
                    final var regionName = "Europe";
                    query.setParameter("regionName", regionName);
                    final var result = query.getResultList();
                    assertThat(result).extracting(Region::getRegionName).allSatisfy(e -> {
                        assertThat(e).isEqualTo(regionName);
                    });
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
                    assertThat(result)
                            .extracting(Region::getRegionName)
                            .isNotEmpty()
                            .allSatisfy(rn -> {
                                assertThat(rn).contains("a");
                            });
                }).doesNotThrowAnyException();
                return null;
            });
        }
    }
}
