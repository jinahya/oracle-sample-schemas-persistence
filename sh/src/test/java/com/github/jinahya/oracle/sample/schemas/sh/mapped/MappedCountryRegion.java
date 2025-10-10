package com.github.jinahya.oracle.sample.schemas.sh.mapped;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * An abstract mapped class for mapping regions and ids.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class MappedCountryRegion extends __MappedSh {

    public static final String COLUMN_NAME_COUNTRY_REGION_NAME = "COUNTRY_REGION_NAME";

    public static final String COLUMN_NAME_COUNTRY_REGION_ID = "COUNTRY_REGION_ID";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountryRegion() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "countryRegionName=" + countryRegionName +
                ",countryRegionId=" + countryRegionId +
                '}';
    }

    // ----------------------------------------------------------------------------------------------- countryRegionName
    @Nonnull
    public String getCountryRegionName() {
        return countryRegionName;
    }

    public void setCountryRegionName(@Nonnull final String countryRegionName) {
        this.countryRegionName = countryRegionName;
    }

    // ------------------------------------------------------------------------------------------------- countryRegionId
    @Nonnull
    public Integer getCountryRegionId() {
        return countryRegionId;
    }

    public void setCountryRegionId(@Nonnull final Integer countryRegionId) {
        this.countryRegionId = countryRegionId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_REGION_NAME, nullable = false, insertable = true, updatable = true)
    private String countryRegionName;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_REGION_ID, nullable = false, insertable = true, updatable = true)
    private Integer countryRegionId;
}
