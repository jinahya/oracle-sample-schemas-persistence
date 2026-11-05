package com.github.jinahya.oracle.sample.persistence.hr.mapped;

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

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The id class of the {@value MappedJobHistory#TABLE_NAME} table, which an entity extending
 * {@link MappedJobHistory} names with {@link jakarta.persistence.IdClass @IdClass}.
 * <p>
 * A plain class with no columns: its attributes are the {@link jakarta.persistence.Id @Id}
 * attributes of {@link MappedJobHistory}, by name and by type, which map the
 * {@value MappedJobHistory#COLUMN_NAME_EMPLOYEE_ID} and
 * {@value MappedJobHistory#COLUMN_NAME_START_DATE} columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class JobHistoryId {

    /**
     * The name of the attribute which matches the {@value MappedJobHistory#COLUMN_NAME_EMPLOYEE_ID}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    /**
     * The name of the attribute which matches the {@value MappedJobHistory#COLUMN_NAME_START_DATE}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public JobHistoryId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "employeeId=" + employeeId +
               ",startDate=" + startDate +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JobHistoryId that)) {
            return false;
        }
        return Objects.equals(getEmployeeId(), that.getEmployeeId()) &&
               Objects.equals(getStartDate(), that.getStartDate());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getEmployeeId(), getStartDate());
    }

    // ---------------------------------------------------------------------------------- employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified
     * value.
     *
     * @param employeeId new value for {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    public void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified
     * value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // ---------------------------------------------------------------------------------------------

    @NotNull
    private Integer employeeId;

    @NotNull
    private LocalDateTime startDate;
}
