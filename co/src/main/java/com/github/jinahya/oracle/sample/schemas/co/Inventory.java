package com.github.jinahya.oracle.sample.schemas.co;

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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity class for mapping the {@value Inventory#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Inventory.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               Inventory.COLUMN_NAME_STORE_ID,
                               Inventory.COLUMN_NAME_PRODUCT_ID
                       }
               )
       }
)
public class Inventory {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "INVENTORY";

    // ---------------------------------------------------------------------------------------------------- INVENTORY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_ID = "INVENTORY_ID";

    /**
     * The name of the entity attribute from which the {@value #COLUMN_NAME_INVENTORY_ID} column maps. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVENTORY_ID = "inventoryId";

    // -------------------------------------------------------------------------------------------------------- STORE_ID

    /**
     * The name of the table column to which the {@code storeId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the entity attribute, of {@link ManyToOne} mapping, which maps the
     * {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID

    /**
     * The name of the table column to which the {@code productId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the entity attribute, of {@link ManyToOne} mapping, which maps the
     * {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ----------------------------------------------------------------------------------------------- PRODUCT_INVENTORY

    /**
     * The name of the table column to which the {@code productInventory} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Inventory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "inventoryId=" + inventoryId +
               ",store=" + store +
               ",product=" + product +
               ",productInventory=" + productInventory +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by ({@value #COLUMN_NAME_STORE_ID}, {@value #COLUMN_NAME_PRODUCT_ID}), the pair the table
     * declares unique, read through the {@value #ATTRIBUTE_NAME_STORE} and {@value #ATTRIBUTE_NAME_PRODUCT}
     * associations which map those two columns; both are read through their getters, which is what makes the
     * comparison correct for an instance which is still a lazy proxy.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Inventory that)) {
            return false;
        }
        return Objects.equals(getStore(), that.getStore())
               && Objects.equals(getProduct(), that.getProduct());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over ({@value #COLUMN_NAME_STORE_ID}, {@value #COLUMN_NAME_PRODUCT_ID}), consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getStore(), getProduct());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Tests whether {@code productInventory} attribute is non-negative.
     *
     * @return {@code true} if {@code productInventory} is non-negative; {@code false} otherwise.
     */
    protected boolean isProductInventoryNonNegative() {
        if (productInventory == null) {
            return true;
        }
        return productInventory >= 0L;
    }

    // ----------------------------------------------------------------------------------------------------- inventoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     */
    public Long getInventoryId() {
        return inventoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute with the specified value.
     *
     * @param inventoryId new value for {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     */
    protected void setInventoryId(final Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    // ----------------------------------------------------------------------------------------------------------- store

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE} attribute with the specified value.
     *
     * @param store new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(@Nonnull final Store store) {
        this.store = store;
    }

    // --------------------------------------------------------------------------------------------------------- product

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT} attribute with the specified value.
     *
     * @param product new value for {@value #ATTRIBUTE_NAME_PRODUCT} attribute.
     */
    public void setProduct(@Nonnull final Product product) {
        this.product = product;
    }

    // ------------------------------------------------------------------------------------------------ productInventory

    /**
     * Returns current value of {@code productInventory} attribute.
     *
     * @return current value of {@code productInventory} attribute.
     */
    @Nonnull
    public Long getProductInventory() {
        return productInventory;
    }

    /**
     * Replaces current value of {@code productInventory} attribute with the specified value.
     *
     * @param productInventory new value for {@code productInventory} attribute.
     */
    public void setProductInventory(@Nonnull final Long productInventory) {
        this.productInventory = productInventory;
    }

    /**
     * Adjusts current value of {@code productInventory} attribute by the specified delta.
     *
     * @param delta the delta to adjust.
     */
    public void adjustProductInventory(final int delta) {
        setProductInventory(getProductInventory() + delta);
    }

    /**
     * Increases current value of {@code productInventory} attribute by the specified quantity.
     *
     * @param quantity the quantity to adjust which should be non-negative.
     */
    public void increaseProductInventoryBy(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("negative quantity: " + quantity);
        }
        adjustProductInventory(+quantity);
    }

    /**
     * Decreases current value of {@code productInventory} attribute by the specified quantity.
     *
     * @param quantity the quantity to adjust which should be non-negative.
     */
    public void decreaseProductInventoryBy(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("non-positive quantity: " + quantity);
        }
        adjustProductInventory(-quantity);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = COLUMN_NAME_INVENTORY_ID,
            nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5.0.1.
            insertable = true,
            updatable = false
    )
    private Long inventoryId;

    // -----------------------------------------------------------------------------------------------------------------
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
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    private Product product;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true, updatable = true)
    private Long productInventory;
}
