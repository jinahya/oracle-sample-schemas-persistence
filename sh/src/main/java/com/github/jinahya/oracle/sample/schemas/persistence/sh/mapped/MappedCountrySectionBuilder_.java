package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

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

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCountrySectionBuilder_<
        SELF extends MappedCountrySectionBuilder_<SELF, TARGET>,
        TARGET extends MappedCountrySection_
        >
        extends _MappedShBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountrySectionBuilder_(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------------ name
    public String name() {
        return name;
    }

    public SELF name(final String name) {
        this.name = name;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Long id() {
        return id;
    }

    public SELF id(final Long id) {
        this.id = id;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String name;

    private Long id;
}
