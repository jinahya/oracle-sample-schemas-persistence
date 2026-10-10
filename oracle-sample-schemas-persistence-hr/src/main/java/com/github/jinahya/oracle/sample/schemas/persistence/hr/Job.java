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
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An entity class for mapping the {@value Job#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
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
@Table(name = Job.TABLE_NAME)
public class Job extends MappedJob implements __DomainEntity<String> {

    /**
     * The name of the attribute which maps the {@link Employee employee}s assigned to this job, mapped by
     * {@value Employee#ATTRIBUTE_NAME_JOB}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEES = "employees";

    /**
     * The name of the attribute which maps the past assignments to this job. The value is {@value}.
     *
     * @see JobHistory#ATTRIBUTE_NAME_JOB
     */
    public static final String ATTRIBUTE_NAME_JOB_HISTORIES = "jobHistories";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Job() {
        super();
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute with the specified value, and raises
     * current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute to it when it is less, so that
     * {@link #isMinSalaryLessThanOrEqualToMaxSalary()} holds.
     *
     * @param minSalary new value for {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute; should be between
     *                  {@value #ATTRIBUTE_MIN_MIN_SALARY} and {@value #ATTRIBUTE_MAX_MIN_SALARY}.
     * @throws IllegalArgumentException if {@code minSalary} is not {@code null} and out of that range.
     * @deprecated for removal.
     */
    @Deprecated(forRemoval = true)
    public void setMinSalaryWhileAdjustingMaxSalary(@jakarta.annotation.Nullable final Integer minSalary) {
        if (minSalary != null && (minSalary < ATTRIBUTE_MIN_MIN_SALARY || minSalary > ATTRIBUTE_MAX_MIN_SALARY)) {
            throw new IllegalArgumentException(
                    "minSalary(" + minSalary + ") is out of [" + ATTRIBUTE_MIN_MIN_SALARY + ".."
                    + ATTRIBUTE_MAX_MIN_SALARY + "]");
        }
        setMinSalary(minSalary);
        {
            final var currentMaxSalary = getMaxSalary();
            final var currentMinSalary = getMinSalary();
            if (currentMaxSalary != null && currentMinSalary != null && currentMaxSalary < currentMinSalary) {
                setMaxSalary(currentMinSalary);
            }
        }
        {
            final var currentMaxSalary = getMaxSalary();
            assert currentMaxSalary == null
                   || (currentMaxSalary >= ATTRIBUTE_MIN_MIN_SALARY && currentMaxSalary <= ATTRIBUTE_MAX_MIN_SALARY);
        }
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute with the specified value, and adjusts
     * current value of {@value #ATTRIBUTE_NAME_MIN_SALARY} attribute, intending that
     * {@link #isMinSalaryLessThanOrEqualToMaxSalary()} holds.
     *
     * @param maxSalary new value for {@value #ATTRIBUTE_NAME_MAX_SALARY} attribute; should be between
     *                  {@value #ATTRIBUTE_MIN_MAX_SALARY} and {@value #ATTRIBUTE_MAX_MAX_SALARY}.
     * @throws IllegalArgumentException if {@code maxSalary} is not {@code null} and out of that range.
     * @deprecated for removal.
     */
    @Deprecated(forRemoval = true)
    public void setMaxSalaryWhileAdjustingMinSalary(@jakarta.annotation.Nullable final Integer maxSalary) {
        if (maxSalary != null && (maxSalary < ATTRIBUTE_MIN_MAX_SALARY || maxSalary > ATTRIBUTE_MAX_MAX_SALARY)) {
            throw new IllegalArgumentException(
                    "maxSalary(" + maxSalary + ") is out of [" + ATTRIBUTE_MIN_MAX_SALARY + ".."
                    + ATTRIBUTE_MAX_MAX_SALARY + "]");
        }
        setMaxSalary(maxSalary);
        {
            final var currentMinSalary = getMinSalary();
            final var currentMaxSalary = getMaxSalary();
            if (currentMinSalary != null && currentMaxSalary != null && currentMinSalary > currentMaxSalary) {
                setMinSalary(currentMaxSalary);
            }
        }
        {
            final var currentMinSalary = getMinSalary();
            assert currentMinSalary == null
                   || (currentMinSalary >= ATTRIBUTE_MIN_MAX_SALARY && currentMinSalary <= ATTRIBUTE_MAX_MAX_SALARY);
        }
    }

    // ------------------------------------------------------------------------------------------------------- employees

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

    // ---------------------------------------------------------------------------------------------------- jobHistories

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

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Employee.ATTRIBUTE_NAME_JOB,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> employees;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = JobHistory.ATTRIBUTE_NAME_JOB,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull JobHistory> jobHistories;
}
