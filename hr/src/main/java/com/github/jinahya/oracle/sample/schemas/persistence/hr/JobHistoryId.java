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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHr;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A composite primary key class for mapping {@value MappedJobHistory#COLUMN_NAME_EMPLOYEE_ID} column and
 * {@value MappedJobHistory#COLUMN_NAME_START_DATE} column, of {@value MappedJobHistory#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedJobHistory
 * @see <a
 * href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#composite-primary-keys">2.4.1.
 * Composite primary keys</a> (Jakarta Persistence 3.2 Specification Document)
 */
@Embeddable
public class JobHistoryId extends _MappedHr {

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    /**
     * Returns a builder for building instances of this id class.
     *
     * @return a builder for building instances of this id class.
     */
    public static JobHistoryIdBuilder builder() {
        return new JobHistoryIdBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryId() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder from which a new instance is built.
     */
    JobHistoryId(@Nonnull final JobHistoryIdBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "employeeId=" + employeeId +
               ",startDate=" + startDate +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof JobHistoryId that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value JobHistoryId_#EMPLOYEE_ID} attribute.
     *
     * @return current value of the {@value JobHistoryId_#EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value JobHistoryId_#EMPLOYEE_ID} attribute with specified value.
     *
     * @param employeeId new value for the {@value JobHistoryId_#EMPLOYEE_ID} attribute.
     */
    protected void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    protected void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Max(MappedJobHistory.ATTRIBUTE_MAX_EMPLOYEE_ID)
    @Min(MappedJobHistory.ATTRIBUTE_MIN_EMPLOYEE_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
            nullable = MappedJobHistory.COLUMN_NULLABLE_EMPLOYEE_ID,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false,
            precision = MappedJobHistory.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    @PastOrPresent
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = MappedJobHistory.COLUMN_NAME_START_DATE,
            nullable = MappedJobHistory.COLUMN_NULLABLE_START_DATE,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private LocalDate startDate;
}
