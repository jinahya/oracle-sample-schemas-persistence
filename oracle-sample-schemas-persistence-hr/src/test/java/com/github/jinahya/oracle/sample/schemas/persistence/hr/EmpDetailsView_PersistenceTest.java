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

/**
 * Verifies the mappings of {@link EmpDetailsView} against the schema generated into the in-memory database.
 * <p>
 * The persistence unit does not list {@link EmpDetailsView}, so every test here aborts until it does.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class EmpDetailsView_PersistenceTest extends _Persistence_Test<EmpDetailsView> {

    EmpDetailsView_PersistenceTest() {
        super(EmpDetailsView.class);
    }
}
