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

import com.github.jinahya.oracle.sample.persistence.co.mapped.MappedInventory;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * An entity class for mapping the {@value MappedInventory#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = MappedInventory.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               Inventory.COLUMN_NAME_STORE_ID,
                               Inventory.COLUMN_NAME_PRODUCT_ID
                       }
               )
       },
       comment = "Details of the quantity of stock available for products at each location"
)
public class Inventory
        extends MappedInventory {

    // ------------------------------------------------------------------------------------ STORE_ID

    /**
     * The name of the {@link ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ---------------------------------------------------------------------------------- PRODUCT_ID

    /**
     * The name of the {@link ManyToOne @ManyToOne} association which joins on the
     * {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Inventory() {
        super();
    }

    // --------------------------------------------------------------------------------------- store

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    @Nonnull
    public Store getStore() {
        return store;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE} attribute with the specified value,
     * and current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified value's
     * identifier.
     *
     * @param store new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(@Nonnull final Store store) {
        this.store = store;
        setStoreId(Optional.ofNullable(store).map(Store::getStoreId).orElse(null));
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    @Nonnull
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute with the specified
     * value, and current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value's identifier.
     *
     * @param product new value for {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public void setProduct(@Nonnull final Product product) {
        this.product = product;
        setProductId(Optional.ofNullable(product).map(Product::getProductId).orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = true, updatable = false)
    private Store store;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true,
                updatable = false)
    private Product product;
}
