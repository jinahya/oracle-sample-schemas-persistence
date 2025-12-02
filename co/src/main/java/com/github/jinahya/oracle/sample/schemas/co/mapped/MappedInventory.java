package com.github.jinahya.oracle.sample.schemas.co.mapped;

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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedInventory extends _MappedCoEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

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
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the entity attribute, of {@link Basic} mapping, from which the {@value #COLUMN_NAME_INVENTORY_ID}
     * column maps. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    /**
     * The name of the entity attribute, of {@link ManyToOne} mapping, from which the {@value #COLUMN_NAME_INVENTORY_ID}
     * column maps. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ----------------------------------------------------------------------------------------------- PRODUCT_INVENTORY
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    // -------------------------------------------------------------------------------------------------------- BUILDERS

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventory() {
        super();
    }

    protected MappedInventory(final MappedInventoryBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "inventoryId=" + inventoryId +
               ",storeId=" + storeId +
               ",productId=" + productId +
               ",productInventory=" + productInventory +
               '}';
    }

    protected final boolean equalsWithStoreIdAndProductId(final Object obj) {
        if (!(obj instanceof MappedInventory that)) {
            return false;
        }
        return Objects.equals(storeId, that.storeId)
               && Objects.equals(productId, that.productId);
    }

    protected final int hashCodeWithStoreIdAndProductId() {
        return Objects.hash(storeId, productId);
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    // ---------------------------------------------------------------------------------------------- Jakarta-Validation
    /**
     * Tests whether {@value MappedInventory_#PRODUCT_INVENTORY} attribute is non-negative.
     *
     * @return {@code true} if {@value MappedInventory_#PRODUCT_INVENTORY} is non-negative; {@code false} otherwise.
     */
    protected boolean isProductInventoryNonNegative() {
        if (productInventory == null) {
            return true;
        }
        return productInventory >= 0L;
    }

    // ----------------------------------------------------------------------------------------------------- inventoryId
    public Long getInventoryId() {
        return inventoryId;
    }

    protected void setInventoryId(final Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Nonnull
    public final Long getStoreId() {
        return storeId;
    }

    protected void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // -------------------------------------------------------------------------------------------------------- productId
    @Nonnull
    public final Long getProductId() {
        return productId;
    }

    protected void setProductId(@Nonnull final Long productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------ productInventory
    @Nonnull
    public Long getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(@Nonnull final Long productInventory) {
        this.productInventory = productInventory;
    }

    /**
     * Adjusts current value of {@value MappedInventory_#PRODUCT_INVENTORY} attribute by the specified delta.
     *
     * @param delta the delta to adjust.
     */
    public void adjustProductInventory(final int delta) {
        setProductInventory(getProductInventory() + delta);
    }

    /**
     * Increases current value of {@value MappedInventory_#PRODUCT_INVENTORY} attribute by the specified quantity.
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
     * Decreases current value of {@value MappedInventory_#PRODUCT_INVENTORY} attribute by the specified quantity.
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
//             insertable = false,
            insertable = true, /* EclipseLink */
            updatable = false
    )
    private Long inventoryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Long storeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Long productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true, updatable = true)
    private Long productInventory;
}
