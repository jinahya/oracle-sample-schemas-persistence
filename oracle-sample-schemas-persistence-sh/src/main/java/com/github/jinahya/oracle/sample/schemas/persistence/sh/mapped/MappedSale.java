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
 * A mapped superclass which holds the mappings of the {@value MappedSale#TABLE_NAME} table, except for its identifier.
 * <p>
 * The table declares no primary key; the five dimension columns are its grain, and the {@code CANDIDATE_KEYS} section
 * of {@code src/test/sql/SALES.sql} is what measured them.
 *
 * @param <T> the type of the identifier; a subclass maps it as it chooses, and exposes it through {@link #getId_()}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedSaleId
 */
@MappedSuperclass
public abstract class MappedSale<T extends MappedSaleId> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SALES";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value MappedSaleId#ATTRIBUTE_NAME_PROD_ID} attribute of
     * {@link MappedSaleId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column, for a subclass whose identifier is
     * an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROD_ID = "id.prodId";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value MappedSaleId#ATTRIBUTE_NAME_CUST_ID} attribute of
     * {@link MappedSaleId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column, for a subclass whose identifier is
     * an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CUST_ID = "id.custId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value MappedSaleId#ATTRIBUTE_NAME_TIME_ID} attribute of
     * {@link MappedSaleId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column, for a subclass whose identifier is
     * an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_TIME_ID = "id.timeId";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value MappedSaleId#ATTRIBUTE_NAME_CHANNEL_ID} attribute of
     * {@link MappedSaleId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CHANNEL_ID = "id.channelId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value MappedSaleId#ATTRIBUTE_NAME_PROMO_ID} attribute of
     * {@link MappedSaleId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROMO_ID = "id.promoId";

    // -------------------------------------------------------- PROD_ID / CUST_ID / TIME_ID / CHANNEL_ID / PROMO_ID / id

    /**
     * The name of the identifier attribute, declared by a subclass, which maps all of the
     * {@value #COLUMN_NAME_PROD_ID}, {@value #COLUMN_NAME_CUST_ID}, {@value #COLUMN_NAME_TIME_ID},
     * {@value #COLUMN_NAME_CHANNEL_ID}, and {@value #COLUMN_NAME_PROMO_ID} columns. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // --------------------------------------------------------------------------------------------------- QUANTITY_SOLD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SOLD = "QUANTITY_SOLD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SOLD = "quantitySold";

    // ----------------------------------------------------------------------------------------------------- AMOUNT_SOLD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute maps. The value is
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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSale() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + getId_() +
               ",quantitySold=" + quantitySold +
               ",amountSold=" + amountSold +
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
        if (!(obj instanceof MappedSale<?> that)) {
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
     * Returns the identifier of this sale. A subclass implements this with whichever attributes it maps the identifier
     * to; {@link #equals(Object)} and {@link #hashCode()} compare by it.
     *
     * @return the identifier of this sale; {@code null} if it has none yet.
     */
    @Transient
    protected abstract T getId_();

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

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_SOLD, nullable = false, insertable = true, updatable = true)
    private Integer quantitySold;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT_SOLD,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AMOUNT_SOLD,
            scale = COLUMN_SCALE_AMOUNT_SOLD)
    private BigDecimal amountSold;
}
