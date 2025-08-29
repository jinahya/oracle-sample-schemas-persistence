package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class OrderItem_Test extends __MappedEntityTest<OrderItem, OrderItemId> {

    OrderItem_Test() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> createEqualsVerifier() {
        return super.createEqualsVerifier();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> configureEqualsVerifier(
            @Nonnull final SingleTypeEqualsVerifierApi<OrderItem> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
