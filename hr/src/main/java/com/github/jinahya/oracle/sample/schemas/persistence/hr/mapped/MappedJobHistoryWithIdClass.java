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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * An abstract mapped-superclass, maps the {@value MappedJobHistory#TABLE_NAME} table, uses {@link JobHistoryId} as its
 * {@link IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedJobHistoryWithIdClass extends MappedJobHistory {

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    public static final long ATTRIBUTE_MIN_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    public static final long ATTRIBUTE_MAX_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ START_DATE
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // -------------------------------------------------------------------------------------------------------- END_DATE

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    // -----------------------------------------------------------------------------------------------------------------
    protected static <T extends MappedJobHistoryWithIdClass> Comparator<T> comparingStartDate() {
        return comparingStartDate(MappedJobHistoryWithIdClass::getStartDate);
    }

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobHistoryWithIdClass() {
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

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistoryWithIdClass that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation
    @AssertTrue
    protected boolean isEndDateAfterStartDate() {
        if (endDate == null) {
            return true;
        }
        if (startDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

    // --------------------------------------------------------------------------------------------------- super.endDate

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value MappedJobHistoryWithIdClass_#EMPLOYEE_ID} attribute.
     *
     * @return current value of the {@value MappedJobHistoryWithIdClass_#EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value MappedJobHistoryWithIdClass_#START_DATE} attribute.
     *
     * @return current value of the {@value MappedJobHistoryWithIdClass_#START_DATE} attribute.
     */
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    void setStartDate(@Nonnull final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(MappedJobHistoryWithIdClass.ATTRIBUTE_MAX_EMPLOYEE_ID)
    @Min(MappedJobHistoryWithIdClass.ATTRIBUTE_MIN_EMPLOYEE_ID)
    @NotNull
    @Id // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Column(name = MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // fuck eclipselink
            updatable = false,
            precision = MappedJobHistory.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @PastOrPresent
    @NotNull
    @Id // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Column(name = MappedJobHistory.COLUMN_NAME_START_DATE,
            nullable = false,
//            insertable = false,
            insertable = true, // fuck eclipselink
            updatable = false
    )
    private LocalDate startDate;
}
