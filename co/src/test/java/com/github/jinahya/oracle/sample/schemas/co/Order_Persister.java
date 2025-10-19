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
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Order_Persister extends __MappedEntity_Persister<Order, Long> {

    Order_Persister() {
        super(Order.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(final EntityManager entityManager, final Order entityInstance) {
        entityInstance.setCustomer(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Customer.class)
        );
        entityInstance.setStore(
                __MappedEntity_PersisterUtils.newPersistedInstanceOf(entityManager, Store.class)
        );
        super.persist(entityManager, entityInstance);
    }
}
