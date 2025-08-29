package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCustomerBuilder<
        SELF extends MappedCustomerBuilder<SELF, CUSTOMER>,
        CUSTOMER extends MappedCustomer
        >
        extends __MappedEntityBuilder<SELF, CUSTOMER> {

    // -----------------------------------------------------------------------------------------------------------------
    protected MappedCustomerBuilder(final Class<CUSTOMER> entityClass) {
        super(entityClass);
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress
    public String emailAddress() {
        return emailAddress;
    }

    public SELF emailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- fullName
    public String fullName() {
        return fullName;
    }

    public SELF fullName(final String fullName) {
        this.fullName = fullName;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String emailAddress;

    private String fullName;
}
