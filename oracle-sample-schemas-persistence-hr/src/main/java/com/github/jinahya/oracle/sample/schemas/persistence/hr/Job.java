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
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Job#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Job.Select__OrderByMaxSalaryDescNullsLast",
            query = """
                    SELECT e
                    FROM Job e
                    ORDER BY e.maxSalary DESC NULLS LAST"""
)
@NamedQuery(name = "Job.Select__OrderByMinSalaryAscNullsFirst",
            query = """
                    SELECT e
                    FROM Job e
                    ORDER BY e.minSalary ASC NULLS FIRST"""
)
@Entity
@Table(name = Job.TABLE_NAME)
public class Job implements __DomainEntity<String> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOBS";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_JOB_ID = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // ------------------------------------------------------------------------------------------------------- JOB_TITLE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_JOB_TITLE = "JOB_TITLE";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_TITLE = 35;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_JOB_TITLE = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_TITLE = COLUMN_LENGTH_JOB_TITLE;

    // ------------------------------------------------------------------------------------------------------ MIN_SALARY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MIN_SALARY = "MIN_SALARY";

    /**
     * The precision of the {@value #COLUMN_NAME_MIN_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MIN_SALARY = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_MIN_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MIN_SALARY = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_MIN_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_MIN_SALARY = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MIN_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MIN_SALARY = +999999;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIN_SALARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIN_SALARY = "minSalary";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_MIN_SALARY = COLUMN_MIN_MIN_SALARY;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_MIN_SALARY = COLUMN_MAX_MIN_SALARY;

    // ------------------------------------------------------------------------------------------------------ MAX_SALARY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MAX_SALARY = "MAX_SALARY";

    /**
     * The precision of the {@value #COLUMN_NAME_MAX_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MAX_SALARY = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_MAX_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MAX_SALARY = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_MAX_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_MAX_SALARY = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MAX_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MAX_SALARY = +999999;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAX_SALARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAX_SALARY = "maxSalary";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_MAX_SALARY = COLUMN_MIN_MAX_SALARY;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_MAX_SALARY = COLUMN_MAX_MAX_SALARY;

    /**
     * The name of the attribute which maps the {@link Employee employee}s assigned to this job, mapped by
     * {@value Employee#ATTRIBUTE_NAME_JOB}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEES = "employees";

    /**
     * The name of the attribute which maps the past assignments to this job. The value is {@value}.
     *
     * @see JobHistory#ATTRIBUTE_NAME_JOB
     */
    public static final String ATTRIBUTE_NAME_JOB_HISTORIES = "jobHistories";

    // ----------------------------------------------------------------------------------------------------- COMPARATORS

    /**
     * A comparator which compares the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute, in
     * {@link Comparator#naturalOrder() natural order}, {@link Comparator#nullsFirst(Comparator) nulls first}.
     */
    public static final Comparator<Job> COMPARATOR_MIN_SALARY_NATURAL_NULLS_FIRST =
            Comparator.comparing(
                    Job::getMinSalary,
                    Comparator.nullsFirst(Comparator.naturalOrder())
            );

    /**
     * A comparator which compares the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute, in
     * {@link Comparator#reverseOrder() reverse order}, {@link Comparator#nullsLast(Comparator) nulls last}.
     */
    public static final Comparator<Job> COMPARATOR_MAX_SALARY_REVERSE_NULLS_LAST =
            Comparator.comparing(
                    Job::getMaxSalary,
                    Comparator.nullsLast(Comparator.reverseOrder())
            );

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
               "jobId=" + jobId +
               ",jobTitle=" + jobTitle +
               ",minSalary=" + minSalary +
               ",maxSalary=" + maxSalary +
//               ",employees=" + employees +
//               ",jobHistories=" + jobHistories +
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
        if (!(obj instanceof Job that)) return false;
        return Objects.equals(jobId, that.jobId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(jobId);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Tests whether current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute is non-negative.
     * <p>
     * Evaluates to {@code true} when the attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute is non-negative;
     * {@code false} otherwise.
     */
    protected boolean isMinSalaryNonNegative() {
        if (minSalary == null) {
            return true;
        }
        return minSalary >= 0;
    }

    /**
     * Tests whether current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute is non-negative.
     * <p>
     * Evaluates to {@code true} when the attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute is non-negative;
     * {@code false} otherwise.
     */
    protected boolean isMaxSalaryNonNegative() {
        if (maxSalary == null) {
            return true;
        }
        return maxSalary >= 0;
    }

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute is positive.
     * <p>
     * Evaluates to {@code true} when the attribute is {@code null}.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute is positive; {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isMinSalaryPositive() {
        if (minSalary == null) {
            return true;
        }
        return minSalary > 0;
    }

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute is positive.
     * <p>
     * Evaluates to {@code true} when the attribute is {@code null}.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute is positive; {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isMaxSalaryPositive() {
        if (maxSalary == null) {
            return true;
        }
        return maxSalary > 0;
    }

    /**
     * Indicates whether the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute is less than or equal to the
     * {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     * <p>
     * Evaluates to {@code true} when either attribute is {@code null}.
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute is less than or equal to the
     * {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute; {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isMinSalaryLessThanOrEqualToMaxSalary() {
        if (minSalary == null || maxSalary == null) {
            return true;
        }
        return minSalary <= maxSalary;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute
     */
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute with the specified value.
     *
     * @param jobId new value for {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     */
    protected void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    @Nonnull
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute with the specified value.
     *
     * @param jobTitle new value for {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public void setJobTitle(@Nonnull final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute.
     */
    @jakarta.annotation.Nullable
    public Integer getMinSalary() {
        return minSalary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute with the specified value.
     *
     * @param minSalary new value for {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute.
     */
    public void setMinSalary(@jakarta.annotation.Nullable final Integer minSalary) {
        this.minSalary = minSalary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute with the specified value, and raises
     * current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute to it when it is less, so that
     * {@link #isMinSalaryLessThanOrEqualToMaxSalary()} holds.
     *
     * @param minSalary new value for {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute; should be between
     *                  {@value #ATTRIBUTE_MIN_MIN_SALARY} and {@value #ATTRIBUTE_MAX_MIN_SALARY}.
     * @throws IllegalArgumentException if {@code minSalary} is not {@code null} and out of that range.
     * @deprecated for removal.
     */
    @Deprecated(forRemoval = true)
    public void setMinSalaryWhileAdjustingMaxSalary(@jakarta.annotation.Nullable final Integer minSalary) {
        if (minSalary != null && (minSalary < ATTRIBUTE_MIN_MIN_SALARY || minSalary > ATTRIBUTE_MAX_MIN_SALARY)) {
            throw new IllegalArgumentException(
                    "minSalary(" + minSalary + ") is out of [" + ATTRIBUTE_MIN_MIN_SALARY + ".."
                    + ATTRIBUTE_MAX_MIN_SALARY + "]");
        }
        setMinSalary(minSalary);
        {
            final var currentMaxSalary = getMaxSalary();
            final var currentMinSalary = getMinSalary();
            if (currentMaxSalary != null && currentMinSalary != null && currentMaxSalary < currentMinSalary) {
                setMaxSalary(currentMinSalary);
            }
        }
        {
            final var currentMaxSalary = getMaxSalary();
            assert currentMaxSalary == null
                   || (currentMaxSalary >= ATTRIBUTE_MIN_MIN_SALARY && currentMaxSalary <= ATTRIBUTE_MAX_MIN_SALARY);
        }
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     */
    @jakarta.annotation.Nullable
    public Integer getMaxSalary() {
        return maxSalary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute with the specified value.
     *
     * @param maxSalary new value for {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     */
    public void setMaxSalary(@jakarta.annotation.Nullable final Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute with the specified value, and adjusts
     * current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute, intending that
     * {@link #isMinSalaryLessThanOrEqualToMaxSalary()} holds.
     *
     * @param maxSalary new value for {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute; should be between
     *                  {@value #ATTRIBUTE_MIN_MAX_SALARY} and {@value #ATTRIBUTE_MAX_MAX_SALARY}.
     * @throws IllegalArgumentException if {@code maxSalary} is not {@code null} and out of that range.
     * @deprecated for removal.
     */
    @Deprecated(forRemoval = true)
    public void setMaxSalaryWhileAdjustingMinSalary(@jakarta.annotation.Nullable final Integer maxSalary) {
        if (maxSalary != null && (maxSalary < ATTRIBUTE_MAX_MAX_SALARY || maxSalary > ATTRIBUTE_MIN_MAX_SALARY)) {
            throw new IllegalArgumentException(
                    "maxSalary(" + maxSalary + ") is out of [" + ATTRIBUTE_MAX_MAX_SALARY + ".."
                    + ATTRIBUTE_MIN_MAX_SALARY + "]");
        }
        setMaxSalary(maxSalary);
        {
            final var currentMinSalary = getMinSalary();
            final var currentMaxSalary = getMaxSalary();
            if (currentMinSalary != null && currentMaxSalary != null && currentMinSalary < currentMaxSalary) {
                setMinSalary(currentMaxSalary);
            }
        }
        {
            final var currentMinSalary = getMinSalary();
            assert currentMinSalary == null
                   || (currentMinSalary >= ATTRIBUTE_MAX_MAX_SALARY && currentMinSalary <= ATTRIBUTE_MIN_MAX_SALARY);
        }
    }

    // ------------------------------------------------------------------------------------------------------- employees

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEES} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_EMPLOYEES} attribute.
     */
    List<Employee> getEmployees() {
        return employees;
    }

    void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    // ---------------------------------------------------------------------------------------------------- jobHistories

    /**
     * Returns the past assignments to this job.
     *
     * @return the past assignments to this job.
     */
    List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    /**
     * Replaces the past assignments to this job.
     *
     * @param jobHistories new past assignments to this job.
     */
    void setJobHistories(final List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Size(min = SIZE_MIN_JOB_ID, max = SIZE_MAX_JOB_ID)
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
    @Size(min = SIZE_MIN_JOB_TITLE, max = SIZE_MAX_JOB_TITLE)
    @NotNull
    @Column(name = COLUMN_NAME_JOB_TITLE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_TITLE
    )
    private String jobTitle;

    @jakarta.annotation.Nullable
    @Max(ATTRIBUTE_MAX_MIN_SALARY)
    @Min(ATTRIBUTE_MIN_MIN_SALARY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIN_SALARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MIN_SALARY,
            scale = COLUMN_SCALE_MIN_SALARY
    )
    private Integer minSalary;

    @jakarta.annotation.Nullable
    @Max(ATTRIBUTE_MAX_MAX_SALARY)
    @Min(ATTRIBUTE_MIN_MAX_SALARY)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_MAX_SALARY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MAX_SALARY,
            scale = COLUMN_SCALE_MAX_SALARY
    )
    private Integer maxSalary;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_JOB,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> employees;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = JobHistory.ATTRIBUTE_NAME_JOB,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull JobHistory> jobHistories;
}
