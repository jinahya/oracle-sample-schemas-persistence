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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A class for the composite identifier of the {@link Profit} entity class, which maps it with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class ProfitId {

    /**
     * The name of the database view whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = Profit.TABLE_NAME;

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the attribute which maps the {@value Profit#COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the attribute which maps the {@value Profit#COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the attribute which maps the {@value Profit#COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the attribute which maps the {@value Profit#COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the attribute which maps the {@value Profit#COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

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

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "channelId=" + channelId +
               ",custId=" + custId +
               ",prodId=" + prodId +
               ",promoId=" + promoId +
               ",timeId=" + timeId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by all of {@value #ATTRIBUTE_NAME_CHANNEL_ID}, {@value #ATTRIBUTE_NAME_CUST_ID},
     * {@value #ATTRIBUTE_NAME_PROD_ID}, {@value #ATTRIBUTE_NAME_PROMO_ID}, and {@value #ATTRIBUTE_NAME_TIME_ID}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof ProfitId that)) {
            return false;
        }
        return Objects.equals(channelId, that.channelId)
               && Objects.equals(custId, that.custId)
               && Objects.equals(prodId, that.prodId)
               && Objects.equals(promoId, that.promoId)
               && Objects.equals(timeId, that.timeId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over all of {@value #ATTRIBUTE_NAME_CHANNEL_ID}, {@value #ATTRIBUTE_NAME_CUST_ID},
     * {@value #ATTRIBUTE_NAME_PROD_ID}, {@value #ATTRIBUTE_NAME_PROMO_ID}, and {@value #ATTRIBUTE_NAME_TIME_ID},
     * consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(channelId, custId, prodId, promoId, timeId);
    }

    // ------------------------------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    // ---------------------------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public Long getCustId() {
        return custId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public void setCustId(final Long custId) {
        this.custId = custId;
    }

    // ---------------------------------------------------------------------------------------------------------- prodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute with the specified value.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    public void setProdId(final Integer prodId) {
        this.prodId = prodId;
    }

    // --------------------------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public Integer getPromoId() {
        return promoId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute with the specified value.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public void setPromoId(final Integer promoId) {
        this.promoId = promoId;
    }

    // ---------------------------------------------------------------------------------------------------------- timeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public LocalDateTime getTimeId() {
        return timeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public void setTimeId(final LocalDateTime timeId) {
        this.timeId = timeId;
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    @Digits(integer = Profit.COLUMN_PRECISION_CHANNEL_ID - Profit.COLUMN_SCALE_CHANNEL_ID,
            fraction = Profit.COLUMN_SCALE_CHANNEL_ID)
    @Basic(optional = false)
    @Column(name = Profit.COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    @Basic(optional = false)
    @Column(name = Profit.COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

    @Digits(integer = Profit.COLUMN_PRECISION_PROD_ID - Profit.COLUMN_SCALE_PROD_ID,
            fraction = Profit.COLUMN_SCALE_PROD_ID)
    @Basic(optional = false)
    @Column(name = Profit.COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @Digits(integer = Profit.COLUMN_PRECISION_PROMO_ID - Profit.COLUMN_SCALE_PROMO_ID,
            fraction = Profit.COLUMN_SCALE_PROMO_ID)
    @Basic(optional = false)
    @Column(name = Profit.COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @Basic(optional = false)
    @Column(name = Profit.COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;
}
