package com.github.jinahya.oracle.sample.schemas.co;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCustomerBuilder<
        SELF extends MappedCustomerBuilder<SELF, ENTITY>,
        ENTITY extends MappedCustomer
        >
        extends _MappedCoEntityBuilder<SELF, ENTITY> {

    // -----------------------------------------------------------------------------------------------------------------
    protected MappedCustomerBuilder(final Class<ENTITY> entityClass) {
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
