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

import java.time.YearMonth;
import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for testing {@link CalMonthSalesMvUtils} against the {@code sh-test} unit, where the materialized view is an
 * ordinary table: a row is inserted with its key, reloaded, and read through the utility.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_Test_Producer.class)
@EnableAutoWeld
class CalMonthSalesMvUtils_PersistenceTest {

    /** Sets the named field of the specified instance reflectively, for an entity which exposes no setter for it. */
    private static <T> T withField(final T instance, final String name, final Object value) {
        try {
            final var field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private <R> R inRolledBackTransaction(final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndRollback(entityManager, function);
    }

    private static CalMonthSalesMv persistAndReload(final EntityManager em, final String calendarMonthDesc) {
        em.persist(withField(new CalMonthSalesMv(), CalMonthSalesMv.ATTRIBUTE_NAME_CALENDAR_MONTH_DESC,
                             calendarMonthDesc));
        em.flush();
        em.clear();
        return em.find(CalMonthSalesMv.class, calendarMonthDesc);
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

    @DisplayName("getCalendarMonthDescAsMapped(calMonthSalesMv, mapper)")
    @Nested
    class GetCalendarMonthDescAsMapped_Test {

        @Test
        void _Mapped_Reloaded() {
            inRolledBackTransaction(em -> {
                final var reloaded = persistAndReload(em, "2019-01");
                assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsMapped(reloaded, String::length)).isEqualTo(7);
                return null;
            });
        }
    }

    @DisplayName("getCalendarMonthDescAsYearMonth(calMonthSalesMv)")
    @Nested
    class GetCalendarMonthDescAsYearMonth_Test {

        @Test
        void _Parsed_Reloaded() {
            inRolledBackTransaction(em -> {
                final var reloaded = persistAndReload(em, "2023-12");
                assertThat(CalMonthSalesMvUtils.getCalendarMonthDescAsYearMonth(reloaded))
                        .isEqualTo(YearMonth.of(2023, 12));
                return null;
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__TestPU
    @Inject
    private EntityManager entityManager;
}
