package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntityBuilder_Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;

class CountryBuilder_Test extends __MappedEntityBuilder_Test<CountryBuilder, Country, String> {

    CountryBuilder_Test() {
        super(CountryBuilder.class, Country.class, String.class);
    }

    @DisplayName("setCountryIdFromLocale(locale)")
    @Nested
    class SetCountryIdFromLocaleTest {

        @DisplayName("(null) -> setCountryId(null)")
        @Test
        void _ShouldInvokeSetCountryIdWithNull_LocaleIsNull() {
        }

        @DisplayName("(locale) -> setCountryId(locale.getCountry)")
        @MethodSource({
                "java.util.Locale#availableLocales"
        })
        @ParameterizedTest
        void _ShouldInvokeSetCountryIdWithLocaleCountry_(final Locale locale) {
        }
    }
}
