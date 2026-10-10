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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedProductOrder;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * An entity class for mapping the {@value ProductOrder#TABLE_NAME} view, whose composite identifier is mapped with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProductOrderId
 */
@Entity
@Table(name = ProductOrder.TABLE_NAME)
public class ProductOrder extends MappedProductOrder<ProductOrderId> implements __DomainEntity<ProductOrderId> {

    /**
     * The name of the attribute, of the {@link ProductOrderId @EmbeddedId}, which maps the
     * {@value #COLUMN_NAME_PRODUCT_NAME} column. The value is {@value}.
     *
     * @see #ATTRIBUTE_NAME_ID_PRODUCT_NAME
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NAME = "productName";

    /**
     * The name of the attribute, of the {@link ProductOrderId @EmbeddedId}, which maps the
     * {@value #COLUMN_NAME_ORDER_STATUS} column. The value is {@value}.
     *
     * @see #ATTRIBUTE_NAME_ID_ORDER_STATUS
     */
    public static final String ATTRIBUTE_NAME_ORDER_STATUS = "orderStatus";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductOrder() {
        super();
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public void setId(final ProductOrderId id) {
        this.id = id;
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute with the specified value, creating the
     * {@value #ATTRIBUTE_NAME_ID} attribute first when it is {@code null}.
     *
     * @param productName new value for {@value #ATTRIBUTE_NAME_PRODUCT_NAME} attribute.
     */
    protected void setProductName(final String productName) {
        if (getId() == null) {
            setId(new ProductOrderId());
        }
        getId().setProductName(productName);
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

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute with the specified value, creating the
     * {@value #ATTRIBUTE_NAME_ID} attribute first when it is {@code null}.
     *
     * @param orderStatus new value for {@value #ATTRIBUTE_NAME_ORDER_STATUS} attribute.
     */
    protected void setOrderStatus(final String orderStatus) {
        if (getId() == null) {
            setId(new ProductOrderId());
        }
        getId().setOrderStatus(orderStatus);
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    @Valid
    @NotNull
    @EmbeddedId
    private ProductOrderId id;

    // ------------------------------------------------------------------------------------------------------ getIdValue

    @Override
    protected ProductOrderId getIdValue() {
        return getId();
    }
}
