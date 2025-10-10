package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

/**
 * An abstract class for building instance of a specific subclass of the {@link MappedLocation} class.
 *
 * @param <SELF>   self type parameter
 * @param <TARGET> target type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedLocationBuilder<
        SELF extends MappedLocationBuilder<SELF, TARGET>,
        TARGET extends MappedLocation
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedLocationBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ locationId
    public Integer locationId() {
        return locationId;
    }

    public SELF locationId(final Integer locationId) {
        this.locationId = locationId;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- streetAddress
    public String streetAddress() {
        return streetAddress;
    }

    public SELF streetAddress(final String streetAddress) {
        this.streetAddress = streetAddress;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ postalCode
    public String postalCode() {
        return postalCode;
    }

    public SELF postalCode(final String postalCode) {
        this.postalCode = postalCode;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------------ city
    public String city() {
        return city;
    }

    public SELF city(final String city) {
        this.city = city;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince
    public String stateProvince() {
        return stateProvince;
    }

    public SELF stateProvince(final String stateProvince) {
        this.stateProvince = stateProvince;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    public String countryId() {
        return countryId;
    }

    public SELF countryId(final String countryId) {
        this.countryId = countryId;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer locationId;

    // -----------------------------------------------------------------------------------------------------------------
    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    private String countryId;
}
