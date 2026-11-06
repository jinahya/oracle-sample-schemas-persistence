package com.github.jinahya.oracle.sample.persistence.co;

/*-
 * #%L
 * co
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

import com.github.jinahya.oracle.sample.persistence.shared._Entity_PersistenceTest;

/**
 * Verifies the mappings of {@link Inventory} against the schema generated into the in-memory
 * database.
 * <p>
 * Each persisted {@link Inventory} is given a store and a product of its own, so a query by either
 * selects it alone.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_PersistenceTest
        extends _Entity_PersistenceTest<Inventory, Long> {

    // ---------------------------------------------------------------------------------------------
    Inventory_PersistenceTest() {
        super(Inventory.class, Long.class, __PersistenceTest_Utils.PERSISTENCE_UNIT_NAME);
    }
}
