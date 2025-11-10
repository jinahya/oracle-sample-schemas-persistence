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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegion;
import jakarta.annotation.Nullable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.FetchType.LAZY;

/**
 * An abstract mapped-superclass for mapping the {@value MappedGenericEmployee#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedGenericEmployee<
        JOB extends MappedGenericJob,
        EMPLOYEE extends MappedGenericEmployee<JOB, EMPLOYEE, DEPARTMENT>,
        DEPARTMENT extends MappedGenericDepartment<EMPLOYEE, ?>
        >
        extends MappedEmployee {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedGenericEmployee() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = MappedEmployee.COLUMN_NAME_JOB_ID, nullable = false, insertable = false, updatable = false)
    private JOB job;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = LAZY)
    @JoinColumn(name = MappedEmployee.COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private EMPLOYEE manager;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = LAZY)
    @JoinColumn(name = MappedEmployee.COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false)
    private DEPARTMENT department;
}
