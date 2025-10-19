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
 * An abstract class for building instance of a specific subclass of the {@link MappedLocation} class.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> target type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedLocationBuilder<
        SELF extends MappedLocationBuilder<SELF, TARGET>,
        TARGET extends MappedLocation
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedLocationBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ locationId
    public Integer locationId() {
        return locationId;
    }

    public SELF locationId(final Integer locationId) {
        this.locationId = locationId;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- streetAddress
    public String streetAddress() {
        return streetAddress;
    }

    public SELF streetAddress(final String streetAddress) {
        this.streetAddress = streetAddress;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ postalCode
    public String postalCode() {
        return postalCode;
    }

    public SELF postalCode(final String postalCode) {
        this.postalCode = postalCode;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------------ city
    public String city() {
        return city;
    }

    public SELF city(final String city) {
        this.city = city;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince
    public String stateProvince() {
        return stateProvince;
    }

    public SELF stateProvince(final String stateProvince) {
        this.stateProvince = stateProvince;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    @Deprecated(forRemoval = true)
    public String countryId() {
        return countryId;
    }

    @Deprecated(forRemoval = true)
    public SELF countryId(final String countryId) {
        this.countryId = countryId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private String countryId;
}
