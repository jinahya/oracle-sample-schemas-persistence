package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
