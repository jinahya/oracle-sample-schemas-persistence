package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityBuilderTest;

class ProductBuilder_Test extends __MappedEntityBuilderTest<ProductBuilder, Product, Long> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    ProductBuilder_Test() {
        super(ProductBuilder.class, Product.class, Long.class);
    }
}
