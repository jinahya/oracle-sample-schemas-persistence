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
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
 * An entity class for mapping the {@value SaleWithIdClass#TABLE_NAME} table, whose composite primary key is mapped with
 * an
 * {@link jakarta.persistence.IdClass @IdClass}.
 * <p>
 * The table declares no primary key; the five dimension columns are its grain, and the
 * {@code CANDIDATE_KEYS} section of {@code src/test/sql/SALES.sql} is what measured them.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see SaleWithEmbeddedId
 */
@Entity
@IdClass(SaleId.class)
@Table(name = SaleWithIdClass.TABLE_NAME)
public class SaleWithIdClass {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SALES";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

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
    protected SaleWithIdClass() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "prodId=" + prodId +
               ",custId=" + custId +
               ",timeId=" + timeId +
               ",channelId=" + channelId +
               ",promoId=" + promoId +
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
        if (!(obj instanceof SaleWithIdClass that)) {
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
     * Returns current value of {@code id} attribute.
     *
     * @return current value of {@code id} attribute.
     */
    public SaleId getId() {
        return new SaleId(getProdId(), getCustId(), getTimeId(), getChannelId(), getPromoId());
    }

    /**
     * Replaces current value of {@code id} attribute with the specified value.
     *
     * @param id new value for {@code id} attribute.
     */
    protected void setId(final SaleId id) {
        setProdId(
                Optional.ofNullable(id)
                        .map(SaleId::getProdId)
                        .orElse(null)
        );
        setCustId(
                Optional.ofNullable(id)
                        .map(SaleId::getCustId)
                        .orElse(null)
        );
        setTimeId(
                Optional.ofNullable(id)
                        .map(SaleId::getTimeId)
                        .orElse(null)
        );
        setChannelId(
                Optional.ofNullable(id)
                        .map(SaleId::getChannelId)
                        .orElse(null)
        );
        setPromoId(
                Optional.ofNullable(id)
                        .map(SaleId::getPromoId)
                        .orElse(null)
        );
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
    public void setProdId(final Integer prodId) {
        this.prodId = prodId;
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public Product getProduct() {
        return product;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public void setCustId(final Long custId) {
        this.custId = custId;
    }

    // -------------------------------------------------------------------------------------------------------- customer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public Customer getCustomer() {
        return customer;
    }

    // ---------------------------------------------------------------------------------------------------------- timeId

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

    // ------------------------------------------------------------------------------------------------------------ time

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME} attribute.
     */
    public Time getTime() {
        return time;
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
    public void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    // --------------------------------------------------------------------------------------------------------- channel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     */
    public Channel getChannel() {
        return channel;
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
    public void setPromoId(final Integer promoId) {
        this.promoId = promoId;
    }

    // ------------------------------------------------------------------------------------------------------- promotion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     */
    public Promotion getPromotion() {
        return promotion;
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

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

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

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

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

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TIME_ID, nullable = false, insertable = true, updatable = false)
    private LocalDate timeId;

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

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

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

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

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
