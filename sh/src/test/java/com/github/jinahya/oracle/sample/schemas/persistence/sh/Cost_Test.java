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

import com.github.jinahya.oracle.sample.schemas.persistence.test._Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link Cost} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Cost_Test extends _Test<Cost> {

    Cost_Test() {
        super(Cost.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link Cost#equals(Object) equals} compares the dimensions the row references -- the table's natural
     * key -- and not the measures, which are the row's payload.
     */
    @Override
    protected SingleTypeEqualsVerifierApi<Cost> equals_verifier_() {
        return super.equals_verifier_()
                .withOnlyTheseFields(
                        Cost.ATTRIBUTE_NAME_PRODUCT,
                        Cost.ATTRIBUTE_NAME_TIME,
                        Cost.ATTRIBUTE_NAME_PROMOTION,
                        Cost.ATTRIBUTE_NAME_CHANNEL
                );
    }
}
