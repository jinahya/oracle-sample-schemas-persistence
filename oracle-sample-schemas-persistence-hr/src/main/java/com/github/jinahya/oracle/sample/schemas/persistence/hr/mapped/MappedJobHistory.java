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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedJobHistory#TABLE_NAME} table, including the two
 * {@link Id @Id} attributes of its composite primary key.
 * <p>
 * The identifier is mapped with an {@link jakarta.persistence.IdClass @IdClass}: an extending entity names its id
 * class, a subclass of {@link MappedJobHistoryId}, with {@code @IdClass} and inherits the {@code @Id} attributes from
 * here.
 * <p>
 * Rows of the table are written by the database's {@code UPDATE_JOB_HISTORY} trigger only, so the mapping is read-only,
 * like {@code JobHistory}'s: no attribute has a setter, and every column except the two {@code @Id} ones is neither
 * insertable nor updatable. The {@code @Id} columns stay {@code insertable = true} only because EclipseLink rejects an
 * identifier with no writable mapping (EclipseLink-46).
 * <p>
 * The {@value MappedJobHistory#COLUMN_NAME_JOB_ID} and {@value MappedJobHistory#COLUMN_NAME_DEPARTMENT_ID} columns are
 * mapped as basic values, with a {@code protected} getter, for {@link #toString()} and for queries. Whether the
 * relationship behind each is also mapped -- an association joined on the column, its fetch type -- is the extending
 * entity's decision; such an association is read-only too, as {@code JobHistory}'s {@code job} and {@code department}
 * are. Every column is populated by a load or a refresh only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedJobHistoryId
 */
@MappedSuperclass
public abstract class MappedJobHistory implements __MappedDomainEntity<MappedJobHistoryId> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JOB_HISTORY";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    /**
     * Whether the {@value #COLUMN_NAME_EMPLOYEE_ID} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_EMPLOYEE_ID = false;

    /**
     * The name of the {@link Id @Id} attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = MappedJobHistoryId.ATTRIBUTE_NAME_EMPLOYEE_ID;

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ START_DATE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_START_DATE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "START_DATE";

    /**
     * Whether the {@value #COLUMN_NAME_START_DATE} column is nullable. The value is {@value}.
     */
    public static final boolean COLUMN_NULLABLE_START_DATE = false;

    /**
     * The name of the {@link Id @Id} attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = MappedJobHistoryId.ATTRIBUTE_NAME_START_DATE;

    /**
     * A comparator which compares the {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public static final Comparator<MappedJobHistory> COMPARING_START_DATE =
            Comparator.comparing(MappedJobHistory::getStartDate);

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
               "employeeId=" + employeeId +
               ",startDate=" + startDate +
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
     * @implSpec Equality is by the two {@link Id @Id} attributes, {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} and
     * {@value #ATTRIBUTE_NAME_START_DATE}, alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistory that)) {
            return false;
        }
        return Objects.equals(getEmployeeId(), that.getEmployeeId())
               && Objects.equals(getStartDate(), that.getStartDate());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the two {@link Id @Id} attributes, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getEmployeeId(), getStartDate());
    }

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    /**
     * Tests whether current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute is after current value of
     * {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return {@code true} if the current value of the {@value #ATTRIBUTE_NAME_END_DATE} attribute is after the current
     * value of the {@value #ATTRIBUTE_NAME_START_DATE} attribute, or either of them is {@code null}; {@code false}
     * otherwise.
     */
    @AssertTrue
    protected boolean isEndDateAfterStartDate() {
        final var endDate = getEndDate();
        if (endDate == null) {
            return true;
        }
        final var startDate = getStartDate();
        if (startDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getStartDate() {
        return startDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute -- the last day of the employee in the job,
     * which the {@code UPDATE_JOB_HISTORY} trigger writes.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getEndDate() {
        return endDate;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute, which is {@code null} until this instance is
     * loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected String getJobId() {
        return jobId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute, which is {@code null} until this
     * instance is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Integer getDepartmentId() {
        return departmentId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Max(ATTRIBUTE_MAX_EMPLOYEE_ID)
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Min(ATTRIBUTE_MIN_EMPLOYEE_ID)
    @Digits(integer = COLUMN_PRECISION_EMPLOYEE_ID, fraction = 0)
    @NotNull
    @Id
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_EMPLOYEE_ID,
            nullable = COLUMN_NULLABLE_EMPLOYEE_ID,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_EMPLOYEE_ID,
            scale = COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = COLUMN_NULLABLE_START_DATE,
            insertable = true,
            updatable = false
    )
    private LocalDateTime startDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_END_DATE, nullable = false, insertable = false, updatable = false)
    private LocalDateTime endDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only, like every column of this table; see the class documentation
    @Size(max = SIZE_MAX_JOB_ID)
    @NotNull
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = COLUMN_NULLABLE_JOB_ID,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only, like every column of this table; see the class documentation
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Max(ATTRIBUTE_MAX_DEPARTMENT_ID)
    // TODO: remove; restates NUMBER(p) -- @Digits states the precision
//    @Min(ATTRIBUTE_MIN_DEPARTMENT_ID)
    @Digits(integer = COLUMN_PRECISION_DEPARTMENT_ID, fraction = 0)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID,
            nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_DEPARTMENT_ID,
            scale = COLUMN_SCALE_DEPARTMENT_ID
    )
    private Integer departmentId;
}
