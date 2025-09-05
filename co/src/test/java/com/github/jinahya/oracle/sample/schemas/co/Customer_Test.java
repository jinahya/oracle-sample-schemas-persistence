package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Customer_Test extends __MappedEntity_Test<Customer, Long> {

    Customer_Test() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> equals_Verify_Configure(
            @Nonnull final SingleTypeEqualsVerifierApi<Customer> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier);
    }
}
