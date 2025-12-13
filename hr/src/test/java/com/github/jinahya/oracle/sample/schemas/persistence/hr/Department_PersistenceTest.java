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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Department_PersistenceTest extends _MappedHrEntity_PersistenceTest<Department, Integer> {

    Department_PersistenceTest() {
        super(Department.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName(value = """
            SelectOne_\
            WhereLocation_"""
    )
    @Nested
    class SelectOne_WhereEmailEqual__Test {

        @Test
        void NamedQuery__() {
            // TODO: implement!
        }

        @Test
        void QueryLanguage__() {

        }

        @Test
        void CriteriaApi__() {
            // TODO: implement!
        }
    }

    @Nested
    class SelectList_WhereLocationEqual_OrderByLocationIdAsc_Test {

        @Test
        void NamedQuery__() {
            final List<Department> result = applyEntityManager(
                    em -> {
                        final var query = em.createNamedQuery(
                                """
                                        Department.\
                                        SelectList_\
                                        Where\
                                        LocationEqual_\
                                        OrderBy\
                                        DepartmentIdAsc""",
                                Department.class
                        );
                        query.setParameter("location", Location.builder().locationId(1).build());
                        query.setFirstResult(0);
                        query.setMaxResults(10);
                        return query.getResultList();
                    }
            );
            assertThat(result).allSatisfy(d -> {
                assertThat(d.getLocationId()).isEqualTo(1);
            });
        }

        @Test
        void QueryLangauge__() {
        }

        @Test
        void CriteriaApi__() {
            final var result = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Department.class);
                final var root = query.from(Department.class);
                query.select(root);
                query.where(builder.equal(
                        root.get(Department_.location),
                        Location.builder().locationId(1).build()
                ));
                query.orderBy(builder.asc(root.get(Department_.departmentId)));
                return em.createQuery(query)
                        .setFirstResult(0)
                        .setMaxResults(10)
                        .getResultList();
            });
            assertThat(result).allSatisfy(e -> {
                assertThat(e.getLocationId()).isEqualTo(1);
            });
        }
    }
}
