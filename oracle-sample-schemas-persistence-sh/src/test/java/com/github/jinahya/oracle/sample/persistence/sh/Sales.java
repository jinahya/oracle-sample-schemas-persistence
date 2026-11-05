package com.github.jinahya.oracle.sample.persistence.sh;

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

import com.github.jinahya.oracle.sample.persistence.sh.mapped.MappedSales;
import com.github.jinahya.oracle.sample.persistence.sh.mapped.SalesId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity class for mapping the {@value Sales#TABLE_NAME} table, whose composite identifier is
 * mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId} which each of its five
 * associations writes, through {@link MapsId @MapsId}.
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
 * @see SalesId
 */
@Entity
@Table(name = MappedSales.TABLE_NAME,
       comment = "facts table, without a primary key; all rows are uniquely identified by the " +
               "combination of all foreign keys")
public class Sales
        extends MappedSales {

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified associations, which the identifier derives from.
     *
     * @param product   a value for the {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     * @param customer  a value for the {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     * @param time      a value for the {@value #ATTRIBUTE_NAME_TIME} attribute.
     * @param channel   a value for the {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     * @param promotion a value for the {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     * @return a new instance; its identifier is {@code null} until it is persisted.
     */
    public static Sales of(final Product product, final Customer customer, final Time time,
                           final Channel channel, final Promotion promotion) {
        Objects.requireNonNull(product, "product is null");
        Objects.requireNonNull(customer, "customer is null");
        Objects.requireNonNull(time, "time is null");
        Objects.requireNonNull(channel, "channel is null");
        Objects.requireNonNull(promotion, "promotion is null");
        final var instance = new Sales();
        instance.product = product;
        instance.customer = customer;
        instance.time = time;
        instance.channel = channel;
        instance.promotion = promotion;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Sales() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote {@code public}, for {@link SalesUtils}, which reads the components of the
     *         identifier. There is no setter: the provider derives the identifier from the
     *         associations.
     */
    @Override
    public SalesId getId_() {
        return id_;
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public Product getProduct() {
        return product;
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER} attribute.
     */
    public Customer getCustomer() {
        return customer;
    }

    // ---------------------------------------------------------------------------------------- time

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME} attribute.
     */
    public Time getTime() {
        return time;
    }

    // ------------------------------------------------------------------------------------- channel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL} attribute.
     */
    public Channel getChannel() {
        return channel;
    }

    // ----------------------------------------------------------------------------------- promotion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMOTION} attribute.
     */
    public Promotion getPromotion() {
        return promotion;
    }

    // ---------------------------------------------------------------------------------------------
    // no @NotNull: the provider fills it from the @MapsId associations at flush, later than the
    // prePersist at which EclipseLink validates. Each of its attributes is still @NotNull, and the
    // associations which write them are.
    @Valid
    @EmbeddedId
    private SalesId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesId.ATTRIBUTE_NAME_PROD_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROD_ID,
                referencedColumnName = Product.COLUMN_NAME_PROD_ID,
                nullable = false,
                insertable = true,
                updatable = false)
    private Product product;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesId.ATTRIBUTE_NAME_CUST_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUST_ID,
                referencedColumnName = Customer.COLUMN_NAME_CUST_ID,
                nullable = false,
                insertable = true,
                updatable = false)
    private Customer customer;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesId.ATTRIBUTE_NAME_TIME_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TIME_ID,
                referencedColumnName = Time.COLUMN_NAME_TIME_ID,
                nullable = false,
                insertable = true,
                updatable = false)
    private Time time;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesId.ATTRIBUTE_NAME_CHANNEL_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHANNEL_ID,
                referencedColumnName = Channel.COLUMN_NAME_CHANNEL_ID,
                nullable = false,
                insertable = true,
                updatable = false)
    private Channel channel;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesId.ATTRIBUTE_NAME_PROMO_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROMO_ID,
                referencedColumnName = Promotion.COLUMN_NAME_PROMO_ID,
                nullable = false,
                insertable = true,
                updatable = false)
    private Promotion promotion;
}
