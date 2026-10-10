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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedProductReview;

import java.math.BigDecimal;

/**
 * A class for the {@value ProductReview#TABLE_NAME} view.
 * <p>
 * No column, and no combination of columns, identifies a row of this view: the narrowest unique combination is
 * {@code (PRODUCT_NAME, REVIEW)}, and {@code REVIEW} is nullable and 4000 characters wide. So this is not an
 * {@link jakarta.persistence.Entity @Entity}: the columns it projects are written out here, and every one of them is
 * read-only. See {@code doc/IDs.asciidoc}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ProductReview extends MappedProductReview implements __DomainEntity<Void> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductReview() {
        super();
    }

    /**
     * Creates a new instance with the specified attribute values.
     *
     * @param productName a value for the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     * @param rating      a value for the {@value #ATTRIBUTE_NAME_RATING} attribute.
     * @param avgRating   a value for the {@value #ATTRIBUTE_NAME_AVG_RATING} attribute.
     * @param review      a value for the {@value #ATTRIBUTE_NAME_REVIEW} attribute.
     */
    public ProductReview(final String productName,
                         final Integer rating,
                         final BigDecimal avgRating,
                         final String review) {
        super(productName, rating, avgRating, review);
    }
}
