package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MappedJob_TestUtilsTest {

    @DisplayName("newRandomPositiveMinSalary()")
    @Nested
    class NewRandomPositiveMinSalary_Test {

        @Test
        void __() {
            final var minSalary = MappedJob_TestUtils.newRandomPositiveMinSalary();
            assertThat(minSalary).isBetween(1, MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }
    }

    @DisplayName("newRandomPositiveMaxSalary(minSalary)")
    @Nested
    class NewRandomPositiveMaxSalary_Test {

        @Test
        void __1() {
            final var maxSalary = MappedJob_TestUtils.newRandomPositiveMaxSalary(1);
            assertThat(maxSalary)
                    .isPositive()
                    .isLessThanOrEqualTo(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }

        @Test
        void _ATTRIBUTE_MAX_MIN_SALARY_ATTRIBUTE_MAX_MIN_SALARY() {
            final var maxSalary = MappedJob_TestUtils.newRandomPositiveMaxSalary(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
            assertThat(maxSalary)
                    .isEqualTo(MappedJob.ATTRIBUTE_MAX_MIN_SALARY);
        }
    }
}
