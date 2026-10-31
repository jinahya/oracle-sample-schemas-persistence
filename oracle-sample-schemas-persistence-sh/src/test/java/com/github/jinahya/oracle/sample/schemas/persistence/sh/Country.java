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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCountry;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value Country#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Country.selectOneByCountryId",
            query = """
                    SELECT e
                    FROM Country e
                    WHERE e.countryId = :countryId"""
)
@NamedQuery(name = "Country.selectListOrderByCountryIdAsc",
            query = """
                    SELECT e
                    FROM Country e
                    ORDER BY e.countryId ASC"""
)
@NamedQuery(name = "Country.selectListOrderByCountryIdAscCountryIdGt",
            query = """
                    SELECT e
                    FROM Country e
                    WHERE e.countryId > :countryIdMinExclusive
                    ORDER BY e.countryId ASC"""
)
@NamedQuery(name = "Country.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Country e"""
)
@Entity
@Table(name = MappedCountry.TABLE_NAME)
public class Country extends MappedCountry {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Country() {
        super();
    }
}
