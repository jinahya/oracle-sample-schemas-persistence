package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemId implements Serializable {

    @Serial
    private static final long serialVersionUID = -7235302121260728301L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static OrderItemId of(final Long orderId, final Long lineItemId) {
        final var instance = new OrderItemId();
        instance.orderId = orderId;
        instance.lineItemId = lineItemId;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected OrderItemId() {
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
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var that = (OrderItemId) obj;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(lineItemId, that.lineItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, lineItemId);
    }

    // --------------------------------------------------------------------------------------------------------- orderId
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public OrderItemId orderId(final Long orderId) {
        setOrderId(orderId);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------ lineItemId
    public Long getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(final Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public OrderItemId lineItemId(final Long lineItemId) {
        setLineItemId(lineItemId);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = OrderItem.COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Long orderId;

    @NotNull
    @Basic(optional = false)
    @Column(name = OrderItem.COLUMN_NAME_LINE_ITEM_ID, nullable = false, insertable = true, updatable = false)
    private Long lineItemId;
}