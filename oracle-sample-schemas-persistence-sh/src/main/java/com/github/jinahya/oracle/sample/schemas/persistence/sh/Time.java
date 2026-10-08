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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Time#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Time.TABLE_NAME)
public class Time implements __DomainEntity<LocalDateTime> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "TIMES";

    // -------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // ------------------------------------------------------------------------------------------------------- DAY_NAME

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

    // --------------------------------------------------------------------------------------------- DAY_NUMBER_IN_WEEK

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

    // -------------------------------------------------------------------------------------------- DAY_NUMBER_IN_MONTH

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

    // ------------------------------------------------------------------------------------------- CALENDAR_WEEK_NUMBER

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

    // --------------------------------------------------------------------------------------------- FISCAL_WEEK_NUMBER

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

    // ------------------------------------------------------------------------------------------------ WEEK_ENDING_DAY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY = "WEEK_ENDING_DAY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    // --------------------------------------------------------------------------------------------- WEEK_ENDING_DAY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY_ID = "WEEK_ENDING_DAY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY_ID = "weekEndingDayId";

    // ------------------------------------------------------------------------------------------ CALENDAR_MONTH_NUMBER

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

    // -------------------------------------------------------------------------------------------- FISCAL_MONTH_NUMBER

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

    // -------------------------------------------------------------------------------------------- CALENDAR_MONTH_DESC

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

    // ---------------------------------------------------------------------------------------------- CALENDAR_MONTH_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_ID = "CALENDAR_MONTH_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_ID = "calendarMonthId";

    // ---------------------------------------------------------------------------------------------- FISCAL_MONTH_DESC

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

    // ------------------------------------------------------------------------------------------------ FISCAL_MONTH_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_MONTH_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_MONTH_ID = "FISCAL_MONTH_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_MONTH_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_MONTH_ID = "fiscalMonthId";

    // ---------------------------------------------------------------------------------------------- DAYS_IN_CAL_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_CAL_MONTH = "DAYS_IN_CAL_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_CAL_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_CAL_MONTH = "daysInCalMonth";

    // ---------------------------------------------------------------------------------------------- DAYS_IN_FIS_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_FIS_MONTH = "DAYS_IN_FIS_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_FIS_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_FIS_MONTH = "daysInFisMonth";

    // ----------------------------------------------------------------------------------------------- END_OF_CAL_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_CAL_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_CAL_MONTH = "END_OF_CAL_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_CAL_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_CAL_MONTH = "endOfCalMonth";

    // ----------------------------------------------------------------------------------------------- END_OF_FIS_MONTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_FIS_MONTH} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_FIS_MONTH = "END_OF_FIS_MONTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_FIS_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_FIS_MONTH = "endOfFisMonth";

    // -------------------------------------------------------------------------------------------- CALENDAR_MONTH_NAME

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

    // ---------------------------------------------------------------------------------------------- FISCAL_MONTH_NAME

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

    // ------------------------------------------------------------------------------------------ CALENDAR_QUARTER_DESC

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

    // -------------------------------------------------------------------------------------------- CALENDAR_QUARTER_ID

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

    // -------------------------------------------------------------------------------------------- FISCAL_QUARTER_DESC

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

    // ---------------------------------------------------------------------------------------------- FISCAL_QUARTER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_QUARTER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_QUARTER_ID = "FISCAL_QUARTER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_QUARTER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_QUARTER_ID = "fiscalQuarterId";

    // -------------------------------------------------------------------------------------------- DAYS_IN_CAL_QUARTER

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

    // -------------------------------------------------------------------------------------------- DAYS_IN_FIS_QUARTER

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

    // --------------------------------------------------------------------------------------------- END_OF_CAL_QUARTER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_CAL_QUARTER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_END_OF_CAL_QUARTER = "END_OF_CAL_QUARTER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_CAL_QUARTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_CAL_QUARTER = "endOfCalQuarter";

    // --------------------------------------------------------------------------------------------- END_OF_FIS_QUARTER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_FIS_QUARTER} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_END_OF_FIS_QUARTER = "END_OF_FIS_QUARTER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_FIS_QUARTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_FIS_QUARTER = "endOfFisQuarter";

    // ---------------------------------------------------------------------------------------- CALENDAR_QUARTER_NUMBER

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

    // ------------------------------------------------------------------------------------------ FISCAL_QUARTER_NUMBER

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

    // -------------------------------------------------------------------------------------------------- CALENDAR_YEAR

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

    // ----------------------------------------------------------------------------------------------- CALENDAR_YEAR_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CALENDAR_YEAR_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_YEAR_ID = "CALENDAR_YEAR_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_YEAR_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_YEAR_ID = "calendarYearId";

    // ---------------------------------------------------------------------------------------------------- FISCAL_YEAR

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

    // ------------------------------------------------------------------------------------------------- FISCAL_YEAR_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FISCAL_YEAR_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FISCAL_YEAR_ID = "FISCAL_YEAR_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FISCAL_YEAR_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FISCAL_YEAR_ID = "fiscalYearId";

    // ----------------------------------------------------------------------------------------------- DAYS_IN_CAL_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_CAL_YEAR = "DAYS_IN_CAL_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_CAL_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_CAL_YEAR = "daysInCalYear";

    // ----------------------------------------------------------------------------------------------- DAYS_IN_FIS_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DAYS_IN_FIS_YEAR = "DAYS_IN_FIS_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_IN_FIS_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_IN_FIS_YEAR = "daysInFisYear";

    // ------------------------------------------------------------------------------------------------ END_OF_CAL_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_CAL_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_CAL_YEAR = "END_OF_CAL_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_CAL_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_CAL_YEAR = "endOfCalYear";

    // ------------------------------------------------------------------------------------------------ END_OF_FIS_YEAR

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_OF_FIS_YEAR} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_OF_FIS_YEAR = "END_OF_FIS_YEAR";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_FIS_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_FIS_YEAR = "endOfFisYear";

    // --------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Time() {
        super();
    }

    // ----------------------------------------------------------------------------------------------- java.lang.Object

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
        if (!(obj instanceof Time that)) {
            return false;
        }
        return Objects.equals(timeId, that.timeId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(timeId);
    }
    // --------------------------------------------------------------------------------------------------------- timeId

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
    public void setTimeId(final LocalDateTime timeId) {
        this.timeId = timeId;
    }

    // -------------------------------------------------------------------------------------------------------- dayName

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

    // ------------------------------------------------------------------------------------------------ dayNumberInWeek

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

    // ----------------------------------------------------------------------------------------------- dayNumberInMonth

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

    // --------------------------------------------------------------------------------------------- calendarWeekNumber

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

    // ----------------------------------------------------------------------------------------------- fiscalWeekNumber

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

    // -------------------------------------------------------------------------------------------------- weekEndingDay

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

    // ------------------------------------------------------------------------------------------------ weekEndingDayId

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

    // -------------------------------------------------------------------------------------------- calendarMonthNumber

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

    // ---------------------------------------------------------------------------------------------- fiscalMonthNumber

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

    // ---------------------------------------------------------------------------------------------- calendarMonthDesc

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

    // ------------------------------------------------------------------------------------------------ calendarMonthId

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

    // ------------------------------------------------------------------------------------------------ fiscalMonthDesc

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

    // -------------------------------------------------------------------------------------------------- fiscalMonthId

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

    // ------------------------------------------------------------------------------------------------- daysInCalMonth

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

    // ------------------------------------------------------------------------------------------------- daysInFisMonth

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

    // -------------------------------------------------------------------------------------------------- endOfCalMonth

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

    // -------------------------------------------------------------------------------------------------- endOfFisMonth

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

    // ---------------------------------------------------------------------------------------------- calendarMonthName

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

    // ------------------------------------------------------------------------------------------------ fiscalMonthName

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

    // -------------------------------------------------------------------------------------------- calendarQuarterDesc

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

    // ---------------------------------------------------------------------------------------------- calendarQuarterId

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

    // ---------------------------------------------------------------------------------------------- fiscalQuarterDesc

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

    // ------------------------------------------------------------------------------------------------ fiscalQuarterId

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

    // ----------------------------------------------------------------------------------------------- daysInCalQuarter

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

    // ----------------------------------------------------------------------------------------------- daysInFisQuarter

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

    // ------------------------------------------------------------------------------------------------ endOfCalQuarter

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

    // ------------------------------------------------------------------------------------------------ endOfFisQuarter

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

    // ------------------------------------------------------------------------------------------ calendarQuarterNumber

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

    // -------------------------------------------------------------------------------------------- fiscalQuarterNumber

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

    // --------------------------------------------------------------------------------------------------- calendarYear

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

    // ------------------------------------------------------------------------------------------------- calendarYearId

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

    // ----------------------------------------------------------------------------------------------------- fiscalYear

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

    // --------------------------------------------------------------------------------------------------- fiscalYearId

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

    // -------------------------------------------------------------------------------------------------- daysInCalYear

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

    // -------------------------------------------------------------------------------------------------- daysInFisYear

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

    // --------------------------------------------------------------------------------------------------- endOfCalYear

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

    // --------------------------------------------------------------------------------------------------- endOfFisYear

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

    // ---------------------------------------------------------------------------------------------------------------

    @Id
    @Column(name = COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    // -----------------------------------------------------------------------------------------------------------------
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
