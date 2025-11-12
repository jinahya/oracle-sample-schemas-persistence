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
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

/**
 * An abstract mapped-superclass for mapping the {@value MappedJobHistory#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedJobHistory extends _MappedHrEntity<JobHistoryId> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    // ------------------------------------------------------------------------------------------------------ START_DATE

    // -------------------------------------------------------------------------------------------------------- END_DATE
    public static final String COLUMN_NAME_END_DATE = "END_DATE";

    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    static {
        assert COLUMN_LENGTH_JOB_ID == MappedJob.COLUMN_LENGTH_JOB_ID;
    }

    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    public static final int ATTRIBUTE_SIZE_MIN_JOB_ID = 0;

    public static final int ATTRIBUTE_SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    public static final String ATTRIBUTE_NAME_JOB = "job";

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    public static final int COLUMN_SCALE_DEPARTMENT_ID = 0;

    public static final int COLUMN_MIN_DEPARTMENT_ID = -9999;

    public static final int COLUMN_MAX_DEPARTMENT_ID = +9999;

    static {
        assert COLUMN_PRECISION_DEPARTMENT_ID == MappedDepartment.COLUMN_PRECISION_DEPARTMENT_ID;
        assert COLUMN_SCALE_DEPARTMENT_ID == MappedDepartment.COLUMN_SCALE_DEPARTMENT_ID;
        assert COLUMN_MIN_DEPARTMENT_ID == MappedDepartment.COLUMN_MIN_DEPARTMENT_ID;
        assert COLUMN_MAX_DEPARTMENT_ID == MappedDepartment.COLUMN_MAX_DEPARTMENT_ID;
    }

    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    public static final long ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    public static final long ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    MappedJobHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "endDate=" + endDate +
               ",jobId=" + jobId +
               ",departmentId=" + departmentId +
               '}';
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation
    @AssertTrue
    protected boolean isStartDateBeforeEndDate() {
        final var startDate = getStartDate();
        if (startDate == null) {
            return true;
        }
        if (endDate == null) {
            return true;
        }
        return startDate.isBefore(endDate);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistory that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of the attribute maps to the {@value JobHistoryId#COLUMN_NAME_EMPLOYEE_ID} column.
     *
     * @return current value of the attribute maps to the {@value JobHistoryId#COLUMN_NAME_EMPLOYEE_ID} column.
     */
    public abstract Integer getEmployeeId();

    /**
     * Returns current value of the attribute maps to the {@value JobHistoryId#COLUMN_NAME_START_DATE} column.
     *
     * @return current value of the attribute maps to the {@value JobHistoryId#COLUMN_NAME_START_DATE} column.
     */
    public abstract LocalDate getStartDate();

    // -------------------------------------------------------------------------------------------------------------- id
    @Nonnull
    public JobHistoryId getId() {
        return JobHistoryId.builder()
                .employeeId(getEmployeeId())
                .startDate(getStartDate())
                .build();
    }

//    @Deprecated(forRemoval = true)
//    abstract void setId(@Nonnull final JobHistoryId id);

    // --------------------------------------------------------------------------------------------------------- endDate
    @Nonnull
    public LocalDate getEndDate() {
        return endDate;
    }

    @Deprecated(forRemoval = true)
    protected void setEndDate(@Nonnull final LocalDate endDate) {
        this.endDate = endDate;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    @Deprecated(forRemoval = true)
    protected void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Nullable
    public Integer getDepartmentId() {
        return departmentId;
    }

    @Deprecated(forRemoval = true)
    protected void setDepartmentId(@Nullable final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
//    @PastOrPresent // @@?
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_END_DATE, nullable = false, insertable = false, updatable = false)
    private LocalDate endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_ID, max = ATTRIBUTE_SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = false, updatable = false,
            length = COLUMN_LENGTH_JOB_ID)
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false,
            precision = COLUMN_PRECISION_DEPARTMENT_ID, scale = COLUMN_SCALE_DEPARTMENT_ID)
    private Integer departmentId;
}
