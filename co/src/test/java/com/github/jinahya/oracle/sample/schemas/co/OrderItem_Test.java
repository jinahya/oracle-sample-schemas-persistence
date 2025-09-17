package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class OrderItem_Test extends _MappedCoEntity_Test<OrderItem, OrderItemId> {

    OrderItem_Test() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<OrderItem> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
