package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class OrderItem_Test extends __MappedEntity_Test<OrderItem, OrderItemId> {

    OrderItem_Test() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> equals_Verify_Configure(
            @Nonnull SingleTypeEqualsVerifierApi<OrderItem> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
