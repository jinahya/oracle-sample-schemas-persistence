package com.github.jinahya.oracle.sample.persistence.hr;

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

import com.github.jinahya.oracle.sample.persistence.hr.mapped.MappedCountry;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * An entity class for mapping the {@value MappedCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = MappedCountry.TABLE_NAME, comment = "country table. References with locations table.")
public class Country
        extends MappedCountry {

    // ----------------------------------------------------------------------------------- REGION_ID

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ----------------------------------------------------------------------------------- LOCATIONS

    /**
     * The name of the attribute which maps the {@link Location location}s of this country. The
     * value is {@value}.
     *
     * @see Location#ATTRIBUTE_NAME_COUNTRY
     */
    public static final String ATTRIBUTE_NAME_LOCATIONS = "locations";

    // ---------------------------------------------------------------------------------------------
    static final Comparator<Country> comparingCountryId =
            Comparator.comparing(Country::getCountryId);

    static Comparator<Country> comparingCountryName(
            final Comparator<? super String> nameComparator) {
        return Comparator.comparing(Country::getCountryName, nameComparator);
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Country() {
        super();
    }

    // ----------------------------------------------------------------------------------- countryId

    /**
     * {@inheritDoc}
     *
     * @param countryId {@inheritDoc}
     * @implNote Re-declared so that the code in this package, which the identifier is
     *         assigned by, can call it.
     */
    @Override
    protected void setCountryId(@Nonnull final String countryId) {
        super.setCountryId(countryId);
    }

    // -------------------------------------------------------------------------------------- region

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    @Nullable
    public Region getRegion() {
        return region;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION} attribute with the specified value,
     * and the {@value #ATTRIBUTE_NAME_REGION_ID} attribute with its identifier.
     *
     * @param region new value for {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    public void setRegion(@Nullable final Region region) {
        this.region = region;
        setRegionId(
                Optional.ofNullable(region)
                        .map(Region::getRegionId)
                        .orElse(null)
        );
    }

    // ----------------------------------------------------------------------------------- locations
    List<Location> getLocations() {
        return locations;
    }

    void setLocations(final List<Location> locations) {
        this.locations = locations;
    }

    // ---------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true,
                insertable = true,
                updatable = true
    )
    private Region region;

    // ---------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = Location.ATTRIBUTE_NAME_COUNTRY,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Location> locations;
}
