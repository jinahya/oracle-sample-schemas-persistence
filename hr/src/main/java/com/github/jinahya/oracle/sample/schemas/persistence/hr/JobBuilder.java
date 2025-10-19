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

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

public class JobBuilder
        extends __MappedEntityBuilder<JobBuilder, Job> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    JobBuilder() {
        super(Job.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // ----------------------------------------------------------------------------------------------------------- jobId
    public String jobId() {
        return jobId;
    }

    public JobBuilder jobId(final String jobId) {
        this.jobId = jobId;
        return this;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    public String jobTitle() {
        return jobTitle;
    }

    public JobBuilder jobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary
    public Integer minSalary() {
        return minSalary;
    }

    public JobBuilder minSalary(final Integer minSalary) {
        this.minSalary = minSalary;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    public Integer maxSalary() {
        return maxSalary;
    }

    public JobBuilder maxSalary(final Integer maxSalary) {
        this.maxSalary = maxSalary;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;
}
