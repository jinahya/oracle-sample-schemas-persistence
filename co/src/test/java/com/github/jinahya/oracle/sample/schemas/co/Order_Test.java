package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

@Slf4j
class Order_Test extends __MappedEntityTest<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_Test() {
        super(Order.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Order> createEqualsVerifier() {
        return super.createEqualsVerifier();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Order> configureEqualsVerifier(
            @Nonnull final SingleTypeEqualsVerifierApi<Order> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
