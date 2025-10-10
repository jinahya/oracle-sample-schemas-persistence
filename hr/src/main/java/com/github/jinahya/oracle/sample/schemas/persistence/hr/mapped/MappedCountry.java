package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * An abstract mapped-superclass for mapping the {@value MappedCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCountry extends _MappedHrEntity<String> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------ COUNTRY_ID / countryId
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // -------------------------------------------------------------------------------------- COUNTRY_NAME / countryName
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ----------------------------------------------------------------------------------- REGION_ID / regionId / region

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_REGION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    /**
     * The name of the entity attribute, of a subclass of the {@link MappedRegion}, from which the
     * {@value #COLUMN_NAME_REGION_ID} column maps. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountry() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected MappedCountry(final MappedCountryBuilder<?, ?> builder) {
        super();
        countryId = builder.countryId();
        countryName = builder.countryName();
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

//    @Override
//    public final boolean equals(final Object obj) {
//        if (!(obj instanceof MappedCountry that)) {
//            return false;
//        }
//        return Objects.equals(getCountryId(), that.getCountryId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return Objects.hashCode(getCountryId());
//    }

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
    public Long getRegionId() {
        return regionId;
    }

    protected void setRegionId(@Nullable final Long regionId) {
        this.regionId = regionId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = SIZE_MAX_COUNTRY_ID)
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, insertable = true, updatable = true, length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REGION_ID, nullable = true, insertable = true, updatable = true)
    private Long regionId;
}
