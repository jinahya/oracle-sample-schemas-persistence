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

import java.time.LocalDate;
import java.util.Objects;

/**
 * An id class for the {@link CostWithEmbeddedId} and {@link CostWithIdClass} entity classes.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class CostId {

    /**
     * The name of the database table whose primary key this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = CostWithEmbeddedId.TABLE_NAME;

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the attribute which maps the {@value CostWithEmbeddedId#COLUMN_NAME_PROD_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the attribute which maps the {@value CostWithEmbeddedId#COLUMN_NAME_TIME_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the attribute which maps the {@value CostWithEmbeddedId#COLUMN_NAME_PROMO_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the attribute which maps the {@value CostWithEmbeddedId#COLUMN_NAME_CHANNEL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param prodId    the {@value CostWithEmbeddedId#COLUMN_NAME_PROD_ID} column value.
     * @param timeId    the {@value CostWithEmbeddedId#COLUMN_NAME_TIME_ID} column value.
     * @param promoId   the {@value CostWithEmbeddedId#COLUMN_NAME_PROMO_ID} column value.
     * @param channelId the {@value CostWithEmbeddedId#COLUMN_NAME_CHANNEL_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static CostId of(final Integer prodId,
                            final LocalDate timeId,
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
    public LocalDate getTimeId() {
        return timeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public void setTimeId(final LocalDate timeId) {
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

    @Basic(optional = false)
    @Column(name = CostWithEmbeddedId.COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @Basic(optional = false)
    @Column(name = CostWithEmbeddedId.COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDate timeId;

    @Basic(optional = false)
    @Column(name = CostWithEmbeddedId.COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @Basic(optional = false)
    @Column(name = CostWithEmbeddedId.COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;
}
