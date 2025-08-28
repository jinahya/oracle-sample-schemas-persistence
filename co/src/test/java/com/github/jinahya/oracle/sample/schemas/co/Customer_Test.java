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
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> createEqualsVerifier() {
        return super.createEqualsVerifier()
                .withOnlyTheseFields(Customer.ATTRIBUTE_NAME_EMAIL_ADDRESS)
                ;
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> configureEqualsVerifier(
            @Nonnull SingleTypeEqualsVerifierApi<Customer> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier);
    }
}
