package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * A class for mapping the JSON document stored in the {@value Product#COLUMN_NAME_PRODUCT_DETAILS} column of the
 * {@value Product#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ProductDetails {

    /**
     * A class for mapping elements of {@code $.reviews} path.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static class Review {

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        /**
         * Creates a new instance.
         */
        public Review() {
            super();
        }

        // -------------------------------------------------------------------------------------------- java.lang.Object

        @Override
        public String toString() {
            return super.toString() + '{' +
                   "rating=" + rating +
                   ",review=" + review +
                   '}';
        }

        /**
         * {@inheritDoc}
         *
         * @param obj {@inheritDoc}
         * @return {@inheritDoc}
         * @implSpec Equality is by both {@code rating} and {@code review}, for an object of exactly this class.
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final var casted = (Review) obj;
            return Objects.equals(rating, casted.rating)
                   && Objects.equals(review, casted.review);
        }

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         * @implSpec The hash is over both {@code rating} and {@code review}, consistent with {@link #equals(Object)}.
         */
        @Override
        public int hashCode() {
            return Objects.hash(rating, review);
        }

        // ------------------------------------------------------------------------------------------------------ rating

        /**
         * Returns current value of {@code rating} attribute.
         *
         * @return current value of {@code rating} attribute.
         */
        public Integer getRating() {
            return rating;
        }

        /**
         * Replaces current value of {@code rating} attribute with the specified value.
         *
         * @param rating new value for {@code rating} attribute.
         */
        public void setRating(final Integer rating) {
            this.rating = rating;
        }

        // ------------------------------------------------------------------------------------------------------ review

        /**
         * Returns current value of {@code review} attribute.
         *
         * @return current value of {@code review} attribute.
         */
        @Nullable
        public String getReview() {
            return review;
        }

        /**
         * Replaces current value of {@code review} attribute with the specified value.
         *
         * @param review new value for {@code review} attribute.
         */
        public void setReview(@Nullable final String review) {
            this.review = review;
        }

        // -------------------------------------------------------------------------------------------------------------
        // TODO: remove; not constrained by the DDL -- PRODUCTS.PRODUCT_DETAILS has only an IS JSON check; 1-10 is from a column comment
//        @Max(10L)
        // TODO: remove; not constrained by the DDL -- PRODUCTS.PRODUCT_DETAILS has only an IS JSON check; 1-10 is from a column comment
//        @Min(1L)
        private Integer rating;

        @Nullable
        private String review;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductDetails() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "colour=" + colour +
               ",gender=" + gender +
               ",brand=" + brand +
               ",description=" + description +
               ",sizes=" + sizes +
//                ",reviews=" + reviews +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by every attribute -- {@code colour}, {@code gender}, {@code brand}, {@code description},
     * {@code sizes} and {@code reviews}.
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof ProductDetails that)) {
            return false;
        }
        return Objects.equals(colour, that.colour)
               && Objects.equals(gender, that.gender)
               && Objects.equals(brand, that.brand)
               && Objects.equals(description, that.description)
               && Objects.equals(sizes, that.sizes)
               && Objects.equals(reviews, that.reviews);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over every attribute, consistent with {@link #equals(Object)}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(colour, gender, brand, description, sizes, reviews);
    }

    // ---------------------------------------------------------------------------------------------------------- colour

    /**
     * Returns current value of {@code colour} attribute.
     *
     * @return current value of {@code colour} attribute.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Replaces current value of {@code colour} attribute with the specified value.
     *
     * @param colour new value for {@code colour} attribute.
     */
    public void setColour(final String colour) {
        this.colour = colour;
    }

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * Returns current value of {@code gender} attribute.
     *
     * @return current value of {@code gender} attribute.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Replaces current value of {@code gender} attribute with the specified value.
     *
     * @param gender new value for {@code gender} attribute.
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    // ----------------------------------------------------------------------------------------------------------- brand

    /**
     * Returns current value of {@code brand} attribute.
     *
     * @return current value of {@code brand} attribute.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Replaces current value of {@code brand} attribute with the specified value.
     *
     * @param brand new value for {@code brand} attribute.
     */
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    // ----------------------------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@code description} attribute.
     *
     * @return current value of {@code description} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@code description} attribute with the specified value.
     *
     * @param description new value for {@code description} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // ----------------------------------------------------------------------------------------------------------- sizes

    /**
     * Returns current value of {@code sizes} attribute.
     *
     * @return current value of {@code sizes} attribute.
     */
    public List<String> getSizes() {
        return sizes;
    }

    /**
     * Replaces current value of {@code sizes} attribute with the specified value.
     *
     * @param sizes new value for {@code sizes} attribute.
     */
    public void setSizes(final List<String> sizes) {
        this.sizes = sizes;
    }

    // --------------------------------------------------------------------------------------------------------- reviews

    /**
     * Returns current value of {@code reviews} attribute.
     *
     * @return current value of {@code reviews} attribute.
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Replaces current value of {@code reviews} attribute with the specified value.
     *
     * @param reviews new value for {@code reviews} attribute.
     */
    public void setReviews(final List<Review> reviews) {
        this.reviews = reviews;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // TODO: remove; not constrained by the DDL -- PRODUCTS.PRODUCT_DETAILS has only an IS JSON check
//    @NotBlank
    private String colour;

    // TODO: remove; not constrained by the DDL -- PRODUCTS.PRODUCT_DETAILS has only an IS JSON check
//    @NotBlank
    private String gender;

    // TODO: remove; not constrained by the DDL -- PRODUCTS.PRODUCT_DETAILS has only an IS JSON check
//    @NotBlank
    private String brand;

    // TODO: remove; not constrained by the DDL -- PRODUCTS.PRODUCT_DETAILS has only an IS JSON check
//    @NotBlank
    private String description;

    private List<@NotBlank String> sizes;

    private List<@Valid @NotNull Review> reviews;
}
