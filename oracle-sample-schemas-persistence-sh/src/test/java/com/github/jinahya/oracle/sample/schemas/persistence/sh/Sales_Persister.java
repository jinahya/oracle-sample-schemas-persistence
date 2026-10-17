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
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

import java.util.stream.LongStream;

/**
 * A persister which persists {@link Sales} instances. Each instance is first given newly persisted {@link Product},
 * {@link Customer}, {@link Time}, {@link Channel} and {@link Promotion} rows, and their keys copied into the composite
 * identifier, because every column of that identifier is a foreign key.
 * <p>
 * The {@link Channel} is the exception: {@code CHANNELS.CHANNEL_ID} is an unbounded {@code NUMBER}, while the
 * {@code SALES.CHANNEL_ID} referencing it holds {@value Sales#COLUMN_PRECISION_CHANNEL_ID} digit(s) only, so a
 * randomized channel key does not fit a sales row. The channel is given the lowest key which fits and is not taken
 * yet.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Sales_Persister extends AbstractEntityPersister<Sales> {

    /**
     * Returns the lowest channel key which fits {@code SALES.CHANNEL_ID} and is not taken by a {@link Channel} yet.
     */
    private static Long freeChannelId(final EntityManager entityManager) {
        final var bound = (long) Math.pow(10, Sales.COLUMN_PRECISION_CHANNEL_ID - Sales.COLUMN_SCALE_CHANNEL_ID);
        final var taken = entityManager
                .createQuery("SELECT c.channelId FROM Channel c WHERE c.channelId < :bound", Long.class)
                .setParameter("bound", bound)
                .getResultList();
        // from 1, not 0: EclipseLink rejects a zero primary key at flush ("Null or zero primary key encountered
        // in UnitOfWork clone", EclipseLink-7197) unless the unit relaxes its id validation
        return LongStream.range(1L, bound)
                .filter(v -> !taken.contains(v))
                .boxed()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("no channel key below " + bound + " is free"));
    }

    // -----------------------------------------------------------------------------------------------------------------
    Sales_Persister() {
        super(Sales.class);
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    /**
     * {@inheritDoc}
     *
     * @param entityManager  {@inheritDoc}
     * @param entityInstance {@inheritDoc}
     * @return {@inheritDoc}
     * @implNote Every column of the composite {@code @Id} is a foreign key, and the randomizer fills them with values
     * no parent row carries. Each parent is persisted first, and its key copied in; the
     * {@link jakarta.persistence.ManyToOne @ManyToOne} mappings alongside are read-only, so assigning the column is
     * what makes the row insertable.
     */
    @Override
    public Sales apply(final EntityManager entityManager, final Sales entityInstance) {
        final var prodId = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class).getProdId();
        final var custId = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class).getCustId();
        final var timeId = Time_Persister.newPersistedInstanceBeforeEarliest(entityManager).getTimeId();
        final var channel = new Channel_Randomizer().get();
        channel.setChannelId(freeChannelId(entityManager));
        final var channelId = new Channel_Persister().apply(entityManager, channel).getChannelId();
        final var promoId = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Promotion.class).getPromoId();
        entityInstance.setId(SalesId.of(prodId, custId, timeId, channelId, promoId));
        return super.apply(entityManager, entityInstance);
    }
}
