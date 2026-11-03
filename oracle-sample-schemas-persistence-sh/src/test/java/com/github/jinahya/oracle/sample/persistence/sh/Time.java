package com.github.jinahya.oracle.sample.persistence.sh;

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

import com.github.jinahya.oracle.sample.persistence.sh.mapped.MappedTime;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value Time#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Time.selectOneByTimeId",
            query = """
                    SELECT e
                    FROM Time e
                    WHERE e.timeId = :timeId"""
)
@NamedQuery(name = "Time.selectListOrderByTimeIdAsc",
            query = """
                    SELECT e
                    FROM Time e
                    ORDER BY e.timeId ASC"""
)
@NamedQuery(name = "Time.selectListOrderByTimeIdAscTimeIdGt",
            query = """
                    SELECT e
                    FROM Time e
                    WHERE e.timeId > :timeIdMinExclusive
                    ORDER BY e.timeId ASC"""
)
@NamedQuery(name = "Time.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Time e"""
)
@Entity
@Table(name = MappedTime.TABLE_NAME)
public class Time extends MappedTime {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Time() {
        super();
    }
}
