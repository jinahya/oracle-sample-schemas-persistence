package com.github.jinahya.oracle.sample.schemas.persistence.sh;

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

import jakarta.persistence.MappedSuperclass;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A name and the number which identifies it, as the {@value Promotion#TABLE_NAME} table carries such pairs:
 * {@code PROMO_CATEGORY} with {@code PROMO_CATEGORY_ID}, say. A concrete subclass is an {@code @Embeddable} which names
 * the two columns, through class-level {@code @AttributeOverride}s, and exposes the two attributes under the column's
 * own names.
 * <p>
 * Equality is not defined here: each concrete class defines its own, over the two attributes and within that class.
 *
 * @param <N> the type of the id.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class PromotionNamedId<N extends Number> {

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * The name of the attribute which holds the name; a subclass overrides its column with an
     * {@link jakarta.persistence.AttributeOverride @AttributeOverride} by this name. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the attribute which holds the id; a subclass overrides its column with an
     * {@link jakarta.persistence.AttributeOverride @AttributeOverride} by this name. The value is {@value}.
     */
    static final String ATTRIBUTE_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance from the specified initializer, with the specified name and id.
     *
     * @param initializer a supplier of a new, empty instance of the concrete class.
     * @param name        the name.
     * @param id          the id.
     * @param <T>         the concrete class.
     * @param <N>         the type of the id.
     * @return the instance the initializer gave, with the name and id set.
     * @throws NullPointerException if {@code initializer} is {@code null}, or gives {@code null}.
     */
    static <T extends PromotionNamedId<N>, N extends Number>
    T of(final Supplier<? extends T> initializer, final String name, final N id) {
        Objects.requireNonNull(initializer, "initializer is null");
        final var instance = Objects.requireNonNull(initializer.get(), "initializer.get() is null");
        instance.setName(name);
        instance.setId(id);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PromotionNamedId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    final String getName() {
        return name;
    }

    /**
     * Replaces current value of the {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for the {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    final void setName(final String name) {
        this.name = name;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of the {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    final N getId() {
        return id;
    }

    /**
     * Replaces current value of the {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for the {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    final void setId(final N id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String name;

    private N id;
}
