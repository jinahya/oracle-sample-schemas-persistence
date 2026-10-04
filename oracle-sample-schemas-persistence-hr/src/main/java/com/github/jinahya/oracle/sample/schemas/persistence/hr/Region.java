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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Region#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Region.selectListWhereRegionNameIsNull",
        query = """
                SELECT e
                FROM Region AS e
                WHERE e.regionName IS NULL"""
)
@NamedQuery(
        name = "Region.selectListWhereRegionNameIsNotNull",
        query = """
                SELECT e
                FROM Region AS e
                WHERE e.regionName IS NOT NULL"""
)
@NamedQuery(
        name = "Region.selectListWhereRegionNameLike",
        query = """
                SELECT e
                FROM Region AS e
                WHERE e.regionName LIKE :regionNamePattern"""
)
@NamedQuery(
        name = "Region.selectListWhereRegionNameEqual",
        query = """
                SELECT e
                FROM Region AS e
                WHERE e.regionName = :regionName"""
)
@Entity
@Table(name = Region.TABLE_NAME)
public class Region {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "REGIONS";

    // -------------------------------------------------------------------------------------------- REGION_ID / regionId

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    // ---------------------------------------------------------------------------------------- REGION_NAME / regionName

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_REGION_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION_NAME = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_NAME = "regionName";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_REGION_NAME = 0;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_REGION_NAME = COLUMN_LENGTH_REGION_NAME;

    /**
     * The name of the attribute which maps the {@link Country countries} of this region. The value is {@value}.
     *
     * @see Country#ATTRIBUTE_NAME_REGION
     */
    public static final String ATTRIBUTE_NAME_COUNTRIES = "countries";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Region() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "regionId=" + regionId +
               ",regionName=" + regionName +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Region that)) {
            return false;
        }
        return Objects.equals(getRegionId(), that.getRegionId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getRegionId());
    }

    // -------------------------------------------------------------------------------------------------------- regionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    @Nonnull
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_ID} attribute with the specified value.
     *
     * @param regionId new value for {@value #ATTRIBUTE_NAME_REGION_ID} attribute.
     */
    protected void setRegionId(@Nonnull final Long regionId) {
        this.regionId = regionId;
    }

    // ------------------------------------------------------------------------------------------------------ regionName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     */
    @jakarta.annotation.Nullable
    public String getRegionName() {
        return regionName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute with the specified value.
     *
     * @param regionName new value for {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     */
    public void setRegionName(@jakarta.annotation.Nullable final String regionName) {
        this.regionName = regionName;
    }

    // ------------------------------------------------------------------------------------------------------- countries
    List<Country> getCountries() {
        return countries;
    }

    void setCountries(final List<Country> countries) {
        this.countries = countries;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_REGION_ID, nullable = false, insertable = true, updatable = false)
    private Long regionId;

    @jakarta.annotation.Nullable
    @Size(min = SIZE_MIN_REGION_NAME, max = SIZE_MAX_REGION_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_REGION_NAME)
    private String regionName;

    @OneToMany(
            mappedBy = Country.ATTRIBUTE_NAME_REGION,
            fetch = FetchType.LAZY,
            cascade = {
            },
            orphanRemoval = false
    )
    private List<@Valid @NotNull Country> countries;
}
