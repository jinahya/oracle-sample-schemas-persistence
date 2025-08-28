package com.github.jinahya.oracle.sample.schemas.co;

class OrderBuilder extends MappedOrderBuilder<OrderBuilder, Order, Customer, Store> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    OrderBuilder() {
        super(Order.class);
    }
}
