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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;
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

import java.util.List;

/**
 * An entity class for mapping the {@value Location#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Location.select_WhereCountryIdEqualStateProvinceEqualCityEqual_OrderByStreetAddressAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.country.countryId = :countryId
                  AND e.stateProvince = :stateProvince
                  AND e.city = :city
                ORDER BY e.streetAddress ASC"""
)
@NamedQuery(
        name = "Location.select_WhereCountryIdEqualCityEqual_OrderByStreetAddressAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.country.countryId = :countryId
                  AND e.city = :city
                ORDER BY e.streetAddress ASC"""
)
@NamedQuery(
        name = "Location.select_WhereCountryIdEqualStateProvinceEqual_OrderByStreetAddressAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.country.countryId = :countryId
                  AND e.stateProvince = :stateProvince
                ORDER BY e.streetAddress ASC"""
)
@NamedQuery(
        name = "Location.select_WhereCountryIdEqual_OrderByLocationIdAsc",
        query = """
                SELECT e
                FROM Location e
                WHERE e.country.countryId = :countryId
                ORDER BY e.locationId ASC"""
)
@NamedQuery(
        name = "Location.select_OrderByLocationIdAsc",
        query = """
                SELECT e
                FROM Location e
                ORDER BY e.locationId ASC"""
)
@Entity
@Table(name = Location.TABLE_NAME)
public class Location extends MappedLocation implements __DomainEntity<Integer> {

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    /**
     * The name of the attribute which maps the {@link Department department}s of this location. The value is {@value}.
     *
     * @see Department#ATTRIBUTE_NAME_LOCATION
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENTS = "departments";

    static {
        assert COLUMN_LENGTH_COUNTRY_ID == Country.COLUMN_LENGTH_COUNTRY_ID;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Location() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- country

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     */
    @Nullable
    public Country getCountry() {
        return country;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute with the specified value.
     *
     * @param country new value for {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     */
    public void setCountry(@Nullable final Country country) {
        this.country = country;
    }

    // ----------------------------------------------------------------------------------------------------- departments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENTS} attribute.
     */
    public List<Department> getDepartments() {
        return departments;
    }

    void setDepartments(final List<Department> departments) {
        this.departments = departments;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = Location.COLUMN_NAME_COUNTRY_ID,
                nullable = COLUMN_NULLABLE_COUNTRY_ID,
                insertable = true,
                updatable = true
    )
    private Country country;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(
            mappedBy = Department.ATTRIBUTE_NAME_LOCATION,
            fetch = FetchType.LAZY,
            cascade = {
            },
            orphanRemoval = false
    )
    private List<@Valid @NotNull Department> departments;
}
