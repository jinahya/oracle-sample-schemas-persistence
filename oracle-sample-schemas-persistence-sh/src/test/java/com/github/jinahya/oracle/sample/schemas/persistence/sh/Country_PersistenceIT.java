package com.github.jinahya.oracle.sample.schemas.persistence.sh;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.Set;

/*-
 * #%L
 * sh
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

/**
 * Verifies the mappings of {@link Country} against the installed {@code SH} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
class Country_PersistenceIT extends _DomainEntity_Persistence_IT<Country, Long> {

    private static final Set<String> LOCALE_COUNTRY_ISO_CODES = Set.of(Locale.getISOCountries());

    // -----------------------------------------------------------------------------------------------------------------
    Country_PersistenceIT() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void randomSelected(final Country instance) {
        super.randomSelected(instance);
        final var countryIsoCode = instance.getCountryIsoCode();
        if (!LOCALE_COUNTRY_ISO_CODES.contains(countryIsoCode)) {
            log.debug("unknown country iso code('{}') from {}", countryIsoCode, instance);
        }
    }
}
