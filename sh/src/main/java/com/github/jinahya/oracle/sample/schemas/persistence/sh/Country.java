package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountry;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = MappedCountry.TABLE_NAME)
public class Country extends MappedCountry {

    // -----------------------------------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Country() {
        super();
    }

    private Country(@Nonnull final CountryBuilder builder) {
        super(builder);
    }
}
