package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

@Slf4j
class Order_Test extends __MappedEntity_Test<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_Test() {
        super(Order.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Order> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Order> equals_Verify_Configure(
            @Nonnull SingleTypeEqualsVerifierApi<Order> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier)
                .suppress(Warning.SURROGATE_KEY)
                ;
    }
}
