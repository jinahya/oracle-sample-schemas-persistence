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

/**
 * A persister which persists {@link CostWithIdClass} instances. Each instance is first given newly persisted
 * {@link Product}, {@link Time}, {@link Promotion} and {@link Channel} rows, and their keys copied into the composite
 * identifier, because every column of that identifier is a foreign key.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class CostWithIdClass_Persister extends AbstractEntityPersister<CostWithIdClass> {

    CostWithIdClass_Persister() {
        super(CostWithIdClass.class);
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
    public CostWithIdClass apply(final EntityManager entityManager, final CostWithIdClass entityInstance) {
        entityInstance.setProdId(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class).getProdId()
        );
        entityInstance.setTimeId(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Time.class).getTimeId()
        );
        entityInstance.setPromoId(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Promotion.class).getPromoId()
        );
        entityInstance.setChannelId(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Channel.class).getChannelId()
        );
        return super.apply(entityManager, entityInstance);
    }
}
