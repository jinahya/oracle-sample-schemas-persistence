package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

public abstract class _MappedShEntity_Test<ENTITY extends _MappedShEntity<ID>, ID>
        extends __MappedEntity_Test<ENTITY, ID> {

    protected _MappedShEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<ENTITY> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<ENTITY> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier);
    }
}
