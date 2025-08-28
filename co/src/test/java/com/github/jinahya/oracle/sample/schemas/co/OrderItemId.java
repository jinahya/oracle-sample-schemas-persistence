package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Embeddable;

import java.io.Serial;

@Embeddable
class OrderItemId extends MappedOrderItemId {

    @Serial
    private static final long serialVersionUID = 6940492962220240933L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected OrderItemId() {
        super();
    }

    OrderItemId(final OrderItemIdBuilder builder) {
        super(builder);
    }
}
