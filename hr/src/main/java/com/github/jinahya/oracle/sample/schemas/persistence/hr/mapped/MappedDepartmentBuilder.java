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

/**
 * An abstract class for building instances of a specific subclass of {@link MappedDepartment}.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> target type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedDepartmentBuilder<
        SELF extends _MappedHrEntityBuilder<SELF, TARGET>,
        TARGET extends MappedLocation
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified target class.
     *
     * @param targetClass the target class.
     */
    protected MappedDepartmentBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@code #departmentId} property.
     *
     * @return current value of {@code #departmentId} property.
     */
    public Integer departmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of {@code #departmentId} property with specified value, and returns this builder
     * instance.
     *
     * @param departmentId new value for {@code #departmentId} property.
     * @return this builder instance
     */
    public SELF departmentId(final Integer departmentId) {
        this.departmentId = departmentId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------- departmentName
    public String departmentName() {
        return departmentName;
    }

    public SELF departmentName(final String departmentName) {
        this.departmentName = departmentName;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    public Integer managerId() {
        return managerId;
    }

    public SELF managerId(final Integer managerId) {
        this.managerId = managerId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ locationId
    public Integer locationId() {
        return locationId;
    }

    public SELF locationId(final Integer locationId) {
        this.locationId = locationId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer departmentId;

    // -----------------------------------------------------------------------------------------------------------------
    private String departmentName;

    // -----------------------------------------------------------------------------------------------------------------
    private Integer managerId;

    // -----------------------------------------------------------------------------------------------------------------
    private Integer locationId;
}
