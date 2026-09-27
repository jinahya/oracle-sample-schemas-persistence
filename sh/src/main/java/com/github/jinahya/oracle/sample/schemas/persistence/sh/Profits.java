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

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A class for the {@value Profits#TABLE_NAME} view.
 * <p>
 * A view carries no primary key, so this is not an {@link jakarta.persistence.Entity @Entity}: the columns it projects
 * are written out here, and every one of them is read-only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Profits {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PROFITS";

    // ------------------------------------------------------------------------------------------------------- CHANNEL_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // ---------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // ---------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // --------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ---------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -------------------------------------------------------------------------------------------------------- UNIT_COST

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_UNIT_COST} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_COST = "UNIT_COST";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_COST = "unitCost";

    // ------------------------------------------------------------------------------------------------------- UNIT_PRICE

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ------------------------------------------------------------------------------------------------------ AMOUNT_SOLD

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_AMOUNT_SOLD = "AMOUNT_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AMOUNT_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AMOUNT_SOLD = "amountSold";

    // ---------------------------------------------------------------------------------------------------- QUANTITY_SOLD

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SOLD = "QUANTITY_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SOLD = "quantitySold";

    // ------------------------------------------------------------------------------------------------------- TOTAL_COST

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute maps. The value is {@value}.
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
    protected Profits() {
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

    // -------------------------------------------------------------------------------------------------------- channelId

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

    // ----------------------------------------------------------------------------------------------------------- custId

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

    // ----------------------------------------------------------------------------------------------------------- prodId

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

    // ---------------------------------------------------------------------------------------------------------- promoId

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

    // ----------------------------------------------------------------------------------------------------------- timeId

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

    // --------------------------------------------------------------------------------------------------------- unitCost

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

    // -------------------------------------------------------------------------------------------------------- unitPrice

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

    // ------------------------------------------------------------------------------------------------------- amountSold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute.
     */
    public BigDecimal getAmountSold() {
        return amountSold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute with the specified value.
     *
     * @param amountSold new value for {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute.
     */
    public void setAmountSold(final BigDecimal amountSold) {
        this.amountSold = amountSold;
    }

    // ----------------------------------------------------------------------------------------------------- quantitySold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute.
     */
    public Integer getQuantitySold() {
        return quantitySold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute with the specified value.
     *
     * @param quantitySold new value for {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute.
     */
    public void setQuantitySold(final Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    // -------------------------------------------------------------------------------------------------------- totalCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute.
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute with the specified value.
     *
     * @param totalCost new value for {@value #ATTRIBUTE_NAME_TOTAL_COST} attribute.
     */
    public void setTotalCost(final BigDecimal totalCost) {
        this.totalCost = totalCost;
    }


    // -----------------------------------------------------------------------------------------------------------------

    @Column(name = COLUMN_NAME_CHANNEL_ID, insertable = false, updatable = false)
    private Long channelId;

    @Column(name = COLUMN_NAME_CUST_ID, insertable = false, updatable = false)
    private Long custId;

    @Column(name = COLUMN_NAME_PROD_ID, insertable = false, updatable = false)
    private Integer prodId;

    @Column(name = COLUMN_NAME_PROMO_ID, insertable = false, updatable = false)
    private Integer promoId;

    @Column(name = COLUMN_NAME_TIME_ID, insertable = false, updatable = false)
    private LocalDate timeId;

    @Column(name = COLUMN_NAME_UNIT_COST, insertable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal unitCost;

    @Column(name = COLUMN_NAME_UNIT_PRICE, insertable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = COLUMN_NAME_AMOUNT_SOLD, insertable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal amountSold;

    @Column(name = COLUMN_NAME_QUANTITY_SOLD, insertable = false, updatable = false)
    private Integer quantitySold;

    @Column(name = COLUMN_NAME_TOTAL_COST, insertable = false, updatable = false)
    private BigDecimal totalCost;
}
