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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedProfitId;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * A class for the composite identifier of the {@link Profit} entity class, which maps it with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class ProfitId extends MappedProfitId {

    /**
     * The name of the database view whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = Profit.TABLE_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param channelId the {@value Profit#COLUMN_NAME_CHANNEL_ID} column value.
     * @param custId    the {@value Profit#COLUMN_NAME_CUST_ID} column value.
     * @param prodId    the {@value Profit#COLUMN_NAME_PROD_ID} column value.
     * @param promoId   the {@value Profit#COLUMN_NAME_PROMO_ID} column value.
     * @param timeId    the {@value Profit#COLUMN_NAME_TIME_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static ProfitId of(final Long channelId,
                              final Long custId,
                              final Integer prodId,
                              final Integer promoId,
                              final LocalDateTime timeId) {
        final var instance = new ProfitId();
        instance.setChannelId(channelId);
        instance.setCustId(custId);
        instance.setProdId(prodId);
        instance.setPromoId(promoId);
        instance.setTimeId(timeId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProfitId() {
        super();
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    @Override
    public void setCustId(final Long custId) {
        super.setCustId(custId);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute with the specified value.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    @Override
    public void setProdId(final Integer prodId) {
        super.setProdId(prodId);
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    @Override
    public void setTimeId(final LocalDateTime timeId) {
        super.setTimeId(timeId);
    }
}
