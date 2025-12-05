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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.JobHistoryId_;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * An abstract mapped-superclass, maps the {@value MappedJobHistory#TABLE_NAME} table, uses {@link JobHistoryId} as its
 * {@link EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobHistoryWithEmbeddedId extends MappedJobHistory {

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String ATTRIBUTE_NAME_ID_EMPLOYEE_ID = "id.employeeId";

    public static final long ATTRIBUTE_MIN_ID_EMPLOYEE_ID = COLUMN_MIN_EMPLOYEE_ID;

    public static final long ATTRIBUTE_MAX_ID_EMPLOYEE_ID = COLUMN_MAX_EMPLOYEE_ID;

    // ------------------------------------------------------------------------------------------------------ START_DATE
    public static final String ATTRIBUTE_NAME_ID_START_DATE = "id.startDate";

    // -------------------------------------------------------------------------------------------------------- END_DATE

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    // -----------------------------------------------------------------------------------------------------------------
    protected static <T extends MappedJobHistoryWithEmbeddedId> Comparator<T> comparingIdStartDate() {
        return comparingStartDate(v -> v.getId().getStartDate());
    }

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
        if (!(obj instanceof MappedJobHistoryWithEmbeddedId that)) {
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

    /**
     * Tests whether current value of {@value MappedJobHistory_#END_DATE} attribute is after the value of
     * {@link JobHistoryId_#START_DATE id.startDate} attribute.
     *
     * @return {@code true} if the current value of the {@value MappedJobHistory_#END_DATE} attribute is after the
     * {@link JobHistoryId_#START_DATE id.startDate} attribute; {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isEndDateAfterIdStartDate() {
        final var endDate = getEndDate();
        if (endDate == null) {
            return true;
        }
        final var idStartDate = Optional.ofNullable(id).map(JobHistoryId::getStartDate).orElse(null);
        if (idStartDate == null) {
            return true;
        }
        return endDate.isAfter(idStartDate);
    }

    // --------------------------------------------------------------------------------------------------- super.endDate

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value MappedJobHistoryWithEmbeddedId_#ID} attribute.
     *
     * @return current value of the {@value MappedJobHistoryWithEmbeddedId_#ID} attribute.
     */
    public JobHistoryId getId() {
        return id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @EmbeddedId
    private JobHistoryId id;
}
