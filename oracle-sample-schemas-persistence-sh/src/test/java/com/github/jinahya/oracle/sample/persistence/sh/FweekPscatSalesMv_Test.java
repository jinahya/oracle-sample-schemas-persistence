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

import com.github.jinahya.oracle.sample.persistence.sh.mapped.FweekPscatSalesMvId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

import java.time.LocalDateTime;

/**
 * A class for testing the {@link FweekPscatSalesMv} entity class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class FweekPscatSalesMv_Test
        extends _Entity_Test<FweekPscatSalesMv> {

    private static FweekPscatSalesMvId id(final int value) {
        final var id = new FweekPscatSalesMvId();
        id.setWeekEndingDay(LocalDateTime.of(2000 + value, 1, 1, 0, 0));
        id.setProdSubcategory("subcategory" + value);
        id.setChannelId((long) value);
        id.setPromoId(value);
        return id;
    }

    // ---------------------------------------------------------------------------------------------

    FweekPscatSalesMv_Test() {
        super(FweekPscatSalesMv.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link FweekPscatSalesMv#equals(Object) equals} compares only the
     *         {@code @Id}, because every other attribute is mutable state.
     */
    @Override
    public SingleTypeEqualsVerifierApi<FweekPscatSalesMv> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValuesForField(FweekPscatSalesMv.ATTRIBUTE_NAME_ID_, id(1), id(2))
                .suppress(Warning.SURROGATE_KEY);
    }
}
