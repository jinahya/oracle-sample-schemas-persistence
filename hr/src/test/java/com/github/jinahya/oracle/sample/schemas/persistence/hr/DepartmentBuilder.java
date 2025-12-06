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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartmentBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;

import java.util.Optional;

class DepartmentBuilder extends MappedDepartmentBuilder<DepartmentBuilder, Department> {

    DepartmentBuilder() {
        super(Department.class);
    }

    // --------------------------------------------------------------------------------------------------------- manager
    public Employee getManager() {
        return manager;
    }

    public DepartmentBuilder setManager(final Employee manager) {
        this.manager = manager;
        return managerId(
                Optional.of(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // -------------------------------------------------------------------------------------------------------- location
    public Location location() {
        return location;
    }

    public DepartmentBuilder location(final Location location) {
        this.location = location;
        return locationId(
                Optional.ofNullable(this.location)
                        .map(MappedLocation::getLocationId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Employee manager;

    private Location location;
}
