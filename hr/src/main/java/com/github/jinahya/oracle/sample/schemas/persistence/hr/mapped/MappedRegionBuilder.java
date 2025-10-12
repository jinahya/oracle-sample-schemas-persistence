package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedRegionBuilder<
        SELF extends MappedRegionBuilder<SELF, TARGET>,
        TARGET extends MappedRegion
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedRegionBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -------------------------------------------------------------------------------------------------------- regionId
    public Long regionId() {
        return regionId;
    }

    public SELF regionId(final Long regionId) {
        this.regionId = regionId;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    public String regionName() {
        return regionName;
    }

    public SELF regionName(final String regionName) {
        this.regionName = regionName;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Long regionId;

    private String regionName;
}
