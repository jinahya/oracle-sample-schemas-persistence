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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * An abstract mapped-superclass for mapping the {@value MappedJobHistory#TABLE_NAME} table.
 * <p>
 * The {@value MappedJobHistory#TABLE_NAME} table has two primary key columns,
 * {@value MappedJobHistory#COLUMN_NAME_EMPLOYEE_ID} and {@value MappedJobHistory#COLUMN_NAME_START_DATE}.
 * <p>
 * Say, we have a class for the composite-primary key,
 * <p>
 * //@formatter:off
 * {@snippet lang = "java" :
 * @jakarta.persistence.Embeddable // only for the @EmbeddedId // @highlight substring="Embeddable"
 * class JobHistoryId {
 *
 *     @jakarta.persistence.Column(name = "EMPLOYEE_ID") // only for the @EmbeddedId
 *     private Integer employeeId; // @highlight substring="Integer employeeId"
 *
 *     @jakarta.persistence.Column(name = "START_DATE") // only for the @EmbeddedId
 *     private java.time.LocalDate startDate; // @highlight substring="java.time.LocalDate startDate"
 * }
 *}
 * //@formatter:on
 * <p>
 * Then we can use the class in two ways, {@link jakarta.persistence.EmbeddedId} or {@link jakarta.persistence.IdClass}.
 * <p>
 * <table>
 *   <caption></caption>
 *   <thead>
 *     <tr>
 *       <th>as an {@link jakarta.persistence.EmbeddedId}</th>
 *       <th>as an {@link jakarta.persistence.IdClass}</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr style="vertical-align: top;">
 *       <td>{@snippet lang = "java":
 *
 * @jakarta.persistence.Entity
 * class JobHistory {
 *
 *
 *
 *
 *
 *
 *     @jakarta.persistence.EmbeddedId // @highlight substring="EmbeddedId"
 *     private JobHistoryId id;
 * }
 *}</td>
 *       <td>{@snippet lang = "java":
 * @IdClass(JobHistoryId.class) // @highlight substring="IdClass"
 * @jakarta.persistence.Entity
 * class JobHistory {
 *
 *     @jakarta.persistence.Id // @highlight substring="Id"
 *     @jakarta.persistence.Column(name = "EMPLOYEE_ID")
 *     private Integer employeeId; // should have the same type/name // @highlight substring="Integer employeeId"
 *
 *     @jakarta.persistence.Id // @highlight substring="Id"
 *     @jakarta.persistence.Column(name = "START_DATE")
 *     private java.time.LocalDate startDate; // should have the same type/name, // @highlight substring="java.time.LocalDate startDate"
 * }
 *}</td>
 *     </tr>
 *   </tbody>
 *   <tfoot>
 *     <tr style="vertical-align: top;">
 *       <td>See <a href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#a14687">11.1.17. EmbeddedId Annotation</a></td>
 *       <td>See <a href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#a14836">11.1.23. IdClass Annotation</a></td>
 *     </tr>
 *   </tfoot>
 * </table>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see jakarta.persistence.Embeddable
 * @see jakarta.persistence.EmbeddedId
 * @see jakarta.persistence.IdClass
 * @see JobHistoryId
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#composite-primary-keys">2.4.1. Composite primary keys</a> (Jakarta Persistence 3.2 Specification Document)
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#a14687">11.1.17. EmbeddedId Annotation</a> (Jakarta Persistence 3.2 Specification Document)
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#a14836">11.1.23. IdClass Annotation</a> (Jakarta Persistence 3.2 Specification Document)
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobHistory extends _MappedHrEntity<JobHistoryId> {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    public static final String ENTITY_NAME = "JobHistory";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * A table column name of {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

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

    public static final String ATTRIBUTE_NAME_EMPLOYEE = "employee";

    // ------------------------------------------------------------------------------------------------------ START_DATE

    /**
     * A table column name of {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "START_DATE";

    public static final boolean COLUMN_NULLABLE_START_DATE = false;

    // -------------------------------------------------------------------------------------------------------- END_DATE
    public static final String COLUMN_NAME_END_DATE = "END_DATE";

    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    public static final boolean COLUMN_NULLABLE_JOB_ID = false;

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

    public static final boolean COLUMN_NULLABLE_DEPARTMENT_ID = true;

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

    // -----------------------------------------------------------------------------------------------------------------
    static <T extends MappedJobHistory> Comparator<T> comparingStartDate(
            @Nonnull final Function<? super T, LocalDate> startDateExtractor) {
        Objects.requireNonNull(startDateExtractor, "startDateExtractor is null");
        return Comparator.comparing(startDateExtractor);
    }

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobHistory() {
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

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value MappedJobHistory_#END_DATE} attribute.
     *
     * @return current value of the {@value MappedJobHistory_#END_DATE} attribute.
     */
    @Nonnull
    public LocalDate getEndDate() {
        return endDate;
    }

    protected void setEndDate(@Nonnull final LocalDate endDate) {
        this.endDate = endDate;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    protected void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Nullable
    public Integer getDepartmentId() {
        return departmentId;
    }

    protected void setDepartmentId(@Nullable final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
//    @PastOrPresent // @@?
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    LocalDate endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_JOB_ID, max = ATTRIBUTE_SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = COLUMN_NULLABLE_JOB_ID,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_DEPARTMENT_ID,
            scale = COLUMN_SCALE_DEPARTMENT_ID
    )
    private Integer departmentId;
}
