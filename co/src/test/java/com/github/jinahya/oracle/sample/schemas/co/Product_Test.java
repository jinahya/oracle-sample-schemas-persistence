package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Product_Test extends __MappedEntity_Test<Product, Long> {

    Product_Test() {
        super(Product.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Product> equalsVerifier() {
        return super.equalsVerifier();
    }
}