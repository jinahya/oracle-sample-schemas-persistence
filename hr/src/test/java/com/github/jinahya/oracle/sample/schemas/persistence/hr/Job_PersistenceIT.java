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

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class Job_PersistenceIT extends _MappedHrEntity_PersistenceIT<Job, String> {

    Job_PersistenceIT() {
        super(Job.class, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("Select__OrderByMinSalaryAscNullsFirst")
    @Nested
    class Select__OrderByMinSalaryAscNullsFirst_Test {

        @Test
        void __NamedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var maxResults = ThreadLocalRandom.current().nextInt(10) + 1;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                return em.createNamedQuery("Job.Select__OrderByMinSalaryAscNullsFirst", Job.class)
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(MappedJob.COMPARATOR_MIN_SALARY_NATURAL_NULLS_FIRST);
        }

        @Test
        void __QueryLanguage() {
            // --------------------------------------------------------------------------------------------------- given
            final var maxResults = ThreadLocalRandom.current().nextInt(10) + 1;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                return em.createQuery(
                                """
                                        SELECT e
                                        FROM Job e
                                        ORDER BY e.minSalary ASC NULLS FIRST""",
                                Job.class
                        )
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(MappedJob.COMPARATOR_MIN_SALARY_NATURAL_NULLS_FIRST);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var maxResults = ThreadLocalRandom.current().nextInt(10) + 1;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var b = em.getCriteriaBuilder();
                final var q = b.createQuery(Job.class);
                final var r = q.from(Job.class);
                q.select(r);
                q.orderBy(
                        b.asc(b.coalesce(r.get(Job_.minSalary), Integer.MIN_VALUE))
                );
                return em.createQuery(q)
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(MappedJob.COMPARATOR_MIN_SALARY_NATURAL_NULLS_FIRST);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("Select__OrderByMaxSalaryDescNullsLast")
    @Nested
    class Select__OrderByMaxSalaryDescNullsLast_Test {

        @Test
        void __NamedQuery() {
            // --------------------------------------------------------------------------------------------------- given
            final var maxResults = ThreadLocalRandom.current().nextInt(10) + 1;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                return em.createNamedQuery("Job.Select__OrderByMaxSalaryDescNullsLast", Job.class)
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(MappedJob.COMPARATOR_MAX_SALARY_REVERSE_NULLS_LAST);
        }

        @Test
        void __QueryLanguage() {
            // --------------------------------------------------------------------------------------------------- given
            final var maxResults = ThreadLocalRandom.current().nextInt(10) + 1;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                return em.createQuery(
                                """
                                        SELECT e
                                        FROM Job e
                                        ORDER BY e.maxSalary DESC NULLS LAST""",
                                Job.class
                        )
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(MappedJob.COMPARATOR_MAX_SALARY_REVERSE_NULLS_LAST);
        }

        @Test
        void __CriteriaApi() {
            // --------------------------------------------------------------------------------------------------- given
            final var maxResults = ThreadLocalRandom.current().nextInt(10) + 1;
            // ---------------------------------------------------------------------------------------------------- when
            final var result = applyEntityManager(em -> {
                final var b = em.getCriteriaBuilder();
                final var q = b.createQuery(Job.class);
                final var r = q.from(Job.class);
                q.select(r);
                q.orderBy(
                        b.desc(b.coalesce(r.get(Job_.maxSalary), Integer.MAX_VALUE))
                );
                return em.createQuery(q)
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(result)
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(MappedJob.COMPARATOR_MAX_SALARY_REVERSE_NULLS_LAST);
        }
    }
}
