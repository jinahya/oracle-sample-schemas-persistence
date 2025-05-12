package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

import java.util.Arrays;
import java.util.List;

class OrderItem_Test extends __MappedEntity_Test<OrderItem, OrderItemId> {

    OrderItem_Test() {
        super(OrderItem.class, OrderItemId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<OrderItem> equalsVerifier() {
        return super.equalsVerifier()
                .withPrefabValues(
                        List.class, Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)
                );
    }
}