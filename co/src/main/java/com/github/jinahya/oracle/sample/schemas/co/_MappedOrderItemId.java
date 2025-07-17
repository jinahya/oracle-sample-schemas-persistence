package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

abstract class _MappedOrderItemId<SELF extends _MappedOrderItemId<SELF>> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7235302121260728301L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    static final String ATTRIBUTE_NAME_LINE_ITEM_ID = "lineItemId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    static <T extends _MappedOrderItemId<T>> T of(@Nonnull final Supplier<? extends T> instantiator,
                                                  @Nonnull final Long orderId, @Nonnull final Long lineItemId) {
        final var instance = Objects.requireNonNull(instantiator.get(), "null supplied from " + instantiator);
        instance.setOrderId(orderId);
        instance.setLineItemId(lineItemId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    _MappedOrderItemId() {
        super();
    }

    // --------------------------------------------------=--------------------------------------------- java.lang.String
    @Override
    public String toString() {
        return super.toString() + '{' +
                "orderId=" + orderId +
                ",lineItemId=" + lineItemId +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof _MappedOrderItemId<?> that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(lineItemId, that.lineItemId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(orderId, lineItemId);
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    @Nonnull
    public Long getOrderId() {
        return orderId;
    }

    void setOrderId(@Nonnull final Long orderId) {
        this.orderId = orderId;
    }

    final SELF orderId(@Nonnull final Long orderId) {
        setOrderId(orderId);
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId
    @Nonnull
    public Long getLineItemId() {
        return lineItemId;
    }

    void setLineItemId(@Nonnull final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    final SELF lineItemId(@Nonnull final Long lineItemId) {
        setLineItemId(lineItemId);
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = OrderItem.COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = OrderItem.COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true, updatable = false)
    private Long lineItemId;
}
