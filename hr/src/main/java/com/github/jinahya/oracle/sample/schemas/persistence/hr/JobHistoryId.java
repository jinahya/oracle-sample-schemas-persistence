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
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A composite primary key class for mapping {@value JobHistoryWithEmbeddedId#COLUMN_NAME_EMPLOYEE_ID} column and
 * {@value JobHistoryWithEmbeddedId#COLUMN_NAME_START_DATE} column, of {@value JobHistoryWithEmbeddedId#TABLE_NAME}
 * table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JobHistoryWithEmbeddedId
 * @see <a
 * href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#composite-primary-keys">2.4.1.
 * Composite primary keys</a> (Jakarta Persistence 3.2 Specification Document)
 */
@Embeddable
public class JobHistoryId {

    /**
     * The name of the attribute which maps the {@code EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    /**
     * The name of the attribute which maps the {@code START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryId() {
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
        if (!(obj instanceof JobHistoryId that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(employeeId, startDate);
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified value.
     *
     * @param employeeId new value for the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    // TODO: remove if it's not used anymore
    void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    // TODO: remove if it's not used anymore
    void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Max(JobHistoryWithEmbeddedId.ATTRIBUTE_MAX_ID_EMPLOYEE_ID)
    @Min(JobHistoryWithEmbeddedId.ATTRIBUTE_MIN_ID_EMPLOYEE_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = JobHistoryWithEmbeddedId.COLUMN_NAME_EMPLOYEE_ID,
            nullable = JobHistoryWithEmbeddedId.COLUMN_NULLABLE_EMPLOYEE_ID,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false,
            precision = JobHistoryWithEmbeddedId.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = JobHistoryWithEmbeddedId.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    @PastOrPresent
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = JobHistoryWithEmbeddedId.COLUMN_NAME_START_DATE,
            nullable = JobHistoryWithEmbeddedId.COLUMN_NULLABLE_START_DATE,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private LocalDate startDate;
}
