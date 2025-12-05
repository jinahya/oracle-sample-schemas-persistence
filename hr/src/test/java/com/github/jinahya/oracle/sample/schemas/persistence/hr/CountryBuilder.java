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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedCountryBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegion;
import jakarta.annotation.Nonnull;

import java.util.Locale;
import java.util.Optional;

class CountryBuilder extends MappedCountryBuilder<CountryBuilder, Country> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static CountryBuilder from(@Nonnull final Locale locale) {
        return from(CountryBuilder::new, locale);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    CountryBuilder() {
        super(Country.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- super
    @Nonnull
    @Override
    public Country build() {
        return new Country(this);
    }

    // ------------------------------------------------------------------------------------------------- super.countryId

    // ----------------------------------------------------------------------------------------------- super.countryName

    // -------------------------------------------------------------------------------------------------- super.regionId

    // ---------------------------------------------------------------------------------------------------------- region
    protected Region region() {
        return region;
    }

    public CountryBuilder region(final Region region) {
        this.region = region;
        return regionId(
                Optional.ofNullable(this.region)
                        .map(MappedRegion::getRegionId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Region region;
}
