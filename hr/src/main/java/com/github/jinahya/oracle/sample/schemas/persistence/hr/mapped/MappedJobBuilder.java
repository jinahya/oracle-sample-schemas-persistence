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

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobBuilder<
        SELF extends MappedJobBuilder<SELF, TARGET>,
        TARGET extends MappedJob
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedJobBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // ----------------------------------------------------------------------------------------------------------- jobId

    /**
     * Returns current value of {@code jobId} property.
     *
     * @return current value of the {@code jobId} property.
     */
    public String jobId() {
        return jobId;
    }

    /**
     * Replaces current value of {@code jobId} property with the specified value, and returns this builder instance.
     *
     * @param jobId new value for the {@code jobId} property.
     * @return this builder instance.
     */
    public SELF jobId(final String jobId) {
        this.jobId = jobId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    public String jobTitle() {
        return jobTitle;
    }

    public SELF jobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary
    public Integer minSalary() {
        return minSalary;
    }

    public SELF minSalary(final Integer minSalary) {
        this.minSalary = minSalary;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    public Integer maxSalary() {
        return maxSalary;
    }

    public SELF maxSalary(final Integer maxSalary) {
        this.maxSalary = maxSalary;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;
}
