package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class __HrDomainUtilsTest {

    @Nested
    class ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR_Test {

        private static Stream<LocalTime> nowBetween() {
            return Stream.of(
                    LocalTime.of(8, 0),
                    LocalTime.of(13, 0),
                    LocalTime.of(18, 0)
            );
        }

        private static Stream<LocalTime> nowNotBetween() {
            return Stream.of(
                    LocalTime.of(7, 59),
                    LocalTime.of(18, 1)
            );
        }

        private static Stream<DayOfWeek> dayOn() {
            return __HrDomainConstants.ROUTINE_SECURE_DML_DAY_OF_WEEK_LIST.stream();
        }

        private static Stream<DayOfWeek> dayNotOn() {
            return Arrays.stream(DayOfWeek.values())
                    .filter(v -> !__HrDomainConstants.ROUTINE_SECURE_DML_DAY_OF_WEEK_LIST.contains(v));
        }

        private static Stream<Arguments> nowNotBetween_dayOn() {
            return nowNotBetween().flatMap(now -> dayOn().map(day -> Arguments.of(now, day)));
        }

        private static Stream<Arguments> nowNotBetween_dayNotOn() {
            return nowNotBetween().flatMap(now -> dayNotOn().map(day -> Arguments.of(now, day)));
        }

        private static Stream<Arguments> nowBetween_dayOn() {
            return nowBetween().flatMap(now -> dayOn().map(day -> Arguments.of(now, day)));
        }

        private static Stream<Arguments> nowBetween_dayNotOn() {
            return nowBetween().flatMap(now -> dayNotOn().map(day -> Arguments.of(now, day)));
        }

        @MethodSource({"nowNotBetween_dayOn"})
        @ParameterizedTest
        void nowNotBetween_dayOn__(final LocalTime now, final DayOfWeek day) {
            assertThat(
                    __HrDomainUtils.ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(now, day)
            ).isTrue();
            assertThat(
                    __HrDomainUtils.ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(
                            LocalDateTime.of(LocalDate.now(), now).with(TemporalAdjusters.nextOrSame(day)))
            ).isTrue();
        }

        @MethodSource({"nowNotBetween_dayNotOn"})
        @ParameterizedTest
        void nowNotBetween_dayNotOn__(final LocalTime now, final DayOfWeek day) {
            assertThat(
                    __HrDomainUtils.ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(now, day)
            ).isFalse();
        }

        @MethodSource({"nowBetween_dayOn"})
        @ParameterizedTest
        void nowBetween_dayOn__(final LocalTime now, final DayOfWeek day) {
            assertThat(
                    __HrDomainUtils.ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(now, day)
            ).isTrue();
        }

        @MethodSource({"nowBetween_dayNotOn"})
        @ParameterizedTest
        void nowBetween_dayNotOn__(final LocalTime now, final DayOfWeek day) {
            assertThat(
                    __HrDomainUtils.ROUTINE_SECURE_DML_RAISE_APPLICATION_ERROR(now, day)
            ).isFalse();
        }
    }
}
