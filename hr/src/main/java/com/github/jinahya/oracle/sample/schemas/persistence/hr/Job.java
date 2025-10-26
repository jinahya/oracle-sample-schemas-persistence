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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;

@NamedQuery(
        name = "Job.select_WhereMaxSalaryIsNotNull_OrderByMaxSalaryDesc",
        query = """
                SELECT e
                FROM Job AS e
                WHERE e.maxSalary IS NOT NULL
                ORDER BY e.maxSalary DESC"""
)
@NamedQuery(
        name = "Job.select_WhereMinSalaryIsNotNull_OrderByMinSalaryAsc",
        query = """
                SELECT e
                FROM Job AS e
                WHERE e.minSalary IS NOT NULL
                ORDER BY e.minSalary ASC"""
)
@NamedQuery(
        name = "Job.select_OrderByMaxSalaryDesc",
        query = """
                SELECT e
                FROM Job AS e
                ORDER BY e.maxSalary DESC"""
)
@NamedQuery(
        name = "Job.select_OrderByMinSalaryAsc",
        query = """
                SELECT e
                FROM Job AS e
                ORDER BY e.minSalary ASC"""
)
@NamedQuery(
        name = "Job.select_WhereMaxSalaryIsNull_OrderByJobTitleAsc",
        query = """
                SELECT e
                FROM Job AS e
                WHERE e.maxSalary IS NULL
                ORDER BY e.jobTitle ASC"""
)
@NamedQuery(
        name = "Job.select_WhereMinSalaryIsNull_OrderByJobTitleAsc",
        query = """
                SELECT e
                FROM Job AS e
                WHERE e.minSalary IS NULL
                ORDER BY e.jobTitle ASC"""
)
@NamedQuery( // no indices
             name = "Job.select_OrderByJobTitleAsc",
             query = """
                     SELECT e
                     FROM Job AS e
                     ORDER BY e.jobTitle ASC"""
)
@Entity
@Table(name = MappedJob.TABLE_NAME)
public class Job extends MappedJob {

    // -----------------------------------------------------------------------------------------------------------------
    public static MappedJobBuilder<?, Job> builder() {
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

    private Job(final JobBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    @AssertTrue
    @Override
    protected boolean isMinSalaryPositive() {
        return super.isMinSalaryPositive();
    }

    @AssertTrue
    @Override
    protected boolean isMaxSalaryPositive() {
        return super.isMaxSalaryPositive();
    }

    @AssertTrue
    @Override
    protected boolean isMinSalaryIsLessThanOrEqualToMaxSalary() {
        return super.isMinSalaryIsLessThanOrEqualToMaxSalary();
    }

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // -------------------------------------------------------------------------------------------------- super.jobTitle

    // ------------------------------------------------------------------------------------------------- super.minSalary

    // ------------------------------------------------------------------------------------------------- super.maxSalary

    // -----------------------------------------------------------------------------------------------------------------
}
