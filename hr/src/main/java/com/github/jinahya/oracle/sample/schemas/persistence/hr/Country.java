package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "COUNTRIES")
public class Country implements __MappedEntity<String> {

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ------------------------------------------------------------------------------------------------------- REGION_ID
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    public static final String ATTRIBUTE_NAME_REGION = "region";

    // -----------------------------------------------------------------------------------------------------------------
    public static CountryBuilder builder() {
        return new CountryBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Country() {
        super();
    }

    Country(@Nonnull final CountryBuilder builder) {
        this();
        countryId = Objects.requireNonNull(builder, "builder is null").countryId();
        countryName = builder.countryName();
        regionId = builder.regionId();
        setRegion(builder.getRegion());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + '{' +
                "countryId=" + countryId +
                ",countryName=" + countryName +
                ",regionId=" + regionId +
//                ",region=" + region +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Country country)) {
            return false;
        }
        return Objects.equals(getCountryId(), country.getCountryId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getCountryId());
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    @Nonnull
    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(@Nonnull final String countryId) {
        this.countryId = countryId;
    }

    // ----------------------------------------------------------------------------------------------------- countryName
    @Nullable
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@Nullable final String countryName) {
        this.countryName = countryName;
    }

    // -------------------------------------------------------------------------------------------------------- regionId
    @Nullable
    private Long getRegionId() {
        return regionId;
    }

    private void setRegionId(@Nullable final Long regionId) {
        this.regionId = regionId;
    }

    // ---------------------------------------------------------------------------------------------------------- region
    @Nullable
    public Region getRegion() {
        return region;
    }

    public void setRegion(@Nullable final Region region) {
        this.region = region;
        setRegionId(
                Optional.ofNullable(this.region)
                        .map(Region::getRegionId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Size(max = SIZE_MAX_COUNTRY_ID)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = false, updatable = false)
    private Long regionId;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = true, updatable = true)
    private Region region;
}
