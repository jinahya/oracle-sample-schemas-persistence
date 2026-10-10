package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedStoreOrder;

import java.math.BigDecimal;

/**
 * A class for the {@value StoreOrder#TABLE_NAME} view.
 * <p>
 * The {@code GROUPING SETS} key {@code (STORE_NAME, ORDER_STATUS)} has no duplicates, but both columns are {@code NULL}
 * in the subtotal and grand-total rows, and an {@code @Id} may not be null. So this is not an
 * {@link jakarta.persistence.Entity @Entity}: the columns it projects are written out here, and every one of them is
 * read-only. See {@code doc/IDs.asciidoc}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class StoreOrder extends MappedStoreOrder implements __DomainEntity<Void> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StoreOrder() {
        super();
    }

    /**
     * Creates a new instance with the specified attribute values.
     *
     * @param total       a value for the {@value #ATTRIBUTE_NAME_TOTAL} attribute.
     * @param storeName   a value for the {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     * @param address     a value for the {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     * @param latitude    a value for the {@value #ATTRIBUTE_NAME_LATITUDE} attribute.
     * @param longitude   a value for the {@value #ATTRIBUTE_NAME_LONGITUDE} attribute.
     * @param orderStatus a value for the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     * @param orderCount  a value for the {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     * @param totalSales  a value for the {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public StoreOrder(final String total,
                      final String storeName,
                      final String address,
                      final BigDecimal latitude,
                      final BigDecimal longitude,
                      final String orderStatus,
                      final BigDecimal orderCount,
                      final BigDecimal totalSales) {
        super(total, storeName, address, latitude, longitude, orderStatus, orderCount, totalSales);
    }
}
