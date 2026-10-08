package com.github.jinahya.oracle.sample.schemas.persistence.sh;

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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

/**
 * A persister which persists {@link Time} instances.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Time_Persister extends AbstractEntityPersister<Time> {

    Time_Persister() {
        super(Time.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Time apply(final EntityManager entityManager, final Time entityInstance) {
        return super.apply(entityManager, entityInstance);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Persists a randomized {@link Time} whose key is the day before the earliest one installed, for a row of a table
     * partitioned by {@code TIME_ID}.
     *
     * @param entityManager an entity manager.
     * @return the persisted instance.
     * @implNote {@code SALES} and {@code COSTS} are range-partitioned by {@code TIME_ID}, and the sample data fills
     * {@code TIMES} for every day those partitions are bounded by; a randomized key -- today, say -- falls past the
     * last partition ({@code ORA-14400}). The first partition of each has no lower bound, so the day before the
     * earliest installed {@code TIMES} row fits both, and is not taken. With no row installed, the randomized key is
     * kept.
     */
    static Time newPersistedInstanceBeforeEarliest(final EntityManager entityManager) {
        final var earliest = entityManager
                .createQuery("SELECT MIN(t.timeId) FROM Time t", LocalDateTime.class)
                .getSingleResult();
        final var instance = new Time_Randomizer().get();
        if (earliest != null) {
            instance.setTimeId(earliest.toLocalDate().minusDays(1L).atStartOfDay());
        }
        return new Time_Persister().apply(entityManager, instance);
    }
}
