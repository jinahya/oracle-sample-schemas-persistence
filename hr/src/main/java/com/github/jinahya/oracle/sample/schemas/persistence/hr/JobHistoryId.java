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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHr;
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

// org.hibernate.AnnotationException:
//     Embeddable class 'com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId'
//     may not be used as an '@EmbeddedId'
//     by 'com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistory.id'
//     because it has no properties
@Embeddable
public class JobHistoryId extends _MappedHr { // implements Serializable {

//    @Serial
//    private static final long serialVersionUID = -537237161047866359L;

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static JobHistoryIdBuilder builder() {
        return new JobHistoryIdBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryId() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder from which a new instance is built.
     */
    JobHistoryId(@Nonnull final JobHistoryIdBuilder builder) {
        super(builder);
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

    // -----------------------------------------------------------------------------------------------------------------
    public JobHistoryIdBuilder toBuilder() {
        return JobHistoryId.builder()
                .employeeId(employeeId)
                .startDate(startDate);
    }

    // ------------------------------------------------------------------------------------------------------ employeeId
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Max(MappedJobHistory.ATTRIBUTE_MAX_EMPLOYEE_ID)
    @Min(MappedJobHistory.ATTRIBUTE_MIN_EMPLOYEE_ID)
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false,
            precision = MappedJobHistory.COLUMN_PRECISION_EMPLOYEE_ID,
            scale = MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    @PastOrPresent
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = MappedJobHistory.COLUMN_NAME_START_DATE,
            nullable = false,
//                insertable = false,
            insertable = true, // eclipselink
            updatable = false
    )
    private LocalDate startDate;
}
