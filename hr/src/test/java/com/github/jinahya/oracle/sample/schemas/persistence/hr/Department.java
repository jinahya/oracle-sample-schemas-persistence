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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
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
import java.util.Optional;

/**
 * .
 *
 * @author Myoungkwon Hwang
 */
@NamedQuery(
        name = """
                Department.\
                SelectList_\
                Where\
                LocationCountryRegionEqual_\
                OrderBy\
                LocationCountryRegionRegionIdAsc\
                LocationCountryCountryIdAsc\
                LocationStateProvinceAsc\
                DepartmentIdAsc""",
        query = """
                SELECT e
                FROM Department e
                WHERE e.location.country.region = :region
                """
)
@NamedQuery(
        name = """
                Department.\
                SelectList_\
                Where\
                LocationCountryEqual_\
                OrderBy\
                LocationCountryCountryIdAsc\
                LocationStateProvinceAsc\
                DepartmentIdAsc""",
        query = """
                SELECT e
                FROM Department e
                WHERE e.location.country = :country
                """
)
@NamedQuery(
        name = """
                Department.\
                SelectList_\
                Where\
                LocationEqual_\
                OrderBy\
                DepartmentIdAsc""",
        query = """
                SELECT e
                FROM Department e
                WHERE e.location = :location
                """
)
@Entity
@Table(name = MappedDepartment.TABLE_NAME)
class Department extends MappedDepartment {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Department() {
        super();
    }

    Department(final DepartmentBuilder builder) {
        super(builder);
        if (getLocationId() == null) {
            setLocation(builder.location());
        }
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final boolean equals(final Object obj) {
        return equalsWithDepartmentId(obj);
    }

    @Override
    public final int hashCode() {
        return hashCodeWithDepartmentId();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation

    // --------------------------------------------------------------------------------------------------------- manager
    @Nullable
    public Employee getManager() {
        return manager;
    }

    public void setManager(@Nullable final Employee manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------------------------------- location
    @Nullable
    public Location getLocation() {
        return location;
    }

    public void setLocation(@Nullable final Location location) {
        this.location = location;
        setLocationId(
                Optional.ofNullable(this.location)
                        .map(MappedLocation::getLocationId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- employees
    List<Employee> getEmployees() {
        return employees;
    }

    void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID,
                nullable = COLUMN_NULLABLE_MANAGER_ID,
                insertable = false,
                updatable = false
    )
    private Employee manager;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                nullable = COLUMN_NULLABLE_LOCATION_ID,
                insertable = false,
                updatable = false
    )
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> employees;
}
