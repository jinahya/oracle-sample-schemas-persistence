package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedCountryBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder_Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CountryBuilder_Test extends _MappedHrEntityBuilder_Test<CountryBuilder, Country, String> {

    CountryBuilder_Test() {
        super(CountryBuilder.class, Country.class, String.class);
    }

    @DisplayName("from(locale)")
    @Nested
    class From_Test {

        @Test
        void __() {
            Locale.availableLocales()
                    .filter(l -> {
                        final var country = l.getCountry();
                        return country != null
                               && MappedCountryBuilder.ISO_3166_1_ALPHA_2_PATTERN.matcher(country).matches();
                    })
                    .forEach(l -> {
                        log.debug("locale: {}, '{}'", l, l.getDisplayCountry());
                        final var builder = CountryBuilder.from(l);
                        final var built = builder.build();
                        assertThat(built.getCountryId()).isEqualTo(l.getCountry());
                        assertThat(built.getCountryName()).isEqualTo(l.getDisplayCountry());
                    });
        }
    }
}
