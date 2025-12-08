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
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@NamedQuery(
        name = """
                Country.\
                SelectList_\
                Where\
                RegionRegionIdEqual_\
                OrderBy\
                CountryIdAsc""",
        query = """
                SELECT e
                FROM Country e
                WHERE e.region.regionId = : regionRegionId
                """
)
@NamedQuery(
        name = """
                Country.\
                SelectList_\
                Where\
                RegionIdEqual_\
                OrderBy\
                CountryIdAsc""",
        query = """
                SELECT e
                FROM Country e
                WHERE e.regionId = : regionId
                """
)
@Entity
@Table(name = MappedCountry.TABLE_NAME)
class Country extends MappedCountry {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static CountryBuilder builder() {
        return new CountryBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Country() {
        super();
    }

    Country(@Nonnull final CountryBuilder builder) {
        super(builder);
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

    // ------------------------------------------------------------------------------------------------------- locations
    List<Location> getLocations() {
        return locations;
    }

    void setLocations(final List<Location> locations) {
        this.locations = locations;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true,
                insertable = false,
//                insertable = true, // eclipselink
                updatable = false
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Region region;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Location.ATTRIBUTE_NAME_COUNTRY,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<@Valid @NotNull Location> locations;
}
