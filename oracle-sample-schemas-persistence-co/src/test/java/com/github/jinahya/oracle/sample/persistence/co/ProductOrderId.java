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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedProductOrderId;
import jakarta.persistence.Embeddable;

/**
 * An embeddable class for the composite identifier of the {@link ProductOrder} entity class, which maps it with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class ProductOrderId
        extends MappedProductOrderId {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param productName the {@value ProductOrder#COLUMN_NAME_PRODUCT_NAME} column value.
     * @param orderStatus the {@value ProductOrder#COLUMN_NAME_ORDER_STATUS} column value.
     * @return a new instance with the specified column values.
     */
    public static ProductOrderId of(final String productName, final String orderStatus) {
        final var instance = new ProductOrderId();
        instance.setProductName(productName);
        instance.setOrderStatus(orderStatus);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductOrderId() {
        super();
    }
}
