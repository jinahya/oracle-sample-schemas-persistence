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

/**
 * Verifies the mappings of {@link ProfitWithIdClass} against the installed {@code SH} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class ProfitWithIdClass_PersistenceIT extends _Persistence_IT<ProfitWithIdClass> {

    ProfitWithIdClass_PersistenceIT() {
        super(ProfitWithIdClass.class);
    }
}
