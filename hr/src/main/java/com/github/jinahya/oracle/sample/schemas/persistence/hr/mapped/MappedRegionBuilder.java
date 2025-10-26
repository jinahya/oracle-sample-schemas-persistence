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
 * An abstract class for building instances of a specific subclass of {@link MappedRegion} class.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> target type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedRegionBuilder<
        SELF extends MappedRegionBuilder<SELF, TARGET>,
        TARGET extends MappedRegion
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instances of the specified target class.
     *
     * @param targetClass the target class.a
     */
    protected MappedRegionBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -------------------------------------------------------------------------------------------------------- regionId

    /**
     * Returns current value of the {@link MappedRegion_#regionId regionId} property.
     *
     * @return the current value of the {@link MappedRegion_#regionId regionId} property.
     */
    public Long regionId() {
        return regionId;
    }

    /**
     * Replaces current value of {@link MappedRegion_#regionId regionId} property with specified
     *
     * @param regionId new value for the {@link MappedRegion_#regionId regionId} property.
     * @return this builder instance.
     */
    public SELF regionId(final Long regionId) {
        this.regionId = regionId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    public String regionName() {
        return regionName;
    }

    public SELF regionName(final String regionName) {
        this.regionName = regionName;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Long regionId;

    private String regionName;
}
