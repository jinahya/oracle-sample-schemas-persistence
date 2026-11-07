package com.github.jinahya.oracle.sample.persistence.hr.hr.regions;

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

import com.github.jinahya.oracle.sample.persistence.hr.Region;
import com.github.jinahya.oracle.sample.persistence.hr.Region_;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code HR_REGIONS_B_01} of {@code src/test/sql-exercises/REGIONS/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_REGIONS_B_01: Region by name")
@Slf4j
class HR_REGIONS_B_01_PersistenceIT {

    @Test
    void __QueryLanguage() {
        final var region = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT r
                                        FROM Region r
                                        WHERE UPPER(r.regionName) = UPPER(:regionName)""",
                                Region.class
                        )
                        .setParameter("regionName", "americas")
                        .getSingleResult()
        );
        log.debug("{}", region);
        assertThat(region)
                .extracting(Region::getRegionId, Region::getRegionName)
                .containsExactly(20L, "Americas");
    }

    @Test
    void __CriteriaApi() {
        final var region = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Region.class);
            final var r = cq.from(Region.class);
            cq.select(r)
                    .where(cb.equal(cb.upper(r.get(Region_.regionName)),
                                    cb.upper(cb.parameter(String.class, "regionName"))));
            return em.createQuery(cq)
                    .setParameter("regionName", "americas")
                    .getSingleResult();
        });
        log.debug("{}", region);
        assertThat(region)
                .extracting(Region::getRegionId, Region::getRegionName)
                .containsExactly(20L, "Americas");
    }

}
