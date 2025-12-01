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

import com.github.jinahya.oracle.sample.schemas.co.mapped.MappedStore;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class Store_Randomizer extends __MappedEntity_Randomizer<Store, Long> {

    Store_Randomizer() {
        super(Store.class, Long.class,
              MappedStore.ATTRIBUTE_NAME_STORE_ID,
              MappedStore.ATTRIBUTE_NAME_LOGO,
              MappedStore.ATTRIBUTE_NAME_LOGO_MIME_TYPE,
              MappedStore.ATTRIBUTE_NAME_LOGO_FILENAME,
              MappedStore.ATTRIBUTE_NAME_LOGO_CHARSET,
              MappedStore.ATTRIBUTE_NAME_LOGO_LAST_UPDATED
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Nonnull
    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    public Store get() {
        return super.get();
    }
}
