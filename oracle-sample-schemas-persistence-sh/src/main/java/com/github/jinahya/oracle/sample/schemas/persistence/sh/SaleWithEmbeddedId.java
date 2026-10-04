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
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value SaleWithEmbeddedId#TABLE_NAME} table, whose composite identifier is mapped
 * with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 * <p>
 * The table declares no primary key; the five dimension columns are its grain, and the {@code CANDIDATE_KEYS} section
 * of {@code src/test/sql/SALES.sql} is what measured them.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see SaleWithIdClass
 */
@Entity
@Table(name = SaleWithEmbeddedId.TABLE_NAME)
public class SaleWithEmbeddedId {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SALES";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the {@link SaleId} attribute which maps the {@value #COLUMN_NAME_PROD_ID} column; this entity reaches
     * it as {@value #ATTRIBUTE_NAME_ID_PROD_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROD_ID = "id.prodId";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the {@link SaleId} attribute which maps the {@value #COLUMN_NAME_CUST_ID} column; this entity reaches
     * it as {@value #ATTRIBUTE_NAME_ID_CUST_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CUST_ID = "id.custId";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the {@link SaleId} attribute which maps the {@value #COLUMN_NAME_TIME_ID} column; this entity reaches
     * it as {@value #ATTRIBUTE_NAME_ID_TIME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_TIME_ID = "id.timeId";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the {@link SaleId} attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column; this entity
     * reaches it as {@value #ATTRIBUTE_NAME_ID_CHANNEL_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CHANNEL_ID = "id.channelId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the {@link SaleId} attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column; this entity
     * reaches it as {@value #ATTRIBUTE_NAME_ID_PROMO_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROMO_ID = "id.promoId";

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

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SaleWithEmbeddedId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
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
        if (!(obj instanceof SaleWithEmbeddedId that)) {
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
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public SaleId getId() {
        return id;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public void setId(final SaleId id) {
        this.id = id;
    }

    // ---------------------------------------------------------------------------------------------------------- prodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute of the {@link #getId() id}; {@code null} if
     * the id is {@code null}.
     */
    public Integer getProdId() {
        return Optional.ofNullable(getId()).map(SaleId::getProdId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    protected void setProdId(final Integer prodId) {
        if (getId() == null) {
            setId(new SaleId());
        }
        getId().setProdId(prodId);
    }

    // ---------------------------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute of the {@link #getId() id}; {@code null} if
     * the id is {@code null}.
     */
    public Long getCustId() {
        return Optional.ofNullable(getId()).map(SaleId::getCustId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    protected void setCustId(final Long custId) {
        if (getId() == null) {
            setId(new SaleId());
        }
        getId().setCustId(custId);
    }

    // ---------------------------------------------------------------------------------------------------------- timeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute of the {@link #getId() id}; {@code null} if
     * the id is {@code null}.
     */
    public LocalDate getTimeId() {
        return Optional.ofNullable(getId()).map(SaleId::getTimeId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    protected void setTimeId(final LocalDate timeId) {
        if (getId() == null) {
            setId(new SaleId());
        }
        getId().setTimeId(timeId);
    }

    // ------------------------------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute of the {@link #getId() id}; {@code null}
     * if the id is {@code null}.
     */
    public Long getChannelId() {
        return Optional.ofNullable(getId()).map(SaleId::getChannelId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    protected void setChannelId(final Long channelId) {
        if (getId() == null) {
            setId(new SaleId());
        }
        getId().setChannelId(channelId);
    }

    // --------------------------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute of the {@link #getId() id}; {@code null} if
     * the id is {@code null}.
     */
    public Integer getPromoId() {
        return Optional.ofNullable(getId()).map(SaleId::getPromoId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    protected void setPromoId(final Integer promoId) {
        if (getId() == null) {
            setId(new SaleId());
        }
        getId().setPromoId(promoId);
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

    // ---------------------------------------------------------------------------------------------------------------- 

    @Valid
    @NotNull
    @EmbeddedId
    private SaleId id;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROD_ID,
                referencedColumnName = Product.COLUMN_NAME_PROD_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Product product;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUST_ID,
                referencedColumnName = Customer.COLUMN_NAME_CUST_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Customer customer;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TIME_ID,
                referencedColumnName = Time.COLUMN_NAME_TIME_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Time time;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHANNEL_ID,
                referencedColumnName = Channel.COLUMN_NAME_CHANNEL_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Channel channel;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROMO_ID,
                referencedColumnName = Promotion.COLUMN_NAME_PROMO_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Promotion promotion;

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
