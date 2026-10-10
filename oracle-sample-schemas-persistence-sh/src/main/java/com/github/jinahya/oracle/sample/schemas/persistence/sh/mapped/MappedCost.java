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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedCost#TABLE_NAME} table, including the four
 * {@link Id @Id} attributes of its composite identifier.
 * <p>
 * The table declares no primary key; the four dimension columns are its grain, and the {@code CANDIDATE_KEYS} section
 * of {@code src/test/sql/COSTS.sql} is what measured them.
 * <p>
 * The identifier is mapped with an {@link jakarta.persistence.IdClass @IdClass}: an extending entity names its id
 * class, a subclass of {@link MappedCostId}, with {@code @IdClass} and inherits the {@code @Id} attributes from here.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedCostId
 */
@MappedSuperclass
public abstract class MappedCost implements __MappedDomainEntity<MappedCostId> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COSTS";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the {@link Id @Id} attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = MappedCostId.ATTRIBUTE_NAME_PROD_ID;

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the {@link Id @Id} attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = MappedCostId.ATTRIBUTE_NAME_TIME_ID;

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the {@link Id @Id} attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = MappedCostId.ATTRIBUTE_NAME_PROMO_ID;

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the {@link Id @Id} attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = MappedCostId.ATTRIBUTE_NAME_CHANNEL_ID;

    // ------------------------------------------------------------------------------------------------------- UNIT_COST

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_UNIT_COST = "UNIT_COST";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_COST = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_COST = 2;

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_UNIT_COST = COLUMN_PRECISION_UNIT_COST - COLUMN_SCALE_UNIT_COST;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_UNIT_COST = COLUMN_SCALE_UNIT_COST;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_COST = "unitCost";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    /**
     * The maximum number of integral digits of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_INTEGER_UNIT_PRICE = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE;

    /**
     * The maximum number of fractional digits of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_DIGITS_FRACTION_UNIT_PRICE = COLUMN_SCALE_UNIT_PRICE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCost() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "prodId=" + prodId +
               ",timeId=" + timeId +
               ",promoId=" + promoId +
               ",channelId=" + channelId +
               ",unitCost=" + unitCost +
               ",unitPrice=" + unitPrice +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the four {@link Id @Id} attributes alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCost that)) {
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
     * @implSpec The hash is over the four {@link Id @Id} attributes, consistent with {@link #equals(Object)}.
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

    // -------------------------------------------------------------------------------------------------------- unitCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute with the specified value.
     *
     * @param unitCost new value for {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    public void setUnitCost(final BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_UNIT_COST, fraction = ATTRIBUTE_DIGITS_FRACTION_UNIT_COST)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_COST,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_COST,
            scale = COLUMN_SCALE_UNIT_COST)
    private BigDecimal unitCost;

    @NotNull
    @Digits(integer = ATTRIBUTE_DIGITS_INTEGER_UNIT_PRICE, fraction = ATTRIBUTE_DIGITS_FRACTION_UNIT_PRICE)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;
}
