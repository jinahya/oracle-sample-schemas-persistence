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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;

/**
 * An entity class for mapping the {@value CalMonthSalesMv#TABLE_NAME} materialized view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "CalMonthSalesMv.selectOneByCalendarMonthDesc",
            query = """
                    SELECT e
                    FROM CalMonthSalesMv e
                    WHERE e.calendarMonthDesc = :calendarMonthDesc"""
)
@NamedQuery(name = "CalMonthSalesMv.selectListOrderByCalendarMonthDescAsc",
            query = """
                    SELECT e
                    FROM CalMonthSalesMv e
                    ORDER BY e.calendarMonthDesc ASC"""
)
@NamedQuery(name = "CalMonthSalesMv.selectListOrderByCalendarMonthDescAscCalendarMonthDescGt",
            query = """
                    SELECT e
                    FROM CalMonthSalesMv e
                    WHERE e.calendarMonthDesc > :calendarMonthDescMinExclusive
                    ORDER BY e.calendarMonthDesc ASC"""
)
@NamedQuery(name = "CalMonthSalesMv.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM CalMonthSalesMv e"""
)
@Entity
@Table(name = CalMonthSalesMv.TABLE_NAME)
public class CalMonthSalesMv {

    /**
     * The name of the database materialized view to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CAL_MONTH_SALES_MV";

    // --------------------------------------------------------------------------------------------- CALENDAR_MONTH_DESC

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CALENDAR_MONTH_DESC = "CALENDAR_MONTH_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CALENDAR_MONTH_DESC = 8;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CALENDAR_MONTH_DESC = COLUMN_LENGTH_CALENDAR_MONTH_DESC;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_DESC = "calendarMonthDesc";

    // --------------------------------------------------------------------------------------------------------- DOLLARS

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_DOLLARS} attribute maps. The value
     * is {@value}.
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

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof CalMonthSalesMv that)) {
            return false;
        }
        return Objects.equals(calendarMonthDesc, that.calendarMonthDesc);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(calendarMonthDesc);
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

    // --------------------------------------------------------------------------------------------------------- dollars

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public BigDecimal getDollars() {
        return dollars;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The calendar month this row aggregates, as the {@code uuuu-MM} text {@code TIMES.CALENDAR_MONTH_DESC} holds --
     * {@code 2019-01}, for example -- which is also the {@link java.time.YearMonth#parse(CharSequence) ISO form} of
     * {@link java.time.YearMonth}.
     * <p>
     * The attribute is nonetheless kept a {@code String}, not a {@code YearMonth} behind an
     * {@link jakarta.persistence.AttributeConverter}, because it is the {@link Id @Id} of this entity, and the
     * specification excludes identifier attributes from attribute conversion:
     * <blockquote>
     * The conversion of all basic types is supported except for the following: Id attributes (including the attributes
     * of embedded ids and derived identities), version attributes, relationship attributes, and attributes explicitly
     * annotated as Enumerated or Temporal or designated as such in the XML descriptor. Auto-apply converters will not
     * be applied to such attributes, and applications that apply converters to such attributes through use of the
     * Convert annotation will not be portable.
     * </blockquote>
     *
     * @see <a href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2.html#a2999">3.9.
     * Type Conversion of Basic Attributes</a> (Jakarta Persistence 3.2 Specification Document)
     * @see <a
     * href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2.html#a14398">11.1.10.
     * Convert Annotation</a> (Jakarta Persistence 3.2 Specification Document)
     * @see <a href="https://github.com/oracle-samples/db-sample-schemas/blob/main/sales_history/sh_create.sql">
     * {@code sh_create.sql}</a> ({@code COMMENT ON COLUMN times.calendar_month_desc IS 'e.g. 1998-01, CORRECT ORDER'})
     */
    @Size(max = SIZE_MAX_CALENDAR_MONTH_DESC)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALENDAR_MONTH_DESC,
            nullable = false,
            insertable = true, // eclipselink
            updatable = false,
            length = COLUMN_LENGTH_CALENDAR_MONTH_DESC)
    private String calendarMonthDesc;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOLLARS, nullable = true, insertable = false, updatable = false)
    private BigDecimal dollars;
}
