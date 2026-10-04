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
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedProfit#TABLE_NAME} view, except for its identifier.
 *
 * @param <T> the type of the identifier; a subclass maps it as it chooses, and exposes it through {@link #getId_()}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedProfitId
 */
@MappedSuperclass
public abstract class MappedProfit<T extends MappedProfitId> {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PROFITS";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the view column to which the {@value MappedProfitId#ATTRIBUTE_NAME_CHANNEL_ID} attribute of
     * {@link MappedProfitId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CHANNEL_ID = "id.channelId";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the view column to which the {@value MappedProfitId#ATTRIBUTE_NAME_CUST_ID} attribute of
     * {@link MappedProfitId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column, for a subclass whose identifier is
     * an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CUST_ID = "id.custId";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the view column to which the {@value MappedProfitId#ATTRIBUTE_NAME_PROD_ID} attribute of
     * {@link MappedProfitId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column, for a subclass whose identifier is
     * an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROD_ID = "id.prodId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the view column to which the {@value MappedProfitId#ATTRIBUTE_NAME_PROMO_ID} attribute of
     * {@link MappedProfitId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROMO_ID = "id.promoId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the view column to which the {@value MappedProfitId#ATTRIBUTE_NAME_TIME_ID} attribute of
     * {@link MappedProfitId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column, for a subclass whose identifier is
     * an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_TIME_ID = "id.timeId";

    // -------------------------------------------------------- CHANNEL_ID / CUST_ID / PROD_ID / PROMO_ID / TIME_ID / id

    /**
     * The name of the identifier attribute, declared by a subclass, which maps all of the
     * {@value #COLUMN_NAME_CHANNEL_ID}, {@value #COLUMN_NAME_CUST_ID}, {@value #COLUMN_NAME_PROD_ID},
     * {@value #COLUMN_NAME_PROMO_ID}, and {@value #COLUMN_NAME_TIME_ID} columns. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

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
    protected MappedProfit() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + getId_() +
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
     * @implSpec Equality is by the identifier a subclass exposes through {@link #getId_()}, alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedProfit<?> that)) {
            return false;
        }
        return Objects.equals(getId_(), that.getId_());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier a subclass exposes through {@link #getId_()}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getId_());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the identifier of this profit. A subclass implements this with whichever attributes it maps the
     * identifier to; {@link #equals(Object)} and {@link #hashCode()} compare by it.
     *
     * @return the identifier of this profit; {@code null} if it has none yet.
     */
    @Transient
    protected abstract T getId_();

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

    // ------------------------------------------------------------------------------------------------------ amountSold

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

    // ---------------------------------------------------------------------------------------------------- quantitySold

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

    // ------------------------------------------------------------------------------------------------------- totalCost

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
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_COST,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_UNIT_COST,
            scale = COLUMN_SCALE_UNIT_COST)
    private BigDecimal unitCost;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT_SOLD,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AMOUNT_SOLD,
            scale = COLUMN_SCALE_AMOUNT_SOLD)
    private BigDecimal amountSold;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_SOLD, nullable = false, insertable = false, updatable = false)
    private Integer quantitySold;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_COST, nullable = true, insertable = false, updatable = false)
    private BigDecimal totalCost;
}
