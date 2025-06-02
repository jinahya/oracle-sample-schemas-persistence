package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/// A class for binding {@value Product#COLUMN_NAME_PRODUCT_DETAILS} column of the {@value Product#TABLE_NAME} table.
///
/// | path                   | type   | description      |
/// |------------------------|--------|------------------|
/// | `$.colour`             | string |                  |
/// | `$.gender`             | string |                  |
/// | `$.brand`              | string |                  |
/// | `$.description`        | string |                  |
/// | `$.sizes`              | array  |                  |
/// | `$.sizes[*]`           | object | number or string |
/// | `$.reviews`            | array  |                  |
/// | `$.reviews[*].rating`  | number | 1 to 10          |
/// | `$.reviews[*].rewview` | string |                  |
///
/// @author Jin Kwon &lt;onacit_at_gmail.com&gt;
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

        @Override
        public boolean equals(final Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Review review1 = (Review) obj;
            return Objects.equals(rating, review1.rating)
                    && Objects.equals(review, review1.review);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rating, review);
        }

        // ------------------------------------------------------------------------------------------------------ rating
        public Integer getRating() {
            return rating;
        }

        public void setRating(final Integer rating) {
            this.rating = rating;
        }

        // ------------------------------------------------------------------------------------------------------ review
        @Nullable
        public String getReview() {
            return review;
        }

        public void setReview(@Nullable final String review) {
            this.review = review;
        }

        // -------------------------------------------------------------------------------------------------------------
        @Max(10L)
        @Min(1L)
        private Integer rating;

        @Nullable
        private String review;

        // -------------------------------------------------------------------------------------------------------------
        private Map<@NotBlank String, Object> unknownProperties;

        public Map<String, Object> unknownProperties() {
            if (unknownProperties == null) {
                unknownProperties = new HashMap<>();
            }
            return unknownProperties;
        }

        //        @com.fasterxml.jackson.annotation.JsonAnySetter
        public Object putUnknownProperty(final String name, final Object value) {
            return unknownProperties().put(name, value);
        }

        @AssertFalse(message = "unknown properties: {unknownProperties}")
        private boolean hasUnknownProperties() {
            return unknownProperties != null && !unknownProperties.isEmpty();
        }
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

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

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductDetails that = (ProductDetails) obj;
        return Objects.equals(colour, that.colour)
                && Objects.equals(gender, that.gender)
                && Objects.equals(brand, that.brand)
                && Objects.equals(description, that.description)
                && Objects.equals(sizes, that.sizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour, gender, brand, description, sizes);
    }

    // ---------------------------------------------------------------------------------------------------------- colour
    public String getColour() {
        return colour;
    }

    public void setColour(final String colour) {
        this.colour = colour;
    }

    // ---------------------------------------------------------------------------------------------------------- gender
    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    // ----------------------------------------------------------------------------------------------------------- brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    // ----------------------------------------------------------------------------------------------------- description
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<Object> getSizes() {
        return sizes;
    }

    public void setSizes(final List<Object> sizes) {
        this.sizes = sizes;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(final List<Review> reviews) {
        this.reviews = reviews;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @NotBlank
    private String colour;

    @NotBlank
    private String gender;

    @NotBlank
    private String brand;

    @NotBlank
    private String description;

    private List<@NotNull Object> sizes;

    private List<@Valid @NotNull Review> reviews;

    // -----------------------------------------------------------------------------------------------------------------
    private Map<@NotBlank String, Object> unknownProperties;

    public Map<String, Object> unknownProperties() {
        if (unknownProperties == null) {
            unknownProperties = new HashMap<>();
        }
        return unknownProperties;
    }

    //    @com.fasterxml.jackson.annotation.JsonAnySetter
    public Object unknownProperty(final String name, final Object value) {
        return unknownProperties().put(name, value);
    }

    @AssertFalse(message = "unknown properties: {unknownProperties}")
    private boolean hasUnknownProperties() {
        return unknownProperties != null && !unknownProperties.isEmpty();
    }
}
