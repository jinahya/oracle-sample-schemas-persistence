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

import com.github.jinahya.oracle.sample.persistence.hr.mapped.MappedRegion;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An entity class for mapping the {@value MappedRegion#TABLE_NAME} table.
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
@NamedQuery(name = "Region.selectOneByRegionId",
            query = """
                    SELECT e
                    FROM Region e
                    WHERE e.regionId = :regionId"""
)
@NamedQuery(name = "Region.selectListOrderByRegionIdAsc",
            query = """
                    SELECT e
                    FROM Region e
                    ORDER BY e.regionId ASC"""
)
@NamedQuery(name = "Region.selectListOrderByRegionIdAscRegionIdGt",
            query = """
                    SELECT e
                    FROM Region e
                    WHERE e.regionId > :regionIdMinExclusive
                    ORDER BY e.regionId ASC"""
)
@NamedQuery(name = "Region.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Region e"""
)
@Entity
@Table(name = MappedRegion.TABLE_NAME)
public class Region extends MappedRegion {

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

    // ------------------------------------------------------------------------------------------------------- countries
    List<Country> getCountries() {
        return countries;
    }

    void setCountries(final List<Country> countries) {
        this.countries = countries;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(
            mappedBy = Country.ATTRIBUTE_NAME_REGION,
            fetch = FetchType.LAZY,
            cascade = {
            },
            orphanRemoval = false
    )
    private List<@Valid @NotNull Country> countries;
}
