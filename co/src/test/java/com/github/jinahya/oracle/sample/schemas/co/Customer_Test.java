package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Configure_EqualsVerifier;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Customer_Test extends __MappedEntity_Test<Customer, Long> {

    Customer_Test() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__Configure_EqualsVerifier
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Customer> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .withOnlyTheseFields("emailAddress")
                ;
    }
}
