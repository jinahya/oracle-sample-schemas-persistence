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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistoryId;
import jakarta.persistence.Embeddable;

/**
 * A composite primary key class for mapping {@value JobHistory#COLUMN_NAME_EMPLOYEE_ID} column and
 * {@value JobHistory#COLUMN_NAME_START_DATE} column, of {@value JobHistory#TABLE_NAME} table; the
 * {@link jakarta.persistence.IdClass @IdClass} of {@link JobHistory}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JobHistory
 * @see <a
 * href="https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2#composite-primary-keys">2.4.1.
 * Composite primary keys</a> (Jakarta Persistence 3.2 Specification Document)
 */
@Embeddable
public class JobHistoryId extends MappedJobHistoryId {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistoryId() {
        super();
    }
}
