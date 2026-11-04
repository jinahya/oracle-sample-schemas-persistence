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

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * A mapped superclass which holds the identifier of a table, or a view, keyed by a single
 * {@code IDENTITY} column, mapped as a {@link Long} whatever the column's width.
 * <p>
 * The class names no column; each extending class names its own, and sets its nullability and
 * writability, with an {@link jakarta.persistence.AttributeOverride @AttributeOverride} of the
 * {@value #ATTRIBUTE_NAME_ID_} attribute, whose column replaces the default one as a whole.
 * <p>
 * The accessors are protected; each extending class exposes the identifier with a getter of its
 * own, named after its column.
 */
@MappedSuperclass
public abstract class MappedWithIdentityId {

    /**
     * The name of the identifier attribute, whose column an extending class overrides. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ = "id_";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWithIdentityId() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_
    // not final, for a lazy proxy overrides it

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    protected Long getId_() {
        return id_;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID_} attribute with the specified value.
     *
     * @param id_ new value for {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    protected void setId_(final Long id_) {
        this.id_ = id_;
    }

    // ---------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_;
}

