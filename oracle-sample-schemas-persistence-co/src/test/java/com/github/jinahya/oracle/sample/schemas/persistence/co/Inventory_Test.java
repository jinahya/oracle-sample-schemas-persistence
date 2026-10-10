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
 * A class for testing the {@link Inventory} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Inventory_Test extends _DomainEntity_Test<Inventory, Long> {

    Inventory_Test() {
        super(Inventory.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote On this branch {@link Inventory} inherits {@code equals} and {@code hashCode} from
     * {@code MappedInventory}: equality is by the business key, ({@code storeId}, {@code productId}), the pair the
     * table declares unique, not by the {@code @Id}; an instance missing either equals itself only; and the hash is
     * constant. That takes three warnings suppressed: {@link Warning#ALL_FIELDS_SHOULD_BE_USED} for the fields outside
     * the key, {@link Warning#IDENTICAL_COPY_FOR_VERSIONED_ENTITY} for an instance missing the key equalling itself
     * only, and {@link Warning#STRICT_HASHCODE} for the constant hash. This copy is this branch's own, kept through
     * merges from {@code develop} by {@code merge=ours}; {@code develop}'s verifies the {@code @Id}-only equality of its
     * own {@code Inventory}.
     */
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.STRICT_HASHCODE,
                          Warning.IDENTICAL_COPY_FOR_VERSIONED_ENTITY,
                          Warning.ALL_FIELDS_SHOULD_BE_USED);
    }
}
