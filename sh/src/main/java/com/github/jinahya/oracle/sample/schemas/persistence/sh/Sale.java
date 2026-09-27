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
 * A class for the {@value Sale#TABLE_NAME} table.
 * <p>
 * The table declares no primary key, so this is not an {@link jakarta.persistence.Entity @Entity}: the columns and
 * the dimensions they reference are written out here, but nothing is mapped until the table gains a key.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Sale {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SALES";

    // ---------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_PRODUCT} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ---------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_CUSTOMER} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // ---------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_TIME} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    // ------------------------------------------------------------------------------------------------------- CHANNEL_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_CHANNEL} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

    // --------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column which the {@value #ATTRIBUTE_NAME_PROMOTION} association maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

    // ---------------------------------------------------------------------------------------------------- QUANTITY_SOLD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_QUANTITYSOLD} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SOLD = "QUANTITY_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITYSOLD = "quantitySold";

    // ------------------------------------------------------------------------------------------------------ AMOUNT_SOLD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_AMOUNTSOLD} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_AMOUNT_SOLD = "AMOUNT_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AMOUNT_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AMOUNTSOLD = "amountSold";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Sale() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "product=" + product +
               ",customer=" + customer +
               ",time=" + time +
               ",channel=" + channel +
               ",promotion=" + promotion +
               ",quantitySold=" + quantitySold +
               ",amountSold=" + amountSold +
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
        if (!(obj instanceof Sale that)) {
            return false;
        }
        return Objects.equals(getProduct(), that.getProduct())
               && Objects.equals(getCustomer(), that.getCustomer())
               && Objects.equals(getTime(), that.getTime())
               && Objects.equals(getChannel(), that.getChannel())
               && Objects.equals(getPromotion(), that.getPromotion());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the same dimensions, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getProduct(), getCustomer(), getTime(), getChannel(), getPromotion());
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

    // --------------------------------------------------------------------------------------------------------- customer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute with the specified value.
     *
     * @param customer new value for {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
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

    // ----------------------------------------------------------------------------------------------------- quantitySold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITYSOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITYSOLD} attribute.
     */
    public Integer getQuantitySold() {
        return quantitySold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITYSOLD} attribute with the specified value.
     *
     * @param quantitySold new value for {@value #ATTRIBUTE_NAME_QUANTITYSOLD} attribute.
     */
    public void setQuantitySold(final Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    // ------------------------------------------------------------------------------------------------------- amountSold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AMOUNTSOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AMOUNTSOLD} attribute.
     */
    public BigDecimal getAmountSold() {
        return amountSold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AMOUNTSOLD} attribute with the specified value.
     *
     * @param amountSold new value for {@value #ATTRIBUTE_NAME_AMOUNTSOLD} attribute.
     */
    public void setAmountSold(final BigDecimal amountSold) {
        this.amountSold = amountSold;
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
    @JoinColumn(name = COLUMN_NAME_CUST_ID,
                referencedColumnName = Customer.COLUMN_NAME_CUST_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Customer customer;

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
    @JoinColumn(name = COLUMN_NAME_CHANNEL_ID,
                referencedColumnName = Channel.COLUMN_NAME_CHANNEL_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Channel channel;

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

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_SOLD,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer quantitySold;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT_SOLD,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = 10,
            scale = 2
    )
    private BigDecimal amountSold;
}
