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
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * A mapped superclass which holds the mappings of the {@value MappedJobHistory#TABLE_NAME} table, except for its
 * identifier.
 *
 * @param <T> the type of the identifier; a subclass maps it as it chooses, and exposes it through {@link #id_()}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedJobHistoryId
 */
@MappedSuperclass
public abstract class MappedJobHistory<T extends MappedJobHistoryId> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the table column to which the {@value MappedJobHistoryId#ATTRIBUTE_NAME_EMPLOYEE_ID} attribute of
     * {@link MappedJobHistoryId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    /**
     * Whether the {@value #COLUMN_NAME_EMPLOYEE_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_EMPLOYEE_ID = false;

    /**
     * The precision of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_EMPLOYEE_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_EMPLOYEE_ID = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_EMPLOYEE_ID = +999999;

//    /**
//     * The path of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column, for a subclass whose
//     * identifier is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
//     */
//    public static final String ATTRIBUTE_NAME_ID_EMPLOYEE_ID = "id.employeeId";

    /**
     * The minimum value of the {@value MappedJobHistoryId#ATTRIBUTE_NAME_EMPLOYEE_ID} attribute of
     * {@link MappedJobHistoryId}. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_ID_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    /**
     * The maximum value of the {@value MappedJobHistoryId#ATTRIBUTE_NAME_EMPLOYEE_ID} attribute of
     * {@link MappedJobHistoryId}. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_ID_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ START_DATE

    /**
     * The name of the table column to which the {@value MappedJobHistoryId#ATTRIBUTE_NAME_START_DATE} attribute of
     * {@link MappedJobHistoryId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "START_DATE";

    /**
     * Whether the {@value #COLUMN_NAME_START_DATE} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_START_DATE = false;

//    /**
//     * The path of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column, for a subclass whose
//     * identifier is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
//     */
//    public static final String ATTRIBUTE_NAME_ID_START_DATE = "id.startDate";

    /**
     * A comparator which compares the {@value MappedJobHistoryId#ATTRIBUTE_NAME_START_DATE} of the identifier a
     * subclass exposes through {@link #id_()}.
     */
    public static final Comparator<MappedJobHistory<?>> COMPARING_ID_START_DATE =
            Comparator.comparing(v -> v.id_().getStartDate());

//    // ----------------------------------------------------------------------------------- EMPLOYEE_ID / START_DATE / id
//
//    /**
//     * The name of the identifier attribute, declared by a subclass, which maps both the
//     * {@value #COLUMN_NAME_EMPLOYEE_ID} and the {@value #COLUMN_NAME_START_DATE} columns. The value is {@value}.
//     */
//    public static final String ATTRIBUTE_NAME_ID = "id";

    // -------------------------------------------------------------------------------------------------------- END_DATE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_END_DATE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "END_DATE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * Whether the {@value #COLUMN_NAME_JOB_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_JOB_ID = false;

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

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    /**
     * Whether the {@value #COLUMN_NAME_DEPARTMENT_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_DEPARTMENT_ID = true;

    /**
     * The precision of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    /**
     * The scale of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DEPARTMENT_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_DEPARTMENT_ID = -9999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_DEPARTMENT_ID = +9999;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "id_=" + id_() +
               ",endDate=" + endDate +
               ",jobId=" + jobId +
               ",departmentId=" + departmentId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier a subclass exposes through {@link #id_()}, alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistory<?> that)) {
            return false;
        }
        return Objects.equals(id_(), that.id_());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier a subclass exposes through {@link #id_()}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(id_());
    }

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    /**
     * Tests whether current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute is after the
     * {@value MappedJobHistoryId#ATTRIBUTE_NAME_START_DATE} of the identifier a subclass exposes through
     * {@link #id_()}.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_END_DATE} attribute is after the
     * {@value MappedJobHistoryId#ATTRIBUTE_NAME_START_DATE} of the identifier, or either of them is {@code null};
     * {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isEndDateAfterIdStartDate() {
        final var endDate = getEndDate();
        if (endDate == null) {
            return true;
        }
        final var idStartDate = Optional.ofNullable(id_()).map(MappedJobHistoryId::getStartDate).orElse(null);
        if (idStartDate == null) {
            return true;
        }
        return endDate.isAfter(idStartDate);
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the identifier of this job history. A subclass implements this with whichever attributes it maps the
     * identifier to; {@link #equals(Object)} and {@link #hashCode()} compare by it.
     *
     * @return the identifier of this job history; {@code null} if it has none yet.
     */
    @Transient
    protected abstract T id_();

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    @Nonnull
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    protected void setEndDate(@Nonnull final LocalDate endDate) {
        this.endDate = endDate;
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

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    @Nullable
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute with the specified value.
     *
     * @param departmentId new value for {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    protected void setDepartmentId(@Nullable final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_END_DATE, nullable = false, insertable = false, updatable = false)
    private LocalDate endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = SIZE_MIN_JOB_ID, max = SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = COLUMN_NULLABLE_JOB_ID)
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = COLUMN_NULLABLE_JOB_ID,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Basic(optional = COLUMN_NULLABLE_DEPARTMENT_ID)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DEPARTMENT_ID,
            scale = COLUMN_SCALE_DEPARTMENT_ID
    )
    private Integer departmentId;
}
