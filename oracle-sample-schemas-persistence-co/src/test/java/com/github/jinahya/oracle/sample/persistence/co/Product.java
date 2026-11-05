package com.github.jinahya.oracle.sample.persistence.co;

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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An entity class for mapping the {@value MappedProduct#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedEntityGraph(
        name = Product.ENTITY_GRAPH_NAME_PRODUCT_DETAILS,
        attributeNodes = @NamedAttributeNode(MappedProduct.ATTRIBUTE_NAME_PRODUCT_DETAILS)
)
@NamedEntityGraph(
        name = Product.ENTITY_GRAPH_NAME_PRODUCT_IMAGE,
        attributeNodes = @NamedAttributeNode(MappedProduct.ATTRIBUTE_NAME_PRODUCT_IMAGE)
)
@Entity
@Table(name = MappedProduct.TABLE_NAME, comment = "Details of goods that customers can purchase")
public class Product
        extends MappedProduct {

    // -------------------------------------------------------------------------------- ENTITY_GRAPHS

    /**
     * The name of the entity graph which fetches the lazy
     * {@value MappedProduct#ATTRIBUTE_NAME_PRODUCT_DETAILS} attribute. The value is {@value}.
     */
    public static final String ENTITY_GRAPH_NAME_PRODUCT_DETAILS = "Product.productDetails";

    /**
     * The name of the entity graph which fetches the lazy
     * {@value MappedProduct#ATTRIBUTE_NAME_PRODUCT_IMAGE} attribute. The value is {@value}.
     */
    public static final String ENTITY_GRAPH_NAME_PRODUCT_IMAGE = "Product.productImage";

    // --------------------------------------------------------------------------------- ORDER_ITEMS

    /**
     * The name of the attribute which maps the order items which order this product. The value is
     * {@value}.
     *
     * @see OrderItem#ATTRIBUTE_NAME_PRODUCT
     */
    public static final String ATTRIBUTE_NAME_ORDER_ITEMS = "orderItems";

    // --------------------------------------------------------------------------------- INVENTORIES

    /**
     * The name of the attribute which maps the inventories which hold this product. The value is
     * {@value}.
     *
     * @see Inventory#ATTRIBUTE_NAME_PRODUCT
     */
    public static final String ATTRIBUTE_NAME_INVENTORIES = "inventories";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Product() {
        super();
    }

    // ---------------------------------------------------------------------------------- orderItems

    /**
     * Returns the order items which order this product.
     *
     * @return the order items which order this product.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Replaces the order items which order this product.
     *
     * @param orderItems new order items which order this product.
     */
    public void setOrderItems(final List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // --------------------------------------------------------------------------------- inventories

    /**
     * Returns the inventories which hold this product.
     *
     * @return the inventories which hold this product.
     */
    public List<Inventory> getInventories() {
        return inventories;
    }

    /**
     * Replaces the inventories which hold this product.
     *
     * @param inventories new inventories which hold this product.
     */
    public void setInventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    // ---------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = OrderItem.ATTRIBUTE_NAME_PRODUCT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull OrderItem> orderItems;

    @OneToMany(mappedBy = Inventory.ATTRIBUTE_NAME_PRODUCT,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Inventory> inventories;
}
