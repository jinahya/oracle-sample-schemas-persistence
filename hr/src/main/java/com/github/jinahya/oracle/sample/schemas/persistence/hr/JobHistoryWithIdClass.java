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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * An entity class for mapping the {@value JobHistoryWithIdClass#TABLE_NAME} table, whose composite primary key is
 * mapped with an {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JobHistoryWithEmbeddedId
 */
@NamedQuery(name = "JobHistory.selectList_WhereEmployeeEqualTo_OrderByStartDateDesc",
            query = """
                    SELECT e
                    FROM JobHistory e
                    WHERE e.employee = :employee"""
)
@NamedQuery(name = "JobHistory.selectList_WhereEmployeeIdEqualTo_OrderByStartDateDesc",
            query = """
                    SELECT e
                    FROM JobHistory e
                    WHERE e.employeeId = :employeeId"""
)
@Entity(name = JobHistoryWithIdClass.ENTITY_NAME)
@Table(name = JobHistoryWithIdClass.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               JobHistoryWithIdClass.COLUMN_NAME_EMPLOYEE_ID,
                               JobHistoryWithIdClass.COLUMN_NAME_START_DATE
                       }
               )
       }
)
public class JobHistoryWithIdClass {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    /**
     * The name of the entity. The value is {@value}.
     */
    public static final String ENTITY_NAME = "JobHistory";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * A table column name of {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    /**
     * Whether the {@code EMPLOYEE_ID} column is nullable. The value is {@value}.
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

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE = "employee";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_EMPLOYEE_ID} column maps. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     *
     * @see #COLUMN_MIN_EMPLOYEE_ID
     */
    public static final long ATTRIBUTE_MIN_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     *
     * @see #COLUMN_MAX_EMPLOYEE_ID
     */
    public static final long ATTRIBUTE_MAX_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ START_DATE

    /**
     * A table column name of {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "START_DATE";

    /**
     * Whether the {@code START_DATE} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_START_DATE = false;

    // ------------------------------------------------------------------------------------------------------ START_DATE

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_START_DATE} column maps. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    /**
     * A comparator for comparing {@link JobHistoryWithIdClass} instances by their {@code startDate} attributes.
     */
    public static final Comparator<JobHistoryWithIdClass> COMPARING_START_DATE = comparingStartDate();

    // -------------------------------------------------------------------------------------------------------- END_DATE

    /**
     * The name of the table column to which the {@code endDate} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "END_DATE";

    /**
     * The name of the attribute which maps the {@code END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the table column to which the {@code jobId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * Whether the {@code JOB_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_JOB_ID = false;

    /**
     * The length of the {@code JOB_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = 10;

    /**
     * The minimum size of the {@code jobId} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_SIZE_MIN_JOB_ID = 0;

    /**
     * The maximum size of the {@code jobId} attribute.
     */
    public static final int ATTRIBUTE_SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB = "job";

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    /**
     * The name of the table column to which the {@code departmentId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    /**
     * Whether the {@code DEPARTMENT_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_DEPARTMENT_ID = true;

    /**
     * The precision of the {@code DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    /**
     * The scale of the {@code DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DEPARTMENT_ID = 0;

    /**
     * The minimum value of the {@code DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_DEPARTMENT_ID = -9999;

    /**
     * The maximum value of the {@code DEPARTMENT_ID} column.
     */
    public static final int COLUMN_MAX_DEPARTMENT_ID = +9999;

    /**
     * The minimum value of the {@code departmentId} attribute.
     */
    public static final long ATTRIBUTE_MIN_DEPARTMENT_ID = COLUMN_MIN_DEPARTMENT_ID;

    /**
     * The maximum value of the {@code departmentId} attribute.
     */
    public static final long ATTRIBUTE_MAX_DEPARTMENT_ID = COLUMN_MAX_DEPARTMENT_ID;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    static {
        assert COLUMN_LENGTH_JOB_ID == Job.COLUMN_LENGTH_JOB_ID;
    }

    static {
        assert COLUMN_PRECISION_DEPARTMENT_ID == Department.COLUMN_PRECISION_DEPARTMENT_ID;
        assert COLUMN_SCALE_DEPARTMENT_ID == Department.COLUMN_SCALE_DEPARTMENT_ID;
        assert COLUMN_MIN_DEPARTMENT_ID == Department.COLUMN_MIN_DEPARTMENT_ID;
        assert COLUMN_MAX_DEPARTMENT_ID == Department.COLUMN_MAX_DEPARTMENT_ID;
    }

    // -------------------------------------------------------------------------------------------------- STATIC_METHODS

    static <T extends JobHistoryWithIdClass> Comparator<T> comparingStartDate(
            @Nonnull final Function<? super T, LocalDate> startDateExtractor) {
        Objects.requireNonNull(startDateExtractor, "startDateExtractor is null");
        return Comparator.comparing(startDateExtractor);
    }

    /**
     * Returns a comparator compares {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @param <T> the type to be compared.
     * @return a comparator compares {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    protected static <T extends JobHistoryWithIdClass> Comparator<T> comparingStartDate() {
        return comparingStartDate(JobHistoryWithIdClass::getStartDate);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryWithIdClass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "employeeId=" + employeeId +
               ",startDate=" + startDate +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof JobHistoryWithIdClass that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Indicates whether the {@code endDate} attribute is after the {@code startDate} attribute.
     *
     * @return {@code true} if the {@code endDate} attribute is after the {@code startDate} attribute; {@code false}
     * otherwise.
     */
    @AssertTrue
    protected boolean isEndDateAfterStartDate() {
        if (endDate == null) {
            return true;
        }
        if (startDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    // -------------------------------------------------------------------------------------------------------- employee

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_EMPLOYEE} attribute.
     */
    @Nonnull
    public Employee getEmployee() {
        return employee;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_END_DATE} attribute.
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

    // ------------------------------------------------------------------------------------------------------------- job

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB} attribute.
     */
    @Nonnull
    public Job getJob() {
        return job;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB} attribute with the specified value.
     *
     * @param job new value for {@value #ATTRIBUTE_NAME_JOB} attribute.
     */
    public void setJob(@Nonnull final Job job) {
        this.job = job;
    }

    // ------------------------------------------------------------------------------------------------------ department

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    @jakarta.annotation.Nullable
    public Department getDepartment() {
        return department;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute with the specified value.
     *
     * @param department new value for {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    public void setDepartment(@jakarta.annotation.Nullable final Department department) {
        this.department = department;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(JobHistoryWithIdClass.ATTRIBUTE_MAX_EMPLOYEE_ID)
    @Min(JobHistoryWithIdClass.ATTRIBUTE_MIN_EMPLOYEE_ID)
    @NotNull
    @Id // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Column(name = JobHistoryWithIdClass.COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
//            insertable = false,
            insertable = true, // fuck eclipselink
            updatable = false,
            precision = JobHistoryWithIdClass.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = JobHistoryWithIdClass.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMPLOYEE_ID,
                referencedColumnName = Employee.COLUMN_NAME_DEPARTMENT_ID,
                nullable = COLUMN_NULLABLE_EMPLOYEE_ID,
                insertable = false,
                updatable = false
    )
    private Employee employee;

    @Nonnull
    @PastOrPresent
    @NotNull
    @Id // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Column(name = JobHistoryWithIdClass.COLUMN_NAME_START_DATE,
            nullable = false,
//            insertable = false,
            insertable = true, // fuck eclipselink
            updatable = false
    )
    private LocalDate startDate;

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

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_JOB_ID,
                referencedColumnName = Job.COLUMN_NAME_JOB_ID,
                nullable = COLUMN_NULLABLE_JOB_ID,
                insertable = true,
                updatable = true
    )
    private Job job;

    @jakarta.annotation.Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                referencedColumnName = Department.COLUMN_NAME_DEPARTMENT_ID,
                nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
                insertable = true,
                updatable = true
    )
    private Department department;
}
