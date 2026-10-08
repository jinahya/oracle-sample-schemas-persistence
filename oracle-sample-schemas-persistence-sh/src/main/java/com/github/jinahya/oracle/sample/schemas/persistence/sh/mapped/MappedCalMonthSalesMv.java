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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedCalMonthSalesMv#TABLE_NAME} materialized view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCalMonthSalesMv {

    /**
     * The name of the database materialized view to which this class maps. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALENDAR_MONTH_DESC} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALENDAR_MONTH_DESC = "calendarMonthDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CALENDAR_MONTH_DESC = COLUMN_LENGTH_CALENDAR_MONTH_DESC;

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
    protected MappedCalMonthSalesMv() {
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
        if (!(obj instanceof MappedCalMonthSalesMv that)) {
            return false;
        }
        return Objects.equals(getCalendarMonthDesc(), that.getCalendarMonthDesc());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getCalendarMonthDesc());
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
    protected void setCalendarMonthDesc(final String calendarMonthDesc) {
        this.calendarMonthDesc = calendarMonthDesc;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute with the specified value.
     *
     * @param dollars new value for {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public void setDollars(final BigDecimal dollars) {
        this.dollars = dollars;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Size(max = SIZE_MAX_CALENDAR_MONTH_DESC)
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_CALENDAR_MONTH_DESC,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_CALENDAR_MONTH_DESC)
    private String calendarMonthDesc;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOLLARS, nullable = true, insertable = false, updatable = false)
    private BigDecimal dollars;
}
