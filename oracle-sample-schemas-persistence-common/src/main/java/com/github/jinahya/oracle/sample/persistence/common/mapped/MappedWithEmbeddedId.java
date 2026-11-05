package com.github.jinahya.oracle.sample.persistence.common.mapped;

/*-
 * #%L
 * oracle-sample-schemas-persistence-common
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
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

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * A mapped superclass for a table whose key is an {@link EmbeddedId @EmbeddedId}, every column of
 * which an entity derives from an association with {@link jakarta.persistence.MapsId @MapsId}.
 * <p>
 * The key starts {@code null}; the provider creates it, and fills it from the associations, when
 * the entity is persisted.
 *
 * @param <T> the type of the key.
 */
@MappedSuperclass
public abstract class MappedWithEmbeddedId<T> {

    /**
     * The name of the attribute which embeds the key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ = "id_";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWithEmbeddedId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The key, which a subclass need not repeat; a subclass appends its own
     *         attributes to what this method returns.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "id_=" + getId_()
               + "}";
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns the key of this instance.
     *
     * @return the key of this instance; {@code null} until the instance is persisted.
     * @implNote A subclass's {@code equals} and {@code hashCode} read the key through this
     *         method, not the field, so that a Hibernate proxy, whose own fields are unset, answers
     *         with the key of the row it stands for. The method is {@code protected}, not
     *         package-private, since a proxy is a subclass in the package of the entity, and
     *         overrides only what that package sees.
     */
    protected T getId_() {
        return id_;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private T id_;
}
