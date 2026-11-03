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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedProductOrder;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * An entity class for mapping the {@value MappedProductOrder#TABLE_NAME} view, whose composite identifier is
 * mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProductOrderId
 */
@NamedQuery(name = "ProductOrder.selectOneByProductNameAndOrderStatus",
            query = """
                    SELECT e
                    FROM ProductOrder e
                    WHERE e.id.productName = :productName
                      AND e.id.orderStatus = :orderStatus"""
)
@NamedQuery(name = "ProductOrder.selectListOrderByProductNameAscOrderStatusAsc",
            query = """
                    SELECT e
                    FROM ProductOrder e
                    ORDER BY e.id.productName ASC, e.id.orderStatus ASC"""
)
@NamedQuery(name = "ProductOrder.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM ProductOrder e"""
)
@Entity
@Table(name = MappedProductOrder.TABLE_NAME)
public class ProductOrder
        extends MappedProductOrder<ProductOrderId> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductOrder() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    @Override
    public ProductOrderId getId() {
        return id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private ProductOrderId id;
}
