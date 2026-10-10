package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

/*-
 * #%L
 * co
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A class which holds the mappings of the {@value MappedProductReview#TABLE_NAME} view.
 * <p>
 * No column, and no combination of columns, identifies a row of this view: the narrowest unique combination is
 * {@code (PRODUCT_NAME, REVIEW)}, and {@code REVIEW} is nullable and 4000 characters wide. So no
 * {@link jakarta.persistence.Entity @Entity} can map this view, and this class is not a
 * {@link jakarta.persistence.MappedSuperclass @MappedSuperclass} for one either: the columns the view projects are
 * written out here, and every one of them is read-only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class MappedProductReview implements __MappedDomainEntity<Void> {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCT_REVIEWS";

    // ---------------------------------------------------------------------------------------------------- PRODUCT_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NAME = 255;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PRODUCT_NAME = COLUMN_LENGTH_PRODUCT_NAME;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    // ---------------------------------------------------------------------------------------------------------- RATING

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_RATING} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATING = "RATING";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATING = "rating";

    // ------------------------------------------------------------------------------------------------------ AVG_RATING

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_AVG_RATING} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_AVG_RATING = "AVG_RATING";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_RATING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_RATING = "avgRating";

    // ---------------------------------------------------------------------------------------------------------- REVIEW

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_REVIEW} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVIEW = "REVIEW";

    /**
     * The length of the {@value #COLUMN_NAME_REVIEW} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REVIEW = 4000;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_REVIEW} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_REVIEW = COLUMN_LENGTH_REVIEW;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVIEW} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVIEW = "review";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductReview() {
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
    protected MappedProductReview(final String productName,
                                  final Integer rating,
                                  final BigDecimal avgRating,
                                  final String review) {
        super();
        this.productName = productName;
        this.rating = rating;
        this.avgRating = avgRating;
        this.review = review;
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "productName=" + productName +
               ",rating=" + rating +
               ",avgRating=" + avgRating +
               ",review=" + review +
               '}';
    }

    // ----------------------------------------------------------------------------------------------------- productName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute with the specified value.
     *
     * @param productName new value for {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    // ---------------------------------------------------------------------------------------------------------- rating

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATING} attribute.
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATING} attribute with the specified value.
     *
     * @param rating new value for {@value #ATTRIBUTE_NAME_RATING} attribute.
     */
    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    // ------------------------------------------------------------------------------------------------------- avgRating

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_RATING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_RATING} attribute.
     */
    public BigDecimal getAvgRating() {
        return avgRating;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_RATING} attribute with the specified value.
     *
     * @param avgRating new value for {@value #ATTRIBUTE_NAME_AVG_RATING} attribute.
     */
    public void setAvgRating(final BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    // ---------------------------------------------------------------------------------------------------------- review

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVIEW} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVIEW} attribute.
     */
    public String getReview() {
        return review;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVIEW} attribute with the specified value.
     *
     * @param review new value for {@value #ATTRIBUTE_NAME_REVIEW} attribute.
     */
    public void setReview(final String review) {
        this.review = review;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_PRODUCT_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PRODUCT_NAME)
    private String productName;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RATING, nullable = true, insertable = false, updatable = false)
    private Integer rating;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_RATING, nullable = true, insertable = false, updatable = false)
    private BigDecimal avgRating;

    @Size(max = SIZE_MAX_REVIEW)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REVIEW,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_REVIEW)
    private String review;
}
