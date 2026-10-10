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

    /**
     * The name of the database materialized view whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = FweekPscatSalesMv.TABLE_NAME;

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
        final var instance = new FweekPscatSalesMvId();
        instance.setWeekEndingDay(weekEndingDay);
        instance.setProdSubcategory(prodSubcategory);
        instance.setChannelId(channelId);
        instance.setPromoId(promoId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public FweekPscatSalesMvId() {
        super();
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute with the specified value.
     *
     * @param weekEndingDay new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    @Override
    public void setWeekEndingDay(final LocalDateTime weekEndingDay) {
        super.setWeekEndingDay(weekEndingDay);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute with the specified value.
     *
     * @param prodSubcategory new value for {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     */
    @Override
    public void setProdSubcategory(final String prodSubcategory) {
        super.setProdSubcategory(prodSubcategory);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    @Override
    public void setChannelId(final Long channelId) {
        super.setChannelId(channelId);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute with the specified value.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    @Override
    public void setPromoId(final Integer promoId) {
        super.setPromoId(promoId);
    }
}
