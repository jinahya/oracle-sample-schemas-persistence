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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegion;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegionBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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
@Table(name = MappedRegion.TABLE_NAME)
class Region extends MappedRegion {

    public static final String ATTRIBUTE_NAME_COUNTRIES = "countries";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // --------------------------------------------------------------------------------------------------------- BUILDER
    public static MappedRegionBuilder<?, Region> builder() {
        return new RegionBuilder();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Region() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    Region(final RegionBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final boolean equals(final Object obj) {
        return equalsWithRegionId(obj);
    }

    @Override
    public final int hashCode() {
        return hashCodeWithRegionId();
    }

    // -------------------------------------------------------------------------------------------------- super.regionId

    // ------------------------------------------------------------------------------------------------ super.regionName

    // ------------------------------------------------------------------------------------------------------- countries
    List<Country> getCountries() {
        return countries;
    }

    void setCountries(final List<Country> countries) {
        this.countries = countries;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(
            mappedBy = Country_.REGION,
            fetch = FetchType.LAZY,
            cascade = {
            },
            orphanRemoval = false
    )
    private List<@Valid @NotNull Country> countries;
}
