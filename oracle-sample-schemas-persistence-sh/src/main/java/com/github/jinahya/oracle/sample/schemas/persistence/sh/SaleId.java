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
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An embeddable class for the composite identifier of the {@link Sale} entity class, which maps it with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class SaleId {

    /**
     * The name of the database table whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = Sale.TABLE_NAME;

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_PROD_ID =
            Sale.COLUMN_PRECISION_PROD_ID - Sale.COLUMN_SCALE_PROD_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_PROD_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_PROD_ID = Sale.COLUMN_SCALE_PROD_ID;

    /**
     * The name of the attribute which maps the {@value Sale#COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the attribute which maps the {@value Sale#COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the attribute which maps the {@value Sale#COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID =
            Sale.COLUMN_PRECISION_CHANNEL_ID - Sale.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID = Sale.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The name of the attribute which maps the {@value Sale#COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_PROMO_ID =
            Sale.COLUMN_PRECISION_PROMO_ID - Sale.COLUMN_SCALE_PROMO_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_PROMO_ID = Sale.COLUMN_SCALE_PROMO_ID;

    /**
     * The name of the attribute which maps the {@value Sale#COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

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

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "prodId=" + prodId +
               ",custId=" + custId +
               ",timeId=" + timeId +
               ",channelId=" + channelId +
               ",promoId=" + promoId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by all of {@value #ATTRIBUTE_NAME_PROD_ID}, {@value #ATTRIBUTE_NAME_CUST_ID},
     * {@value #ATTRIBUTE_NAME_TIME_ID}, {@value #ATTRIBUTE_NAME_CHANNEL_ID}, and {@value #ATTRIBUTE_NAME_PROMO_ID}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof SaleId that)) {
            return false;
        }
        return Objects.equals(prodId, that.prodId)
               && Objects.equals(custId, that.custId)
               && Objects.equals(timeId, that.timeId)
               && Objects.equals(channelId, that.channelId)
               && Objects.equals(promoId, that.promoId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over all of {@value #ATTRIBUTE_NAME_PROD_ID}, {@value #ATTRIBUTE_NAME_CUST_ID},
     * {@value #ATTRIBUTE_NAME_TIME_ID}, {@value #ATTRIBUTE_NAME_CHANNEL_ID}, and {@value #ATTRIBUTE_NAME_PROMO_ID},
     * consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(prodId, custId, timeId, channelId, promoId);
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

    // ---------------------------------------------------------------------------------------------------------------- 

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_PROD_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_PROD_ID)
    @Basic(optional = false)
    @Column(name = Sale.COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @NotNull
    @Basic(optional = false)
    @Column(name = Sale.COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

    @NotNull
    @Basic(optional = false)
    @Column(name = Sale.COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID)
    @Basic(optional = false)
    @Column(name = Sale.COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_PROMO_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_PROMO_ID)
    @Basic(optional = false)
    @Column(name = Sale.COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;
}
