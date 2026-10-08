package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * A superclass for a category -- a category or a subcategory -- of the {@value MappedProduct#TABLE_NAME} table: a
 * name column, the numeric id column, and the description column that go with it.
 * <p>
 * The class declares no column names: an embedding entity names all three columns of each category with
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}s.
 * <table>
 *   <caption>Categories of the {@value MappedProduct#TABLE_NAME} table</caption>
 *   <thead>
 *     <tr>
 *       <th>category</th>
 *       <th>{@value #ATTRIBUTE_NAME_NAME}</th>
 *       <th>{@value #ATTRIBUTE_NAME_ID}</th>
 *       <th>{@value #ATTRIBUTE_NAME_DESC}</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>subcategory</td>
 *       <td>{@value MappedProduct#COLUMN_NAME_PROD_SUBCATEGORY}</td>
 *       <td>{@value MappedProduct#COLUMN_NAME_PROD_SUBCATEGORY_ID}</td>
 *       <td>{@value MappedProduct#COLUMN_NAME_PROD_SUBCATEGORY_DESC}</td>
 *     </tr>
 *     <tr>
 *       <td>category</td>
 *       <td>{@value MappedProduct#COLUMN_NAME_PROD_CATEGORY}</td>
 *       <td>{@value MappedProduct#COLUMN_NAME_PROD_CATEGORY_ID}</td>
 *       <td>{@value MappedProduct#COLUMN_NAME_PROD_CATEGORY_DESC}</td>
 *     </tr>
 *   </tbody>
 * </table>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedProduct
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class MappedProductCategory {

    // ------------------------------------------------------------------------------------------------------------ NAME

    /**
     * The name of the attribute which maps the name column of a category. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------------------------------------------- ID

    /**
     * The name of the attribute which maps the id column of a category. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------------------------ DESC

    /**
     * The name of the attribute which maps the description column of a category. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESC = "desc";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductCategory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "name=" + name +
               ",id=" + id +
               ",desc=" + desc +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by all of {@value #ATTRIBUTE_NAME_NAME}, {@value #ATTRIBUTE_NAME_ID} and
     * {@value #ATTRIBUTE_NAME_DESC}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedProductCategory that)) {
            return false;
        }
        return Objects.equals(getName(), that.getName())
               && Objects.equals(getId(), that.getId())
               && Objects.equals(getDesc(), that.getDesc());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over all of {@value #ATTRIBUTE_NAME_NAME}, {@value #ATTRIBUTE_NAME_ID} and
     * {@value #ATTRIBUTE_NAME_DESC}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getName(), getId(), getDesc());
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

    // ------------------------------------------------------------------------------------------------------------ desc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESC} attribute.
     */
    @Nonnull
    public String getDesc() {
        return desc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESC} attribute with the specified value.
     *
     * @param desc new value for {@value #ATTRIBUTE_NAME_DESC} attribute.
     */
    public void setDesc(@Nonnull final String desc) {
        this.desc = desc;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    // no @Column: the embedding entity defines all three columns of each category -- name, length, nullability --
    // through an @AttributeOverride, which replaces any @Column declared here
    private String name;

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    private Long id;

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    private String desc;
}
