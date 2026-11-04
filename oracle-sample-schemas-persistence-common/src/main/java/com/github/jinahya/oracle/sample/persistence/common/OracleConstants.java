package com.github.jinahya.oracle.sample.persistence.common;

import java.time.LocalDateTime;

public final class OracleConstants {

    // ---------------------------------------------------------------------------------------------

    /**
     * The minimum value of the {@code DATE} data type; January 1, 4712 BC, at {@code 00:00:00}.
     * <p>
     * A {@code DATE} holds the fields {@code YEAR}, {@code MONTH}, {@code DAY}, {@code HOUR},
     * {@code MINUTE} and {@code SECOND}, with no fractional seconds and no time zone, which is what a
     * {@link LocalDateTime} with a zero nano-of-second holds. The ISO year {@code -4711} is 4712 BC,
     * since the ISO calendar counts 1 BC as year {@code 0}.
     *
     * @implNote Oracle counts the days before October 15, 1582 in the Julian calendar, whereas
     *         {@link LocalDateTime} uses the proleptic Gregorian one; the constant has the same
     *         fields as the database's minimum, not the same day.
     * @see <a
     *         href="https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/Data-Types.html#GUID-5405B652-C30E-4F4F-9D33-9A4CB2110F1B">DATE
     *         Data Type</a>
     */
    public static final LocalDateTime DATE_MIN = LocalDateTime.of(-4711, 1, 1, 0, 0, 0);

    /**
     * The maximum value of the {@code DATE} data type; December 31, 9999 AD, at {@code 23:59:59}.
     *
     * @see #DATE_MIN
     * @see <a
     *         href="https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/Data-Types.html#GUID-5405B652-C30E-4F4F-9D33-9A4CB2110F1B">DATE
     *         Data Type</a>
     */
    public static final LocalDateTime DATE_MAX = LocalDateTime.of(9999, 12, 31, 23, 59, 59);

    // ---------------------------------------------------------------------------------------------
    
    private OracleConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
