package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * A class for mapping {@link Product#COLUMN_NAME_PRODUCT_DETAILS} column.
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

        // -------------------------------------------------------------------------------------------------------------
        public Integer getRating() {
            return rating;
        }

        public void setRating(final Integer rating) {
            this.rating = rating;
        }

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

//    @Override
//    public boolean equals(final Object obj) {
//        if (obj == null || getClass() != obj.getClass()) return false;
//        ProductDetails that = (ProductDetails) obj;
//        return Objects.equals(colour, that.colour)
//                && Objects.equals(gender, that.gender)
//                && Objects.equals(brand, that.brand)
//                && Objects.equals(description, that.description)
//                && Objects.equals(sizes, that.sizes)
////                && Objects.equals(reviews, that.reviews)
//                ;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(
//                colour,
//                gender,
//                brand,
//                description,
//                sizes

    /// /                , /                reviews
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

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

    private List<@NotBlank String> sizes;

    private List<@Valid @NotNull Review> reviews;
}
