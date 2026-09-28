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
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value ProfitsWithEmbeddedId#TABLE_NAME} view, whose composite primary key is mapped
 * with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProfitsWithIdClass
 */
@Entity
@Table(name = ProfitsWithEmbeddedId.TABLE_NAME)
public class ProfitsWithEmbeddedId {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CHANNEL_ID = "id.channelId";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CUST_ID = "id.custId";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROD_ID = "id.prodId";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROMO_ID = "id.promoId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_TIME_ID = "id.timeId";

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

    /**
     * The name of the attribute which maps the primary key columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProfitsWithEmbeddedId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
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
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof ProfitsWithEmbeddedId that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@code id} attribute.
     *
     * @return current value of {@code id} attribute.
     */
    public ProfitsId getId() {
        return id;
    }

    /**
     * Replaces current value of {@code id} attribute with the specified value.
     *
     * @param id new value for {@code id} attribute.
     */
    public void setId(final ProfitsId id) {
        this.id = id;
    }

    // ------------------------------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public Long getChannelId() {
        return Optional.ofNullable(getId()).map(ProfitsId::getChannelId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    protected void setChannelId(final Long channelId) {
        if (getId() == null) {
            setId(new ProfitsId());
        }
        getId().setChannelId(channelId);
    }

    // ---------------------------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public Long getCustId() {
        return Optional.ofNullable(getId()).map(ProfitsId::getCustId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    protected void setCustId(final Long custId) {
        if (getId() == null) {
            setId(new ProfitsId());
        }
        getId().setCustId(custId);
    }

    // ---------------------------------------------------------------------------------------------------------- prodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    public Integer getProdId() {
        return Optional.ofNullable(getId()).map(ProfitsId::getProdId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute with the specified value.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    protected void setProdId(final Integer prodId) {
        if (getId() == null) {
            setId(new ProfitsId());
        }
        getId().setProdId(prodId);
    }

    // --------------------------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public Integer getPromoId() {
        return Optional.ofNullable(getId()).map(ProfitsId::getPromoId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute with the specified value.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    protected void setPromoId(final Integer promoId) {
        if (getId() == null) {
            setId(new ProfitsId());
        }
        getId().setPromoId(promoId);
    }

    // ---------------------------------------------------------------------------------------------------------- timeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public LocalDate getTimeId() {
        return Optional.ofNullable(getId()).map(ProfitsId::getTimeId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    protected void setTimeId(final LocalDate timeId) {
        if (getId() == null) {
            setId(new ProfitsId());
        }
        getId().setTimeId(timeId);
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

    // ---------------------------------------------------------------------------------------------------------------- 

    @Valid
    @NotNull
    @EmbeddedId
    private ProfitsId id;

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
