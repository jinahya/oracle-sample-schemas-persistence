package com.github.jinahya.oracle.sample.schemas.persistence.co;

/*-
 * #%L
 * co
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
 * A persister which persists {@link Inventory} instances.
 * <p>
 * Each instance is given a newly persisted {@link Store} and a newly persisted {@link Product} first, so that its
 * {@code store} and its {@code product} -- neither of which is nullable -- refer to rows which are already in the
 * database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_Persister extends AbstractEntityPersister<Inventory> {

    Inventory_Persister() {
        super(Inventory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Inventory apply(final EntityManager entityManager, final Inventory entityInstance) {
        final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class);
        final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        entityManager.flush();
        entityInstance.setStore(store);
        entityInstance.setProduct(product);
        return super.apply(entityManager, entityInstance);
    }
}
