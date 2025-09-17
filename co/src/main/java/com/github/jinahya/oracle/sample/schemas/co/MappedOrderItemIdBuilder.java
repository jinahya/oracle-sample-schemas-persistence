package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__Builder;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedOrderItemIdBuilder<
        SELF extends MappedOrderItemIdBuilder<SELF, ORDER_ITEM_ID>,
        ORDER_ITEM_ID extends MappedOrderItemId
        >
        extends __Builder<SELF, ORDER_ITEM_ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderItemIdBuilder(final Class<ORDER_ITEM_ID> targetClass) {
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
