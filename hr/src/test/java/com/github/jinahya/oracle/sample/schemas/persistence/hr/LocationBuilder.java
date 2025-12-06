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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedCountry;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocationBuilder;

import java.util.Optional;

class LocationBuilder extends MappedLocationBuilder<LocationBuilder, Location> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    LocationBuilder() {
        super(Location.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // ------------------------------------------------------------------------------------------------ super.locationId

    // --------------------------------------------------------------------------------------------- super.streetAddress

    // ------------------------------------------------------------------------------------------------ super.postalCode

    // ------------------------------------------------------------------------------------------------------ super.city

    // --------------------------------------------------------------------------------------------- super.stateProvince

    // ------------------------------------------------------------------------------------------------- super.countryId

    // --------------------------------------------------------------------------------------------------------- country
    public Country country() {
        return country;
    }

    public LocationBuilder country(final Country country) {
        this.country = country;
        return countryId(
                Optional.ofNullable(this.country)
                        .map(MappedCountry::getCountryId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Country country;
}
