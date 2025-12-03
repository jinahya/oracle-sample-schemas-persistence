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
 * A class for building instance of {@link JobHistoryId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
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

    /**
     * Returns current value of {@code employeeId} property.
     *
     * @return current value of the {@code employeeId} property.
     */
    public Integer employeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@code employeeId} property with specified value, and returns this builder instance.
     *
     * @param employeeId new value for the {@code employeeId} property.
     * @return this builder instance.
     */
    public JobHistoryIdBuilder employeeId(final Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- startDate
    // TODO: javadoc
    public LocalDate startDate() {
        return startDate;
    }

    // TODO: javadoc
    public JobHistoryIdBuilder startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer employeeId;

    private LocalDate startDate;
}
