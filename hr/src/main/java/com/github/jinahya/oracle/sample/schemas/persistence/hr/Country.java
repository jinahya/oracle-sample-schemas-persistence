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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Country#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Country.TABLE_NAME)
public class Country {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * The length of the {@code COUNTRY_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    /**
     * The name of the attribute which maps the {@code COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    /**
     * The minimum size of the {@code countryId} attribute.
     */
    public static final int ATTRIBUTE_SIZE_MIN_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    /**
     * The maximum size of the {@code countryId} attribute.
     */
    public static final int ATTRIBUTE_SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    /**
     * The name of the table column to which the {@code countryName} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    /**
     * The length of the {@code COUNTRY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    /**
     * The name of the attribute which maps the {@code COUNTRY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    /**
     * The minimum size of the {@code countryName} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_SIZE_NIN_COUNTRY_NAME = 0;

    /**
     * The maximum size of the {@code countryName} attribute.
     */
    public static final int ATTRIBUTE_SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ------------------------------------------------------------------------------------------------------- REGION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_REGION_ID} column maps. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    /**
     * The name of the attribute which maps the {@link Location location}s of this country. The value is {@value}.
     *
     * @see Location#ATTRIBUTE_NAME_COUNTRY
     */
    public static final String ATTRIBUTE_NAME_LOCATIONS = "locations";

    /**
     * The name of the entity attribute, of  {@link Region}, from which the {@value #COLUMN_NAME_REGION_ID} column maps.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Country() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "countryId=" + countryId +
               ",countryName=" + countryName +
               ",regionId=" + regionId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Country that)) {
            return false;
        }
        return Objects.equals(getCountryId(), that.getCountryId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getCountryId());
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@code countryId} attribute.
     *
     * @return the current value of {@code countryId} attribute.
     */
    @Nonnull
    public String getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@code countryId} attribute with the specified value.
     *
     * @param countryId new value for the {@code countryId} attribute.
     */
    protected void setCountryId(@Nonnull final String countryId) {
        this.countryId = countryId;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@code countryName} attribute.
     *
     * @return the current value of {@code countryName} attribute.
     */
    @jakarta.annotation.Nullable
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@code countryName} attribute with the specified value.
     *
     * @param countryName new value for the {@code countryName} attribute.
     */
    public void setCountryName(@jakarta.annotation.Nullable final String countryName) {
        this.countryName = countryName;
    }

    // -------------------------------------------------------------------------------------------------------- regionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    @jakarta.annotation.Nullable
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute with the specified value.
     *
     * @param regionId new value for {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    protected void setRegionId(@jakarta.annotation.Nullable final Long regionId) {
        this.regionId = regionId;
    }

    // ---------------------------------------------------------------------------------------------------------- region

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    @jakarta.annotation.Nullable
    public Region getRegion() {
        return region;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION} attribute with the specified value.
     *
     * @param region new value for {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    public void setRegion(@jakarta.annotation.Nullable final Region region) {
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

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(min = ATTRIBUTE_SIZE_MIN_COUNTRY_ID, max = ATTRIBUTE_SIZE_MAX_COUNTRY_ID)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;

    @jakarta.annotation.Nullable
    @Size(min = ATTRIBUTE_SIZE_NIN_COUNTRY_NAME, max = ATTRIBUTE_SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    @jakarta.annotation.Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = true, updatable = true)
    private Long regionId;

    @jakarta.annotation.Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true,
                insertable = false,
//                insertable = true, // eclipselink
                updatable = false
    )
    private Region region;

    @OneToMany(mappedBy = Location.ATTRIBUTE_NAME_COUNTRY,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Location> locations;
}
