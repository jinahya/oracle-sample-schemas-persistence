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

import java.time.YearMonth;
import java.util.List;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_IT_Producer.__ItPU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * A class for testing the readers of {@link TimeUtils} against every row of the {@value Time#TABLE_NAME} table as
 * installed, a nested class per method; each derived value is checked against the row's own
 * {@value Time#ATTRIBUTE_NAME_TIME_ID}, which is what the text attributes describe.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_IT_Producer.class)
@EnableAutoWeld
class TimeUtils_PersistenceIT {

    private List<Time> all;

    @BeforeEach
    void loadAll() {
        all = entityManager.createQuery("SELECT t FROM Time t", Time.class).getResultList();
        assumeTrue(!all.isEmpty(), () -> Time.TABLE_NAME + " holds no row");
    }

    // --------------------------------------------------------------------------------------------------------- dayName

    @DisplayName("getDayNameAsDayOfWeek(time)")
    @Nested
    class GetDayNameAsDayOfWeek_IT {

        @Test
        void _WeekdayOfTimeId_EveryRow() {
            for (final var time : all) {
                assertThat(TimeUtils.getDayNameAsDayOfWeek(time)).isSameAs(time.getTimeId().getDayOfWeek());
            }
        }
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    @DisplayName("getCalendarMonthDescAsYearMonth(time)")
    @Nested
    class GetCalendarMonthDescAsYearMonth_IT {

        @Test
        void _MonthOfTimeId_EveryRow() {
            for (final var time : all) {
                assertThat(TimeUtils.getCalendarMonthDescAsYearMonth(time)).isEqualTo(YearMonth.from(time.getTimeId()));
            }
        }
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthDesc

    @DisplayName("getFiscalMonthDescAsYearMonth(time)")
    @Nested
    class GetFiscalMonthDescAsYearMonth_IT {

        @Test
        void _Parses_EveryRow() {
            for (final var time : all) {
                assertThat(TimeUtils.getFiscalMonthDescAsYearMonth(time))
                        .isNotNull()
                        .hasToString(time.getFiscalMonthDesc());
            }
        }
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthName

    @DisplayName("getCalendarMonthNameAsMonth(time)")
    @Nested
    class GetCalendarMonthNameAsMonth_IT {

        @Test
        void _MonthOfTimeId_EveryRow() {
            for (final var time : all) {
                assertThat(TimeUtils.getCalendarMonthNameAsMonth(time)).isSameAs(time.getTimeId().getMonth());
            }
        }
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthName

    @DisplayName("getFiscalMonthNameAsMonth(time)")
    @Nested
    class GetFiscalMonthNameAsMonth_IT {

        @Test
        void _MonthOfFiscalMonthDesc_EveryRow() {
            for (final var time : all) {
                assertThat(TimeUtils.getFiscalMonthNameAsMonth(time))
                        .isSameAs(TimeUtils.getFiscalMonthDescAsYearMonth(time).getMonth());
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__ItPU
    @Inject
    private EntityManager entityManager;
}
