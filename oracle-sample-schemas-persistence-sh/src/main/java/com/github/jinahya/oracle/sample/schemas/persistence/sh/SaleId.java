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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedSaleId;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * An embeddable class for the composite identifier of the {@link Sale} entity class, which maps it with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class SaleId extends MappedSaleId {

    /**
     * The name of the database table whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = Sale.TABLE_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param prodId    the {@value Sale#COLUMN_NAME_PROD_ID} column value.
     * @param custId    the {@value Sale#COLUMN_NAME_CUST_ID} column value.
     * @param timeId    the {@value Sale#COLUMN_NAME_TIME_ID} column value.
     * @param channelId the {@value Sale#COLUMN_NAME_CHANNEL_ID} column value.
     * @param promoId   the {@value Sale#COLUMN_NAME_PROMO_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static SaleId of(final Integer prodId,
                            final Long custId,
                            final LocalDateTime timeId,
                            final Long channelId,
                            final Integer promoId) {
        final var instance = new SaleId();
        instance.setProdId(prodId);
        instance.setCustId(custId);
        instance.setTimeId(timeId);
        instance.setChannelId(channelId);
        instance.setPromoId(promoId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public SaleId() {
        super();
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    @Override
    public void setCustId(final Long custId) {
        super.setCustId(custId);
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
