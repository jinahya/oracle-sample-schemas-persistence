package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Embeddable;

import java.io.Serial;

@Embeddable
public class OrderItemId extends _MappedOrderItemId<OrderItemId> {

    @Serial
    private static final long serialVersionUID = -7235302121260728301L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static OrderItemId of(final Long orderId, final Long lineItemId) {
        return _MappedOrderItemId.of(OrderItemId::new, orderId, lineItemId);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItemId() {
        super();
    }
}
