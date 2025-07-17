package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class Customer_Test extends __MappedEntityTest<Customer, Long> {

    Customer_Test() {
        super(Customer.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_verify() {
        super.equals_verify();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> getEqualsVerifier() {
        return super.getEqualsVerifier();
    }
}
