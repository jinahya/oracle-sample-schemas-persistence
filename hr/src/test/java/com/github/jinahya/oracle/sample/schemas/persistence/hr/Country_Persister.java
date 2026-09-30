package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
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
 * A persister which persists {@link Country} instances.
 * <p>
 * Each instance is given a newly persisted {@link Region} first, so that its {@code region} refers to a row which is
 * already in the database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Country_Persister extends AbstractEntityPersister<Country> {

    Country_Persister() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Country apply(final EntityManager entityManager, final Country entityInstance) {
        entityInstance.setRegion(EntityPersisterUtils.newPersistedInstanceOf(entityManager, Region.class));
        return super.apply(entityManager, entityInstance);
    }
}
