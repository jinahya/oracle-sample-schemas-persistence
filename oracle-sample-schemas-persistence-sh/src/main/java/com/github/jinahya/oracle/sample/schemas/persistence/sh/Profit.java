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
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Profit#TABLE_NAME} view, whose composite identifier is mapped with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProfitId
 */
@Entity
@IdClass(ProfitId.class)
@Table(name = Profit.TABLE_NAME)
public class Profit implements __DomainEntity<ProfitId> {

    /**
     * The name of the database view to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PROFITS";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    /**
     * The precision of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CHANNEL_ID = 1;

    /**
     * The scale of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CHANNEL_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_CHANNEL_ID = -9;

    /**
     * The maximum value of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_CHANNEL_ID = +9;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_CHANNEL_ID = COLUMN_MIN_CHANNEL_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_CHANNEL_ID = COLUMN_MAX_CHANNEL_ID;

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    /**
     * The precision of the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROD_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROD_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_PROD_ID = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_PROD_ID = +999999;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROD_ID = COLUMN_MIN_PROD_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROD_ID = COLUMN_MAX_PROD_ID;

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    /**
     * The precision of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROMO_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROMO_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_PROMO_ID = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_PROMO_ID = +999999;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROMO_ID = COLUMN_MIN_PROMO_ID;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROMO_ID = COLUMN_MAX_PROMO_ID;

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // ------------------------------------------------------------------------------------------------------- UNIT_COST

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute maps. The value is
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_COST = "unitCost";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_COST = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_UNIT_COST = "+99999999.99";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute maps. The value is
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE = "+99999999.99";

    // ----------------------------------------------------------------------------------------------------- AMOUNT_SOLD

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_AMOUNT_SOLD = "AMOUNT_SOLD";

    /**
     * The precision of the {@value #COLUMN_NAME_AMOUNT_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AMOUNT_SOLD = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_AMOUNT_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AMOUNT_SOLD = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AMOUNT_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AMOUNT_SOLD = "amountSold";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AMOUNT_SOLD = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AMOUNT_SOLD = "+99999999.99";

    // --------------------------------------------------------------------------------------------------- QUANTITY_SOLD

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SOLD = "QUANTITY_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SOLD = "quantitySold";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_SOLD = 3;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_SOLD = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_QUANTITY_SOLD = -999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_QUANTITY_SOLD = +999;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_QUANTITY_SOLD = COLUMN_MIN_QUANTITY_SOLD;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_QUANTITY_SOLD = COLUMN_MAX_QUANTITY_SOLD;

    // ------------------------------------------------------------------------------------------------------ TOTAL_COST

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_COST = "TOTAL_COST";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_COST = "totalCost";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Profit() {
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
               ",unitCost=" + unitCost +
               ",unitPrice=" + unitPrice +
               ",amountSold=" + amountSold +
               ",quantitySold=" + quantitySold +
               ",totalCost=" + totalCost +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the five {@code @Id} attributes, compared as the {@link #getId() id} they make up.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Profit that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the five {@code @Id} attributes, through {@link #getId()}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns a new {@link ProfitId} holding current values of the identifying attributes.
     *
     * @return a new {@link ProfitId} holding current values of the identifying attributes.
     */
    public ProfitId getId() {
        return ProfitId.of(getChannelId(), getCustId(), getProdId(), getPromoId(), getTimeId());
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

    // ---------------------------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public Long getCustId() {
        return custId;
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

    // --------------------------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public Integer getPromoId() {
        return promoId;
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

    // -------------------------------------------------------------------------------------------------------- unitCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    public BigDecimal getUnitCost() {
        return unitCost;
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

    // ------------------------------------------------------------------------------------------------------ amountSold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute.
     */
    public BigDecimal getAmountSold() {
        return amountSold;
    }

    // ---------------------------------------------------------------------------------------------------- quantitySold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute.
     */
    public Integer getQuantitySold() {
        return quantitySold;
    }

    // ------------------------------------------------------------------------------------------------------- totalCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute.
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    @Id
    @NotNull
    @Max(ATTRIBUTE_MAX_CHANNEL_ID)
    @Min(ATTRIBUTE_MIN_CHANNEL_ID)
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    @Id
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

    @Id
    @NotNull
    @Max(ATTRIBUTE_MAX_PROD_ID)
    @Min(ATTRIBUTE_MIN_PROD_ID)
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @Id
    @NotNull
    @Max(ATTRIBUTE_MAX_PROMO_ID)
    @Min(ATTRIBUTE_MIN_PROMO_ID)
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @Id
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDateTime timeId;

    @NotNull
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_UNIT_COST, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_UNIT_COST, inclusive = true)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_COST,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_UNIT_COST,
            scale = COLUMN_SCALE_UNIT_COST)
    private BigDecimal unitCost;

    @NotNull
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE, inclusive = true)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    @NotNull
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_AMOUNT_SOLD, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_AMOUNT_SOLD, inclusive = true)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT_SOLD,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AMOUNT_SOLD,
            scale = COLUMN_SCALE_AMOUNT_SOLD)
    private BigDecimal amountSold;

    @NotNull
    @Max(ATTRIBUTE_MAX_QUANTITY_SOLD)
    @Min(ATTRIBUTE_MIN_QUANTITY_SOLD)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_SOLD, nullable = false, insertable = false, updatable = false)
    private Integer quantitySold;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_COST, nullable = true, insertable = false, updatable = false)
    private BigDecimal totalCost;
}
