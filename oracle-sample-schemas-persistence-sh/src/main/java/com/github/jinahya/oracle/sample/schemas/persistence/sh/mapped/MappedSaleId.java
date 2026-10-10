package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * A superclass for the identifier of the {@value MappedSale#TABLE_NAME} table -- the
 * {@value MappedSale#COLUMN_NAME_PROD_ID}, the {@value MappedSale#COLUMN_NAME_CUST_ID}, the
 * {@value MappedSale#COLUMN_NAME_TIME_ID}, the {@value MappedSale#COLUMN_NAME_CHANNEL_ID}, and the
 * {@value MappedSale#COLUMN_NAME_PROMO_ID} columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedSale
 */
@MappedSuperclass
public abstract class MappedSaleId {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_PROD_ID =
            MappedSale.COLUMN_PRECISION_PROD_ID - MappedSale.COLUMN_SCALE_PROD_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_PROD_ID = MappedSale.COLUMN_SCALE_PROD_ID;

    /**
     * The name of the attribute which maps the {@value MappedSale#COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedSale#COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedSale#COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID =
            MappedSale.COLUMN_PRECISION_CHANNEL_ID - MappedSale.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID = MappedSale.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The name of the attribute which maps the {@value MappedSale#COLUMN_NAME_CHANNEL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_PROMO_ID =
            MappedSale.COLUMN_PRECISION_PROMO_ID - MappedSale.COLUMN_SCALE_PROMO_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_PROMO_ID = MappedSale.COLUMN_SCALE_PROMO_ID;

    /**
     * The name of the attribute which maps the {@value MappedSale#COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance of a subclass, with the specified values.
     *
     * @param <T>          the type of the identifier.
     * @param instantiator a supplier of a new, empty instance of {@code T}.
     * @param prodId       a value for the {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     * @param custId       a value for the {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     * @param timeId       a value for the {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     * @param channelId    a value for the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     * @param promoId      a value for the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     * @return the instance supplied by {@code instantiator}, with its attributes set.
     * @throws NullPointerException if {@code instantiator} is {@code null}, or it supplies {@code null}.
     */
    protected static <T extends MappedSaleId> T of(final Supplier<? extends T> instantiator,
                                                   final Integer prodId,
                                                   final Long custId,
                                                   final LocalDateTime timeId,
                                                   final Long channelId,
                                                   final Integer promoId) {
        Objects.requireNonNull(instantiator, "instantiator is null");
        final var instance = Objects.requireNonNull(instantiator.get(), "instantiator.get() is null");
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
    protected MappedSaleId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
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
        if (!(obj instanceof MappedSaleId that)) {
            return false;
        }
        return Objects.equals(getProdId(), that.getProdId())
               && Objects.equals(getCustId(), that.getCustId())
               && Objects.equals(getTimeId(), that.getTimeId())
               && Objects.equals(getChannelId(), that.getChannelId())
               && Objects.equals(getPromoId(), that.getPromoId());
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
        return Objects.hash(getProdId(), getCustId(), getTimeId(), getChannelId(), getPromoId());
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
    protected void setProdId(final Integer prodId) {
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
    protected void setCustId(final Long custId) {
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
    protected void setTimeId(final LocalDateTime timeId) {
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
    protected void setChannelId(final Long channelId) {
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
    protected void setPromoId(final Integer promoId) {
        this.promoId = promoId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_PROD_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_PROD_ID)
    @Basic(optional = false)
    @Column(name = MappedSale.COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @NotNull
    @Basic(optional = false)
    @Column(name = MappedSale.COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

    @NotNull
    @Basic(optional = false)
    @Column(name = MappedSale.COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID)
    @Basic(optional = false)
    @Column(name = MappedSale.COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_PROMO_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_PROMO_ID)
    @Basic(optional = false)
    @Column(name = MappedSale.COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;
}
