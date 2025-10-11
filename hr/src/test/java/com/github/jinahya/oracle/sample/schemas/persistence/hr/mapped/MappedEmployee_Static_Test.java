package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MappedEmployee_Static_Test {

    @Test
    void __COLUMN_VALUE_MIN_EMPLOYEE_ID() {
        assertThat(MappedEmployee.COLUMN_VALUE_MIN_EMPLOYEE_ID).isEqualTo(-999999);
    }

    @Test
    void __COLUMN_VALUE_MAX_EMPLOYEE_ID() {
        assertThat(MappedEmployee.COLUMN_VALUE_MAX_EMPLOYEE_ID).isEqualTo(+999999);
    }
}
