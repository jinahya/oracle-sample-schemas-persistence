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

import jakarta.annotation.Nonnull;

import java.util.regex.Pattern;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCountryBuilder<
        SELF extends MappedCountryBuilder<SELF, TARGET>,
        TARGET extends MappedCountry
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String ISO_3166_1_ALPHA_2_REGEXP = "[A-Z]{2}";

    public static final Pattern ISO_3166_1_ALPHA_2_PATTERN = Pattern.compile(ISO_3166_1_ALPHA_2_REGEXP);

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for building instance of the specified target class.
     *
     * @param targetClass the target class to build.
     */
    protected MappedCountryBuilder(@Nonnull final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------- countryId
    public String countryId() {
        return countryId;
    }

    public SELF countryId(final String countryId) {
        this.countryId = countryId;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- countryName
    public String countryName() {
        return countryName;
    }

    public SELF countryName(final String countryName) {
        this.countryName = countryName;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- regionId
    public Long regionId() {
        return regionId;
    }

    public SELF regionId(final Long regionId) {
        this.regionId = regionId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String countryId;

    private String countryName;

    private Long regionId;
}
