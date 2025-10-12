package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * An abstract mapped-superclass for mapping the {@value MappedRegion#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedRegion extends _MappedHrEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "REGIONS";

    // -------------------------------------------------------------------------------------------- REGION_ID / regionId
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    public static final String ATTRIBUTE_NAME_REGION_ID = "regionId";

    // ---------------------------------------------------------------------------------------- REGION_NAME / regionName
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    public static final int COLUMN_LENGTH_REGION_NAME = 25;

    public static final String ATTRIBUTE_NAME_REGION_NAME = "regionName";

    public static final int SIZE_MAX_REGION_NAME = COLUMN_LENGTH_REGION_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedRegion() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected MappedRegion(final MappedRegionBuilder<?, ?> builder) {
        super();
        regionId = builder.regionId();
        regionName = builder.regionName();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "regionId=" + regionId +
                ",regionName=" + regionName +
                '}';
    }

//    @Override
//    public final boolean equals(final Object obj) {
//        if (!(obj instanceof MappedRegion that)) {
//            return false;
//        }
//        return Objects.equals(getRegionId(), that.getRegionId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return Objects.hashCode(getRegionId());
//    }

    // -------------------------------------------------------------------------------------------------------- regionId
    @Nonnull
    public Long getRegionId() {
        return regionId;
    }

    void setRegionId(@Nonnull final Long regionId) {
        this.regionId = regionId;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    @Nullable
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(@Nullable final String regionName) {
        this.regionName = regionName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_REGION_ID, nullable = false, insertable = true, updatable = false)
    private Long regionId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = SIZE_MAX_REGION_NAME)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_REGION_NAME)
    private String regionName;
}
