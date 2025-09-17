package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedStore.TABLE_NAME)
class Store extends MappedStore {

    static MappedStoreBuilder<?, Store> builder() {
        return new StoreBuilder();
    }

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
}
