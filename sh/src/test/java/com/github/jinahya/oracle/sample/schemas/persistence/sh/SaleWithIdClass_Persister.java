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

import com.github.jinahya.persistence.test.util.__Persister;
import com.github.jinahya.persistence.test.util.__PersisterUtils;
import jakarta.persistence.EntityManager;

class SaleWithIdClass_Persister extends __Persister<SaleWithIdClass> {

    SaleWithIdClass_Persister() {
        super(SaleWithIdClass.class);
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
    public SaleWithIdClass apply(final EntityManager entityManager, final SaleWithIdClass entityInstance) {
        entityInstance.setProdId(
                __PersisterUtils.newPersistedInstanceOf(entityManager, Product.class).getProdId()
        );
        entityInstance.setCustId(
                __PersisterUtils.newPersistedInstanceOf(entityManager, Customer.class).getCustId()
        );
        entityInstance.setTimeId(
                __PersisterUtils.newPersistedInstanceOf(entityManager, Time.class).getTimeId()
        );
        entityInstance.setChannelId(
                __PersisterUtils.newPersistedInstanceOf(entityManager, Channel.class).getChannelId()
        );
        entityInstance.setPromoId(
                __PersisterUtils.newPersistedInstanceOf(entityManager, Promotion.class).getPromoId()
        );
        return super.apply(entityManager, entityInstance);
    }
}
