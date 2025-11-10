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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;
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

@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedGenericDepartment<
        EMPLOYEE extends MappedGenericEmployee<?, EMPLOYEE, ?>,
        LOCATION extends MappedGenericLocation<?, ?>
        >
        extends MappedDepartment {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedGenericDepartment() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    public EMPLOYEE getManager() {
        return manager;
    }

    public void setManager(@Nullable final EMPLOYEE manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(@Nullable final LOCATION location) {
        this.location = location;
        setLocationId(
                Optional.ofNullable(this.location)
                        .map(MappedLocation::getLocationId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected List<EMPLOYEE> getEmployees() {
        return employees;
    }

    protected void setEmployees(final List<EMPLOYEE> employees) {
        this.employees = employees;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = MappedDepartment.COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private EMPLOYEE manager;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = MappedDepartment.COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private LOCATION location;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull EMPLOYEE> employees;
}
