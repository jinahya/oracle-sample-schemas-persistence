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

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class for the {@value CalMonthSalesMv#TABLE_NAME} materialized view.
 * <p>
 * A materialized view carries no primary key, so this is not an {@link jakarta.persistence.Entity @Entity}: the columns it projects
 * are written out here, and every one of them is read-only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class CalMonthSalesMv {

    /**
     * The name of the database materialized view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CAL_MONTH_SALES_MV";

    // ---------------------------------------------------------------------------------------------- CALENDAR_MONTH_DESC

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_DESC = "CALENDAR_MONTH_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CALENDAR_MONTH_DESC = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_DESC = "calendarMonthDesc";

    // ---------------------------------------------------------------------------------------------------------- DOLLARS

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_DOLLARS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOLLARS = "DOLLARS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOLLARS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOLLARS = "dollars";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CalMonthSalesMv() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "calendarMonthDesc=" + calendarMonthDesc +
               ",dollars=" + dollars +
               '}';
    }

    // ------------------------------------------------------------------------------------------------ calendarMonthDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute.
     */
    public String getCalendarMonthDesc() {
        return calendarMonthDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute with the specified value.
     *
     * @param calendarMonthDesc new value for {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute.
     */
    public void setCalendarMonthDesc(final String calendarMonthDesc) {
        this.calendarMonthDesc = calendarMonthDesc;
    }

    // ---------------------------------------------------------------------------------------------------------- dollars

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public BigDecimal getDollars() {
        return dollars;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute with the specified value.
     *
     * @param dollars new value for {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public void setDollars(final BigDecimal dollars) {
        this.dollars = dollars;
    }


    // -----------------------------------------------------------------------------------------------------------------

    @Column(name = COLUMN_NAME_CALENDAR_MONTH_DESC, insertable = false, updatable = false, length = COLUMN_LENGTH_CALENDAR_MONTH_DESC)
    private String calendarMonthDesc;

    @Column(name = COLUMN_NAME_DOLLARS, insertable = false, updatable = false)
    private BigDecimal dollars;
}
