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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryId;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Optional;

// org.hibernate.AnnotationException:
//     Embeddable class 'com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId'
//     may not be used as an '@EmbeddedId'
//     by 'com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistory.id'
//     because it has no properties
@Embeddable
public class JobHistoryId extends MappedJobHistoryId {

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

    // -----------------------------------------------------------------------------------------------------------------
//    public JobHistoryIdBuilder toBuilder() {
//        return JobHistoryId.builder()
//                .employeeId(employeeId)
//                .startDate(startDate);
//    }

    // ------------------------------------------------------------------------------------------------ super.employeeId
    @Nonnull
    public Integer getEmployeeId() {
        return super.getEmployeeId();
    }

    @Override
    protected void setEmployeeId(final Integer employeeId) {
        super.setEmployeeId(employeeId);
    }

    // ------------------------------------------------------------------------------------------------- super.startDate
    @Nonnull
    public LocalDate getStartDate() {
        return super.getStartDate();
    }

    @Override
    protected void setStartDate(final LocalDate startDate) {
        super.setStartDate(startDate);
    }

//    // -------------------------------------------------------------------------------------------------------- employee
//    Employee getEmployee() {
//        return employee;
//    }
//
//    void setEmployee(final Employee employee) {
//        this.employee = employee;
//        setEmployeeId(
//                Optional.ofNullable(this.employee)
//                        .map(MappedEmployee::getEmployeeId)
//                        .orElse(null)
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Max(MappedJobHistory.ATTRIBUTE_MAX_EMPLOYEE_ID)
//    @Min(MappedJobHistory.ATTRIBUTE_MIN_EMPLOYEE_ID)
//    @NotNull
//    @Basic(optional = false, fetch = FetchType.EAGER)
//    @Column(name = MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
//            nullable = false,
////                insertable = false,
//            insertable = true, // eclipselink
//            updatable = false,
//            precision = MappedJobHistory.COLUMN_PRECISION_EMPLOYEE_ID,
//            scale = MappedJobHistory.COLUMN_SCALE_EMPLOYEE_ID
//    )
//    private Integer employeeId;

//    @Valid
//    @NotNull
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = MappedJobHistory.COLUMN_NAME_EMPLOYEE_ID,
//                nullable = false,
//                insertable = false,
////                insertable = true, // eclipselink
//                updatable = false
//    )
//    private Employee employee;

    // -----------------------------------------------------------------------------------------------------------------
//    @PastOrPresent
//    @NotNull
//    @Basic(optional = false, fetch = FetchType.EAGER)
//    @Column(name = MappedJobHistory.COLUMN_NAME_START_DATE,
//            nullable = false,
////                insertable = false,
//            insertable = true, // eclipselink
//            updatable = false
//    )
//    private LocalDate startDate;
}
