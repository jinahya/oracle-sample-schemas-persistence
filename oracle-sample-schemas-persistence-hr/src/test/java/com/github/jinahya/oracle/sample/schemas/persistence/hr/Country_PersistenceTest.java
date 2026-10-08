package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Country} against the schema generated into the in-memory database.
 * <p>
 * Each persisted {@link Country} is given a region of its own, so a query by the region selects it alone.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Country_PersistenceTest extends _Persistence_Test<Country> {

    // -----------------------------------------------------------------------------------------------------------------
    Country_PersistenceTest() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@code Country.selectListByRegionOrderByCountryNameAsc}.
     */
    @Nested
    class SelectListByRegionOrderByCountryNameAsc_Test {

        @DisplayName("the named query selects the country just persisted, by its region")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Country.selectListByRegionOrderByCountryNameAsc", targetClass)
                        .setParameter("region", v.getRegion())
                        .getResultList();
                assertThat(found)
                        .as("the countries of the region")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the country just persisted, by its region")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        WHERE e.region = :region
                                        ORDER BY e.countryName ASC""",
                                targetClass
                        )
                        .setParameter("region", v.getRegion())
                        .getResultList();
                assertThat(found)
                        .as("the countries of the region")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the country just persisted, by its region")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Country_.region), v.getRegion()));
                query.orderBy(builder.asc(root.get(Country_.countryName)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("the countries of the region")
                        .containsExactly(v);
                return found;
            });
        }
    }

    /**
     * Tests {@code Country.selectListByRegionOrderByCountryIdAsc}.
     */
    @Nested
    class SelectListByRegionOrderByCountryIdAsc_Test {

        @DisplayName("the named query selects the country just persisted, by its region")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Country.selectListByRegionOrderByCountryIdAsc", targetClass)
                        .setParameter("region", v.getRegion())
                        .getResultList();
                assertThat(found)
                        .as("the countries of the region")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the country just persisted, by its region")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        WHERE e.region = :region
                                        ORDER BY e.countryId ASC""",
                                targetClass
                        )
                        .setParameter("region", v.getRegion())
                        .getResultList();
                assertThat(found)
                        .as("the countries of the region")
                        .containsExactly(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the country just persisted, by its region")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Country_.region), v.getRegion()));
                query.orderBy(builder.asc(root.get(Country_.countryId)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("the countries of the region")
                        .containsExactly(v);
                return found;
            });
        }
    }

    /**
     * Tests {@code Country.selectListOrderByCountryNameAsc}.
     *
     * @implNote Only checks that the country just persisted is among those selected; the order is left to
     * {@link Country_PersistenceIT}, since the in-memory database places {@code NULL}s differently from Oracle.
     */
    @Nested
    class SelectListOrderByCountryNameAsc_Test {

        @DisplayName("the named query selects the country just persisted")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Country.selectListOrderByCountryNameAsc", targetClass)
                        .getResultList();
                assertThat(found)
                        .as("all countries")
                        .contains(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the country just persisted")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        ORDER BY e.countryName ASC""",
                                targetClass
                        )
                        .getResultList();
                assertThat(found)
                        .as("all countries")
                        .contains(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the country just persisted")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Country_.countryName)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("all countries")
                        .contains(v);
                return found;
            });
        }
    }

    /**
     * Tests {@code Country.selectListOrderByCountryIdAsc}.
     */
    @Nested
    class SelectListOrderByCountryIdAsc_Test {

        @DisplayName("the named query selects the country just persisted")
        @Test
        void __NamedQuery() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createNamedQuery("Country.selectListOrderByCountryIdAsc", targetClass)
                        .getResultList();
                assertThat(found)
                        .as("all countries")
                        .contains(v);
                return found;
            });
        }

        @DisplayName("a query-language query selects the country just persisted")
        @Test
        void __QueryLanguage() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var found = em
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        ORDER BY e.countryId ASC""",
                                targetClass
                        )
                        .getResultList();
                assertThat(found)
                        .as("all countries")
                        .contains(v);
                return found;
            });
        }

        @DisplayName("a criteria query selects the country just persisted")
        @Test
        void __CriteriaApi() {
            applyNewPersistedEntityInstanceAndRollback((em, v) -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Country_.countryId)));
                final var found = em.createQuery(query).getResultList();
                assertThat(found)
                        .as("all countries")
                        .contains(v);
                return found;
            });
        }
    }
}
