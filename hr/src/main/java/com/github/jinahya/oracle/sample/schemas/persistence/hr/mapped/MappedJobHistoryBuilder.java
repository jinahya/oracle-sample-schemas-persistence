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
 * An abstract class for building a specific subclass of {@link MappedJobHistory} class.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> target type parameter
 * @param <ID>     id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @deprecated no longer used
 */
@Deprecated(forRemoval = true)
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobHistoryBuilder<
        SELF extends MappedJobHistoryBuilder<SELF, TARGET, ID>,
        TARGET extends MappedJobHistory<ID>,
        ID extends MappedJobHistoryId
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedJobHistoryBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -----------------------------------------------------------------------------------------------------------------
    public ID id() {
        return id;
    }

    public SELF id(final ID id) {
        this.id = id;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public LocalDate endDate() {
        return endDate;
    }

    public SELF endDate(final LocalDate endDate) {
        this.endDate = endDate;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String jobId() {
        return jobId;
    }

    public SELF jobId(final String jobId) {
        this.jobId = jobId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Integer departmentId() {
        return departmentId;
    }

    public SELF departmentId(final Integer departmentId) {
        this.departmentId = departmentId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ID id;

    // -----------------------------------------------------------------------------------------------------------------
    private LocalDate endDate;

    private String jobId;

    private Integer departmentId;
}
