package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountrySection_;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountrySectionBuilder_;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped._MappedShBuilder;
import com.github.jinahya.persistence.mapped.__Mapped;
import com.github.jinahya.persistence.mapped.__MappedBuilder;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;

@Embeddable
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class CountrySection_ extends MappedCountrySection_ {

    // -----------------------------------------------------------------------------------------------------------------
    public static MappedCountrySectionBuilder_<?, CountrySection_> builder() {
        return new CountrySectionBuilder_();
    }

    // TODO: remove!
    static _MappedShBuilder<?, ? extends MappedCountrySection_> builder2() {
        return builder();
    }

    // TODO: remove!
    static __MappedBuilder<?, ? extends __Mapped> builder3() {
        return builder2();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected CountrySection_() {
        super();
    }

    private CountrySection_(@Nonnull final CountrySectionBuilder_ builder) {
        super(builder);
    }
}
