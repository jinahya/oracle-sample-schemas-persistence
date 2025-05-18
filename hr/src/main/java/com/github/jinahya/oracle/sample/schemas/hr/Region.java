package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas._MappedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

@Entity
@Table(name = Region.TABLE_NAME)
public class Region extends _MappedEntity<Region> {

    @Serial
    private static final long serialVersionUID = 3312400803248208293L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "REGIONS";

    // ------------------------------------------------------------------------------------------------------- REGION_ID
    public static final String COLUMN_NAME_REGION_ID = "REGION_ID";

    // ----------------------------------------------------------------------------------------------------- REGION_NAME
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Region() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "regionId=" + regionId +
                ",regionName=" + regionName +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return getRegionId();
    }

    @Override
    protected final void _id_(final Long _id_) {
        setRegionId(_id_);
    }

    // -------------------------------------------------------------------------------------------------------- regionId

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(final Long regionId) {
        this.regionId = regionId;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(final String regionName) {
        this.regionName = regionName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_REGION_ID, nullable = false, insertable = true, updatable = false)
    private Long regionId;

    @jakarta.annotation.Nullable
    @Size(max = 25)
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = true, updatable = true)
    private String regionName;
}