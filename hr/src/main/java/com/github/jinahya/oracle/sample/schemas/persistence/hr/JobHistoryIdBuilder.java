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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrBuilder;

import java.time.LocalDate;

/**
 * A class for building instances of {@link JobHistoryId} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class JobHistoryIdBuilder
        extends _MappedHrBuilder<JobHistoryIdBuilder, JobHistoryId> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    JobHistoryIdBuilder() {
        super(JobHistoryId.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ employeeId
    public Integer employeeId() {
        return employeeId;
    }

    public JobHistoryIdBuilder employeeId(final Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    public LocalDate startDate() {
        return startDate;
    }

    public JobHistoryIdBuilder startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    private LocalDate startDate;
}
