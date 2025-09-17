package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
public class MappedCountry<REGION extends MappedRegion, LOCATION extends MappedLocation<?>>
        extends __MappedEntity<String> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------ COUNTRY_ID / countryId
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // -------------------------------------------------------------------------------------- COUNTRY_NAME / countryName
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ----------------------------------------------------------------------------------- REGION_ID / regionId / region
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountry() {
        super();
    }

    protected MappedCountry(final MappedCountryBuilder<?, ?, REGION> builder) {
        super();
        countryId = builder.countryId();
        countryName = builder.countryName();
        setRegion(builder.region());
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "countryId=" + countryId +
                ",countryName=" + countryName +
                ",regionId=" + regionId +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCountry<?, ?> that)) {
            return false;
        }
        return Objects.equals(getCountryId(), that.getCountryId());
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

    @Transient
    public void setCountryIdFromLocale(@Nonnull final java.util.Locale locale) {
        Objects.requireNonNull(locale, "locale is null");
        // TODO: implement
        throw new UnsupportedOperationException("not yet implemented");
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
    Long getRegionId() {
        return regionId;
    }

    void setRegionId(@Nullable final Long regionId) {
        this.regionId = regionId;
    }

    // ---------------------------------------------------------------------------------------------------------- region
    @Nullable
    public REGION getRegion() {
        return region;
    }

    public void setRegion(@Nullable final REGION region) {
        this.region = region;
        setRegionId(
                Optional.ofNullable(this.region)
                        .map(MappedRegion::getRegionId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = 2)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false, length = 2)
    private String countryId;

    @Nullable
    @Size(max = 60)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, insertable = true, updatable = true, length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = true, updatable = true)
    private Long regionId;

    @Nullable
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = false, updatable = false)
    private REGION region;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedLocation.ATTRIBUTE_NAME_COUNTRY)
    private List<@Valid @NotNull LOCATION> locations;
}
