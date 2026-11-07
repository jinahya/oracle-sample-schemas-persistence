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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code SH_CHANNELS_B_01} of {@code src/test/sql-exercises/CHANNELS/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("SH_CHANNELS_B_01: Routes to market")
@Slf4j
class SH_CHANNELS_B_01_PersistenceIT {

    private static void verify(final List<Channel> list) {
        list.forEach(c -> log.debug("{}", c));
        assertThat(list)
                .extracting(Channel::getChannelClass, Channel::getChannelId,
                            Channel::getChannelDesc)
                .containsExactly(
                        tuple("Direct", 3L, "Direct Sales"),
                        tuple("Direct", 9L, "Tele Sales"),
                        tuple("Indirect", 5L, "Catalog"),
                        tuple("Indirect", 4L, "Internet"),
                        tuple("Others", 2L, "Partners"));
    }

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT c
                                        FROM Channel c
                                        ORDER BY c.channelClass, c.channelDesc""",
                                Channel.class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Channel.class);
            final var c = cq.from(Channel.class);
            cq.select(c)
                    .orderBy(cb.asc(c.get(Channel_.channelClass)),
                             cb.asc(c.get(Channel_.channelDesc)));
            return em.createQuery(cq)
                    .getResultList();
        });
        verify(list);
    }
}
