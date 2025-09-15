package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQuery(
        name = "Customer.selectSingleByEmailAddress",
        query = """
                SELECT e
                FROM Customer e
                WHERE e.emailAddress = :emailAddress"""
)
@NamedQuery(
        name = "Customer.selectListOrderByEmailAddressAscEmailAddressGt",
        query = """
                SELECT e
                FROM Customer e
                WHERE e.emailAddress > :emailAddressMinExclusive
                ORDER BY e.emailAddress ASC"""
)
@NamedQuery(
        name = "Customer.selectListOrderByEmailAddressAsc",
        query = """
                SELECT e
                FROM Customer e
                ORDER BY e.emailAddress ASC"""
)
@NamedQuery(
        name = "Customer.selectListOrderByCustomerIdAscCustomerIdGt",
        query = """
                SELECT e
                FROM Customer e
                WHERE e.customerId > :customerIdMinExclusive
                ORDER BY e.customerId ASC"""
)
@NamedQuery(
        name = "Customer.selectListOrderByCustomerIdAsc",
        query = """
                SELECT e
                FROM Customer e
                ORDER BY e.customerId ASC"""
)
@Entity
@Table(name = MappedCustomer.TABLE_NAME)
class Customer extends MappedCustomer {

    // -----------------------------------------------------------------------------------------------------------------
    static MappedCustomerBuilder<?, Customer> builder() {
        return new CustomerBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
        super();
    }

    Customer(@Nonnull final CustomerBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
