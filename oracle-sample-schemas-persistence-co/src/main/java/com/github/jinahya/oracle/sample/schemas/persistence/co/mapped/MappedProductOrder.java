package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

/*-
 * #%L
 * co
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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedProductOrder#TABLE_NAME} view, except for its
 * identifier.
 *
 * @param <T> the type of the identifier; a subclass maps it as it chooses, and exposes it through {@link #getId_()}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedProductOrderId
 */
@MappedSuperclass
public abstract class MappedProductOrder<T extends MappedProductOrderId> {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCT_ORDERS";

    // ---------------------------------------------------------------------------------------------------- PRODUCT_NAME

    /**
     * The name of the view column to which the {@value MappedProductOrderId#ATTRIBUTE_NAME_PRODUCT_NAME} attribute of
     * {@link MappedProductOrderId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NAME = 255;

    /**
     * The maximum size of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_PRODUCT_NAME = COLUMN_LENGTH_PRODUCT_NAME;

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NAME} column, for a subclass whose
     * identifier is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PRODUCT_NAME = "id.productName";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the view column to which the {@value MappedProductOrderId#ATTRIBUTE_NAME_ORDER_STATUS} attribute of
     * {@link MappedProductOrderId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * The maximum size of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column, for a subclass whose
     * identifier is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ORDER_STATUS = "id.orderStatus";

    // -------------------------------------------------------------------------------- PRODUCT_NAME / ORDER_STATUS / id

    /**
     * The name of the identifier attribute, declared by a subclass, which maps both the
     * {@value #COLUMN_NAME_PRODUCT_NAME} and the {@value #COLUMN_NAME_ORDER_STATUS} columns. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ----------------------------------------------------------------------------------------------------- TOTAL_SALES

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_SALES = "TOTAL_SALES";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_SALES = "totalSales";

    // ----------------------------------------------------------------------------------------------------- ORDER_COUNT

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "ORDER_COUNT";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

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
               "id=" + getId_() +
               ",totalSales=" + totalSales +
               ",orderCount=" + orderCount +
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
        if (!(obj instanceof MappedProductOrder<?> that)) {
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
     * Returns the identifier of this product order. A subclass implements this with whichever attributes it maps the
     * identifier to; {@link #equals(Object)} and {@link #hashCode()} compare by it.
     *
     * @return the identifier of this product order; {@code null} if it has none yet.
     */
    @Transient
    protected abstract T getId_();

    // ------------------------------------------------------------------------------------------------------ totalSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public BigDecimal getTotalSales() {
        return totalSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute with the specified value.
     *
     * @param totalSales new value for {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public void setTotalSales(final BigDecimal totalSales) {
        this.totalSales = totalSales;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute with the specified value.
     *
     * @param orderCount new value for {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    public void setOrderCount(final Long orderCount) {
        this.orderCount = orderCount;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SALES, nullable = true, insertable = false, updatable = false)
    private BigDecimal totalSales;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT, nullable = true, insertable = false, updatable = false)
    private Long orderCount;
}
