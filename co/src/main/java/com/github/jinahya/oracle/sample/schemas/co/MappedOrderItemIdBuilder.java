package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__Builder;

@SuppressWarnings({
        "unchecked"
})
abstract class MappedOrderItemIdBuilder<
        SELF extends MappedOrderItemIdBuilder<SELF, TARGET>,
        TARGET extends MappedOrderItemId
        >
        extends __Builder<SELF, TARGET> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItemIdBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // --------------------------------------------------=--------------------------------------------- java.lang.String

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long orderId() {
        return orderId;
    }

    public SELF orderId(final Long orderId) {
        this.orderId = orderId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId
    public Long lineItemId() {
        return lineItemId;
    }

    public SELF lineItemId(final Long lineItemId) {
        this.lineItemId = lineItemId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Long orderId;

    private Long lineItemId;
}
