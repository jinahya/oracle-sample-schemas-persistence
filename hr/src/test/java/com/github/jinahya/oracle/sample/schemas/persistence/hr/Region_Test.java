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

import com.github.jinahya.oracle.sample.schemas.persistence.test._Test;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link Region} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Region_Test extends _Test<Region> {

    Region_Test() {
        super(Region.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link Region#equals(Object) equals} compares only the {@code @Id} {@code regionId} -- the surrogate
     * key -- because every other attribute is mutable state.
     */
    @Override
    protected SingleTypeEqualsVerifierApi<Region> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY);
    }
}
