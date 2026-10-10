package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Optional;

/**
 * A mapped superclass which holds the mappings of the {@value MappedTime#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedTime implements __MappedDomainEntity<LocalDateTime> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "TIMES";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -------------------------------------------------------------------------------------------------------- DAY_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAY_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAY_NAME = "DAY_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_DAY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DAY_NAME = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_NAME = "dayName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_DAY_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_DAY_NAME = COLUMN_LENGTH_DAY_NAME;

    // ---------------------------------------------------------------------------------------------- DAY_NUMBER_IN_WEEK

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_DAY_NUMBER_IN_WEEK = "DAY_NUMBER_IN_WEEK";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_NUMBER_IN_WEEK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK = "dayNumberInWeek";

    /**
     * The precision of the {@value #COLUMN_NAME_DAY_NUMBER_IN_WEEK} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DAY_NUMBER_IN_WEEK = 1;

    /**
     * The scale of the {@value #COLUMN_NAME_DAY_NUMBER_IN_WEEK} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DAY_NUMBER_IN_WEEK = 0;

    // --------------------------------------------------------------------------------------------- DAY_NUMBER_IN_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_MONTH} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_DAY_NUMBER_IN_MONTH = "DAY_NUMBER_IN_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_NUMBER_IN_MONTH} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_NUMBER_IN_MONTH = "dayNumberInMonth";

    /**
     * The precision of the {@value #COLUMN_NAME_DAY_NUMBER_IN_MONTH} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DAY_NUMBER_IN_MONTH = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_DAY_NUMBER_IN_MONTH} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DAY_NUMBER_IN_MONTH = 0;

    // -------------------------------------------------------------------------------------------- CALENDAR_WEEK_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_WEEK_NUMBER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_WEEK_NUMBER = "CALENDAR_WEEK_NUMBER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_WEEK_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_WEEK_NUMBER = "calendarWeekNumber";

    /**
     * The precision of the {@value #COLUMN_NAME_CALENDAR_WEEK_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CALENDAR_WEEK_NUMBER = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_CALENDAR_WEEK_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CALENDAR_WEEK_NUMBER = 0;

    // ---------------------------------------------------------------------------------------------- FISCAL_WEEK_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_WEEK_NUMBER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_WEEK_NUMBER = "FISCAL_WEEK_NUMBER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_WEEK_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_WEEK_NUMBER = "fiscalWeekNumber";

    /**
     * The precision of the {@value #COLUMN_NAME_FISCAL_WEEK_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_FISCAL_WEEK_NUMBER = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_FISCAL_WEEK_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_FISCAL_WEEK_NUMBER = 0;

    // ------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY = "WEEK_ENDING_DAY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    // ---------------------------------------------------------------------------------------------- WEEK_ENDING_DAY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY_ID = "WEEK_ENDING_DAY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID = "weekEndingDayId";

    // ------------------------------------------------------------------------------------------- CALENDAR_MONTH_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_NUMBER = "CALENDAR_MONTH_NUMBER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER = "calendarMonthNumber";

    /**
     * The precision of the {@value #COLUMN_NAME_CALENDAR_MONTH_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CALENDAR_MONTH_NUMBER = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_CALENDAR_MONTH_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CALENDAR_MONTH_NUMBER = 0;

    // --------------------------------------------------------------------------------------------- FISCAL_MONTH_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NUMBER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_MONTH_NUMBER = "FISCAL_MONTH_NUMBER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_MONTH_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_MONTH_NUMBER = "fiscalMonthNumber";

    /**
     * The precision of the {@value #COLUMN_NAME_FISCAL_MONTH_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_FISCAL_MONTH_NUMBER = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_FISCAL_MONTH_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_FISCAL_MONTH_NUMBER = 0;

    // --------------------------------------------------------------------------------------------- CALENDAR_MONTH_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_DESC = "CALENDAR_MONTH_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CALENDAR_MONTH_DESC = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_DESC = "calendarMonthDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CALENDAR_MONTH_DESC = COLUMN_LENGTH_CALENDAR_MONTH_DESC;

    // ----------------------------------------------------------------------------------------------- CALENDAR_MONTH_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_ID = "CALENDAR_MONTH_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_ID = "calendarMonthId";

    // ----------------------------------------------------------------------------------------------- FISCAL_MONTH_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_MONTH_DESC = "FISCAL_MONTH_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_FISCAL_MONTH_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FISCAL_MONTH_DESC = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_MONTH_DESC} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_MONTH_DESC = "fiscalMonthDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FISCAL_MONTH_DESC = COLUMN_LENGTH_FISCAL_MONTH_DESC;

    // ------------------------------------------------------------------------------------------------- FISCAL_MONTH_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_MONTH_ID = "FISCAL_MONTH_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_MONTH_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_MONTH_ID = "fiscalMonthId";

    // ----------------------------------------------------------------------------------------------- DAYS_IN_CAL_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_CAL_MONTH = "DAYS_IN_CAL_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_CAL_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH = "daysInCalMonth";

    // ----------------------------------------------------------------------------------------------- DAYS_IN_FIS_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_FIS_MONTH = "DAYS_IN_FIS_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_FIS_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH = "daysInFisMonth";

    // ------------------------------------------------------------------------------------------------ END_OF_CAL_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_CAL_MONTH = "END_OF_CAL_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_CAL_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_CAL_MONTH = "endOfCalMonth";

    // ------------------------------------------------------------------------------------------------ END_OF_FIS_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_FIS_MONTH = "END_OF_FIS_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_FIS_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_FIS_MONTH = "endOfFisMonth";

    // --------------------------------------------------------------------------------------------- CALENDAR_MONTH_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_NAME = "CALENDAR_MONTH_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_CALENDAR_MONTH_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CALENDAR_MONTH_NAME = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_NAME = "calendarMonthName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CALENDAR_MONTH_NAME = COLUMN_LENGTH_CALENDAR_MONTH_NAME;

    // ----------------------------------------------------------------------------------------------- FISCAL_MONTH_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_MONTH_NAME = "FISCAL_MONTH_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_FISCAL_MONTH_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FISCAL_MONTH_NAME = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_MONTH_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_MONTH_NAME = "fiscalMonthName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FISCAL_MONTH_NAME = COLUMN_LENGTH_FISCAL_MONTH_NAME;

    // ------------------------------------------------------------------------------------------- CALENDAR_QUARTER_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_QUARTER_DESC = "CALENDAR_QUARTER_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_CALENDAR_QUARTER_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CALENDAR_QUARTER_DESC = 7;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_QUARTER_DESC} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC = "calendarQuarterDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CALENDAR_QUARTER_DESC = COLUMN_LENGTH_CALENDAR_QUARTER_DESC;

    // --------------------------------------------------------------------------------------------- CALENDAR_QUARTER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_QUARTER_ID = "CALENDAR_QUARTER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_QUARTER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_QUARTER_ID = "calendarQuarterId";

    // --------------------------------------------------------------------------------------------- FISCAL_QUARTER_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_DESC} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_QUARTER_DESC = "FISCAL_QUARTER_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_FISCAL_QUARTER_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FISCAL_QUARTER_DESC = 7;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_QUARTER_DESC} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_QUARTER_DESC = "fiscalQuarterDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FISCAL_QUARTER_DESC = COLUMN_LENGTH_FISCAL_QUARTER_DESC;

    // ----------------------------------------------------------------------------------------------- FISCAL_QUARTER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_QUARTER_ID = "FISCAL_QUARTER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_QUARTER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_QUARTER_ID = "fiscalQuarterId";

    // --------------------------------------------------------------------------------------------- DAYS_IN_CAL_QUARTER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_QUARTER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_CAL_QUARTER = "DAYS_IN_CAL_QUARTER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_CAL_QUARTER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_CAL_QUARTER = "daysInCalQuarter";

    // --------------------------------------------------------------------------------------------- DAYS_IN_FIS_QUARTER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_QUARTER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_FIS_QUARTER = "DAYS_IN_FIS_QUARTER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_FIS_QUARTER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_FIS_QUARTER = "daysInFisQuarter";

    // ---------------------------------------------------------------------------------------------- END_OF_CAL_QUARTER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_END_OF_CAL_QUARTER = "END_OF_CAL_QUARTER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_CAL_QUARTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_CAL_QUARTER = "endOfCalQuarter";

    // ---------------------------------------------------------------------------------------------- END_OF_FIS_QUARTER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_END_OF_FIS_QUARTER = "END_OF_FIS_QUARTER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_FIS_QUARTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_FIS_QUARTER = "endOfFisQuarter";

    // ----------------------------------------------------------------------------------------- CALENDAR_QUARTER_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_NUMBER} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_QUARTER_NUMBER = "CALENDAR_QUARTER_NUMBER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_QUARTER_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_QUARTER_NUMBER = "calendarQuarterNumber";

    /**
     * The precision of the {@value #COLUMN_NAME_CALENDAR_QUARTER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CALENDAR_QUARTER_NUMBER = 1;

    /**
     * The scale of the {@value #COLUMN_NAME_CALENDAR_QUARTER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CALENDAR_QUARTER_NUMBER = 0;

    // ------------------------------------------------------------------------------------------- FISCAL_QUARTER_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_NUMBER} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_QUARTER_NUMBER = "FISCAL_QUARTER_NUMBER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_QUARTER_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_QUARTER_NUMBER = "fiscalQuarterNumber";

    /**
     * The precision of the {@value #COLUMN_NAME_FISCAL_QUARTER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_FISCAL_QUARTER_NUMBER = 1;

    /**
     * The scale of the {@value #COLUMN_NAME_FISCAL_QUARTER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_FISCAL_QUARTER_NUMBER = 0;

    // --------------------------------------------------------------------------------------------------- CALENDAR_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_YEAR = "CALENDAR_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_YEAR = "calendarYear";

    /**
     * The precision of the {@value #COLUMN_NAME_CALENDAR_YEAR} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CALENDAR_YEAR = 4;

    /**
     * The scale of the {@value #COLUMN_NAME_CALENDAR_YEAR} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CALENDAR_YEAR = 0;

    // ------------------------------------------------------------------------------------------------ CALENDAR_YEAR_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_YEAR_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_YEAR_ID = "CALENDAR_YEAR_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_YEAR_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_YEAR_ID = "calendarYearId";

    // ----------------------------------------------------------------------------------------------------- FISCAL_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_YEAR = "FISCAL_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_YEAR = "fiscalYear";

    /**
     * The precision of the {@value #COLUMN_NAME_FISCAL_YEAR} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_FISCAL_YEAR = 4;

    /**
     * The scale of the {@value #COLUMN_NAME_FISCAL_YEAR} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_FISCAL_YEAR = 0;

    // -------------------------------------------------------------------------------------------------- FISCAL_YEAR_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_YEAR_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_YEAR_ID = "FISCAL_YEAR_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_YEAR_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_YEAR_ID = "fiscalYearId";

    // ------------------------------------------------------------------------------------------------ DAYS_IN_CAL_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_CAL_YEAR = "DAYS_IN_CAL_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_CAL_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR = "daysInCalYear";

    // ------------------------------------------------------------------------------------------------ DAYS_IN_FIS_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_FIS_YEAR = "DAYS_IN_FIS_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_FIS_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR = "daysInFisYear";

    // ------------------------------------------------------------------------------------------------- END_OF_CAL_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_CAL_YEAR = "END_OF_CAL_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_CAL_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_CAL_YEAR = "endOfCalYear";

    // ------------------------------------------------------------------------------------------------- END_OF_FIS_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_FIS_YEAR = "END_OF_FIS_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_FIS_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_FIS_YEAR = "endOfFisYear";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedTime() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "timeId=" + timeId +
               ",dayName=" + dayName +
               ",dayNumberInWeek=" + dayNumberInWeek +
               ",dayNumberInMonth=" + dayNumberInMonth +
               ",calendarWeekNumber=" + calendarWeekNumber +
               ",fiscalWeekNumber=" + fiscalWeekNumber +
               ",weekEndingDay=" + weekEndingDay +
               ",weekEndingDayId=" + weekEndingDayId +
               ",calendarMonthNumber=" + calendarMonthNumber +
               ",fiscalMonthNumber=" + fiscalMonthNumber +
               ",calendarMonthDesc=" + calendarMonthDesc +
               ",calendarMonthId=" + calendarMonthId +
               ",fiscalMonthDesc=" + fiscalMonthDesc +
               ",fiscalMonthId=" + fiscalMonthId +
               ",daysInCalMonth=" + daysInCalMonth +
               ",daysInFisMonth=" + daysInFisMonth +
               ",endOfCalMonth=" + endOfCalMonth +
               ",endOfFisMonth=" + endOfFisMonth +
               ",calendarMonthName=" + calendarMonthName +
               ",fiscalMonthName=" + fiscalMonthName +
               ",calendarQuarterDesc=" + calendarQuarterDesc +
               ",calendarQuarterId=" + calendarQuarterId +
               ",fiscalQuarterDesc=" + fiscalQuarterDesc +
               ",fiscalQuarterId=" + fiscalQuarterId +
               ",daysInCalQuarter=" + daysInCalQuarter +
               ",daysInFisQuarter=" + daysInFisQuarter +
               ",endOfCalQuarter=" + endOfCalQuarter +
               ",endOfFisQuarter=" + endOfFisQuarter +
               ",calendarQuarterNumber=" + calendarQuarterNumber +
               ",fiscalQuarterNumber=" + fiscalQuarterNumber +
               ",calendarYear=" + calendarYear +
               ",calendarYearId=" + calendarYearId +
               ",fiscalYear=" + fiscalYear +
               ",fiscalYearId=" + fiscalYearId +
               ",daysInCalYear=" + daysInCalYear +
               ",daysInFisYear=" + daysInFisYear +
               ",endOfCalYear=" + endOfCalYear +
               ",endOfFisYear=" + endOfFisYear +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedTime that)) {
            return false;
        }
        return Objects.equals(getTimeId(), that.getTimeId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getTimeId());
    }

    // ---------------------------------------------------------------------------------------------------------- timeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public LocalDateTime getTimeId() {
        return timeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    protected void setTimeId(final LocalDateTime timeId) {
        this.timeId = timeId;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_TIME_ID} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute; {@code null} when the
     * attribute is {@code null}.
     * @see #getTimeId()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getTimeIdAsLocalDate() {
        return Optional.ofNullable(getTimeId())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the start of the specified local date.
     *
     * @param timeId the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code timeId} is before {@code 1582-10-15} or after {@code 9999-12-31}.
     * @see #setTimeId(LocalDateTime)
     * @see #getTimeIdAsLocalDate()
     */
    @Transient
    protected void setTimeIdFromLocalDate(final LocalDate timeId) {
        setTimeId(
                Optional.ofNullable(timeId)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    /**
     * Returns the start of the specified local date, after checking that an Oracle {@code DATE} stores it as the same
     * calendar date.
     * <p>
     * A {@link LocalDate} is in the proleptic Gregorian calendar, while an Oracle {@code DATE} ends at
     * {@code 9999-12-31} and holds dates before {@code 1582-10-15} in the Julian calendar. A date outside that range
     * would either be rejected by the database, or be stored as, and read back as, another date.
     *
     * @param date the local date to check.
     * @return the start of {@code date}.
     * @throws IllegalArgumentException if {@code date} is before {@code 1582-10-15} or after {@code 9999-12-31}.
     */
    private static LocalDateTime atStartOfStorableDay(final LocalDate date) {
        if (date.isBefore(LocalDate.of(1582, 10, 15)) || date.isAfter(LocalDate.of(9999, 12, 31))) {
            throw new IllegalArgumentException("date not storable as an Oracle DATE: " + date);
        }
        return date.atStartOfDay();
    }

    // --------------------------------------------------------------------------------------------------------- dayName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_NAME} attribute.
     */
    public String getDayName() {
        return dayName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_NAME} attribute with the specified value.
     *
     * @param dayName new value for {@value #ATTRIBUTE_NAME_DAY_NAME} attribute.
     */
    public void setDayName(final String dayName) {
        this.dayName = dayName;
    }

    // ------------------------------------------------------------------------------------------------- dayNumberInWeek

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute.
     */
    public Integer getDayNumberInWeek() {
        return dayNumberInWeek;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute with the specified value.
     *
     * @param dayNumberInWeek new value for {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute.
     */
    public void setDayNumberInWeek(final Integer dayNumberInWeek) {
        this.dayNumberInWeek = dayNumberInWeek;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute as a day-of-week.
     * <p>
     * This method assumes ISO-8601 numbering, from {@code 1} (Monday) to {@code 7} (Sunday). The type of the
     * {@value #COLUMN_NAME_DAY_NUMBER_IN_WEEK} column guarantees neither the range nor the numbering; a value numbered
     * from another day results in a wrong day, silently.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute as a day-of-week; {@code null}
     * when the attribute is {@code null}.
     * @throws java.time.DateTimeException if the value is not between {@code 1} and {@code 7}.
     * @see #getDayNumberInWeek()
     * @see DayOfWeek#of(int)
     */
    @Transient
    public DayOfWeek getDayNumberInWeekAsDayOfWeek() {
        return Optional.ofNullable(getDayNumberInWeek())
                .map(DayOfWeek::of)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_WEEK} attribute with the ISO-8601 number of the
     * specified day-of-week, from {@code 1} (Monday) to {@code 7} (Sunday).
     * <p>
     * The numbering is this method's; the type of the {@value #COLUMN_NAME_DAY_NUMBER_IN_WEEK} column does not define
     * one, and rows numbered from another day are not consistent with the value this method sets.
     *
     * @param dayNumberInWeek the day-of-week whose number is set; may be {@code null}.
     * @see #setDayNumberInWeek(Integer)
     * @see #getDayNumberInWeekAsDayOfWeek()
     * @see DayOfWeek#getValue()
     */
    @Transient
    public void setDayNumberInWeekFromDayOfWeek(final DayOfWeek dayNumberInWeek) {
        setDayNumberInWeek(
                Optional.ofNullable(dayNumberInWeek)
                        .map(DayOfWeek::getValue)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------ dayNumberInMonth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_MONTH} attribute.
     */
    public Integer getDayNumberInMonth() {
        return dayNumberInMonth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_MONTH} attribute with the specified value.
     *
     * @param dayNumberInMonth new value for {@value #ATTRIBUTE_NAME_DAY_NUMBER_IN_MONTH} attribute.
     */
    public void setDayNumberInMonth(final Integer dayNumberInMonth) {
        this.dayNumberInMonth = dayNumberInMonth;
    }

    // ---------------------------------------------------------------------------------------------- calendarWeekNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_WEEK_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_WEEK_NUMBER} attribute.
     */
    public Integer getCalendarWeekNumber() {
        return calendarWeekNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_WEEK_NUMBER} attribute with the specified value.
     *
     * @param calendarWeekNumber new value for {@value #ATTRIBUTE_NAME_CALENDAR_WEEK_NUMBER} attribute.
     */
    public void setCalendarWeekNumber(final Integer calendarWeekNumber) {
        this.calendarWeekNumber = calendarWeekNumber;
    }

    // ------------------------------------------------------------------------------------------------ fiscalWeekNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_WEEK_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_WEEK_NUMBER} attribute.
     */
    public Integer getFiscalWeekNumber() {
        return fiscalWeekNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_WEEK_NUMBER} attribute with the specified value.
     *
     * @param fiscalWeekNumber new value for {@value #ATTRIBUTE_NAME_FISCAL_WEEK_NUMBER} attribute.
     */
    public void setFiscalWeekNumber(final Integer fiscalWeekNumber) {
        this.fiscalWeekNumber = fiscalWeekNumber;
    }

    // --------------------------------------------------------------------------------------------------- weekEndingDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public LocalDateTime getWeekEndingDay() {
        return weekEndingDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute with the specified value.
     *
     * @param weekEndingDay new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public void setWeekEndingDay(final LocalDateTime weekEndingDay) {
        this.weekEndingDay = weekEndingDay;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_WEEK_ENDING_DAY} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute; {@code null}
     * when the attribute is {@code null}.
     * @see #getWeekEndingDay()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getWeekEndingDayAsLocalDate() {
        return Optional.ofNullable(getWeekEndingDay())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute with the start of the specified
     * local date.
     *
     * @param weekEndingDay the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code weekEndingDay} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setWeekEndingDay(LocalDateTime)
     * @see #getWeekEndingDayAsLocalDate()
     */
    @Transient
    public void setWeekEndingDayFromLocalDate(final LocalDate weekEndingDay) {
        setWeekEndingDay(
                Optional.ofNullable(weekEndingDay)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------- weekEndingDayId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID} attribute.
     */
    public Long getWeekEndingDayId() {
        return weekEndingDayId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID} attribute with the specified value.
     *
     * @param weekEndingDayId new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID} attribute.
     */
    public void setWeekEndingDayId(final Long weekEndingDayId) {
        this.weekEndingDayId = weekEndingDayId;
    }

    // --------------------------------------------------------------------------------------------- calendarMonthNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute.
     */
    public Integer getCalendarMonthNumber() {
        return calendarMonthNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute with the specified value.
     *
     * @param calendarMonthNumber new value for {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute.
     */
    public void setCalendarMonthNumber(final Integer calendarMonthNumber) {
        this.calendarMonthNumber = calendarMonthNumber;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute as a month-of-year.
     * <p>
     * The type of the {@value #COLUMN_NAME_CALENDAR_MONTH_NUMBER} column does not guarantee the range.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute as a month-of-year;
     * {@code null} when the attribute is {@code null}.
     * @throws java.time.DateTimeException if the value is not between {@code 1} and {@code 12}.
     * @see #getCalendarMonthNumber()
     * @see Month#of(int)
     */
    @Transient
    public Month getCalendarMonthNumberAsMonth() {
        return Optional.ofNullable(getCalendarMonthNumber())
                .map(Month::of)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NUMBER} attribute with the number of the
     * specified month-of-year, from {@code 1} (January) to {@code 12} (December).
     *
     * @param calendarMonthNumber the month-of-year whose number is set; may be {@code null}.
     * @see #setCalendarMonthNumber(Integer)
     * @see #getCalendarMonthNumberAsMonth()
     * @see Month#getValue()
     */
    @Transient
    public void setCalendarMonthNumberFromMonth(final Month calendarMonthNumber) {
        setCalendarMonthNumber(
                Optional.ofNullable(calendarMonthNumber)
                        .map(Month::getValue)
                        .orElse(null)
        );
    }

    // ----------------------------------------------------------------------------------------------- fiscalMonthNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NUMBER} attribute.
     */
    public Integer getFiscalMonthNumber() {
        return fiscalMonthNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NUMBER} attribute with the specified value.
     *
     * @param fiscalMonthNumber new value for {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NUMBER} attribute.
     */
    public void setFiscalMonthNumber(final Integer fiscalMonthNumber) {
        this.fiscalMonthNumber = fiscalMonthNumber;
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthDesc

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

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute as a year-month.
     * <p>
     * This method assumes the {@code yyyy-MM} format, e.g. {@code 2019-05}, which the type of the
     * {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column does not guarantee.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute as a year-month; {@code null}
     * when the attribute is {@code null}.
     * @throws java.time.format.DateTimeParseException if the value is not in the {@code yyyy-MM} format.
     * @see #getCalendarMonthDesc()
     * @see YearMonth#parse(CharSequence)
     */
    @Transient
    public YearMonth getCalendarMonthDescAsYearMonth() {
        return Optional.ofNullable(getCalendarMonthDesc())
                .map(YearMonth::parse)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute with the specified year-month,
     * in the {@code yyyy-MM} format.
     * <p>
     * A year outside {@code 0} to {@code 9999} prints in another format, e.g. {@code 10000-01} or {@code -0001-01},
     * which {@link #getCalendarMonthDescAsYearMonth()} could not parse back; such a year-month is rejected.
     *
     * @param calendarMonthDesc the year-month to set; may be {@code null}.
     * @throws IllegalArgumentException if the year of {@code calendarMonthDesc} is not between {@code 0} and
     *                                  {@code 9999}.
     * @see #setCalendarMonthDesc(String)
     * @see #getCalendarMonthDescAsYearMonth()
     * @see YearMonth#toString()
     */
    @Transient
    public void setCalendarMonthDescFromYearMonth(final YearMonth calendarMonthDesc) {
        setCalendarMonthDesc(
                Optional.ofNullable(calendarMonthDesc)
                        .map(v -> {
                            if (v.getYear() < 0 || v.getYear() > 9999) {
                                throw new IllegalArgumentException("year not between 0 and 9999: " + v);
                            }
                            return v.toString();
                        })
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------- calendarMonthId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_ID} attribute.
     */
    public Long getCalendarMonthId() {
        return calendarMonthId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_ID} attribute with the specified value.
     *
     * @param calendarMonthId new value for {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_ID} attribute.
     */
    public void setCalendarMonthId(final Long calendarMonthId) {
        this.calendarMonthId = calendarMonthId;
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute.
     */
    public String getFiscalMonthDesc() {
        return fiscalMonthDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute with the specified value.
     *
     * @param fiscalMonthDesc new value for {@value #ATTRIBUTE_NAME_FISCAL_MONTH_DESC} attribute.
     */
    public void setFiscalMonthDesc(final String fiscalMonthDesc) {
        this.fiscalMonthDesc = fiscalMonthDesc;
    }

    // --------------------------------------------------------------------------------------------------- fiscalMonthId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_ID} attribute.
     */
    public Long getFiscalMonthId() {
        return fiscalMonthId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_ID} attribute with the specified value.
     *
     * @param fiscalMonthId new value for {@value #ATTRIBUTE_NAME_FISCAL_MONTH_ID} attribute.
     */
    public void setFiscalMonthId(final Long fiscalMonthId) {
        this.fiscalMonthId = fiscalMonthId;
    }

    // -------------------------------------------------------------------------------------------------- daysInCalMonth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH} attribute.
     */
    public Long getDaysInCalMonth() {
        return daysInCalMonth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH} attribute with the specified value.
     *
     * @param daysInCalMonth new value for {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH} attribute.
     */
    public void setDaysInCalMonth(final Long daysInCalMonth) {
        this.daysInCalMonth = daysInCalMonth;
    }

    // -------------------------------------------------------------------------------------------------- daysInFisMonth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH} attribute.
     */
    public Long getDaysInFisMonth() {
        return daysInFisMonth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH} attribute with the specified value.
     *
     * @param daysInFisMonth new value for {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH} attribute.
     */
    public void setDaysInFisMonth(final Long daysInFisMonth) {
        this.daysInFisMonth = daysInFisMonth;
    }

    // --------------------------------------------------------------------------------------------------- endOfCalMonth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute.
     */
    public LocalDateTime getEndOfCalMonth() {
        return endOfCalMonth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute with the specified value.
     *
     * @param endOfCalMonth new value for {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute.
     */
    public void setEndOfCalMonth(final LocalDateTime endOfCalMonth) {
        this.endOfCalMonth = endOfCalMonth;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_END_OF_CAL_MONTH} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute; {@code null}
     * when the attribute is {@code null}.
     * @see #getEndOfCalMonth()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getEndOfCalMonthAsLocalDate() {
        return Optional.ofNullable(getEndOfCalMonth())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute with the start of the specified
     * local date.
     *
     * @param endOfCalMonth the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code endOfCalMonth} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setEndOfCalMonth(LocalDateTime)
     * @see #getEndOfCalMonthAsLocalDate()
     */
    @Transient
    public void setEndOfCalMonthFromLocalDate(final LocalDate endOfCalMonth) {
        setEndOfCalMonth(
                Optional.ofNullable(endOfCalMonth)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------- endOfFisMonth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute.
     */
    public LocalDateTime getEndOfFisMonth() {
        return endOfFisMonth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute with the specified value.
     *
     * @param endOfFisMonth new value for {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute.
     */
    public void setEndOfFisMonth(final LocalDateTime endOfFisMonth) {
        this.endOfFisMonth = endOfFisMonth;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_END_OF_FIS_MONTH} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute; {@code null}
     * when the attribute is {@code null}.
     * @see #getEndOfFisMonth()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getEndOfFisMonthAsLocalDate() {
        return Optional.ofNullable(getEndOfFisMonth())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute with the start of the specified
     * local date.
     *
     * @param endOfFisMonth the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code endOfFisMonth} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setEndOfFisMonth(LocalDateTime)
     * @see #getEndOfFisMonthAsLocalDate()
     */
    @Transient
    public void setEndOfFisMonthFromLocalDate(final LocalDate endOfFisMonth) {
        setEndOfFisMonth(
                Optional.ofNullable(endOfFisMonth)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // ----------------------------------------------------------------------------------------------- calendarMonthName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute.
     */
    public String getCalendarMonthName() {
        return calendarMonthName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute with the specified value.
     *
     * @param calendarMonthName new value for {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_NAME} attribute.
     */
    public void setCalendarMonthName(final String calendarMonthName) {
        this.calendarMonthName = calendarMonthName;
    }

    // ------------------------------------------------------------------------------------------------- fiscalMonthName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute.
     */
    public String getFiscalMonthName() {
        return fiscalMonthName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute with the specified value.
     *
     * @param fiscalMonthName new value for {@value #ATTRIBUTE_NAME_FISCAL_MONTH_NAME} attribute.
     */
    public void setFiscalMonthName(final String fiscalMonthName) {
        this.fiscalMonthName = fiscalMonthName;
    }

    // --------------------------------------------------------------------------------------------- calendarQuarterDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC} attribute.
     */
    public String getCalendarQuarterDesc() {
        return calendarQuarterDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC} attribute with the specified value.
     *
     * @param calendarQuarterDesc new value for {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_DESC} attribute.
     */
    public void setCalendarQuarterDesc(final String calendarQuarterDesc) {
        this.calendarQuarterDesc = calendarQuarterDesc;
    }

    // ----------------------------------------------------------------------------------------------- calendarQuarterId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_ID} attribute.
     */
    public Long getCalendarQuarterId() {
        return calendarQuarterId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_ID} attribute with the specified value.
     *
     * @param calendarQuarterId new value for {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_ID} attribute.
     */
    public void setCalendarQuarterId(final Long calendarQuarterId) {
        this.calendarQuarterId = calendarQuarterId;
    }

    // ----------------------------------------------------------------------------------------------- fiscalQuarterDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_DESC} attribute.
     */
    public String getFiscalQuarterDesc() {
        return fiscalQuarterDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_DESC} attribute with the specified value.
     *
     * @param fiscalQuarterDesc new value for {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_DESC} attribute.
     */
    public void setFiscalQuarterDesc(final String fiscalQuarterDesc) {
        this.fiscalQuarterDesc = fiscalQuarterDesc;
    }

    // ------------------------------------------------------------------------------------------------- fiscalQuarterId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_ID} attribute.
     */
    public Long getFiscalQuarterId() {
        return fiscalQuarterId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_ID} attribute with the specified value.
     *
     * @param fiscalQuarterId new value for {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_ID} attribute.
     */
    public void setFiscalQuarterId(final Long fiscalQuarterId) {
        this.fiscalQuarterId = fiscalQuarterId;
    }

    // ------------------------------------------------------------------------------------------------ daysInCalQuarter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_QUARTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_QUARTER} attribute.
     */
    public Long getDaysInCalQuarter() {
        return daysInCalQuarter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_QUARTER} attribute with the specified value.
     *
     * @param daysInCalQuarter new value for {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_QUARTER} attribute.
     */
    public void setDaysInCalQuarter(final Long daysInCalQuarter) {
        this.daysInCalQuarter = daysInCalQuarter;
    }

    // ------------------------------------------------------------------------------------------------ daysInFisQuarter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_QUARTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_QUARTER} attribute.
     */
    public Long getDaysInFisQuarter() {
        return daysInFisQuarter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_QUARTER} attribute with the specified value.
     *
     * @param daysInFisQuarter new value for {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_QUARTER} attribute.
     */
    public void setDaysInFisQuarter(final Long daysInFisQuarter) {
        this.daysInFisQuarter = daysInFisQuarter;
    }

    // ------------------------------------------------------------------------------------------------- endOfCalQuarter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute.
     */
    public LocalDateTime getEndOfCalQuarter() {
        return endOfCalQuarter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute with the specified value.
     *
     * @param endOfCalQuarter new value for {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute.
     */
    public void setEndOfCalQuarter(final LocalDateTime endOfCalQuarter) {
        this.endOfCalQuarter = endOfCalQuarter;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_END_OF_CAL_QUARTER} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute;
     * {@code null} when the attribute is {@code null}.
     * @see #getEndOfCalQuarter()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getEndOfCalQuarterAsLocalDate() {
        return Optional.ofNullable(getEndOfCalQuarter())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute with the start of the specified
     * local date.
     *
     * @param endOfCalQuarter the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code endOfCalQuarter} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setEndOfCalQuarter(LocalDateTime)
     * @see #getEndOfCalQuarterAsLocalDate()
     */
    @Transient
    public void setEndOfCalQuarterFromLocalDate(final LocalDate endOfCalQuarter) {
        setEndOfCalQuarter(
                Optional.ofNullable(endOfCalQuarter)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------- endOfFisQuarter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute.
     */
    public LocalDateTime getEndOfFisQuarter() {
        return endOfFisQuarter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute with the specified value.
     *
     * @param endOfFisQuarter new value for {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute.
     */
    public void setEndOfFisQuarter(final LocalDateTime endOfFisQuarter) {
        this.endOfFisQuarter = endOfFisQuarter;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_END_OF_FIS_QUARTER} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute;
     * {@code null} when the attribute is {@code null}.
     * @see #getEndOfFisQuarter()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getEndOfFisQuarterAsLocalDate() {
        return Optional.ofNullable(getEndOfFisQuarter())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute with the start of the specified
     * local date.
     *
     * @param endOfFisQuarter the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code endOfFisQuarter} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setEndOfFisQuarter(LocalDateTime)
     * @see #getEndOfFisQuarterAsLocalDate()
     */
    @Transient
    public void setEndOfFisQuarterFromLocalDate(final LocalDate endOfFisQuarter) {
        setEndOfFisQuarter(
                Optional.ofNullable(endOfFisQuarter)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------- calendarQuarterNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_NUMBER} attribute.
     */
    public Integer getCalendarQuarterNumber() {
        return calendarQuarterNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_NUMBER} attribute with the specified value.
     *
     * @param calendarQuarterNumber new value for {@value #ATTRIBUTE_NAME_CALENDAR_QUARTER_NUMBER} attribute.
     */
    public void setCalendarQuarterNumber(final Integer calendarQuarterNumber) {
        this.calendarQuarterNumber = calendarQuarterNumber;
    }

    // --------------------------------------------------------------------------------------------- fiscalQuarterNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_NUMBER} attribute.
     */
    public Integer getFiscalQuarterNumber() {
        return fiscalQuarterNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_NUMBER} attribute with the specified value.
     *
     * @param fiscalQuarterNumber new value for {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_NUMBER} attribute.
     */
    public void setFiscalQuarterNumber(final Integer fiscalQuarterNumber) {
        this.fiscalQuarterNumber = fiscalQuarterNumber;
    }

    // ---------------------------------------------------------------------------------------------------- calendarYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute.
     */
    public Integer getCalendarYear() {
        return calendarYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute with the specified value.
     *
     * @param calendarYear new value for {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute.
     */
    public void setCalendarYear(final Integer calendarYear) {
        this.calendarYear = calendarYear;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute as a year.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute as a year; {@code null} when the
     * attribute is {@code null}.
     * @see #getCalendarYear()
     * @see Year#of(int)
     */
    @Transient
    public Year getCalendarYearAsYear() {
        return Optional.ofNullable(getCalendarYear())
                .map(Year::of)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR} attribute with the value of the specified year.
     *
     * @param calendarYear the year whose value is set; may be {@code null}.
     * @throws IllegalArgumentException if the value of {@code calendarYear} has more than
     *                                  {@value #COLUMN_PRECISION_CALENDAR_YEAR} digits.
     * @see #setCalendarYear(Integer)
     * @see #getCalendarYearAsYear()
     * @see Year#getValue()
     */
    @Transient
    public void setCalendarYearFromYear(final Year calendarYear) {
        setCalendarYear(
                Optional.ofNullable(calendarYear)
                        .map(Year::getValue)
                        .map(v -> {
                            if (Math.abs(v) > 9999) {
                                throw new IllegalArgumentException(
                                        "more than " + COLUMN_PRECISION_CALENDAR_YEAR + " digits: " + v);
                            }
                            return v;
                        })
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------------------------- calendarYearId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR_ID} attribute.
     */
    public Long getCalendarYearId() {
        return calendarYearId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_YEAR_ID} attribute with the specified value.
     *
     * @param calendarYearId new value for {@value #ATTRIBUTE_NAME_CALENDAR_YEAR_ID} attribute.
     */
    public void setCalendarYearId(final Long calendarYearId) {
        this.calendarYearId = calendarYearId;
    }

    // ------------------------------------------------------------------------------------------------------ fiscalYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_YEAR} attribute.
     */
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_YEAR} attribute with the specified value.
     *
     * @param fiscalYear new value for {@value #ATTRIBUTE_NAME_FISCAL_YEAR} attribute.
     */
    public void setFiscalYear(final Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    // ---------------------------------------------------------------------------------------------------- fiscalYearId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FISCAL_YEAR_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FISCAL_YEAR_ID} attribute.
     */
    public Long getFiscalYearId() {
        return fiscalYearId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FISCAL_YEAR_ID} attribute with the specified value.
     *
     * @param fiscalYearId new value for {@value #ATTRIBUTE_NAME_FISCAL_YEAR_ID} attribute.
     */
    public void setFiscalYearId(final Long fiscalYearId) {
        this.fiscalYearId = fiscalYearId;
    }

    // --------------------------------------------------------------------------------------------------- daysInCalYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR} attribute.
     */
    public Long getDaysInCalYear() {
        return daysInCalYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR} attribute with the specified value.
     *
     * @param daysInCalYear new value for {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR} attribute.
     */
    public void setDaysInCalYear(final Long daysInCalYear) {
        this.daysInCalYear = daysInCalYear;
    }

    // --------------------------------------------------------------------------------------------------- daysInFisYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR} attribute.
     */
    public Long getDaysInFisYear() {
        return daysInFisYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR} attribute with the specified value.
     *
     * @param daysInFisYear new value for {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR} attribute.
     */
    public void setDaysInFisYear(final Long daysInFisYear) {
        this.daysInFisYear = daysInFisYear;
    }

    // ---------------------------------------------------------------------------------------------------- endOfCalYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute.
     */
    public LocalDateTime getEndOfCalYear() {
        return endOfCalYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute with the specified value.
     *
     * @param endOfCalYear new value for {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute.
     */
    public void setEndOfCalYear(final LocalDateTime endOfCalYear) {
        this.endOfCalYear = endOfCalYear;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_END_OF_CAL_YEAR} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute; {@code null}
     * when the attribute is {@code null}.
     * @see #getEndOfCalYear()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getEndOfCalYearAsLocalDate() {
        return Optional.ofNullable(getEndOfCalYear())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute with the start of the specified
     * local date.
     *
     * @param endOfCalYear the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code endOfCalYear} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setEndOfCalYear(LocalDateTime)
     * @see #getEndOfCalYearAsLocalDate()
     */
    @Transient
    public void setEndOfCalYearFromLocalDate(final LocalDate endOfCalYear) {
        setEndOfCalYear(
                Optional.ofNullable(endOfCalYear)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- endOfFisYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute.
     */
    public LocalDateTime getEndOfFisYear() {
        return endOfFisYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute with the specified value.
     *
     * @param endOfFisYear new value for {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute.
     */
    public void setEndOfFisYear(final LocalDateTime endOfFisYear) {
        this.endOfFisYear = endOfFisYear;
    }

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute as a local date.
     * <p>
     * The {@value #COLUMN_NAME_END_OF_FIS_YEAR} column is a {@code DATE}, which carries a time of day; this method
     * <em>discards</em> it. That is lossless only while the column holds midnight, which its type does not guarantee.
     *
     * @return the local date part of current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute; {@code null}
     * when the attribute is {@code null}.
     * @see #getEndOfFisYear()
     * @see LocalDateTime#toLocalDate()
     */
    @Transient
    public LocalDate getEndOfFisYearAsLocalDate() {
        return Optional.ofNullable(getEndOfFisYear())
                .map(LocalDateTime::toLocalDate)
                .orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute with the start of the specified
     * local date.
     *
     * @param endOfFisYear the local date whose start is set; may be {@code null}.
     * @throws IllegalArgumentException if {@code endOfFisYear} is before {@code 1582-10-15} or after
     *                                  {@code 9999-12-31}.
     * @see #setEndOfFisYear(LocalDateTime)
     * @see #getEndOfFisYearAsLocalDate()
     */
    @Transient
    public void setEndOfFisYearFromLocalDate(final LocalDate endOfFisYear) {
        setEndOfFisYear(
                Optional.ofNullable(endOfFisYear)
                        .map(MappedTime::atStartOfStorableDay)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    @Size(max = SIZE_MAX_DAY_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAY_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DAY_NAME
    )
    private String dayName;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_DAY_NUMBER_IN_WEEK - COLUMN_SCALE_DAY_NUMBER_IN_WEEK,
            fraction = COLUMN_SCALE_DAY_NUMBER_IN_WEEK)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAY_NUMBER_IN_WEEK,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer dayNumberInWeek;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_DAY_NUMBER_IN_MONTH - COLUMN_SCALE_DAY_NUMBER_IN_MONTH,
            fraction = COLUMN_SCALE_DAY_NUMBER_IN_MONTH)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAY_NUMBER_IN_MONTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer dayNumberInMonth;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_CALENDAR_WEEK_NUMBER - COLUMN_SCALE_CALENDAR_WEEK_NUMBER,
            fraction = COLUMN_SCALE_CALENDAR_WEEK_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_WEEK_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer calendarWeekNumber;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_FISCAL_WEEK_NUMBER - COLUMN_SCALE_FISCAL_WEEK_NUMBER,
            fraction = COLUMN_SCALE_FISCAL_WEEK_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_WEEK_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer fiscalWeekNumber;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WEEK_ENDING_DAY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime weekEndingDay;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WEEK_ENDING_DAY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long weekEndingDayId;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_CALENDAR_MONTH_NUMBER - COLUMN_SCALE_CALENDAR_MONTH_NUMBER,
            fraction = COLUMN_SCALE_CALENDAR_MONTH_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_MONTH_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer calendarMonthNumber;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_FISCAL_MONTH_NUMBER - COLUMN_SCALE_FISCAL_MONTH_NUMBER,
            fraction = COLUMN_SCALE_FISCAL_MONTH_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_MONTH_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer fiscalMonthNumber;

    @Size(max = SIZE_MAX_CALENDAR_MONTH_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_MONTH_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CALENDAR_MONTH_DESC
    )
    private String calendarMonthDesc;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_MONTH_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long calendarMonthId;

    @Size(max = SIZE_MAX_FISCAL_MONTH_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_MONTH_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FISCAL_MONTH_DESC
    )
    private String fiscalMonthDesc;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_MONTH_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long fiscalMonthId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_IN_CAL_MONTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long daysInCalMonth;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_IN_FIS_MONTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long daysInFisMonth;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_CAL_MONTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endOfCalMonth;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_FIS_MONTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endOfFisMonth;

    @Size(max = SIZE_MAX_CALENDAR_MONTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_MONTH_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CALENDAR_MONTH_NAME
    )
    private String calendarMonthName;

    @Size(max = SIZE_MAX_FISCAL_MONTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_MONTH_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FISCAL_MONTH_NAME
    )
    private String fiscalMonthName;

    @Size(max = SIZE_MAX_CALENDAR_QUARTER_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_QUARTER_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CALENDAR_QUARTER_DESC
    )
    private String calendarQuarterDesc;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_QUARTER_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long calendarQuarterId;

    @Size(max = SIZE_MAX_FISCAL_QUARTER_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_QUARTER_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FISCAL_QUARTER_DESC
    )
    private String fiscalQuarterDesc;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_QUARTER_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long fiscalQuarterId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_IN_CAL_QUARTER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long daysInCalQuarter;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_IN_FIS_QUARTER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long daysInFisQuarter;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_CAL_QUARTER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endOfCalQuarter;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_FIS_QUARTER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endOfFisQuarter;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_CALENDAR_QUARTER_NUMBER - COLUMN_SCALE_CALENDAR_QUARTER_NUMBER,
            fraction = COLUMN_SCALE_CALENDAR_QUARTER_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_QUARTER_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer calendarQuarterNumber;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_FISCAL_QUARTER_NUMBER - COLUMN_SCALE_FISCAL_QUARTER_NUMBER,
            fraction = COLUMN_SCALE_FISCAL_QUARTER_NUMBER)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_QUARTER_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer fiscalQuarterNumber;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_CALENDAR_YEAR - COLUMN_SCALE_CALENDAR_YEAR,
            fraction = COLUMN_SCALE_CALENDAR_YEAR)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer calendarYear;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_YEAR_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long calendarYearId;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_FISCAL_YEAR - COLUMN_SCALE_FISCAL_YEAR, fraction = COLUMN_SCALE_FISCAL_YEAR)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer fiscalYear;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FISCAL_YEAR_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long fiscalYearId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_IN_CAL_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long daysInCalYear;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_IN_FIS_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long daysInFisYear;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_CAL_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endOfCalYear;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_FIS_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endOfFisYear;
}
