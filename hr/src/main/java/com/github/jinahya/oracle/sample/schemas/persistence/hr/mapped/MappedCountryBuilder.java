package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCountryBuilder<
        SELF extends MappedCountryBuilder<SELF, TARGET>,
        TARGET extends MappedCountry
        >
        extends __MappedEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountryBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------- countryId
    public String countryId() {
        return countryId;
    }

    public SELF countryId(final String countryId) {
        this.countryId = countryId;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    public String countryName() {
        return countryName;
    }

    public SELF countryName(final String countryName) {
        this.countryName = countryName;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String countryId;

    private String countryName;
}
