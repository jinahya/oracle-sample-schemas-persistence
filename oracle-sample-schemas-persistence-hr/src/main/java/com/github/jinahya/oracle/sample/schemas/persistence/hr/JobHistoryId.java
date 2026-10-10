package com.github.jinahya.oracle.sample.schemas.persistence.hr;

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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A composite primary key class for mapping {@value JobHistory#COLUMN_NAME_EMPLOYEE_ID} column and
 * {@value JobHistory#COLUMN_NAME_START_DATE} column, of {@value JobHistory#TABLE_NAME} table; the
 * {@link jakarta.persistence.IdClass @IdClass} of {@link JobHistory}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JobHistory
 * @see <a
 * href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#composite-primary-keys">2.4.1.
 * Composite primary keys</a> (Jakarta Persistence 3.2 Specification Document)
 */
@Embeddable
public class JobHistoryId {

    /**
     * The name of the attribute which maps the {@value JobHistory#COLUMN_NAME_EMPLOYEE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    /**
     * The name of the attribute which maps the {@value JobHistory#COLUMN_NAME_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "employeeId=" + employeeId +
               ",startDate=" + startDate +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by both {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} and {@value #ATTRIBUTE_NAME_START_DATE}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof JobHistoryId that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over both {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} and {@value #ATTRIBUTE_NAME_START_DATE},
     * consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified value.
     *
     * @param employeeId new value for the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    // TODO: remove if it's not used anymore
    void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getStartDate() {
        return startDate;
    }

    // TODO: remove if it's not used anymore
    void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Max(JobHistory.ATTRIBUTE_MAX_EMPLOYEE_ID)
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Min(JobHistory.ATTRIBUTE_MIN_EMPLOYEE_ID)
    @Digits(integer = JobHistory.COLUMN_PRECISION_EMPLOYEE_ID, fraction = 0)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = JobHistory.COLUMN_NAME_EMPLOYEE_ID,
            nullable = JobHistory.COLUMN_NULLABLE_EMPLOYEE_ID,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false,
            precision = JobHistory.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = JobHistory.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // TODO: remove; not constrained by the DDL -- JOB_HISTORY.START_DATE is DATE with no check
//    @PastOrPresent
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = JobHistory.COLUMN_NAME_START_DATE,
            nullable = JobHistory.COLUMN_NULLABLE_START_DATE,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private LocalDateTime startDate;
}
