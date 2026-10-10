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
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * A superclass for the identifier of the {@value MappedFweekPscatSalesMv#TABLE_NAME} materialized view -- the
 * {@value MappedFweekPscatSalesMv#COLUMN_NAME_WEEK_ENDING_DAY}, the
 * {@value MappedFweekPscatSalesMv#COLUMN_NAME_PROD_SUBCATEGORY}, the
 * {@value MappedFweekPscatSalesMv#COLUMN_NAME_CHANNEL_ID}, and the
 * {@value MappedFweekPscatSalesMv#COLUMN_NAME_PROMO_ID} columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedFweekPscatSalesMv
 */
@MappedSuperclass
public abstract class MappedFweekPscatSalesMvId {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedFweekPscatSalesMv#COLUMN_NAME_WEEK_ENDING_DAY} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the {@value MappedFweekPscatSalesMv#COLUMN_NAME_PROD_SUBCATEGORY} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY = "prodSubcategory";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID =
            MappedFweekPscatSalesMv.COLUMN_PRECISION_CHANNEL_ID
                      - MappedFweekPscatSalesMv.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID = MappedFweekPscatSalesMv.COLUMN_SCALE_CHANNEL_ID;

    /**
     * The name of the attribute which maps the {@value MappedFweekPscatSalesMv#COLUMN_NAME_CHANNEL_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_PROMO_ID =
            MappedFweekPscatSalesMv.COLUMN_PRECISION_PROMO_ID
                      - MappedFweekPscatSalesMv.COLUMN_SCALE_PROMO_ID;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_PROMO_ID = MappedFweekPscatSalesMv.COLUMN_SCALE_PROMO_ID;

    /**
     * The name of the attribute which maps the {@value MappedFweekPscatSalesMv#COLUMN_NAME_PROMO_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance of a subclass, with the specified values.
     *
     * @param <T>             the type of the identifier.
     * @param instantiator    a supplier of a new, empty instance of {@code T}.
     * @param weekEndingDay   a value for the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     * @param prodSubcategory a value for the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     * @param channelId       a value for the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     * @param promoId         a value for the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     * @return the instance supplied by {@code instantiator}, with its attributes set.
     * @throws NullPointerException if {@code instantiator} is {@code null}, or it supplies {@code null}.
     */
    protected static <T extends MappedFweekPscatSalesMvId> T of(final Supplier<? extends T> instantiator,
                                                                final LocalDateTime weekEndingDay,
                                                                final String prodSubcategory,
                                                                final Long channelId,
                                                                final Integer promoId) {
        Objects.requireNonNull(instantiator, "instantiator is null");
        final var instance = Objects.requireNonNull(instantiator.get(), "instantiator.get() is null");
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
    protected MappedFweekPscatSalesMvId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
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
        if (!(obj instanceof MappedFweekPscatSalesMvId that)) {
            return false;
        }
        return Objects.equals(getWeekEndingDay(), that.getWeekEndingDay())
               && Objects.equals(getProdSubcategory(), that.getProdSubcategory())
               && Objects.equals(getChannelId(), that.getChannelId())
               && Objects.equals(getPromoId(), that.getPromoId());
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
        return Objects.hash(getWeekEndingDay(), getProdSubcategory(), getChannelId(), getPromoId());
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
    protected void setWeekEndingDay(final LocalDateTime weekEndingDay) {
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
    protected void setProdSubcategory(final String prodSubcategory) {
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
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = MappedFweekPscatSalesMv.COLUMN_NAME_WEEK_ENDING_DAY,
            nullable = false,
            insertable = true,
            updatable = false)
    private LocalDateTime weekEndingDay;

    @Size(max = MappedFweekPscatSalesMv.SIZE_MAX_PROD_SUBCATEGORY)
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = MappedFweekPscatSalesMv.COLUMN_NAME_PROD_SUBCATEGORY,
            nullable = false,
            insertable = true,
            updatable = false,
            length = MappedFweekPscatSalesMv.COLUMN_LENGTH_PROD_SUBCATEGORY)
    private String prodSubcategory;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_CHANNEL_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_CHANNEL_ID)
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = MappedFweekPscatSalesMv.COLUMN_NAME_CHANNEL_ID,
            nullable = false,
            insertable = true,
            updatable = false)
    private Long channelId;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_PROMO_ID, fraction = ATTRIBUTE_DIGITS_FRACTION_PROMO_ID)
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = MappedFweekPscatSalesMv.COLUMN_NAME_PROMO_ID,
            nullable = false,
            insertable = true,
            updatable = false)
    private Integer promoId;
}
