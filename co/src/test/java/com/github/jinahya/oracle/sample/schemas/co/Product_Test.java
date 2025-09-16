package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Configure_EqualsVerifier;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Product_Test extends __MappedEntity_Test<Product, Long> {

    Product_Test() {
        super(Product.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__Configure_EqualsVerifier
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Product> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Product> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
