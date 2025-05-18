package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

@Entity
@Table(name = Job.TABLE_NAME)
public class Job extends __MappedEntity<Job, String> {

    @Serial
    private static final long serialVersionUID = -3918037798759237986L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "JOBS";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // ------------------------------------------------------------------------------------------------------- JOB_TITLE

    // ------------------------------------------------------------------------------------------------------ MIN_SALARY
    public static final String COLUMN_NAME_MIN_SALARY = "MIN_SALARY";

    public static final int COLUMN_PRECISION_MIN_SALARY = 6;

    public static final int COLUMN_VALUE_MIN_MIN_SALARY = 0x000000;

    public static final int COLUMN_VALUE_MAX_MIN_SALARY = 0x0F423F; // 999999

    public static final int MIN_MIN_SALARY = COLUMN_VALUE_MIN_MIN_SALARY;

    public static final int MAX_MIN_SALARY = COLUMN_VALUE_MAX_MIN_SALARY;

    // ------------------------------------------------------------------------------------------------------ MAX_SALARY
    public static final String COLUMN_NAME_MAX_SALARY = "MAX_SALARY";

    public static final int COLUMN_PRECISION_MAX_SALARY = 6;

    public static final int COLUMN_VALUE_MIN_MAX_SALARY = 0x000000;

    public static final int COLUMN_VALUE_MAX_MAX_SALARY = 0x0F423F; // 999999

    public static final int MIN_MAX_SALARY = COLUMN_VALUE_MIN_MAX_SALARY;

    public static final int MAX_MAX_SALARY = COLUMN_VALUE_MAX_MAX_SALARY;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Job() {
        super();
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

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final String _id_() {
        return getJobId();
    }

    @Override
    protected final void _id_(final String _id_) {
        setJobId(_id_);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    @AssertTrue(message = "the minSalary should be less than or equals to the maxSalary")
    private boolean isMinSalaryIsLessThanMaxSalary() {
        return minSalary == null || maxSalary == null || (minSalary <= maxSalary);
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    public String getJobId() {
        return jobId;
    }

    public void setJobId(final String jobId) {
        this.jobId = jobId;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary
    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(final Integer minSalary) {
        this.minSalary = minSalary;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(final Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    @Size(max = SIZE_MAX_JOB_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_JOB_ID)
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    @Size(max = 35)
    @NotNull
    @Column(name = "JOB_TITLE", nullable = false, length = 35)
    private String jobTitle;

    @jakarta.annotation.Nullable
    @Max(MAX_MIN_SALARY)
    @Min(MIN_MIN_SALARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIN_SALARY, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_MIN_SALARY)
    private Integer minSalary;

    @Max(MAX_MAX_SALARY)
    @Min(MIN_MAX_SALARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MAX_SALARY, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_MAX_SALARY)
    private Integer maxSalary;
}