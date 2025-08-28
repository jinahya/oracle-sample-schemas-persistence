package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__Builder;

import java.math.BigDecimal;

@SuppressWarnings({
        "unchecked",
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedLocationBuilder<
        SELF extends _MappedLocationBuilder<SELF, TARGET>,
        TARGET extends _MappedLocation
        >
        extends __Builder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedLocationBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -------------------------------------------------------------------------------------------------------- latitude
    public BigDecimal latitude() {
        return latitude;
    }

    public SELF latitude(final BigDecimal latitude) {
        this.latitude = latitude;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- longitude
    public BigDecimal lLongitude() {
        return longitude;
    }

    public SELF lLongitude(final BigDecimal longitude) {
        this.longitude = longitude;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private BigDecimal latitude;

    private BigDecimal longitude;
}
