package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountrySection;
import jakarta.persistence.Embeddable;

/**
 * An embeddable class for mapping a named section -- a region, a subregion or a total -- of the
 * {@value Country#TABLE_NAME} table: a name column, and the numeric id column that goes with it.
 * <p>
 * No entity of this module embeds this class yet; {@link Country} maps each of those column pairs as plain attributes.
 * The class declares no column names: an embedding entity names both columns of each section with
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}s, e.g.
 * {@code (COUNTRY_SUBREGION, COUNTRY_SUBREGION_ID)} and {@code (COUNTRY_REGION, COUNTRY_REGION_ID)}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class CountrySection extends MappedCountrySection {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CountrySection() {
        super();
    }
}
