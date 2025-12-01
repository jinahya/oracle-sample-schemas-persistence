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
import com.github.jinahya.oracle.sample.schemas.co.mapped._MappedCoEntity_PersistenceIT;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
class Store_PersistenceIT extends _MappedCoEntity_PersistenceIT<Store, Long> {

    Store_PersistenceIT() {
        super(Store.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void _Mapped_AllTableColumnNames(@Nonnull final Collection<String> remainingTableColumnNames) {
        remainingTableColumnNames.remove(MappedStore.COLUMN_NAME_LOGO);
        remainingTableColumnNames.remove(MappedStore.COLUMN_NAME_LOGO_MIME_TYPE);
        remainingTableColumnNames.remove(MappedStore.COLUMN_NAME_LOGO_FILENAME);
        remainingTableColumnNames.remove(MappedStore.COLUMN_NAME_LOGO_CHARSET);
        remainingTableColumnNames.remove(MappedStore.COLUMN_NAME_LOGO_LAST_UPDATED);
        super._Mapped_AllTableColumnNames(remainingTableColumnNames);
    }
}
