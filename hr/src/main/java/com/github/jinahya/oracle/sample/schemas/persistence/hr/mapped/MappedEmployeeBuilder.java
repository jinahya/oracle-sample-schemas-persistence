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

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedEmployeeBuilder<
        SELF extends _MappedHrEntityBuilder<SELF, TARGET>,
        TARGET extends MappedEmployee
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified target class.
     *
     * @param targetClass the target class.
     */
    protected MappedEmployeeBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ employeeId
    public Integer employeeId() {
        return employeeId;
    }

    public SELF employeeId(final Integer employeeId) {
        this.employeeId = employeeId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- firstName
    public String firstName() {
        return firstName;
    }

    public SELF firstName(final String firstName) {
        this.firstName = firstName;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- lastName
    public String lLastName() {
        return lastName;
    }

    public SELF lastName(final String lastName) {
        this.lastName = lastName;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------------- email
    public String email() {
        return email;
    }

    public SELF email(final String email) {
        this.email = email;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber
    public String phoneNumber() {
        return phoneNumber;
    }

    public SELF phoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- hireDate
    public LocalDate hireDate() {
        return hireDate;
    }

    public SELF hireDate(final LocalDate hireDate) {
        this.hireDate = hireDate;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    public String jobId() {
        return jobId;
    }

    public SELF jobId(final String jobId) {
        this.jobId = jobId;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------------- salary
    public BigDecimal salary() {
        return salary;
    }

    public SELF salary(final BigDecimal salary) {
        this.salary = salary;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct
    public BigDecimal commissionPct() {
        return commissionPct;
    }

    public SELF commissionPct(final BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    public Integer managerId() {
        return managerId;
    }

    public SELF managerId(final Integer managerId) {
        this.managerId = managerId;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    public Integer departmentId() {
        return departmentId;
    }

    public SELF departmentId(final Integer departmentId) {
        this.departmentId = departmentId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    // -----------------------------------------------------------------------------------------------------------------
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    private BigDecimal salary;

    private BigDecimal commissionPct;

    // -----------------------------------------------------------------------------------------------------------------
    private Integer managerId;

    // -----------------------------------------------------------------------------------------------------------------
    private Integer departmentId;
}
