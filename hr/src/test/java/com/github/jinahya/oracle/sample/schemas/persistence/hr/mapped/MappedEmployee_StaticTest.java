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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MappedEmployee_StaticTest {

    @DisplayName("..._EMPLOYEE_ID")
    @Nested
    class _EMPLOYEE_ID_TEST {

        @Test
        void __COLUMN_MIN_EMPLOYEE_ID() {
            assertThat(MappedEmployee.COLUMN_MIN_EMPLOYEE_ID).isEqualTo(-999999);
        }

        @Test
        void __COLUMN_MAX_EMPLOYEE_ID() {
            assertThat(MappedEmployee.COLUMN_MAX_EMPLOYEE_ID).isEqualTo(+999999);
        }

        @Test
        void __ATTRIBUTE_MIN_EMPLOYEE_ID() {
            assertThat(MappedEmployee.ATTRIBUTE_MIN_EMPLOYEE_ID).isEqualTo(-999999L);
        }

        @Test
        void __ATTRIBUTE_MAX_EMPLOYEE_ID() {
            assertThat(MappedEmployee.ATTRIBUTE_MAX_EMPLOYEE_ID).isEqualTo(+999999L);
        }
    }

    @DisplayName("..._SALARY")
    @Nested
    class _SALARY_TEST {

        @Test
        void __ATTRIBUTE_DECIMAL_MIN_SALARY() {
            assertThat(new BigDecimal(MappedEmployee.ATTRIBUTE_DECIMAL_MIN_SALARY).doubleValue())
                    .isEqualTo(MappedEmployee.COLUMN_MIN_SALARY);
        }

        @Test
        void __ATTRIBUTE_DECIMAL_MAX_SALARY() {
            assertThat(new BigDecimal(MappedEmployee.ATTRIBUTE_DECIMAL_MAX_SALARY).doubleValue())
                    .isEqualTo(MappedEmployee.COLUMN_MAX_SALARY);
        }
    }

    @DisplayName("..._COMMISSION_PCT")
    @Nested
    class _COMMISSION_PCT_TEST {

        @Test
        void __ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT() {
            assertThat(new BigDecimal(MappedEmployee.ATTRIBUTE_DECIMAL_MIN_COMMISSION_PCT).doubleValue())
                    .isEqualTo(MappedEmployee.COLUMN_MIN_COMMISSION_PCT);
        }

        @Test
        void __ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT() {
            assertThat(new BigDecimal(MappedEmployee.ATTRIBUTE_DECIMAL_MAX_COMMISSION_PCT).doubleValue())
                    .isEqualTo(MappedEmployee.COLUMN_MAX_COMMISSION_PCT);
        }
    }

    @DisplayName("..._DEPARTMENT_ID")
    @Nested
    class _DEPARTMENT_ID_TEST {

        @Test
        void __COLUMN_MIN_DEPARTMENT_ID() {
            assertThat(MappedEmployee.COLUMN_MIN_DEPARTMENT_ID).isEqualTo(-9999);
        }

        @Test
        void __COLUMN_MAX_DEPARTMENT_ID() {
            assertThat(MappedEmployee.COLUMN_MAX_DEPARTMENT_ID).isEqualTo(+9999);
        }

        @Test
        void __ATTRIBUTE_MIN_DEPARTMENT_ID() {
            assertThat(MappedEmployee.ATTRIBUTE_MIN_DEPARTMENT_ID).isEqualTo(-9999L);
        }

        @Test
        void __ATTRIBUTE_MAX_DEPARTMENT_ID() {
            assertThat(MappedEmployee.ATTRIBUTE_MAX_DEPARTMENT_ID).isEqualTo(+9999L);
        }
    }
}
