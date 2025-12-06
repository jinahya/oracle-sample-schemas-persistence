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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployeeBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;

import java.util.Optional;

class EmployeeBuilder extends MappedEmployeeBuilder<EmployeeBuilder, Employee> {

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR
    EmployeeBuilder() {
        super(Employee.class);
    }

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ------------------------------------------------------------------------------------------------- super.managerId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // ------------------------------------------------------------------------------------------------------------- job
    Job job() {
        return job;
    }

    public EmployeeBuilder job(final Job job) {
        this.job = job;
        return jobId(
                Optional.ofNullable(this.job)
                        .map(MappedJob::getJobId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ department
    Department department() {
        return department;
    }

    public EmployeeBuilder department(final Department department) {
        this.department = department;
        return departmentId(
                Optional.ofNullable(this.department)
                        .map(MappedDepartment::getDepartmentId)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- manager
    Employee manager() {
        return manager;
    }

    public EmployeeBuilder manager(final Employee manager) {
        this.manager = manager;
        return managerId(
                Optional.ofNullable(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Job job;

    private Employee manager;

    private Department department;
}
