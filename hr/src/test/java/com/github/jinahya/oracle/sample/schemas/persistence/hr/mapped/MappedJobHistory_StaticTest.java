package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class MappedJobHistory_StaticTest {

    @Test
    void __COLUMN_MIN_EMPLOYEE_ID() {
        assertThat(
                BigDecimal.valueOf(MappedJobHistory.COLUMN_MIN_EMPLOYEE_ID)
                        .setScale(MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID, RoundingMode.UNNECESSARY)
                        .intValue()
        ).isEqualTo(MappedJobHistory.COLUMN_MIN_EMPLOYEE_ID);
    }

    @Test
    void __COLUMN_MAX_EMPLOYEE_ID() {
        assertThat(
                BigDecimal.valueOf(MappedJobHistory.COLUMN_MAX_EMPLOYEE_ID)
                        .setScale(MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID, RoundingMode.UNNECESSARY)
                        .intValue()
        ).isEqualTo(MappedJobHistory.COLUMN_MAX_EMPLOYEE_ID);
    }

    @Test
    void __COLUMN_MIN_DEPARTMENT_ID() {
        assertThat(
                BigDecimal.valueOf(MappedJobHistory.COLUMN_MIN_DEPARTMENT_ID)
                        .setScale(MappedJobHistory.COLUMN_SCALE_DEPARTMENT_ID, RoundingMode.UNNECESSARY)
                        .intValue()
        ).isEqualTo(MappedJobHistory.COLUMN_MIN_DEPARTMENT_ID);
    }

    @Test
    void __COLUMN_MAX_DEPARTMENT_ID() {
        assertThat(
                BigDecimal.valueOf(MappedJobHistory.COLUMN_MAX_DEPARTMENT_ID)
                        .setScale(MappedJobHistory.COLUMN_SCALE_DEPARTMENT_ID, RoundingMode.UNNECESSARY)
                        .intValue()
        ).isEqualTo(MappedJobHistory.COLUMN_MAX_DEPARTMENT_ID);
    }
}
