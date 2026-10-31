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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedFweekPscatSalesMvId;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * An embeddable class for the composite identifier of the {@link FweekPscatSalesMv} entity class, which maps it with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class FweekPscatSalesMvId extends MappedFweekPscatSalesMvId {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param weekEndingDay   the {@value FweekPscatSalesMv#COLUMN_NAME_WEEK_ENDING_DAY} column value.
     * @param prodSubcategory the {@value FweekPscatSalesMv#COLUMN_NAME_PROD_SUBCATEGORY} column value.
     * @param channelId       the {@value FweekPscatSalesMv#COLUMN_NAME_CHANNEL_ID} column value.
     * @param promoId         the {@value FweekPscatSalesMv#COLUMN_NAME_PROMO_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static FweekPscatSalesMvId of(final LocalDateTime weekEndingDay,
                                         final String prodSubcategory,
                                         final Long channelId,
                                         final Integer promoId) {
        return of(FweekPscatSalesMvId::new, weekEndingDay, prodSubcategory, channelId, promoId);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public FweekPscatSalesMvId() {
        super();
    }
}
