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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An entity class for mapping the {@value Sales#TABLE_NAME} table, whose composite identifier is mapped with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 * <p>
 * The table declares no primary key; the five dimension columns are its grain, and the {@code CANDIDATE_KEYS} section
 * of {@code src/test/sql/SALES.sql} is what measured them.
 * <p>
 * That identifier is a measurement of the shipped data, not a constraint, and nothing stops a second sale of the same
 * product to the same customer on the same day through the same channel under the same promotion. Should the table ever
 * hold two such rows, a query returning both yields one managed instance, twice, and the second row's
 * {@value #ATTRIBUTE_NAME_QUANTITY_SOLD} and {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} are silently lost: the persistence
 * context keeps one instance per identifier. Both providers behave so.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see SalesId
 */
@NamedQuery(name = "Sales.selectOneByProdIdAndCustIdAndTimeIdAndChannelIdAndPromoId",
            query = """
                    SELECT e
                    FROM Sales e
                    WHERE e.id.prodId = :prodId
                      AND e.id.custId = :custId
                      AND e.id.timeId = :timeId
                      AND e.id.channelId = :channelId
                      AND e.id.promoId = :promoId"""
)
@NamedQuery(name = "Sales.selectListOrderByProdIdAscCustIdAscTimeIdAscChannelIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM Sales e
                    ORDER BY e.id.prodId ASC,
                             e.id.custId ASC,
                             e.id.timeId ASC,
                             e.id.channelId ASC,
                             e.id.promoId ASC"""
)
@NamedQuery(name = "Sales.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Sales e"""
)
@NamedQuery(name = "Sales.selectListByProductOrderByCustIdAscTimeIdAscChannelIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM Sales e
                    WHERE e.product = :product
                    ORDER BY e.id.custId ASC, e.id.timeId ASC, e.id.channelId ASC, e.id.promoId ASC"""
)
@NamedQuery(name = "Sales.countByProduct",
            query = """
                    SELECT COUNT(e)
                    FROM Sales e
                    WHERE e.product = :product"""
)
@NamedQuery(name = "Sales.selectListByCustomerOrderByProdIdAscTimeIdAscChannelIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM Sales e
                    WHERE e.customer = :customer
                    ORDER BY e.id.prodId ASC, e.id.timeId ASC, e.id.channelId ASC, e.id.promoId ASC"""
)
@NamedQuery(name = "Sales.countByCustomer",
            query = """
                    SELECT COUNT(e)
                    FROM Sales e
                    WHERE e.customer = :customer"""
)
@NamedQuery(name = "Sales.selectListByTimeOrderByProdIdAscCustIdAscChannelIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM Sales e
                    WHERE e.time = :time
                    ORDER BY e.id.prodId ASC, e.id.custId ASC, e.id.channelId ASC, e.id.promoId ASC"""
)
@NamedQuery(name = "Sales.countByTime",
            query = """
                    SELECT COUNT(e)
                    FROM Sales e
                    WHERE e.time = :time"""
)
@NamedQuery(name = "Sales.selectListByChannelOrderByProdIdAscCustIdAscTimeIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM Sales e
                    WHERE e.channel = :channel
                    ORDER BY e.id.prodId ASC, e.id.custId ASC, e.id.timeId ASC, e.id.promoId ASC"""
)
@NamedQuery(name = "Sales.countByChannel",
            query = """
                    SELECT COUNT(e)
                    FROM Sales e
                    WHERE e.channel = :channel"""
)
@NamedQuery(name = "Sales.selectListByPromotionOrderByProdIdAscCustIdAscTimeIdAscChannelIdAsc",
            query = """
                    SELECT e
                    FROM Sales e
                    WHERE e.promotion = :promotion
                    ORDER BY e.id.prodId ASC, e.id.custId ASC, e.id.timeId ASC, e.id.channelId ASC"""
)
@NamedQuery(name = "Sales.countByPromotion",
            query = """
                    SELECT COUNT(e)
                    FROM Sales e
                    WHERE e.promotion = :promotion"""
)
@Entity
@Table(name = Sales.TABLE_NAME)
public class Sales {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SALES";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PROD_ID = "id.prodId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_CUST_ID = "id.custId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_TIME_ID = "id.timeId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_CHANNEL_ID = "id.channelId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PROMO_ID = "id.promoId";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value SalesId#ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

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
     * The minimum value of the {@value SalesId#ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROD_ID = COLUMN_MIN_PROD_ID;

    /**
     * The maximum value of the {@value SalesId#ATTRIBUTE_NAME_PROD_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROD_ID = COLUMN_MAX_PROD_ID;

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value SalesId#ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // --------------------------------------------------------------------------------------------------------- TIME_ID

    /**
     * The name of the table column to which the {@value SalesId#ATTRIBUTE_NAME_TIME_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TIME_ID = "TIME_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value SalesId#ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

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
     * The minimum value of the {@value SalesId#ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_CHANNEL_ID = COLUMN_MIN_CHANNEL_ID;

    /**
     * The maximum value of the {@value SalesId#ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_CHANNEL_ID = COLUMN_MAX_CHANNEL_ID;

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value SalesId#ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

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
     * The minimum value of the {@value SalesId#ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROMO_ID = COLUMN_MIN_PROMO_ID;

    /**
     * The maximum value of the {@value SalesId#ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROMO_ID = COLUMN_MAX_PROMO_ID;

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AMOUNT_SOLD = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AMOUNT_SOLD} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AMOUNT_SOLD = "+99999999.99";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Sales() {
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
        if (!(obj instanceof Sales that)) {
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
    public SalesId getId() {
        return id;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public void setId(final SalesId id) {
        this.id = id;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute with the specified value.
     * <p>
     * The {@value #COLUMN_NAME_PROD_ID} column is written by the {@link #getId() id}, and this association only reads
     * it back, so the new value changes what this instance refers to and nothing in the database; the id is what
     * decides the row.
     *
     * @param product new value for {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public void setProduct(final Product product) {
        this.product = product;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute with the specified value.
     * <p>
     * The {@value #COLUMN_NAME_CUST_ID} column is written by the {@link #getId() id}, and this association only reads
     * it back, so the new value changes what this instance refers to and nothing in the database; the id is what
     * decides the row.
     *
     * @param customer new value for {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME} attribute with the specified value.
     * <p>
     * The {@value #COLUMN_NAME_TIME_ID} column is written by the {@link #getId() id}, and this association only reads
     * it back, so the new value changes what this instance refers to and nothing in the database; the id is what
     * decides the row.
     *
     * @param time new value for {@value #ATTRIBUTE_NAME_TIME} attribute.
     */
    public void setTime(final Time time) {
        this.time = time;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute with the specified value.
     * <p>
     * The {@value #COLUMN_NAME_CHANNEL_ID} column is written by the {@link #getId() id}, and this association only
     * reads it back, so the new value changes what this instance refers to and nothing in the database; the id is what
     * decides the row.
     *
     * @param channel new value for {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     */
    public void setChannel(final Channel channel) {
        this.channel = channel;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute with the specified value.
     * <p>
     * The {@value #COLUMN_NAME_PROMO_ID} column is written by the {@link #getId() id}, and this association only reads
     * it back, so the new value changes what this instance refers to and nothing in the database; the id is what
     * decides the row.
     *
     * @param promotion new value for {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     */
    public void setPromotion(final Promotion promotion) {
        this.promotion = promotion;
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
    private SalesId id;

    // -----------------------------------------------------------------------------------------------------------------
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
