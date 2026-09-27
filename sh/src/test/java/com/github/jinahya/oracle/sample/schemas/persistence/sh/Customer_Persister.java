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

class Customer_Persister extends __Persister<Customer> {

    Customer_Persister() {
        super(Customer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Customer apply(final EntityManager entityManager, final Customer entityInstance) {
        entityInstance.setCountry(__PersisterUtils.newPersistedInstanceOf(entityManager, Country.class));
        return super.apply(entityManager, entityInstance);
    }
}
