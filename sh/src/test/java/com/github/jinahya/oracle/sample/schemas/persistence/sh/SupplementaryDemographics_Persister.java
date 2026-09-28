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
import jakarta.persistence.EntityManager;

class SupplementaryDemographics_Persister extends __Persister<SupplementaryDemographics> {

    SupplementaryDemographics_Persister() {
        super(SupplementaryDemographics.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public SupplementaryDemographics apply(final EntityManager entityManager,
                                           final SupplementaryDemographics entityInstance) {
        return super.apply(entityManager, entityInstance);
    }
}
