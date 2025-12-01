package com.github.jinahya.oracle.sample.schemas.co.mapped;

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

import com.github.jinahya.persistence.mapped.test.__MappedBuilder_Test;

abstract class _MappedCoBuilder_Test<
        BUILDER extends _MappedCoBuilder<BUILDER, MAPPED>,
        MAPPED extends _MappedCo
        >
        extends __MappedBuilder_Test<BUILDER, MAPPED> {

    _MappedCoBuilder_Test(final Class<BUILDER> builderClass, final Class<MAPPED> mappedClass) {
        super(builderClass, mappedClass);
    }
}
