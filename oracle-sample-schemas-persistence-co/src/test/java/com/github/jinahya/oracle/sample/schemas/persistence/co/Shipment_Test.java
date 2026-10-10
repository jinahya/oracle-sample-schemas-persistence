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

import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link Shipment} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Shipment_Test extends _DomainEntity_Test<Shipment, Long> {

    Shipment_Test() {
        super(Shipment.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link Shipment#equals(Object) equals} compares only the generated {@code @Id}, {@code shipmentId}, the
     * surrogate key, and {@link Shipment#hashCode() hashCode} is constant, so that the hash does not change when the
     * {@code @Id} is assigned on persist. That takes three warnings suppressed: {@link Warning#SURROGATE_KEY} for the
     * former, {@link Warning#STRICT_HASHCODE} for the latter, and {@link Warning#IDENTICAL_COPY_FOR_VERSIONED_ENTITY}
     * because an instance whose {@code @Id} is still {@code null} equals itself only.
     */
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY,
                          Warning.IDENTICAL_COPY_FOR_VERSIONED_ENTITY,
                          Warning.STRICT_HASHCODE);
    }
}
