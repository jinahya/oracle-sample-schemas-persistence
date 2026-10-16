package com.github.jinahya.oracle.sample.schemas.persistence.co;

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
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value ProductOrder#TABLE_NAME} view, whose composite identifier is mapped with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProductOrderId
 */
@NamedQuery(name = "ProductOrder.selectOneByProductNameAndOrderStatus",
            query = """
                    SELECT e
                    FROM ProductOrder e
                    WHERE e.id.productName = :productName
                      AND e.id.orderStatus = :orderStatus"""
)
@NamedQuery(name = "ProductOrder.selectListOrderByProductNameAscOrderStatusAsc",
            query = """
                    SELECT e
                    FROM ProductOrder e
                    ORDER BY e.id.productName ASC, e.id.orderStatus ASC"""
)
@NamedQuery(name = "ProductOrder.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM ProductOrder e"""
)
@Entity
@Table(name = ProductOrder.TABLE_NAME)
public class ProductOrder {

    /**
     * The name of the database view to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCT_ORDERS";

    // ---------------------------------------------------------------------------------------------------- PRODUCT_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NAME = 255;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PRODUCT_NAME = COLUMN_LENGTH_PRODUCT_NAME;

    /**
     * The name of the attribute, of the {@link ProductOrderId @EmbeddedId}, which maps the
     * {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     *
     * @see #ATTRIBUTE_NAME_ID_PRODUCT_NAME
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NAME} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PRODUCT_NAME = "id.productName";

    // ---------------------------------------------------------------------------------------------------- ORDER_STATUS

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ORDER_STATUS = "ORDER_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_STATUS = 10;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ORDER_STATUS = COLUMN_LENGTH_ORDER_STATUS;

    /**
     * The name of the attribute, of the {@link ProductOrderId @EmbeddedId}, which maps the
     * {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     *
     * @see #ATTRIBUTE_NAME_ID_ORDER_STATUS
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_STATUS} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_ORDER_STATUS = "id.orderStatus";

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

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
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
        if (!(obj instanceof ProductOrder that)) {
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
    public ProductOrderId getId() {
        return id;
    }

    // ----------------------------------------------------------------------------------------------------- productName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute; {@code null} when
     * {@value #ATTRIBUTE_NAME_ID} attribute is {@code null}.
     */
    public String getProductName() {
        return Optional.ofNullable(getId()).map(ProductOrderId::getProductName).orElse(null);
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute; {@code null} when
     * {@value #ATTRIBUTE_NAME_ID} attribute is {@code null}.
     */
    public String getOrderStatus() {
        return Optional.ofNullable(getId()).map(ProductOrderId::getOrderStatus).orElse(null);
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

    @Valid
    @NotNull
    @EmbeddedId
    private ProductOrderId id;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SALES, nullable = true, insertable = false, updatable = false)
    private BigDecimal totalSales;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT, nullable = true, insertable = false, updatable = false)
    private Long orderCount;
}
