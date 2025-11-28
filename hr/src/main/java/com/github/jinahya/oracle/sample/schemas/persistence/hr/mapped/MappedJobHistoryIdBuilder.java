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

import java.time.LocalDate;

/**
 * An abstract builder class for building instances of a specific subclass of {@link MappedJobHistoryId} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "unchecked"
})
public abstract class MappedJobHistoryIdBuilder<
        SELF extends MappedJobHistoryIdBuilder<SELF, TARGET>,
        TARGET extends MappedJobHistoryId
        >
        extends _MappedHrBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobHistoryIdBuilder(final Class<TARGET> targetClass) {
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

    // ------------------------------------------------------------------------------------------------------- startDate
    public LocalDate startDate() {
        return startDate;
    }

    public SELF startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    private LocalDate startDate;
}
