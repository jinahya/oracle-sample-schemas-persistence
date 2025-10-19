package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersisterUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Inventory_Persister extends __MappedEntity_Persister<Inventory, Long> {

    Inventory_Persister() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Inventory entityInstance) {
        entityInstance.setStore(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        entityInstance.setProduct(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Product.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
