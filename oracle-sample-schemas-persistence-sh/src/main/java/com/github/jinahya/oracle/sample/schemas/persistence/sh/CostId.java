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
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A class for the composite identifier of the {@link Cost} entity class, which maps it with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class CostId {

    /**
     * The name of the database table whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = Cost.TABLE_NAME;

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the attribute which maps the {@value Cost#COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the attribute which maps the {@value Cost#COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the attribute which maps the {@value Cost#COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the attribute which maps the {@value Cost#COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param prodId    the {@value Cost#COLUMN_NAME_PROD_ID} column value.
     * @param timeId    the {@value Cost#COLUMN_NAME_TIME_ID} column value.
     * @param promoId   the {@value Cost#COLUMN_NAME_PROMO_ID} column value.
     * @param channelId the {@value Cost#COLUMN_NAME_CHANNEL_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static CostId of(final Integer prodId,
                            final LocalDateTime timeId,
                            final Integer promoId,
                            final Long channelId) {
        final var instance = new CostId();
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
    public CostId() {
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
        if (!(obj instanceof CostId that)) {
            return false;
        }
        return Objects.equals(prodId, that.prodId)
               && Objects.equals(timeId, that.timeId)
               && Objects.equals(promoId, that.promoId)
               && Objects.equals(channelId, that.channelId);
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
        return Objects.hash(prodId, timeId, promoId, channelId);
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

    // ---------------------------------------------------------------------------------------------------------------- 

    @NotNull
    @Basic(optional = false)
    @Column(name = Cost.COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @NotNull
    @Basic(optional = false)
    @Column(name = Cost.COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    @NotNull
    @Basic(optional = false)
    @Column(name = Cost.COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @NotNull
    @Basic(optional = false)
    @Column(name = Cost.COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;
}
