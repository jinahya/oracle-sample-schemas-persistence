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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class Location_PersistenceTest extends __MappedEntity_PersistenceTest<Location, Integer> {

    Location_PersistenceTest() {
        super(Location.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("Select_WhereCountryIdEqual_OrderByLocationIdAsc")
    @Nested
    class Select_WhereCountryIdEqual_OrderByLocationIdAsc_Test {

        @Test
        void __() {
            final var result = applyEntityManager(em -> {
                return em.createNamedQuery("Location.select_WhereCountryIdEqual_OrderByLocationIdAsc", Location.class)
                        .setParameter("countryId", "KR")
                        .setMaxResults(10)
                        .getResultList();
            });
            assertThat(result).satisfiesAnyOf(
                    l -> {
                        assertThat(l).isEmpty();
                    },
                    l -> {
                        assertThat(l)
                                .extracting(Location::getLocationId)
                                .isSortedAccordingTo(Comparator.naturalOrder());
                    }
            );
        }
    }
}
