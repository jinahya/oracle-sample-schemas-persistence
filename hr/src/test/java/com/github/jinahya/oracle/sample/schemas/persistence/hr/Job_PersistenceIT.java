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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class Job_PersistenceIT extends _MappedHrEntity_PersistenceIT<Job, String> {

    Job_PersistenceIT() {
        super(Job.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("select_OrderByJobTitleAsc")
    @Nested
    class Select_OrderByJobTitleAsc_Test {

        @Test
        void __() {
            final var result = applyEntityManager(em -> {
                return em.createNamedQuery("Job.select_OrderByJobTitleAsc", Job.class)
                        .setMaxResults(10)
                        .getResultList();
            });
            assertThat(result)
                    .isNotEmpty()
                    .satisfiesAnyOf(
                            l -> {
                                assertThat(l).isSortedAccordingTo(
                                        Comparator.comparing(Job::getJobTitle)
                                );
                            },
                            l -> {
                                assertThat(l).isSortedAccordingTo(
                                        Comparator.comparing(Job::getJobTitle, String.CASE_INSENSITIVE_ORDER)
                                );
                            }
                    )
            ;
        }
    }
}
