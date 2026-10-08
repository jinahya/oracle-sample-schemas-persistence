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
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Comparator;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedJob#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedJob implements __MappedDomainEntity<String> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
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

    /**
     * A comparator which compares the {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute, in
     * {@link Comparator#naturalOrder() natural order}, {@link Comparator#nullsFirst(Comparator) nulls first}.
     */
    public static final Comparator<MappedJob> COMPARATOR_MIN_SALARY_NATURAL_NULLS_FIRST =
            Comparator.comparing(
                    MappedJob::getMinSalary,
                    Comparator.nullsFirst(Comparator.naturalOrder())
            );

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
     * A comparator which compares the {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute, in
     * {@link Comparator#reverseOrder() reverse order}, {@link Comparator#nullsLast(Comparator) nulls last}.
     */
    public static final Comparator<MappedJob> COMPARATOR_MAX_SALARY_REVERSE_NULLS_LAST =
            Comparator.comparing(
                    MappedJob::getMaxSalary,
                    Comparator.nullsLast(Comparator.reverseOrder())
            );

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJob() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "jobId=" + jobId +
               ",jobTitle=" + jobTitle +
               ",minSalary=" + minSalary +
               ",maxSalary=" + maxSalary +
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
        if (!(obj instanceof MappedJob that)) {
            return false;
        }
        return Objects.equals(getJobId(), that.getJobId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getJobId());
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
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
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
    @Nullable
    public Integer getMinSalary() {
        return minSalary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute with the specified value.
     *
     * @param minSalary new value for {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute.
     */
    public void setMinSalary(@Nullable final Integer minSalary) {
        this.minSalary = minSalary;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     */
    @Nullable
    public Integer getMaxSalary() {
        return maxSalary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute with the specified value.
     *
     * @param maxSalary new value for {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute.
     */
    public void setMaxSalary(@Nullable final Integer maxSalary) {
        this.maxSalary = maxSalary;
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

    @Nullable
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

    @Nullable
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
}
