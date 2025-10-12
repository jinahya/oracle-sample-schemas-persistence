package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountrySection_;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountrySectionBuilder_;
import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped._MappedShBuilder;
import com.github.jinahya.persistence.mapped.__Mapped;
import com.github.jinahya.persistence.mapped.__MappedBuilder;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;

@Embeddable
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class CountrySection_ extends MappedCountrySection_ {

    // -----------------------------------------------------------------------------------------------------------------
    public static MappedCountrySectionBuilder_<?, CountrySection_> builder() {
        return new CountrySectionBuilder_();
    }

    // TODO: remove!
    static _MappedShBuilder<?, ? extends MappedCountrySection_> builder2() {
        return builder();
    }

    // TODO: remove!
    static __MappedBuilder<?, ? extends __Mapped> builder3() {
        return builder2();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected CountrySection_() {
        super();
    }

    private CountrySection_(@Nonnull final CountrySectionBuilder_ builder) {
        super(builder);
    }
}
