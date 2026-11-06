package com.github.jinahya.oracle.sample.persistence.sh;

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

import com.github.jinahya.oracle.sample.persistence.sh.mapped.SalesId;
import com.github.jinahya.oracle.sample.persistence.shared._Entity_Test;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

import java.time.LocalDateTime;

/**
 * A class for testing the {@link Sales} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Sales_Test
        extends _Entity_Test<Sales, SalesId> {

    private static SalesId id(final int value) {
        final var id = new SalesId();
        id.setProdId(value);
        id.setCustId((long) value);
        id.setTimeId(LocalDateTime.of(2000 + value, 1, 1, 0, 0));
        id.setChannelId((long) value);
        id.setPromoId(value);
        return id;
    }

    // ---------------------------------------------------------------------------------------------

    Sales_Test() {
        super(Sales.class, SalesId.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link Sales#equals(Object) equals} compares only the {@code @EmbeddedId},
     *         because every other attribute is mutable state, and {@link Sales#hashCode()
     *         hashCode} is a constant, because the {@code @EmbeddedId} is assigned only on persist.
     *         EqualsVerifier is given two identifiers, since it cannot mock {@link SalesId}.
     */
    @Override
    public SingleTypeEqualsVerifierApi<Sales> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValuesForField(Sales.ATTRIBUTE_NAME_ID_, id(1), id(2))
                .suppress(Warning.SURROGATE_KEY, Warning.STRICT_HASHCODE);
    }
}
