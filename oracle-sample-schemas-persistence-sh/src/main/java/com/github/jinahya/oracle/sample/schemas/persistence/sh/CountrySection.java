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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An embeddable class for mapping a named section -- a region, a subregion or a total -- of the
 * {@value Country#TABLE_NAME} table: a name column, and the numeric id column that goes with it.
 * <p>
 * No entity of this module embeds this class yet; {@link Country} maps each of those column pairs as plain attributes.
 * The class declares no column names: an embedding entity names both columns of each section with
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}s, e.g.
 * {@code (COUNTRY_SUBREGION, COUNTRY_SUBREGION_ID)} and {@code (COUNTRY_REGION, COUNTRY_REGION_ID)}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class CountrySection {

    // ------------------------------------------------------------------------------------------------------------ NAME

    /**
     * The name of the attribute which maps the name column of a section. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------------------------------------------- ID

    /**
     * The name of the attribute which maps the id column of a section. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CountrySection() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "name=" + name +
               ",id=" + id +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by both {@value #ATTRIBUTE_NAME_NAME} and {@value #ATTRIBUTE_NAME_ID}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof CountrySection that)) {
            return false;
        }
        return Objects.equals(name, that.name) &&
               Objects.equals(id, that.id);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over both {@value #ATTRIBUTE_NAME_NAME} and {@value #ATTRIBUTE_NAME_ID}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(name, id);
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setName(@Nonnull final String name) {
        this.name = name;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    @Nonnull
    public Long getId() {
        return id;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public void setId(@Nonnull final Long id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    // no @Column: the embedding entity defines both columns of each section -- name, length, nullability --
    // through an @AttributeOverride, which replaces any @Column declared here
    private String name;

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    private Long id;
}
