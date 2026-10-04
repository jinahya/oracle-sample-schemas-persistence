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
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * A superclass for the identifier of the {@value MappedCost#TABLE_NAME} table -- the
 * {@value MappedCost#COLUMN_NAME_PROD_ID}, the {@value MappedCost#COLUMN_NAME_TIME_ID}, the
 * {@value MappedCost#COLUMN_NAME_PROMO_ID}, and the {@value MappedCost#COLUMN_NAME_CHANNEL_ID} columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedCost
 */
public abstract class MappedCostId {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedCost#COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedCost#COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedCost#COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedCost#COLUMN_NAME_CHANNEL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance of a subclass, with the specified values.
     *
     * @param <T>          the type of the identifier.
     * @param instantiator a supplier of a new, empty instance of {@code T}.
     * @param prodId       a value for the {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     * @param timeId       a value for the {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     * @param promoId      a value for the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     * @param channelId    a value for the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     * @return the instance supplied by {@code instantiator}, with its attributes set.
     * @throws NullPointerException if {@code instantiator} is {@code null}, or it supplies {@code null}.
     */
    protected static <T extends MappedCostId> T of(final Supplier<? extends T> instantiator,
                                                   final Integer prodId,
                                                   final LocalDate timeId,
                                                   final Integer promoId,
                                                   final Long channelId) {
        Objects.requireNonNull(instantiator, "instantiator is null");
        final var instance = Objects.requireNonNull(instantiator.get(), "instantiator.get() is null");
        instance.setProdId(prodId);
        instance.setTimeId(timeId);
        instance.setPromoId(promoId);
        instance.setChannelId(channelId);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCostId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "prodId=" + prodId +
               ",timeId=" + timeId +
               ",promoId=" + promoId +
               ",channelId=" + channelId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by all of {@value #ATTRIBUTE_NAME_PROD_ID}, {@value #ATTRIBUTE_NAME_TIME_ID},
     * {@value #ATTRIBUTE_NAME_PROMO_ID}, and {@value #ATTRIBUTE_NAME_CHANNEL_ID}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCostId that)) {
            return false;
        }
        return Objects.equals(getProdId(), that.getProdId())
               && Objects.equals(getTimeId(), that.getTimeId())
               && Objects.equals(getPromoId(), that.getPromoId())
               && Objects.equals(getChannelId(), that.getChannelId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over all of {@value #ATTRIBUTE_NAME_PROD_ID}, {@value #ATTRIBUTE_NAME_TIME_ID},
     * {@value #ATTRIBUTE_NAME_PROMO_ID}, and {@value #ATTRIBUTE_NAME_CHANNEL_ID}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getProdId(), getTimeId(), getPromoId(), getChannelId());
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

    // ---------------------------------------------------------------------------------------------------------- timeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public LocalDate getTimeId() {
        return timeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    protected void setTimeId(final LocalDate timeId) {
        this.timeId = timeId;
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

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedCost.COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @NotNull
    @Basic(optional = false)
    @Column(name = MappedCost.COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDate timeId;

    @NotNull
    @Basic(optional = false)
    @Column(name = MappedCost.COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @NotNull
    @Basic(optional = false)
    @Column(name = MappedCost.COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;
}
