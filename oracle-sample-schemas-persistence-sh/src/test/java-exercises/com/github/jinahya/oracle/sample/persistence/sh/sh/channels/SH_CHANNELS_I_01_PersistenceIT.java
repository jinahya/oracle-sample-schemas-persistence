package com.github.jinahya.oracle.sample.persistence.sh.sh.channels;

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

import com.github.jinahya.oracle.sample.persistence.sh.Channel;
import com.github.jinahya.oracle.sample.persistence.sh.Channel_;
import com.github.jinahya.oracle.sample.persistence.sh.__Exercises_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_CHANNELS_I_01} of {@code src/test/sql-exercises/CHANNELS/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CHANNELS_I_01: Channels per class")
@Slf4j
class SH_CHANNELS_I_01_PersistenceIT {

    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> r[0], r -> ((Number) r[1]).longValue())
                .containsExactly(
                        tuple("Direct", 2L),
                        tuple("Indirect", 2L),
                        tuple("Others", 1L));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c.channelClass, COUNT(c) AS channels
                                        FROM Channel c
                                        GROUP BY c.channelClass
                                        ORDER BY channels DESC, c.channelClass""",
                                Object[].class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var c = cq.from(Channel.class);
            final var channels = cb.count(c);
            cq.select(cb.array(c.get(Channel_.channelClass), channels))
                    .groupBy(c.get(Channel_.channelClass))
                    .orderBy(cb.desc(channels), cb.asc(c.get(Channel_.channelClass)));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
