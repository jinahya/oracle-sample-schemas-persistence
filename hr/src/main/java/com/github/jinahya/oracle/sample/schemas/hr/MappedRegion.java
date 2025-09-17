package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public abstract class MappedRegion extends __MappedEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "REGIONS";

    // ------------------------------------------------------------------------------------------------------- REGION_ID
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    // ----------------------------------------------------------------------------------------------------- REGION_NAME
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedRegion() {
        super();
    }

    protected MappedRegion(final MappedRegionBuilder<?, ?> builder) {
        super(builder);
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

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedRegion that)) {
            return false;
        }
        return Objects.equals(getRegionId(), that.getRegionId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getRegionId());
    }

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

    @Nullable
    @Size(max = 25)
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = true, updatable = true)
    private String regionName;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: map countries by Country#region
}
