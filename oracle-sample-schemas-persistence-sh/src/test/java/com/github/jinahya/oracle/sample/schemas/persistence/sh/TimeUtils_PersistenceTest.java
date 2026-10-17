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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.YearMonth;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for testing {@link TimeUtils} against the {@code sh-test} unit, a nested class per typed writer: each writes
 * through the utility, flushes, clears, reloads the row and reads it back through the utility.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_Test_Producer.class)
@EnableAutoWeld
class TimeUtils_PersistenceTest {

    private <R> R inRolledBackTransaction(final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndRollback(entityManager, function);
    }

    /** Persists a row, applies the specified writer to it, flushes and clears, and returns the row reloaded. */
    private static Time roundTrip(final EntityManager em, final java.util.function.Consumer<? super Time> writer) {
        final var persisted = Time_Persister.newPersistedInstanceBeforeEarliest(em);
        writer.accept(persisted);
        em.flush();
        em.clear();
        return em.find(Time.class, persisted.getTimeId());
    }

    // --------------------------------------------------------------------------------------------------------- dayName

    @DisplayName("setDayNameFromDayOfWeek(time, dayOfWeek)")
    @Nested
    class SetDayNameFromDayOfWeek_Test {

        @Test
        void _ReadBack_() {
            inRolledBackTransaction(em -> {
                final var reloaded = roundTrip(em, t -> TimeUtils.setDayNameFromDayOfWeek(t, DayOfWeek.FRIDAY));
                assertThat(reloaded.getDayName()).isEqualTo("Friday");
                assertThat(TimeUtils.getDayNameAsDayOfWeek(reloaded)).isSameAs(DayOfWeek.FRIDAY);
                return null;
            });
        }
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    @DisplayName("setCalendarMonthDescFromYearMonth(time, yearMonth)")
    @Nested
    class SetCalendarMonthDescFromYearMonth_Test {

        @Test
        void _ReadBack_() {
            inRolledBackTransaction(em -> {
                final var reloaded = roundTrip(
                        em, t -> TimeUtils.setCalendarMonthDescFromYearMonth(t, YearMonth.of(2019, 1)));
                assertThat(reloaded.getCalendarMonthDesc()).isEqualTo("2019-01");
                assertThat(TimeUtils.getCalendarMonthDescAsYearMonth(reloaded)).isEqualTo(YearMonth.of(2019, 1));
                return null;
            });
        }
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthDesc

    @DisplayName("setFiscalMonthDescFromYearMonth(time, yearMonth)")
    @Nested
    class SetFiscalMonthDescFromYearMonth_Test {

        @Test
        void _ReadBack_() {
            inRolledBackTransaction(em -> {
                final var reloaded = roundTrip(
                        em, t -> TimeUtils.setFiscalMonthDescFromYearMonth(t, YearMonth.of(2024, 1)));
                assertThat(reloaded.getFiscalMonthDesc()).isEqualTo("2024-01");
                assertThat(TimeUtils.getFiscalMonthDescAsYearMonth(reloaded)).isEqualTo(YearMonth.of(2024, 1));
                return null;
            });
        }
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthName

    @DisplayName("setCalendarMonthNameFromMonth(time, month)")
    @Nested
    class SetCalendarMonthNameFromMonth_Test {

        @Test
        void _ReadBack_() {
            inRolledBackTransaction(em -> {
                final var reloaded = roundTrip(em, t -> TimeUtils.setCalendarMonthNameFromMonth(t, Month.SEPTEMBER));
                assertThat(reloaded.getCalendarMonthName()).isEqualTo("September");
                assertThat(TimeUtils.getCalendarMonthNameAsMonth(reloaded)).isSameAs(Month.SEPTEMBER);
                return null;
            });
        }
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthName

    @DisplayName("setFiscalMonthNameFromMonth(time, month)")
    @Nested
    class SetFiscalMonthNameFromMonth_Test {

        @Test
        void _ReadBack_() {
            inRolledBackTransaction(em -> {
                final var reloaded = roundTrip(em, t -> TimeUtils.setFiscalMonthNameFromMonth(t, Month.MAY));
                assertThat(reloaded.getFiscalMonthName()).isEqualTo("May");
                assertThat(TimeUtils.getFiscalMonthNameAsMonth(reloaded)).isSameAs(Month.MAY);
                return null;
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__TestPU
    @Inject
    private EntityManager entityManager;
}
