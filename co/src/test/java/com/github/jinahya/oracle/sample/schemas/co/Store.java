package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = MappedStore.TABLE_NAME)
public class Store extends MappedStore {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Store() {
        super();
    }

    Store(@Nonnull final StoreBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Store that)) {
            return false;
        }
        return Objects.equals(getStoreName(), that.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getStoreName());
    }
}
