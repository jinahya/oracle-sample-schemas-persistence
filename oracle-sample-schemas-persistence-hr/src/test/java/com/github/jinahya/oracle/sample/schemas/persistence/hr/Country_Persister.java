package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
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
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A persister which persists {@link Country} instances.
 * <p>
 * Each instance is given a newly persisted {@link Region} first, so that its {@code region} refers to a row which is
 * already in the database.
 * <p>
 * Its {@code countryId}, a {@value Country#COLUMN_LENGTH_COUNTRY_ID}-character key, is replaced with an upper-case code
 * no row takes: a randomized one collides, now and then, with the 25 codes the sample data installs, and fails with
 * {@code ORA-00001}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Country_Persister extends AbstractEntityPersister<Country> {

    /**
     * Every upper-case code of {@value Country#COLUMN_LENGTH_COUNTRY_ID} characters, {@code AA} to {@code ZZ}.
     */
    private static final List<String> CODES = codes(Country.COLUMN_LENGTH_COUNTRY_ID, "").toList();

    /**
     * The position, in {@link #CODES}, of the next code to hand out; shared, so that no two calls hand out the same
     * one, whether or not the first has been flushed.
     */
    private static final AtomicInteger NEXT = new AtomicInteger();

    Country_Persister() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Country apply(final EntityManager entityManager, final Country entityInstance) {
        entityInstance.setRegion(EntityPersisterUtils.newPersistedInstanceOf(entityManager, Region.class));
        entityInstance.setCountryId(freeCountryId(entityManager));
        return super.apply(entityManager, entityInstance);
    }

    /**
     * Returns the next code which no {@link Country} row takes.
     *
     * @implNote The query runs with {@link FlushModeType#COMMIT}: under the default mode EclipseLink flushes every
     * pending change before it, which splits a caller's own pending {@code UPDATE} in two -- {@code HR.EMPLOYEES}'s
     * {@code UPDATE_JOB_HISTORY} trigger then fires twice for one employee and day, and violates
     * {@code JHIST_EMP_ID_ST_DATE_PK}. A country persisted but not flushed is therefore not seen by the query; it is
     * never handed out again because {@link #NEXT} only moves forward.
     */
    private static String freeCountryId(final EntityManager entityManager) {
        final var taken = new HashSet<>(
                entityManager.createQuery("SELECT c.countryId FROM Country c", String.class)
                        .setFlushMode(FlushModeType.COMMIT)
                        .getResultList()
        );
        for (int i = 0; i < CODES.size(); i++) {
            final var code = CODES.get(Math.floorMod(NEXT.getAndIncrement(), CODES.size()));
            if (!taken.contains(code)) {
                return code;
            }
        }
        throw new IllegalStateException("no country code is free");
    }

    private static Stream<String> codes(final int length, final String prefix) {
        if (length == 0) {
            return Stream.of(prefix);
        }
        return IntStream.rangeClosed('A', 'Z')
                .boxed()
                .flatMap(c -> codes(length - 1, prefix + (char) c.intValue()));
    }
}
