package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Verifies the mappings of {@link CustomerOrderProduct} against the schema generated into the in-memory database.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class CustomerOrderProduct_Persistence_Test extends _DomainEntity_Persistence_Test<CustomerOrderProduct, Long> {

    CustomerOrderProduct_Persistence_Test() {
        super(CustomerOrderProduct.class);
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    /**
     * Does nothing; {@value CustomerOrderProduct#TABLE_NAME} is a view, so there is nothing to insert a randomized
     * instance into. The provider generates a table for it into the in-memory database, which would make this pass for
     * the wrong reason, and the real object is read-only.
     */
    @Disabled("a view: nothing is inserted into it")
    @Override
    @Test
    protected void _persist_RandomizedInstance() {
        // empty
    }
}
