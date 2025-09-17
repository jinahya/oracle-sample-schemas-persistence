package com.github.jinahya.oracle.sample.schemas.co;

/**
 * A class for mapping {@link Product#COLUMN_NAME_PRODUCT_DETAILS} column.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ProductDetails extends MappedProductDetails<ProductDetails.Review> {

    /**
     * A class for mapping elements of {@code $.reviews} path.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static class Review extends MappedProductDetails.MappedReview {

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        /**
         * Creates a new instance.
         */
        public Review() {
            super();
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
}
