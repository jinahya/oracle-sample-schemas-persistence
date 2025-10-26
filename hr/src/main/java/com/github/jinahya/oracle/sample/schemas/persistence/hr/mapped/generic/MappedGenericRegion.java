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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedRegion;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An abstract mapped-superclass for mapping the {@value MappedGenericRegion#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedGenericRegion<COUNTRY extends MappedGenericCountry<?, ?>> extends MappedRegion {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedGenericRegion() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------- countries
    protected List<COUNTRY> getCountries() {
        return countries;
    }

    public void setCountries(final List<COUNTRY> countries) {
        this.countries = countries;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedGenericCountry.ATTRIBUTE_NAME_REGION, fetch = FetchType.LAZY, cascade = {},
               orphanRemoval = false)
    private List<@Valid @NotNull COUNTRY> countries;
}
