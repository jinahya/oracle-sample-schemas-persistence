package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.generic;

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
import jakarta.annotation.Nullable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * An abstract mapped-superclass for mapping the {@value MappedGenericCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedGenericCountry<
        REGION extends MappedGenericRegion<?>,
        LOCATION extends MappedGenericLocation<?>
        >
        extends MappedCountry {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String ATTRIBUTE_NAME_REGION = "region";

    public static final String ATTRIBUTE_NAME_LOCATIONS = "locations";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedGenericCountry() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- super.countryId

    // ----------------------------------------------------------------------------------------------- super.countryName

    // -------------------------------------------------------------------------------------------------- super.regionId
    @Nullable
    @Override
    public Long getRegionId() {
        return super.getRegionId();
    }

    @Deprecated(forRemoval = true)
    @Override
    public void setRegionId(@Nullable final Long regionId) {
        super.setRegionId(regionId);
    }

    // ---------------------------------------------------------------------------------------------------------- region
    @Nullable
    public REGION getRegion() {
        return region;
    }

    public void setRegion(@Nullable final REGION region) {
        this.region = region;
        setRegionId(
                Optional.ofNullable(this.region)
                        .map(MappedGenericRegion::getRegionId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- locations
    protected List<LOCATION> getLocations() {
        return locations;
    }

    protected void setLocations(final List<LOCATION> locations) {
        this.locations = locations;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = false, updatable = false)
    private REGION region;

    @OneToMany(mappedBy = MappedGenericLocation.ATTRIBUTE_NAME_COUNTRY, fetch = FetchType.LAZY, cascade = {},
               orphanRemoval = false)
    private List<@Valid @NotNull LOCATION> locations;
}
