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

import java.util.Comparator;
import java.util.Objects;

/**
 * An abstract mapped-superclass for mapping the {@value MappedJob#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedJob extends _MappedHrEntity<String> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOBS";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the table column to which the {@value MappedJob_#JOB_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = 10;

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_JOB_ID} column maps. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    /**
     * The value for the {@link Size#min()} of the {@value MappedJob_#JOB_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_SIZE_MIN_JOB_ID = 0;

    /**
     * The value for the {@link Size#max()} of the {@value MappedJob_#JOB_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // ------------------------------------------------------------------------------------------------------- JOB_TITLE
    public static final String COLUMN_NAME_JOB_TITLE = "JOB_TITLE";

    public static final int COLUMN_LENGTH_JOB_TITLE = 35;

    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    public static final int ATTRIBUTE_SIZE_MIN_JOB_TITLE = 0;

    public static final int ATTRIBUTE_SIZE_MAX_JOB_TITLE = COLUMN_LENGTH_JOB_TITLE;

    // ------------------------------------------------------------------------------------------------------ MIN_SALARY

    /**
     * The name of the table column to which the {@value MappedJob_#MIN_SALARY} attribute maps. The value is {@value}.
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

    public static final int COLUMN_MIN_MIN_SALARY = -999999;

    public static final int COLUMN_MAX_MIN_SALARY = +999999;

    public static final String ATTRIBUTE_NAME_MIN_SALARY = "minSalary";

    public static final int ATTRIBUTE_MIN_MIN_SALARY = COLUMN_MIN_MIN_SALARY;

    public static final int ATTRIBUTE_MAX_MIN_SALARY = COLUMN_MAX_MIN_SALARY;

    // ------------------------------------------------------------------------------------------------------ MAX_SALARY
    public static final String COLUMN_NAME_MAX_SALARY = "MAX_SALARY";

    public static final int COLUMN_PRECISION_MAX_SALARY = 6;

    public static final int COLUMN_SCALE_MAX_SALARY = 0;

    public static final int COLUMN_MIN_MAX_SALARY = -999999;

    public static final int COLUMN_MAX_MAX_SALARY = +999999;

    public static final String ATTRIBUTE_NAME_MAX_SALARY = "maxSalary";

    public static final int ATTRIBUTE_MIN_MAX_SALARY = COLUMN_MIN_MAX_SALARY;

    public static final int ATTRIBUTE_MAX_MAX_SALARY = COLUMN_MAX_MAX_SALARY;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the plural attribute, of {@link MappedEmployee employee}s, mapped by
     * {@value MappedEmployee#ATTRIBUTE_NAME_JOB}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEES = "employees";

    // ----------------------------------------------------------------------------------------------------- COMPARATORS

    /**
     * A comparator compares {@value MappedJob_#MIN_SALARY} attribute, in
     * {@link Comparator#naturalOrder() natural order}, {@link Comparator#nullsFirst(Comparator) nulls first}.
     */
    public static final Comparator<MappedJob> COMPARATOR_MIN_SALARY_NATURAL_NULLS_FIRST =
            Comparator.comparing(
                    MappedJob::getMinSalary,
                    Comparator.nullsFirst(Comparator.naturalOrder())
            );

    /**
     * A comparator compares {@value MappedJob_#MAX_SALARY} attribute, in
     * {@link Comparator#reverseOrder() reverse order}, {@link Comparator#nullsLast(Comparator) nulls last}.
     */
    public static final Comparator<MappedJob> COMPARATOR_MAX_SALARY_REVERSE_NULLS_LAST =
            Comparator.comparing(
                    MappedJob::getMaxSalary,
                    Comparator.nullsLast(Comparator.reverseOrder())
            );

    // --------------------------------------------------------------------------------------------------------- BUILDER

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJob() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected MappedJob(final _MappedHrEntityBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "jobId=" + jobId +
               ",jobTitle=" + jobTitle +
               ",minSalary=" + minSalary +
               ",maxSalary=" + maxSalary +
               '}';
    }

    protected final boolean equalsWithJobId(final Object obj) {
        if (!(obj instanceof MappedJob that)) return false;
        return Objects.equals(jobId, that.jobId);
    }

    protected final int hashCodeWithJobId() {
        return Objects.hashCode(jobId);
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    /**
     * Tests whether current value of {@value MappedJob_#MIN_SALARY} attribute is non-negative.
     * <p>
     * Evaluates to {@code} when the attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value MappedJob_#MIN_SALARY} attribute is non-negative;
     * {@code false} otherwise.
     */
    protected boolean isMinSalaryNonNegative() {
        if (minSalary == null) {
            return true;
        }
        return minSalary <= 0;
    }

    /**
     * Tests whether current value of {@value MappedJob_#MIN_SALARY} attribute (is {@code null} or) is positive.
     * <p>
     * Evaluates to {@code true} when the attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value MappedJob_#MIN_SALARY} attribute is positive;
     * {@code false} otherwise.
     */
    protected boolean isMinSalaryPositive() {
        if (minSalary == null) {
            return true;
        }
        return minSalary > 0;
    }

    /**
     * Tests whether current value of {@value MappedJob_#MAX_SALARY} attribute is non-negative.
     * <p>
     * Evaluates to {@code} when the attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value MappedJob_#MAX_SALARY} attribute is non-negative;
     * {@code false} otherwise.
     */
    protected boolean isMaxSalaryNonNegative() {
        if (maxSalary == null) {
            return true;
        }
        return maxSalary <= 0;
    }

    /**
     * Tests whether current value of {@value MappedJob_#MAX_SALARY} attribute (is {@code null} or) is positive.
     * <p>
     * Evaluates to {@code true} when the attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value MappedJob_#MAX_SALARY} attribute is positive;
     * {@code false} otherwise.
     */
    protected boolean isMaxSalaryPositive() {
        if (maxSalary == null) {
            return true;
        }
        return maxSalary > 0;
    }

    /**
     * Tests whether current value of {@value MappedJob_#MIN_SALARY} attribute is less than or equals to that of
     * {@value MappedJob_#MAX_SALARY} attribute.
     * <p>
     * Evaluates to {@code true} when either attribute is {@code null}.
     *
     * @return {@code true} if the current value of the {@value MappedJob_#MIN_SALARY} attribute is less than or equals
     * to that of the {@value MappedJob_#MAX_SALARY} attribute; {@code false} otherwise.
     */
    protected boolean isMinSalaryLessThanOrEqualToMaxSalary() {
        if (minSalary == null) {
            return true;
        }
        if (maxSalary == null) {
            return true;
        }
        return minSalary <= maxSalary;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute
     */
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute with specified value.
     *
     * @param jobId new value for the {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     */
    protected void setJobId(@Nonnull final String jobId) {
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

    /**
     * Returns current value of {@value MappedJob_#MIN_SALARY} attribute.
     *
     * @return current value of the {@value MappedJob_#MIN_SALARY} attribute.
     */
    @Nullable
    public Integer getMinSalary() {
        return minSalary;
    }

    /**
     * Replaces current value of {@value MappedJob_#MIN_SALARY} attribute with specified value.
     *
     * @param minSalary new value for the {@value MappedJob_#MIN_SALARY} attribute.
     */
    public void setMinSalary(@Nullable final Integer minSalary) {
        this.minSalary = minSalary;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    // TODO: javadoc
    @Nullable
    public Integer getMaxSalary() {
        return maxSalary;
    }

    // TODO: javadoc
    public void setMaxSalary(@Nullable final Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_ID, max = ATTRIBUTE_SIZE_MAX_JOB_ID)
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
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_TITLE, max = ATTRIBUTE_SIZE_MAX_JOB_TITLE)
    @NotNull
    @Column(name = COLUMN_NAME_JOB_TITLE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_TITLE
    )
    private String jobTitle;

    // -----------------------------------------------------------------------------------------------------------------
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
