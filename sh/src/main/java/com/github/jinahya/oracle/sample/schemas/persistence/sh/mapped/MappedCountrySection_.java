package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public abstract class MappedCountrySection_ extends _MappedSh {

    // ------------------------------------------------------------------------------------------------------------ NAME
    public static final String COLUMN_NAME_NAME = "NAME";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------------------------------------------- ID
    public static final String COLUMN_NAME_ID = "ID";

    public static final String ATTRIBUTE_NAME_ID = "id";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountrySection_() {
        super();
    }

    protected MappedCountrySection_(@Nonnull final MappedCountrySectionBuilder_<?, ?> builder) {
        this();
        id = Objects.requireNonNull(builder, "builder is null").id();
        name = builder.name();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "name=" + name +
               ",id=" + id +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCountrySection_ that)) {
            return false;
        }
        return Objects.equals(name, that.name) &&
               Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name, id);
    }

    // ------------------------------------------------------------------------------------------------------------ name
    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull final String name) {
        this.name = name;
    }

    // -------------------------------------------------------------------------------------------------------------- id
    @Nonnull
    public Long getId() {
        return id;
    }

    public void setId(@Nonnull final Long id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = false)
    private String name;

    @Nonnull
    @NotNull
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = COLUMN_NAME_ID, nullable = false, insertable = true, updatable = false)
    private Long id;
}
