package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
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

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCountryBuilder<
        SELF extends MappedCountryBuilder<SELF, TARGET>,
        TARGET extends MappedCountry
        >
        extends _MappedShEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountryBuilder(@Nonnull final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -------------------------------------------------------------------------------------------------------------- id
    public Long id() {
        return id;
    }

    public SELF id(final Long id) {
        this.id = id;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------- countryIsoCode
    public String countryIsoCode() {
        return countryIsoCode;
    }

    public SELF countryIsoCode(final String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String countryName() {
        return countryName;
    }

    public SELF countryName(final String countryName) {
        this.countryName = countryName;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String countrySubregion() {
        return countrySubregion;
    }

    public SELF countrySubregion(final String countrySubregion) {
        this.countrySubregion = countrySubregion;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Long countrySubregionId() {
        return countrySubregionId;
    }

    public SELF countrySubregionId(final Long countrySubregionId) {
        this.countrySubregionId = countrySubregionId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String countryRegion() {
        return countryRegion;
    }

    public SELF countryRegion(final String countryRegion) {
        this.countryRegion = countryRegion;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Long countryRegionId() {
        return countryRegionId;
    }

    public SELF countryRegionId(final Long countryRegionId) {
        this.countryRegionId = countryRegionId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String countryTotal() {
        return countryTotal;
    }

    public SELF countryTotal(final String countryTotal) {
        this.countryTotal = countryTotal;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Long countryTotalId() {
        return countryTotalId;
    }

    public SELF countryTotalId(final Long countryTotalId) {
        this.countryTotalId = countryTotalId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Long id;

    // -----------------------------------------------------------------------------------------------------------------
    private String countryIsoCode;

    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    private String countrySubregion;

    private Long countrySubregionId;

    // -----------------------------------------------------------------------------------------------------------------
    private String countryRegion;

    private Long countryRegionId;

    // -----------------------------------------------------------------------------------------------------------------
    private String countryTotal;

    private Long countryTotalId;
}
