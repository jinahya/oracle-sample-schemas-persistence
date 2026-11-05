package com.github.jinahya.oracle.sample.persistence.hr;

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
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.github.jinahya.oracle.sample.persistence.hr.__PersistenceIT_Utils.applyEntityManagerFactory;
import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the mappings of {@link Country} against the installed {@code HR} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Country_PersistenceIT
        extends _Entity_PersistenceIT<Country, String> {

    // ---------------------------------------------------------------------------------------------
    private static final Comparator<Country> COUNTRY_NAME_ASC =
            Comparator.comparing(Country::getCountryName,
                                 Comparator.nullsLast(Comparator.naturalOrder()));

    private static final Comparator<Country> COUNTRY_ID_ASC =
            Comparator.comparing(Country::getCountryId);

    // ---------------------------------------------------------------------------------------------
    Country_PersistenceIT() {
        super(Country.class, String.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Returns some ids of the regions the installed table holds countries of.
     */
    Stream<Long> regionIds() {
        return applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
            return entityManager
                    .createQuery(
                            """
                                    SELECT DISTINCT e.region.regionId
                                    FROM Country e
                                    ORDER BY e.region.regionId ASC""",
                            Long.class
                    )
                    .setMaxResults(5)
                    .getResultList()
                    .stream();
        }));
    }

    private void verifyByRegion(final Long regionId, final List<Country> found,
                                final Comparator<Country> order) {
        applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
            final var count = entityManager
                    .createQuery(
                            """
                                    SELECT COUNT(e)
                                    FROM Country e
                                    WHERE e.region.regionId = :regionId""",
                            Long.class
                    )
                    .setParameter("regionId", regionId)
                    .getSingleResult();
            assertThat(found)
                    .as("the countries of region %s", regionId)
                    .hasSize(count.intValue())
                    .allSatisfy(c -> assertThat(c.getRegion().getRegionId()).isEqualTo(regionId))
                    .isSortedAccordingTo(order);
            return null;
        }));
    }

    private void verifyAll(final List<Country> found, final Comparator<Country> order) {
        applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
            final var count = entityManager
                    .createQuery(
                            """
                                    SELECT COUNT(e)
                                    FROM Country e""",
                            Long.class
                    )
                    .getSingleResult();
            assertThat(found)
                    .as("all countries")
                    .hasSize(count.intValue())
                    .isSortedAccordingTo(order);
            return null;
        }));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Tests {@code Country.selectListByRegionOrderByCountryNameAsc}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectListByRegionOrderByCountryNameAsc_Test {

        Stream<Long> regionIds() {
            return Country_PersistenceIT.this.regionIds();
        }

        @DisplayName("a query-language query selects the region's countries, by name")
        @MethodSource("regionIds")
        @ParameterizedTest
        void __QueryLanguage(final Long regionId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        WHERE e.region = :region
                                        ORDER BY e.countryName ASC""",
                                targetClass
                        )
                        .setParameter("region", entityManager.getReference(Region.class, regionId))
                        .getResultList();
                verifyByRegion(regionId, found, COUNTRY_NAME_ASC);
                return null;
            }));
        }

        @DisplayName("a criteria query selects the region's countries, by name")
        @MethodSource("regionIds")
        @ParameterizedTest
        void __CriteriaApi(final Long regionId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Country_.region),
                                          entityManager.getReference(Region.class, regionId)));
                query.orderBy(builder.asc(root.get(Country_.countryName)));
                final var found = entityManager.createQuery(query).getResultList();
                verifyByRegion(regionId, found, COUNTRY_NAME_ASC);
                return null;
            }));
        }
    }

    /**
     * Tests {@code Country.selectListByRegionOrderByCountryIdAsc}.
     */
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class SelectListByRegionOrderByCountryIdAsc_Test {

        Stream<Long> regionIds() {
            return Country_PersistenceIT.this.regionIds();
        }

        @DisplayName("a query-language query selects the region's countries, by id")
        @MethodSource("regionIds")
        @ParameterizedTest
        void __QueryLanguage(final Long regionId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        WHERE e.region = :region
                                        ORDER BY e.countryId ASC""",
                                targetClass
                        )
                        .setParameter("region", entityManager.getReference(Region.class, regionId))
                        .getResultList();
                verifyByRegion(regionId, found, COUNTRY_ID_ASC);
                return null;
            }));
        }

        @DisplayName("a criteria query selects the region's countries, by id")
        @MethodSource("regionIds")
        @ParameterizedTest
        void __CriteriaApi(final Long regionId) {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.where(builder.equal(root.get(Country_.region),
                                          entityManager.getReference(Region.class, regionId)));
                query.orderBy(builder.asc(root.get(Country_.countryId)));
                final var found = entityManager.createQuery(query).getResultList();
                verifyByRegion(regionId, found, COUNTRY_ID_ASC);
                return null;
            }));
        }
    }

    /**
     * Tests {@code Country.selectListOrderByCountryNameAsc}.
     */
    @Nested
    class SelectListOrderByCountryNameAsc_Test {

        @DisplayName("a query-language query selects every country, by name")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        ORDER BY e.countryName ASC""",
                                targetClass
                        )
                        .getResultList();
                verifyAll(found, COUNTRY_NAME_ASC);
                return null;
            }));
        }

        @DisplayName("a criteria query selects every country, by name")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Country_.countryName)));
                final var found = entityManager.createQuery(query).getResultList();
                verifyAll(found, COUNTRY_NAME_ASC);
                return null;
            }));
        }
    }

    /**
     * Tests {@code Country.selectListOrderByCountryIdAsc}.
     */
    @Nested
    class SelectListOrderByCountryIdAsc_Test {

        @DisplayName("a query-language query selects every country, by id")
        @Test
        void __QueryLanguage() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var found = entityManager
                        .createQuery(
                                """
                                        SELECT e
                                        FROM Country AS e
                                        ORDER BY e.countryId ASC""",
                                targetClass
                        )
                        .getResultList();
                verifyAll(found, COUNTRY_ID_ASC);
                return null;
            }));
        }

        @DisplayName("a criteria query selects every country, by id")
        @Test
        void __CriteriaApi() {
            applyEntityManagerFactory(emf -> applyEntityManager(emf, entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(targetClass);
                final var root = query.from(targetClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Country_.countryId)));
                final var found = entityManager.createQuery(query).getResultList();
                verifyAll(found, COUNTRY_ID_ASC);
                return null;
            }));
        }
    }
}
