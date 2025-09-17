package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _MappedCoLocation extends _MappedCo {

    // --------------------------------------------------------------------------------------------- LATITUDE / latitude
    public static final String COLUMN_NAME_LATITUDE = "LATITUDE";

    public static final int COLUMN_PRECISION_LATITUDE = 9;

    public static final int COLUMN_SCALE_LATITUDE = 6;

    public static final String ATTRIBUTE_NAME_LATITUDE = "latitude";

    public static final String DECIMAL_MIN_LATITUDE = _DomainConstants.DECIMAL_MIN_LATITUDE;

    public static final String DECIMAL_MAX_LATITUDE = _DomainConstants.DECIMAL_MAX_LATITUDE;

    // ------------------------------------------------------------------------------------------- LONGITUDE / longitude
    public static final String COLUMN_NAME_LONGITUDE = "LONGITUDE";

    public static final int COLUMN_PRECISION_LONGITUDE = 9;

    public static final int COLUMN_SCALE_LONGITUDE = 6;

    public static final String ATTRIBUTE_NAME_LONGITUDE = "longitude";

    public static final String DECIMAL_MIN_LONGITUDE = _DomainConstants.DECIMAL_MIN_LONGITUDE;

    public static final String DECIMAL_MAX_LONGITUDE = _DomainConstants.DECIMAL_MAX_LONGITUDE;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedCoLocation() {
        super();
    }

    protected _MappedCoLocation(@Nonnull final _MappedCoLocationBuilder<?, ?> builder) {
        super(builder);
        Objects.requireNonNull(builder, "builder is null");
        latitude = builder.latitude();
        longitude = builder.longitude();
    }

    // -------------------------------------------------------------------------------------------------------- latitude
    @Nullable
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(@Nullable final BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * .
     *
     * @return .
     * @see BigDecimal#doubleValue()
     */
    @Nullable
    @Transient
    public Double getLatitudeAsDouble() {
        return Optional.ofNullable(getLatitude())
                .map(BigDecimal::doubleValue)
                .orElse(null);
    }

    /**
     * .
     *
     * @param latitude     .
     * @param roundingMode .
     * @see BigDecimal#valueOf(double)
     * @see BigDecimal#setScale(int, RoundingMode)
     */
    @Transient
    public void setLatitudeFromDouble(@Nullable final Double latitude, @Nullable final RoundingMode roundingMode) {
        setLatitude(
                Optional.ofNullable(latitude)
                        .map(BigDecimal::valueOf)
                        .map(v -> roundingMode == null ? v : v.setScale(COLUMN_SCALE_LATITUDE, roundingMode))
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- longitude
    @Nullable
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(@Nullable final BigDecimal longitude) {
        this.longitude = longitude;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @DecimalMax(value = DECIMAL_MAX_LATITUDE, inclusive = true)
    @DecimalMin(value = DECIMAL_MIN_LATITUDE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATITUDE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LATITUDE,
            scale = COLUMN_SCALE_LATITUDE
    )
    private BigDecimal latitude;

    @Nullable
    @DecimalMax(value = DECIMAL_MAX_LONGITUDE, inclusive = true)
    @DecimalMin(value = DECIMAL_MIN_LONGITUDE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LONGITUDE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LONGITUDE,
            scale = COLUMN_SCALE_LONGITUDE
    )
    private BigDecimal longitude;
}
