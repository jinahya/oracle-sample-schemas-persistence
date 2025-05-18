package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.Optional;

@NamedQuery(name = "Country.findAllByRegion", query = "SELECT e FROM Country e WHERE e.region = :region")
@NamedQuery(name = "Country.findAllByRegionId", query = "SELECT e FROM Country e WHERE e.regionId = :regionId")
@Entity
@Table(name = Country.TABLE_NAME)
public class Country extends __MappedEntity<Country, String> {

    @Serial
    private static final long serialVersionUID = 4640115280278406681L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    // ------------------------------------------------------------------------------------------------------- REGION_ID
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    // ------------------------------------------------------------------------------------------------------- REGION_ID

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Country() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "countryId=" + countryId +
                ",countryName=" + countryName +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final String _id_() {
        return getCountryId();
    }

    @Override
    protected final void _id_(final String _id_) {
        setCountryId(_id_);
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
    @Deprecated(forRemoval = true)
    @Nonnull
    protected Long getRegionId() {
        return regionId;
    }

    @Deprecated(forRemoval = true)
    protected void setRegionId(@Nonnull final Long regionId) {
        this.regionId = regionId;
    }

    // ---------------------------------------------------------------------------------------------------------- region
    @Nonnull
    public Region getRegion() {
        return region;
    }

    public void setRegion(@Nonnull final Region region) {
        this.region = region;
        setRegionId(
                Optional.ofNullable(this.region)
                        .map(Region::getRegionId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    @Size(max = 2)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false, length = 2)
    private String countryId;

    @jakarta.annotation.Nullable
    @Size(max = 60)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, insertable = true, updatable = true, length = 60)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @jakarta.annotation.Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = false, insertable = true, updatable = true)
    private Long regionId;

    @jakarta.annotation.Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_REGION_ID, nullable = false, insertable = false, updatable = false)
    private Region region;
}