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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;
import jakarta.annotation.Nullable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;

/**
 * An abstract mapped superclass for mapping {@value MappedGenericLocation#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedGenericLocation<
        COUNTRY extends MappedGenericCountry<?, ?>
        >
        extends MappedLocation {

    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedGenericLocation() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------ super.locationId

    // --------------------------------------------------------------------------------------------- super.streetAddress

    // ------------------------------------------------------------------------------------------------ super.postalCode

    // ------------------------------------------------------------------------------------------------------ super.city

    // --------------------------------------------------------------------------------------------- super.stateProvince

    // ------------------------------------------------------------------------------------------------- super.countryId
    @Nullable
    @Override
    public String getCountryId() {
        return super.getCountryId();
    }

    /**
     * {@inheritDoc}
     *
     * @param countryId {@inheritDoc}
     * @deprecated Use {@link #setCountry(MappedGenericCountry)} instead.
     */
    @Deprecated(forRemoval = true)
    @Override
    public void setCountryId(@Nullable final String countryId) {
        super.setCountryId(countryId);
    }

    // --------------------------------------------------------------------------------------------------------- country
    @Nullable
    public COUNTRY getCountry() {
        return country;
    }

    public void setCountry(@Nullable final COUNTRY country) {
        this.country = country;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_ID, nullable = true, insertable = false, updatable = false)
    private COUNTRY country;
}
