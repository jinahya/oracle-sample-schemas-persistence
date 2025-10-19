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
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedCountryBuilder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.util.Optional;

@Entity
@Table(name = MappedCountry.TABLE_NAME)
public class Country extends MappedCountry {

    // -----------------------------------------------------------------------------------------------------------------
    public static MappedCountryBuilder<?, Country> builder() {
        return new CountryBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Country() {
        super();
    }

    Country(@Nonnull final CountryBuilder builder) {
        super(builder);
        setRegion(builder.getRegion());
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- super.countryId

    // ----------------------------------------------------------------------------------------------- super.countryName

    // -------------------------------------------------------------------------------------------------- super.regionId

    // ---------------------------------------------------------------------------------------------------------- region
    @Nullable
    public Region getRegion() {
        return region;
    }

    public void setRegion(@Nullable final Region region) {
        this.region = region;
        setRegionId(
                Optional.ofNullable(this.region)
                        .map(Region::getRegionId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = false, updatable = false)
    private Region region;
}
