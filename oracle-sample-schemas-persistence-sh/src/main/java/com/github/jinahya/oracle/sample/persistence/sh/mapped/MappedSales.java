package com.github.jinahya.oracle.sample.persistence.sh.mapped;

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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedSales#TABLE_NAME} table, whose
 * composite identifier is mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 * <p>
 * The table declares no primary key; the five dimension columns are its grain, and the
 * {@code CANDIDATE_KEYS} section of {@code src/test/sql/SALES.sql} is what measured them.
 * <p>
 * That identifier is a measurement of the shipped data, not a constraint, and nothing stops a
 * second sale of the same product to the same customer on the same day through the same channel
 * under the same promotion. Should the table ever hold two such rows, a query returning both yields
 * one managed instance, twice, and the second row's {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} and
 * {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} are silently lost: the persistence context keeps one
 * instance per identifier. Both providers behave so.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedSalesId
 */
@MappedSuperclass
public abstract class MappedSales {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SALES";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column -- a path into
     * the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually
     * lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PROD_ID = "id.prodId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column -- a path into
     * the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually
     * lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_CUST_ID = "id.custId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column -- a path into
     * the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually
     * lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_TIME_ID = "id.timeId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column -- a path
     * into the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column
     * actually lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_CHANNEL_ID = "id.channelId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column -- a path into
     * the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually
     * lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PROMO_ID = "id.promoId";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value MappedSalesId#ATTRIBUTE_NAME_PROD_ID}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

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
     * The minimum value of the {@value MappedSalesId#ATTRIBUTE_NAME_PROD_ID} attribute. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROD_ID = COLUMN_MIN_PROD_ID;

    /**
     * The maximum value of the {@value MappedSalesId#ATTRIBUTE_NAME_PROD_ID} attribute. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROD_ID = COLUMN_MAX_PROD_ID;

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value MappedSalesId#ATTRIBUTE_NAME_CUST_ID}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value MappedSalesId#ATTRIBUTE_NAME_TIME_ID}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value MappedSalesId#ATTRIBUTE_NAME_CHANNEL_ID}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

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
     * The minimum value of the {@value MappedSalesId#ATTRIBUTE_NAME_CHANNEL_ID} attribute. The
     * value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_CHANNEL_ID = COLUMN_MIN_CHANNEL_ID;

    /**
     * The maximum value of the {@value MappedSalesId#ATTRIBUTE_NAME_CHANNEL_ID} attribute. The
     * value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_CHANNEL_ID = COLUMN_MAX_CHANNEL_ID;

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value MappedSalesId#ATTRIBUTE_NAME_PROMO_ID}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

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
     * The minimum value of the {@value MappedSalesId#ATTRIBUTE_NAME_PROMO_ID} attribute. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROMO_ID = COLUMN_MIN_PROMO_ID;

    /**
     * The maximum value of the {@value MappedSalesId#ATTRIBUTE_NAME_PROMO_ID} attribute. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROMO_ID = COLUMN_MAX_PROMO_ID;

    // --------------------------------------------------------------------------------------------------- QUANTITY_SOLD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SOLD = "QUANTITY_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The
     * value is {@value}.
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
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_MIN_QUANTITY_SOLD = COLUMN_MIN_QUANTITY_SOLD;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_MAX_QUANTITY_SOLD = COLUMN_MAX_QUANTITY_SOLD;

    // ----------------------------------------------------------------------------------------------------- AMOUNT_SOLD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute
     * maps. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_AMOUNT_SOLD} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AMOUNT_SOLD = "amountSold";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AMOUNT_SOLD = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AMOUNT_SOLD = "+99999999.99";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSales() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + getId() +
               ",quantitySold=" + quantitySold +
               ",amountSold=" + amountSold +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedSales that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     * @implSpec The extending entity declares the
     *         {@link jakarta.persistence.EmbeddedId @EmbeddedId} attribute, of a concrete subclass
     *         of {@link MappedSalesId}, and returns it.
     */
    public abstract MappedSalesId getId();

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute with the specified
     * value.
     *
     * @param quantitySold new value for {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute.
     */
    public void setQuantitySold(final Integer quantitySold) {
        this.quantitySold = quantitySold;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute with the specified
     * value.
     *
     * @param amountSold new value for {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute.
     */
    public void setAmountSold(final BigDecimal amountSold) {
        this.amountSold = amountSold;
    }

    // ----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Max(ATTRIBUTE_MAX_QUANTITY_SOLD)
    @Min(ATTRIBUTE_MIN_QUANTITY_SOLD)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_SOLD, nullable = false, insertable = true, updatable = true)
    private Integer quantitySold;

    @NotNull
    @DecimalMax(value = ATTRIBUTE_DECIMAL_MAX_AMOUNT_SOLD, inclusive = true)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_AMOUNT_SOLD, inclusive = true)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT_SOLD,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AMOUNT_SOLD,
            scale = COLUMN_SCALE_AMOUNT_SOLD)
    private BigDecimal amountSold;
}
