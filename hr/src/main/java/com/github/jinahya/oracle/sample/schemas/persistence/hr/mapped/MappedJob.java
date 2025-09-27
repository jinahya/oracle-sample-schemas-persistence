package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
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

    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // -------------------------------------------------------------------------------------------- JOB_TITLE / jobTitle
    public static final String COLUMN_NAME_JOB_TITLE = "JOB_TITLE";

    public static final int COLUMN_LENGTH_JOB_TITLE = 35;

    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    public static final int SIZE_MAX_JOB_TITLE = COLUMN_LENGTH_JOB_TITLE;

    // ------------------------------------------------------------------------------------------ MIN_SALARY / minSalary
    public static final String COLUMN_NAME_MIN_SALARY = "MIN_SALARY";

    public static final int COLUMN_PRECISION_MIN_SALARY = 6;

    public static final int COLUMN_MIN_MIN_SALARY = 0x000000;

    public static final int COLUMN_MAX_MIN_SALARY = 0x0F423F; // 999999

    public static final String ATTRIBUTE_NAME_MIN_SALARY = "minSalary";

    public static final int MIN_MIN_SALARY = COLUMN_MIN_MIN_SALARY;

    public static final int MAX_MIN_SALARY = COLUMN_MAX_MIN_SALARY;

    // ------------------------------------------------------------------------------------------ MAX_SALARY / maxSalary
    public static final String COLUMN_NAME_MAX_SALARY = "MAX_SALARY";

    public static final int COLUMN_PRECISION_MAX_SALARY = 6;

    public static final int COLUMN_VALUE_MIN_MAX_SALARY = 0x000000;

    public static final int COLUMN_VALUE_MAX_MAX_SALARY = 0x0F423F; // 999999

    public static final String ATTRIBUTE_NAME_MAX_SALARY = "maxSalary";

    public static final int MIN_MAX_SALARY = COLUMN_VALUE_MIN_MAX_SALARY;

    public static final int MAX_MAX_SALARY = COLUMN_VALUE_MAX_MAX_SALARY;

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
//    @AssertTrue(message = "the minSalary should be less than or equals to the maxSalary")
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
    @Size(max = SIZE_MAX_JOB_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = SIZE_MAX_JOB_TITLE)
    @NotNull
    @Column(name = COLUMN_NAME_JOB_TITLE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_TITLE
    )
    private String jobTitle;

    @Nullable
    @Max(MAX_MIN_SALARY)
    @Min(MIN_MIN_SALARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIN_SALARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MIN_SALARY
    )
    private Integer minSalary;

    @Nullable
    @Max(MAX_MAX_SALARY)
    @Min(MIN_MAX_SALARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MAX_SALARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MAX_SALARY
    )
    private Integer maxSalary;
}
