package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

@Entity
@Table(name = Country.TABLE_NAME)
public class Country extends __MappedEntity<Country, Long> {

    @Serial
    private static final long serialVersionUID = 2421376875922293948L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    // ------------------------------------------------------------------------------------------------ COUNTRY_ISO_CODE
    public static final String COLUMN_NAME_COUNTRY_ISO_CODE = "COUNTRY_ISO_CODE";

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    // ----------------------------------------------------------------------------------------------- COUNTRY_SUBREGION

    // -------------------------------------------------------------------------------------------- COUNTRY_SUBREGION_ID

    // -------------------------------------------------------------------------------------------------- COUNTRY_REGION

    // ----------------------------------------------------------------------------------------------- COUNTRY_REGION_ID

    // --------------------------------------------------------------------------------------------------- COUNTRY_TOTAL

    // ------------------------------------------------------------------------------------------------ COUNTRY_TOTAL_ID

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Country() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "countryId=" + countryId +
                ",countryIsoCode=" + countryIsoCode +
                ",countryName=" + countryName +
                ",countrySubregion=" + countrySubregion +
                ",countrySubregionId=" + countrySubregionId +
                ",countryRegion=" + countryRegion +
                ",countryRegionId=" + countryRegionId +
                ",countryTotal=" + countryTotal +
                ",countryTotalId=" + countryTotalId +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_

    @Override
    protected Long _id_() {
        return getCountryId();
    }

    @Override
    protected void _id_(final Long _id_) {
        setCountryId(_id_);
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    @Nonnull
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(@Nonnull final Long countryId) {
        this.countryId = countryId;
    }

    // -------------------------------------------------------------------------------------------------- countryIsoCode
    @Nonnull
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(@Nonnull final String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    // ----------------------------------------------------------------------------------------------------- countryName
    @Nonnull
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@Nonnull final String countryName) {
        this.countryName = countryName;
    }

    // ------------------------------------------------------------------------------------------------ countrySubregion
    @Nonnull
    public String getCountrySubregion() {
        return countrySubregion;
    }

    public void setCountrySubregion(@Nonnull final String countrySubregion) {
        this.countrySubregion = countrySubregion;
    }

    // ---------------------------------------------------------------------------------------------- countrySubregionId
    @Nonnull
    public Long getCountrySubregionId() {
        return countrySubregionId;
    }

    public void setCountrySubregionId(@Nonnull final Long countrySubregionId) {
        this.countrySubregionId = countrySubregionId;
    }

    // --------------------------------------------------------------------------------------------------- countryRegion
    @Nonnull
    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(@Nonnull final String countryRegion) {
        this.countryRegion = countryRegion;
    }

    // ------------------------------------------------------------------------------------------------- countryRegionId
    @Nonnull
    public Long getCountryRegionId() {
        return countryRegionId;
    }

    public void setCountryRegionId(@Nonnull final Long countryRegionId) {
        this.countryRegionId = countryRegionId;
    }

    // ---------------------------------------------------------------------------------------------------- countryTotal
    @Nonnull
    public String getCountryTotal() {
        return countryTotal;
    }

    public void setCountryTotal(@Nonnull final String countryTotal) {
        this.countryTotal = countryTotal;
    }

    // -------------------------------------------------------------------------------------------------- countryTotalId
    @Nonnull
    public Long getCountryTotalId() {
        return countryTotalId;
    }

    public void setCountryTotalId(@Nonnull final Long countryTotalId) {
        this.countryTotalId = countryTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Id
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false)
    private Long countryId;

    @Nonnull
    @Size(max = 2)
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_ISO_CODE", nullable = false, insertable = true, updatable = true, length = 2)
    private String countryIsoCode;

    @Nonnull
    @Size(max = 40)
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_NAME", nullable = false, insertable = true, updatable = true, length = 40)
    private String countryName;

    @Nonnull
    @Size(max = 30)
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_SUBREGION", nullable = false, insertable = true, updatable = true, length = 30)
    private String countrySubregion;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_SUBREGION_ID", nullable = false, insertable = true, updatable = true)
    private Long countrySubregionId;

    @Nonnull
    @Size(max = 20)
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_REGION", nullable = false, insertable = true, updatable = false, length = 20)
    private String countryRegion;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_REGION_ID", nullable = false, insertable = true, updatable = false)
    private Long countryRegionId;

    @Nonnull
    @Size(max = 11)
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_TOTAL", nullable = false, insertable = true, updatable = true, length = 11)
    private String countryTotal;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_TOTAL_ID", nullable = false, insertable = true, updatable = true)
    private Long countryTotalId;
}