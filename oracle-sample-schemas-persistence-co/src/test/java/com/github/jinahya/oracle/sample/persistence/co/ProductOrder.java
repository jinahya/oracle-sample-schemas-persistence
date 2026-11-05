package com.github.jinahya.oracle.sample.persistence.co;

/*-
 * #%L
 * co
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

import com.github.jinahya.oracle.sample.persistence.co.mapped.ProductOrderId;
import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedProductOrder;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * An entity class for mapping the {@value MappedProductOrder#TABLE_NAME} view, whose composite
 * identifier is mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProductOrderId
 */
@Entity
@Table(name = MappedProductOrder.TABLE_NAME,
       comment = "A summary of the state of the orders placed for each product")
public class ProductOrder
        extends MappedProductOrder {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductOrder() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    public ProductOrderId getId_() {
        return id_;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private ProductOrderId id_;
}
