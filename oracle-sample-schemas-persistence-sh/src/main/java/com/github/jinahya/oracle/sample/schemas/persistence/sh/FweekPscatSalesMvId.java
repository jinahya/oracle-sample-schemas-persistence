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
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An embeddable class for the composite identifier of the {@link FweekPscatSalesMv} entity class, which maps it with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class FweekPscatSalesMvId {

    /**
     * The name of the database materialized view whose identifying columns this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = FweekPscatSalesMv.TABLE_NAME;

    // ------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

    /**
     * The name of the attribute which maps the {@value FweekPscatSalesMv#COLUMN_NAME_WEEK_ENDING_DAY} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    // ------------------------------------------------------------------------------------------------ PROD_SUBCATEGORY

    /**
     * The name of the attribute which maps the {@value FweekPscatSalesMv#COLUMN_NAME_PROD_SUBCATEGORY} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY = "prodSubcategory";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID =
            FweekPscatSalesMv.COLUMN_PRECISION_CHANNEL_ID - FweekPscatSalesMv.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID = FweekPscatSalesMv.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The name of the attribute which maps the {@value FweekPscatSalesMv#COLUMN_NAME_CHANNEL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_PROMO_ID =
            FweekPscatSalesMv.COLUMN_PRECISION_PROMO_ID - FweekPscatSalesMv.COLUMN_SCALE_PROMO_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_PROMO_ID = FweekPscatSalesMv.COLUMN_SCALE_PROMO_ID;

    /**
     * The name of the attribute which maps the {@value FweekPscatSalesMv#COLUMN_NAME_PROMO_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

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

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "weekEndingDay=" + weekEndingDay +
               ",prodSubcategory=" + prodSubcategory +
               ",channelId=" + channelId +
               ",promoId=" + promoId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by all of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY},
     * {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY}, {@value #ATTRIBUTE_NAME_CHANNEL_ID}, and
     * {@value #ATTRIBUTE_NAME_PROMO_ID}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof FweekPscatSalesMvId that)) {
            return false;
        }
        return Objects.equals(weekEndingDay, that.weekEndingDay)
               && Objects.equals(prodSubcategory, that.prodSubcategory)
               && Objects.equals(channelId, that.channelId)
               && Objects.equals(promoId, that.promoId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over all of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY},
     * {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY}, {@value #ATTRIBUTE_NAME_CHANNEL_ID}, and
     * {@value #ATTRIBUTE_NAME_PROMO_ID}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(weekEndingDay, prodSubcategory, channelId, promoId);
    }

    // --------------------------------------------------------------------------------------------------- weekEndingDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public LocalDateTime getWeekEndingDay() {
        return weekEndingDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute with the specified value.
     *
     * @param weekEndingDay new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public void setWeekEndingDay(final LocalDateTime weekEndingDay) {
        this.weekEndingDay = weekEndingDay;
    }

    // ------------------------------------------------------------------------------------------------- prodSubcategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     */
    public String getProdSubcategory() {
        return prodSubcategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute with the specified value.
     *
     * @param prodSubcategory new value for {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     */
    public void setProdSubcategory(final String prodSubcategory) {
        this.prodSubcategory = prodSubcategory;
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
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects an identifier with no
    // non-read-only mapping -- "There should be one non-read-only mapping defined for the primary key field",
    // EclipseLink-46. Nothing writes to a view anyway.

    @NotNull
    @Basic(optional = false)
    @Column(name = FweekPscatSalesMv.COLUMN_NAME_WEEK_ENDING_DAY,
            nullable = false,
            insertable = true,
            updatable = false)
    private LocalDateTime weekEndingDay;

    @Size(max = FweekPscatSalesMv.SIZE_MAX_PROD_SUBCATEGORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = FweekPscatSalesMv.COLUMN_NAME_PROD_SUBCATEGORY,
            nullable = false,
            insertable = true,
            updatable = false,
            length = FweekPscatSalesMv.COLUMN_LENGTH_PROD_SUBCATEGORY)
    private String prodSubcategory;

    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = FweekPscatSalesMv.COLUMN_NAME_CHANNEL_ID,
            nullable = false,
            insertable = true,
            updatable = false)
    private Long channelId;

    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_PROMO_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_PROMO_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = FweekPscatSalesMv.COLUMN_NAME_PROMO_ID,
            nullable = false,
            insertable = true,
            updatable = false)
    private Integer promoId;
}
