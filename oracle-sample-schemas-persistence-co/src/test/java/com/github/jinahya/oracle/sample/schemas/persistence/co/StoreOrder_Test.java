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

import org.junit.jupiter.api.Test;

/**
 * A class for testing the {@link StoreOrder} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class StoreOrder_Test extends _DomainEntity_Test<StoreOrder, Void> {

    StoreOrder_Test() {
        super(StoreOrder.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * Does nothing; {@link StoreOrder} declares no {@code equals(Object)}, so there is no value equality to verify.
     */
    @Override
    @Test
    protected void equals_verify_() {
        // empty
    }
}
