package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class maps to {@value _MappedCustomer#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Customer.selectListWhereFullNameLike",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.fullName LIKE :fullNamePattern"""
)
@NamedQuery(
        name = "Customer.selectSingleWhereFullNameEqual",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.fullName = :fullName"""
)
@NamedQuery(
        name = "Customer.selectListWhereEmailAddressLike",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.emailAddress LIKE :emailAddressPattern"""
)
@NamedQuery(
        name = "Customer.selectSingleWhereEmailAddressEqual",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.emailAddress = :emailAddress"""
)
@Entity
@Table(name = _MappedCustomer.TABLE_NAME)
public class Customer extends _MappedCustomer<Customer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
        super();
    }
}
