package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Region#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Region.TABLE_NAME)
public class Region implements __MappedEntity<Long> {

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

    // --------------------------------------------------------------------------------------------------------- BUILDER
    public static __MappedEntityBuilder<?, Region> builder() {
        return new RegionBuilder();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Region() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    Region(final RegionBuilder builder) {
        this();
        regionId = builder.regionId();
        regionName = builder.regionName();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "regionId=" + regionId +
                ",regionName=" + regionName +
//                ",countries=" + countries +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Region that)) {
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

    public void setRegionId(@Nonnull final Long regionId) {
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

    // ------------------------------------------------------------------------------------------------------- countries
    private List<Country> getCountries() {
        return countries;
    }

    private void setCountries(final List<Country> countries) {
        this.countries = countries;
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
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_REGION_NAME)
    private String regionName;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(
            mappedBy = "region",
            fetch = FetchType.LAZY,
            cascade = {
            },
            orphanRemoval = false
    )
    private List<Country> countries;
}
