package com.github.jinahya.oracle.sample.persistence.sh;

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

import com.github.jinahya.oracle.sample.persistence.sh.mapped.MappedChannel;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value Channel#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Channel.selectOneByChannelId",
            query = """
                    SELECT e
                    FROM Channel e
                    WHERE e.channelId = :channelId"""
)
@NamedQuery(name = "Channel.selectListOrderByChannelIdAsc",
            query = """
                    SELECT e
                    FROM Channel e
                    ORDER BY e.channelId ASC"""
)
@NamedQuery(name = "Channel.selectListOrderByChannelIdAscChannelIdGt",
            query = """
                    SELECT e
                    FROM Channel e
                    WHERE e.channelId > :channelIdMinExclusive
                    ORDER BY e.channelId ASC"""
)
@NamedQuery(name = "Channel.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Channel e"""
)
@Entity
@Table(name = MappedChannel.TABLE_NAME)
public class Channel extends MappedChannel {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Channel() {
        super();
    }
}
