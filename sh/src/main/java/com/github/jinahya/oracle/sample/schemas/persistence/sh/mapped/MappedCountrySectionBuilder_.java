package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCountrySectionBuilder_<
        SELF extends MappedCountrySectionBuilder_<SELF, TARGET>,
        TARGET extends MappedCountrySection_
        >
        extends _MappedShBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountrySectionBuilder_(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------------ name
    public String name() {
        return name;
    }

    public SELF name(final String name) {
        this.name = name;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Long id() {
        return id;
    }

    public SELF id(final Long id) {
        this.id = id;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String name;

    private Long id;
}
