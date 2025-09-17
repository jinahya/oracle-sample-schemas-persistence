package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedOrder.TABLE_NAME)
class Order extends MappedOrder<Customer, Store, OrderItem> {

    public static MappedOrderBuilder<?, Order, ?, ?> builder() {
        return new OrderBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Order() {
        super();
    }

    Order(final OrderBuilder builder) {
        super(builder);
    }
}
