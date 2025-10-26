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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedInventory<
        STORE extends MappedStore,
        PRODUCT extends MappedProduct
        >
        extends _MappedCoEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "INVENTORY";

    // -------------------------------------------------------------------------------------- INVENTORY_ID / inventoryId

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

    // -------------------------------------------------------------------------------------- STORE_ID / storeId / store
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

    // -------------------------------------------------------------------------------- PRODUCT_ID / productId / product
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ----------------------------------------------------------------------------------------------- PRODUCT_INVENTORY
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventory() {
        super();
    }

    protected MappedInventory(final MappedInventoryBuilder<?, ?, ? extends STORE, ? extends PRODUCT> builder) {
        super(builder);
        setStoreId(builder.storeId());
        setStore(builder.store());
        setProductId(builder.productId());
        setProduct(builder.product());
        productInventory = builder.productInventory();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "inventoryId=" + inventoryId +
               ",storeId=" + storeId +
//                ",store=" + store +
               ",productId=" + productId +
//                ",product=" + product +
               ",productInventory=" + productInventory +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedInventory<?, ?> that)) {
            return false;
        }
        return Objects.equals(storeId, that.storeId)
               && Objects.equals(productId, that.productId);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(storeId, productId);
    }

    // ----------------------------------------------------------------------------------------------------- inventoryId
    public Long getInventoryId() {
        return inventoryId;
    }

    void setInventoryId(final Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Nonnull
    public final Long getStoreId() {
        return storeId;
    }

    final void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    @Nonnull
    public STORE getStore() {
        return store;
    }

    protected void setStore(@Nonnull final STORE store) {
        this.store = store;
        setStoreId(
                Optional.ofNullable(this.store)
                        .map(MappedStore::getStoreId)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------------- productId
    @Nonnull
    public final Long getProductId() {
        return productId;
    }

    final void setProductId(@Nonnull final Long productId) {
        this.productId = productId;
    }

    // --------------------------------------------------------------------------------------------------------- product
    @Nonnull
    public PRODUCT getProduct() {
        return product;
    }

    protected void setProduct(@Nonnull final PRODUCT product) {
        this.product = product;
        setProductId(
                Optional.ofNullable(this.product)
                        .map(MappedProduct::getProductId)
                        .orElse(null)
        );
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
     * Adjusts current value of {@link #getProductInventory() productInventory} attribute by the specified delta.
     *
     * @param delta the delta to adjust.
     */
    public void adjustProductInventory(final int delta) {
        setProductInventory(getProductInventory() + delta);
    }

    /**
     * Increases current value of {@link #getProductInventory() productInventory} attribute by the specified delta.
     *
     * @param delta the delta to adjust which should be non-negative.
     */
    public void increaseProductInventoryBy(final int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("negative delta: " + delta);
        }
        adjustProductInventory(delta);
    }

    /**
     * Decreases current value of {@link #getProductInventory() productInventory} attribute by the specified delta.
     *
     * @param delta the delta to adjust which should be non-negative.
     */
    public void decreaseProductInventoryBy(final int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("non-positive delta: " + delta);
        }
        adjustProductInventory(-delta);
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

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID,
                referencedColumnName = MappedStore.COLUMN_NAME_STORE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private STORE store;

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

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private PRODUCT product;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true, updatable = true)
    private Long productInventory;
}
