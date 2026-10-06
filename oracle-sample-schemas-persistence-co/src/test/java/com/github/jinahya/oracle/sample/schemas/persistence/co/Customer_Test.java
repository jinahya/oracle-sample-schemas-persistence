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

import com.github.jinahya.oracle.sample.schemas.persistence.test.__Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link Customer} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Customer_Test extends __Test<Customer> {

    Customer_Test() {
        super(Customer.class);
    }

    // -------------------------------------------------------------------------------------------------------- toString

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link Customer#equals(Object) equals} compares the business key -- the unique
     * {@value Customer#COLUMN_NAME_EMAIL_ADDRESS} column -- rather than the surrogate
     * {@value Customer#ATTRIBUTE_NAME_CUSTOMER_ID}, which is null until the row is inserted. The remaining attributes
     * are mutable state, and the associations are recursive, so none of them may take part.
     */
    @Override
    protected SingleTypeEqualsVerifierApi<Customer> equals_verifier_() {
        return super.equals_verifier_()
                .withOnlyTheseFields(Customer.ATTRIBUTE_NAME_EMAIL_ADDRESS);
    }
}
