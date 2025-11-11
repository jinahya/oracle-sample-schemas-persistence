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
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * An abstract mapped-superclass for mapping the {@value MappedJobHistory#TABLE_NAME} table.
 *
 * @param <ID> id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobHistory<ID extends MappedJobHistoryId> extends _MappedHrEntity<ID> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

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

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_EMPLOYEE_ID} column maps. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    /**
     * The name of the entity attribute, of a subclass of {@link MappedEmployee}, from which the
     * {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute maps. The value {@value}
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE = "employee";

    // ------------------------------------------------------------------------------------------ START_DATE / startDate
    public static final String COLUMN_NAME_START_DATE = "START_DATE";

    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ---------------------------------------------------------------------------------------------- END_DATE / endDate
    public static final String COLUMN_NAME_END_DATE = "END_DATE";

    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // -------------------------------------------------------------------------------------------- JOB_ID / jobId / job
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final int COLUMN_LENGTH_JOB_ID = 10;

    static {
        assert COLUMN_LENGTH_JOB_ID == MappedJob.COLUMN_LENGTH_JOB_ID;
    }

    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    public static final int ATTRIBUTE_SIZE_MIN_JOB_ID = 0;

    public static final int ATTRIBUTE_SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    public static final String ATTRIBUTE_NAME_JOB = "job";

    // ------------------------------------------------------------------------------------ DEPARTMENT_ID / departmentId
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    public static final int COLUMN_SCALE_DEPARTMENT_ID = 0;

    public static final int COLUMN_MIN_DEPARTMENT_ID = -9999;

    public static final int COLUMN_MAX_DEPARTMENT_ID = +9999;

    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    public static final long ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    public static final long ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedJobHistory() {
        super();
    }

    @Deprecated(forRemoval = true)
    protected MappedJobHistory(final MappedJobHistoryBuilder<?, ?, ID> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",endDate=" + endDate +
               ",jobId=" + jobId +
               ",departmentId=" + departmentId +
               '}';
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    /**
     * Tests whether {@value #ATTRIBUTE_NAME_START_DATE} attribute is before the {@value #ATTRIBUTE_NAME_END_DATE}
     * attribute.
     *
     * @return true if {@value #ATTRIBUTE_NAME_START_DATE} is before the {@value #ATTRIBUTE_NAME_END_DATE};
     * {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isIdStartDateBeforeEndDate() {
        if (id == null) {
            return true;
        }
        final var idStartDate = id.getStartDate();
        if (idStartDate == null) {
            return true;
        }
        if (endDate == null) {
            return true;
        }
        return idStartDate.isBefore(endDate);
    }

    // -------------------------------------------------------------------------------------------------------------- id
    @Nonnull
    public ID getId() {
        return id;
    }

    @Deprecated(forRemoval = true)
    protected void setId(@Nonnull final ID id) {
        this.id = id;
    }

    // TODO: remove!
    ID getIdOrSetAndGet(final Supplier<? extends ID> idSupplier) {
        return Optional.ofNullable(getId())
                .orElseGet(() -> {
                    setId(Objects.requireNonNull(idSupplier, "idSupplier is null").get());
                    return getId();
                });
    }

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
    @Valid
    @NotNull
    @EmbeddedId
    private ID id;

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
