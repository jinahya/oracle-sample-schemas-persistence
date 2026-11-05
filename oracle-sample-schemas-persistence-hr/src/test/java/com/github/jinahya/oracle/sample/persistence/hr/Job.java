package com.github.jinahya.oracle.sample.persistence.hr;

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

import com.github.jinahya.oracle.sample.persistence.hr.mapped.MappedJob;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An entity class for mapping the {@value MappedJob#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = MappedJob.TABLE_NAME,
       comment = "jobs table with job titles and salary ranges.\n" +
               "References with employees and job_history table.")
public class Job
        extends MappedJob {

    // ----------------------------------------------------------------------------------- EMPLOYEES

    /**
     * The name of the attribute which maps the {@link Employee employee}s assigned to this job,
     * mapped by {@value Employee#ATTRIBUTE_NAME_JOB}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEES = "employees";

    // ------------------------------------------------------------------------------- JOB_HISTORIES

    /**
     * The name of the attribute which maps the past assignments to this job. The value is
     * {@value}.
     *
     * @see JobHistory#ATTRIBUTE_NAME_JOB
     */
    public static final String ATTRIBUTE_NAME_JOB_HISTORIES = "jobHistories";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Job() {
        super();
    }

    // ----------------------------------------------------------------------------------- employees

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEES} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_EMPLOYEES} attribute.
     */
    List<Employee> getEmployees() {
        return employees;
    }

    void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    // -------------------------------------------------------------------------------- jobHistories

    /**
     * Returns the past assignments to this job.
     *
     * @return the past assignments to this job.
     */
    List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    /**
     * Replaces the past assignments to this job.
     *
     * @param jobHistories new past assignments to this job.
     */
    void setJobHistories(final List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }

    // ---------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_JOB,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> employees;

    // ---------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = JobHistory.ATTRIBUTE_NAME_JOB,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull JobHistory> jobHistories;
}
