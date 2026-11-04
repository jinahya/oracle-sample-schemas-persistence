package com.github.jinahya.oracle.sample.persistence.co.mapped;

/*-
 * #%L
 * co
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

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * A mapped superclass for mapping the {@value MappedProductOrder#TABLE_NAME} view.
 * <p>
 * The identifying columns are an {@link jakarta.persistence.EmbeddedId @EmbeddedId} of a subclass
 * of {@link MappedProductOrderId}, {@code T}. The extending entity declares the attribute, and
 * returns it from {@link #getId()}, which {@link #equals(Object)}, {@link #hashCode()} and
 * {@link #toString()} read: an {@code @EmbeddedId} of a type variable, declared here, is what
 * EclipseLink does not resolve.
 *
 * @param <T> the type of the identifier.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedProductOrder<T extends MappedProductOrderId> {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCT_ORDERS";

    // ---------------------------------------------------------------------------------------------------- PRODUCT_NAME

    /**
     * The name of the view column to which the
     * {@value MappedProductOrderId#ATTRIBUTE_NAME_PRODUCT_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NAME = 255;

    /**
     * The maximum size of the {@value MappedProductOrderId#ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     * The value is {@value}.
     */
    public static final int SIZE_MAX_PRODUCT_NAME = COLUMN_LENGTH_PRODUCT_NAME;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NAME} column -- a path
     * into the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column
     * actually lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PRODUCT_NAME = "id.productName";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the view column to which the
     * {@value MappedProductOrderId#ATTRIBUTE_NAME_ORDER_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * The maximum size of the {@value MappedProductOrderId#ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     * The value is {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column -- a path
     * into the {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column
     * actually lives. The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_ORDER_STATUS = "id.orderStatus";

    // ----------------------------------------------------------------------------------------------------- TOTAL_SALES

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_SALES = "TOTAL_SALES";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_SALES} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_SALES = "totalSales";

    // ----------------------------------------------------------------------------------------------------- ORDER_COUNT

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "ORDER_COUNT";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + getId() +
               ",totalSales=" + totalSales +
               ",orderCount=" + orderCount +
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
        if (!(obj instanceof MappedProductOrder<?> that)) {
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
     */
    public abstract T getId();

    // ----------------------------------------------------------------------------------------------------- productName

    /**
     * Returns current value of {@value MappedProductOrderId#ATTRIBUTE_NAME_PRODUCT_NAME}
     * attribute.
     *
     * @return current value of {@value MappedProductOrderId#ATTRIBUTE_NAME_PRODUCT_NAME} attribute;
     *         {@code null} when {@value #ATTRIBUTE_NAME_ID} attribute is {@code null}.
     */
    public String getProductName() {
        return Optional.ofNullable(getId()).map(MappedProductOrderId::getProductName).orElse(null);
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    /**
     * Returns current value of {@value MappedProductOrderId#ATTRIBUTE_NAME_ORDER_STATUS}
     * attribute.
     *
     * @return current value of {@value MappedProductOrderId#ATTRIBUTE_NAME_ORDER_STATUS} attribute;
     *         {@code null} when {@value #ATTRIBUTE_NAME_ID} attribute is {@code null}.
     */
    public String getOrderStatus() {
        return Optional.ofNullable(getId()).map(MappedProductOrderId::getOrderStatus).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------ totalSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public BigDecimal getTotalSales() {
        return totalSales;
    }

    // ------------------------------------------------------------------------------------------------------ orderCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    public Long getOrderCount() {
        return orderCount;
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SALES, nullable = true, insertable = false, updatable = false)
    private BigDecimal totalSales;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT, nullable = true, insertable = false, updatable = false)
    private Long orderCount;
}
