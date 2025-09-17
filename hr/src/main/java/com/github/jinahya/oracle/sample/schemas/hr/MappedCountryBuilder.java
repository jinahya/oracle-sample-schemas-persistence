package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCountryBuilder<
        SELF extends MappedCountryBuilder<SELF, COUNTRY, REGION>,
        COUNTRY extends MappedCountry<REGION, ?>,
        REGION extends MappedRegion
        >
        extends __MappedEntityBuilder<SELF, COUNTRY> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountryBuilder(final Class<COUNTRY> entityClass) {
        super(entityClass);
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

    // ---------------------------------------------------------------------------------------------------------- region
    public REGION region() {
        return region;
    }

    public SELF region(final REGION region) {
        this.region = region;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String countryId;

    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    private REGION region;
}
