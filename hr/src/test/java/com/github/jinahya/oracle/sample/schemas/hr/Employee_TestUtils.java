package com.github.jinahya.oracle.sample.schemas.hr;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

final class Employee_TestUtils {

    public static BigDecimal randomSalary() {
        return BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(
                        Employee.COLUMN_VALUE_MIN_SALARY,
                        Employee.COLUMN_VALUE_MAX_SALARY + Double.MIN_VALUE
                )
        );
    }

    public static BigDecimal randomCommissionPct() {
        return BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(
                        Employee.COLUMN_VALUE_MIN_COMMISSION_PCT,
                        Employee.COLUMN_VALUE_MAX_COMMISSION_PCT + Double.MIN_VALUE
                )
        );
    }

    private Employee_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}