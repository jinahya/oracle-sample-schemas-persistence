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
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

//@NamedQuery(
//        name = "Job.select_WhereMaxSalaryIsNotNull_OrderByMaxSalaryDesc",
//        query = """
//                SELECT e
//                FROM Job AS e
//                WHERE e.maxSalary IS NOT NULL
//                ORDER BY e.maxSalary DESC"""
//)
//@NamedQuery(
//        name = "Job.select_WhereMinSalaryIsNotNull_OrderByMinSalaryAsc",
//        query = """
//                SELECT e
//                FROM Job AS e
//                WHERE e.minSalary IS NOT NULL
//                ORDER BY e.minSalary ASC"""
//)
//@NamedQuery(
//        name = "Job.select_OrderByMaxSalaryDesc",
//        query = """
//                SELECT e
//                FROM Job AS e
//                ORDER BY e.maxSalary DESC"""
//)
//@NamedQuery(
//        name = "Job.select_OrderByMinSalaryAsc",
//        query = """
//                SELECT e
//                FROM Job AS e
//                ORDER BY e.minSalary ASC"""
//)
//@NamedQuery(
//        name = "Job.select_WhereMaxSalaryIsNull_OrderByJobTitleAsc",
//        query = """
//                SELECT e
//                FROM Job AS e
//                WHERE e.maxSalary IS NULL
//                ORDER BY e.jobTitle ASC"""
//)
//@NamedQuery(
//        name = "Job.select_WhereMinSalaryIsNull_OrderByJobTitleAsc",
//        query = """
//                SELECT e
//                FROM Job AS e
//                WHERE e.minSalary IS NULL
//                ORDER BY e.jobTitle ASC"""
//)
//@NamedQuery( // no indices
//             name = "Job.select_OrderByJobTitleAsc",
//             query = """
//                     SELECT e
//                     FROM Job AS e
//                     ORDER BY e.jobTitle ASC"""
//)
@NamedQuery(name = "Job.Select__OrderByMaxSalaryDescNullsLast",
            query = """
                    SELECT e
                    FROM Job e 
                    ORDER BY e.maxSalary DESC NULLS LAST"""
)
@NamedQuery(name = "Job.Select__OrderByMinSalaryAscNullsFirst",
            query = """
                    SELECT e
                    FROM Job e
                    ORDER BY e.minSalary ASC NULLS FIRST"""
)
@Entity
@Table(name = MappedJob.TABLE_NAME)
public class Job extends MappedJob {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static JobBuilder builder() {
        return new JobBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Job() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    private Job(final JobBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final boolean equals(final Object obj) {
        return super.equalsWithJobId(obj);
    }

    @Override
    public final int hashCode() {
        return super.hashCodeWithJobId();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // -------------------------------------------------------------------------------------------------- super.jobTitle

    // ------------------------------------------------------------------------------------------------- super.minSalary

    // ------------------------------------------------------------------------------------------------- super.maxSalary

    // ------------------------------------------------------------------------------------------------------- employees
    List<Employee> getEmployees() {
        return employees;
    }

    void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_JOB, fetch = FetchType.LAZY)
    private List<@Valid @NotNull Employee> employees;
}
