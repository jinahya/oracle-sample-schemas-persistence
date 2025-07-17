package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/// An entity class for mapping {@value Order#TABLE_NAME} table.
///
/// @author Jin Kwon &lt;onacit_at_gmail.com&gt;
@NamedQuery(
        name = "Order.findAllByStore",
        query = """
                SELECT e
                FROM Order AS e
                WHERE e.store = :store"""
)
@NamedQuery(
        name = "Order.findAllByCustomer",
        query = """
                SELECT e
                FROM Order AS e
                WHERE e.customer = :customer"""
)
@Entity
@Table(name = _MappedOrder.TABLE_NAME)
public class Order extends _MappedOrder<Order, OrderItem> {

}
