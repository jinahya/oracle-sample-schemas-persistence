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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public abstract class MappedJob extends _MappedHrEntity<String> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "JOBS";

    // -------------------------------------------------------------------------------------------------- JOB_ID / jobId
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    public static final int ATTRIBUTE_SIZE_MIN_JOB_ID = 0;

    public static final int ATTRIBUTE_SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // -------------------------------------------------------------------------------------------- JOB_TITLE / jobTitle
    public static final String COLUMN_NAME_JOB_TITLE = "JOB_TITLE";

    public static final int COLUMN_LENGTH_JOB_TITLE = 35;

    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    public static final int ATTRIBUTE_SIZE_MIN_JOB_TITLE = 0;

    public static final int ATTRIBUTE_SIZE_MAX_JOB_TITLE = COLUMN_LENGTH_JOB_TITLE;

    // ------------------------------------------------------------------------------------------ MIN_SALARY / minSalary
    public static final String COLUMN_NAME_MIN_SALARY = "MIN_SALARY";

    public static final int COLUMN_PRECISION_MIN_SALARY = 6;

    public static final int COLUMN_SCALE_MIN_SALARY = 0;

    public static final int COLUMN_MIN_MIN_SALARY = -999999;

    public static final int COLUMN_MAX_MIN_SALARY = +999999;

    public static final String ATTRIBUTE_NAME_MIN_SALARY = "minSalary";

    public static final int ATTRIBUTE_MIN_MIN_SALARY = COLUMN_MIN_MIN_SALARY;

    public static final int ATTRIBUTE_MAX_MIN_SALARY = COLUMN_MAX_MIN_SALARY;

    // ------------------------------------------------------------------------------------------ MAX_SALARY / maxSalary
    public static final String COLUMN_NAME_MAX_SALARY = "MAX_SALARY";

    public static final int COLUMN_PRECISION_MAX_SALARY = 6;

    public static final int COLUMN_SCALE_MAX_SALARY = 0;

    public static final int COLUMN_VALUE_MIN_MAX_SALARY = -999999;

    public static final int COLUMN_VALUE_MAX_MAX_SALARY = +999999;

    public static final String ATTRIBUTE_NAME_MAX_SALARY = "maxSalary";

    public static final int ATTRIBUTE_MIN_MAX_SALARY = COLUMN_VALUE_MIN_MAX_SALARY;

    public static final int ATTRIBUTE_MAX_MAX_SALARY = COLUMN_VALUE_MAX_MAX_SALARY;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJob() {
        super();
    }

    protected MappedJob(final MappedJobBuilder<?, ?> builder) {
        super();
        jobId = builder.jobId();
        jobTitle = builder.jobTitle();
        minSalary = builder.minSalary();
        maxSalary = builder.maxSalary();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "jobId='" + jobId +
               ",jobTitle='" + jobTitle +
               ",minSalary=" + minSalary +
               ",maxSalary=" + maxSalary +
               '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof MappedJob that)) {
            return false;
        }
        return Objects.equals(getJobId(), that.getJobId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getJobId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    protected boolean isMinSalaryPositive() {
        if (minSalary == null) {
            return true;
        }
        return minSalary > 0;
    }

    protected boolean isMaxSalaryPositive() {
        if (maxSalary == null) {
            return true;
        }
        return maxSalary > 0;
    }

    protected boolean isMinSalaryIsLessThanOrEqualToMaxSalary() {
        if (minSalary == null) {
            return true;
        }
        if (maxSalary == null) {
            return true;
        }
        return minSalary <= maxSalary;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    public void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    @Nonnull
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(@Nonnull final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary
    @Nullable
    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(@Nullable final Integer minSalary) {
        this.minSalary = minSalary;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    @Nullable
    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(@Nullable final Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_ID, max = ATTRIBUTE_SIZE_MAX_JOB_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_JOB_ID)
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_TITLE, max = ATTRIBUTE_SIZE_MAX_JOB_TITLE)
    @NotNull
    @Column(name = COLUMN_NAME_JOB_TITLE, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_JOB_TITLE)
    private String jobTitle;

    @Nullable
    @Max(ATTRIBUTE_MAX_MIN_SALARY)
    @Min(ATTRIBUTE_MIN_MIN_SALARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIN_SALARY, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_MIN_SALARY, scale = COLUMN_SCALE_MIN_SALARY)
    private Integer minSalary;

    @Nullable
    @Max(ATTRIBUTE_MAX_MAX_SALARY)
    @Min(ATTRIBUTE_MIN_MAX_SALARY)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_MAX_SALARY, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_MAX_SALARY, scale = COLUMN_SCALE_MAX_SALARY)
    private Integer maxSalary;
}
