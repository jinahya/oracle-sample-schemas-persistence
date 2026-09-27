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
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class for the {@value Cost#TABLE_NAME} table.
 * <p>
 * The table declares no primary key, so this is not an {@link jakarta.persistence.Entity @Entity}: the columns and
 * the dimensions they reference are written out here, but nothing is mapped until the table gains a key.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Cost {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COSTS";

    // ---------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_PRODUCT} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ---------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_TIME} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    // --------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_PROMOTION} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

    // ------------------------------------------------------------------------------------------------------- CHANNEL_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_CHANNEL} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

    // -------------------------------------------------------------------------------------------------------- UNIT_COST

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_UNITCOST} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_COST = "UNIT_COST";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNITCOST = "unitCost";

    // ------------------------------------------------------------------------------------------------------- UNIT_PRICE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_UNITPRICE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNITPRICE = "unitPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_COST = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_COST = 2;

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Cost() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "product=" + product +
               ",time=" + time +
               ",promotion=" + promotion +
               ",channel=" + channel +
               ",unitCost=" + unitCost +
               ",unitPrice=" + unitPrice +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the dimensions the row references -- the table's natural key -- read through their
     * getters.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Cost that)) {
            return false;
        }
        return Objects.equals(getProduct(), that.getProduct())
               && Objects.equals(getTime(), that.getTime())
               && Objects.equals(getPromotion(), that.getPromotion())
               && Objects.equals(getChannel(), that.getChannel());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the same dimensions, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getProduct(), getTime(), getPromotion(), getChannel());
    }

    // ---------------------------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute with the specified value.
     *
     * @param product new value for {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    // ------------------------------------------------------------------------------------------------------------- time

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME} attribute.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME} attribute with the specified value.
     *
     * @param time new value for {@value #ATTRIBUTE_NAME_TIME} attribute.
     */
    public void setTime(final Time time) {
        this.time = time;
    }

    // -------------------------------------------------------------------------------------------------------- promotion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute with the specified value.
     *
     * @param promotion new value for {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     */
    public void setPromotion(final Promotion promotion) {
        this.promotion = promotion;
    }

    // ---------------------------------------------------------------------------------------------------------- channel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute with the specified value.
     *
     * @param channel new value for {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     */
    public void setChannel(final Channel channel) {
        this.channel = channel;
    }

    // --------------------------------------------------------------------------------------------------------- unitCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNITCOST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNITCOST} attribute.
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNITCOST} attribute with the specified value.
     *
     * @param unitCost new value for {@value #ATTRIBUTE_NAME_UNITCOST} attribute.
     */
    public void setUnitCost(final BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    // -------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNITPRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNITPRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNITPRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNITPRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    // -----------------------------------------------------------------------------------------------------------------

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROD_ID,
                referencedColumnName = Product.COLUMN_NAME_PROD_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Product product;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TIME_ID,
                referencedColumnName = Time.COLUMN_NAME_TIME_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Time time;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROMO_ID,
                referencedColumnName = Promotion.COLUMN_NAME_PROMO_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Promotion promotion;

    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHANNEL_ID,
                referencedColumnName = Channel.COLUMN_NAME_CHANNEL_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Channel channel;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_COST,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_COST,
            scale = COLUMN_SCALE_UNIT_COST
    )
    private BigDecimal unitCost;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;
}
