package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_IT_Producer.__ItPU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * A class for testing {@link CalMonthSalesMvUtils} against every row of {@value CalMonthSalesMv#TABLE_NAME} as
 * installed, a nested class per method. The installer leaves the materialized view empty until it is refreshed, and
 * then every test here is skipped rather than failed.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_IT_Producer.class)
@EnableAutoWeld
class CalMonthSalesMvUtils_PersistenceIT {

    private List<CalMonthSalesMv> all;

    @BeforeEach
    void loadAll() {
        all = entityManager.createQuery("SELECT e FROM CalMonthSalesMv e", CalMonthSalesMv.class).getResultList();
        assumeTrue(!all.isEmpty(), () -> CalMonthSalesMv.TABLE_NAME + " holds no row; refresh it first");
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    @DisplayName("getCalendarMonthDescAsMapped(calMonthSalesMv, mapper)")
    @Nested
    class GetCalendarMonthDescAsMapped_IT {

        @Test
        void _SevenCharacters_EveryRow() {
            for (final var each : all) {
                assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsMapped(each, String::length)).isEqualTo(7);
            }
        }
    }

    @DisplayName("getCalendarMonthDescAsYearMonth(calMonthSalesMv)")
    @Nested
    class GetCalendarMonthDescAsYearMonth_IT {

        @Test
        void _Parses_EveryRow() {
            for (final var each : all) {
                assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsYearMonth(each))
                        .isNotNull()
                        .hasToString(each.getCalendarMonthDesc());
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__ItPU
    @Inject
    private EntityManager entityManager;
}
