package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

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

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;

import java.util.Objects;

/**
 * An abstract mapped-superclass, maps the {@value MappedJobHistory#TABLE_NAME} table, uses {@link MappedJobHistoryId}
 * as its {@link EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobHistoryWithEmbeddedId<ID extends MappedJobHistoryId> extends MappedJobHistory<ID> {

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    // ------------------------------------------------------------------------------------------------------ START_DATE

    // -------------------------------------------------------------------------------------------------------- END_DATE

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobHistoryWithEmbeddedId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + getId() +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedJobHistoryWithEmbeddedId<?> that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation
    @AssertTrue
    protected boolean isEndDateAfterThanIdStartDate() {
        final var endDate = getEndDate();
        if (endDate == null) {
            return true;
        }
        final var id = getId();
        if (id == null) {
            return true;
        }
        final var startDate = id.getStartDate();
        if (startDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

    // --------------------------------------------------------------------------------------------------- super.endDate

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the id of this entity.
     *
     * @return the id of this entity.
     */
    public abstract ID getId();

    // -----------------------------------------------------------------------------------------------------------------
    // fuck eclipselink
//    @Nonnull
//    @Valid
//    @NotNull
//    @EmbeddedId
//    private ID id;
}
