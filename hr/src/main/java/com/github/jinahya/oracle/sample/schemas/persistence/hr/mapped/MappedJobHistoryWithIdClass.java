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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * An abstract mapped-superclass, maps the {@value MappedJobHistory#TABLE_NAME} table, uses {@link JobHistoryId} as its
 * {@link IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@IdClass(JobHistoryId.class)
@MappedSuperclass
public abstract class MappedJobHistoryWithIdClass extends MappedJobHistory {

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = JobHistoryId.ATTRIBUTE_NAME_EMPLOYEE_ID;

    public static final String ATTRIBUTE_NAME_EMPLOYEE = "employee";

    // ------------------------------------------------------------------------------------------------------ START_DATE
    public static final String ATTRIBUTE_NAME_START_DATE = JobHistoryId.ATTRIBUTE_NAME_START_DATE;

    // -------------------------------------------------------------------------------------------------------- END_DATE

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
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
    public boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistoryWithIdClass that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // -------------------------------------------------------------------------------------------------------- super.id
    @Override
    public JobHistoryId getId() {
        return super.getId();
    }

    @Deprecated(forRemoval = true)
    @Override
    protected void setId(final JobHistoryId id) {
        setEmployeeId(
                Optional.ofNullable(id)
                        .map(JobHistoryId::getEmployeeId)
                        .orElse(null)
        );
        setStartDate(
                Optional.ofNullable(id)
                        .map(JobHistoryId::getStartDate)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------- super.endDate

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // ------------------------------------------------------------------------------------------------------ employeeId
    @Nonnull
    @Override
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    @Nonnull
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nonnull final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(JobHistoryId.ATTRIBUTE_MAX_EMPLOYEE_ID)
    @Min(JobHistoryId.ATTRIBUTE_MIN_EMPLOYEE_ID)
    @NotNull
    @Id // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Column(name = JobHistoryId.COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = JobHistoryId.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = JobHistoryId.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @PastOrPresent
    @NotNull
    @Id // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Column(name = JobHistoryId.COLUMN_NAME_START_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate startDate;
}
