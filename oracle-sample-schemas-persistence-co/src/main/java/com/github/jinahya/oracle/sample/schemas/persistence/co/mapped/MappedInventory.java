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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

/**
 * A mapped superclass which holds the mappings of the {@value MappedInventory#TABLE_NAME} table.
 * <p>
 * A mapped superclass cannot carry a {@link jakarta.persistence.Table @Table}, so the unique constraint over
 * {@value MappedInventory#COLUMN_NAME_STORE_ID} and {@value MappedInventory#COLUMN_NAME_PRODUCT_ID} is declared by the
 * entity which extends this class.
 * <p>
 * The {@value MappedInventory#COLUMN_NAME_STORE_ID} and {@value MappedInventory#COLUMN_NAME_PRODUCT_ID} columns are
 * mapped read-only ({@code insertable = false, updatable = false}) and have no setters. They are here for
 * {@link #equals(Object)}, {@link #hashCode()}, {@link #toString()} and for queries, not for writing: how the
 * {@code STORES} and {@code PRODUCTS} relationships are mapped -- fetch type, cascade, whether there is an association
 * at all -- is the extending entity's decision, so the extending entity also owns the writable mapping of each column,
 * by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes these columns.</strong>
 * <p>
 * Being read-only, both attributes are populated by a load or a refresh only; they stay {@code null} on an instance
 * which has not been loaded yet, whatever its associations hold.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedInventory {

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVENTORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVENTORY_ID = "inventoryId";

    // -------------------------------------------------------------------------------------------------------- STORE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ----------------------------------------------------------------------------------------------- PRODUCT_INVENTORY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_INVENTORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_INVENTORY = "productInventory";
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final String toString() {
        return super.toString() + '{' +
               "inventoryId=" + inventoryId +
               ",storeId=" + storeId +
               ",productId=" + productId +
               ",productInventory=" + productInventory +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by ({@value #COLUMN_NAME_STORE_ID}, {@value #COLUMN_NAME_PRODUCT_ID}), the pair the table
     * declares unique, read through the {@value #ATTRIBUTE_NAME_STORE_ID} and {@value #ATTRIBUTE_NAME_PRODUCT_ID}
     * attributes which map those two columns; both are read through their {@code protected} getters, which a lazy proxy
     * can override, and which is what makes the comparison correct for an instance which is still a proxy. Both
     * attributes are read-only and stay {@code null} until the instance is loaded, so an instance missing either one
     * equals itself only; otherwise every instance not yet loaded would equal every other.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedInventory that)) {
            return false;
        }
        final var storeId = getStoreId();
        final var productId = getProductId();
        if (storeId == null || productId == null) {
            return false;
        }
        return storeId.equals(that.getStoreId())
               && productId.equals(that.getProductId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is constant, so that it does not change when ({@value #COLUMN_NAME_STORE_ID},
     * {@value #COLUMN_NAME_PRODUCT_ID}) goes from {@code null} to loaded, which would lose an instance already held in
     * a hash-based collection. It is {@code MappedInventory}'s rather than {@link #getClass()}'s, because a lazy
     * proxy's class is a generated subclass and must hash alike to the instance it stands for.
     */
    @Override
    public final int hashCode() {
        return MappedInventory.class.hashCode();
    }

    // ------------------------------------------------------------------------------------------------------ Validation

    /**
     * Tests whether {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute is non-negative.
     *
     * @return {@code true} if {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} is {@code null} or non-negative; {@code false}
     * otherwise.
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
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getStoreId() {
        return storeId;
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getProductId() {
        return productId;
    }

    // ------------------------------------------------------------------------------------------------ productInventory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute.
     */
    @Nonnull
    public Long getProductInventory() {
        return productInventory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute with the specified value.
     *
     * @param productInventory new value for {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute.
     */
    public void setProductInventory(@Nonnull final Long productInventory) {
        this.productInventory = productInventory;
    }

    /**
     * Adjusts current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute by the specified delta.
     *
     * @param delta the delta to adjust.
     */
    public void adjustProductInventory(final int delta) {
        setProductInventory(getProductInventory() + delta);
    }

    /**
     * Increases current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute by the specified quantity.
     *
     * @param quantity the quantity to adjust which should be non-negative.
     * @throws IllegalArgumentException if {@code quantity} is negative.
     */
    public void increaseProductInventoryBy(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("negative quantity: " + quantity);
        }
        adjustProductInventory(+quantity);
    }

    /**
     * Decreases current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute by the specified quantity.
     *
     * @param quantity the quantity to adjust which should be non-negative.
     * @throws IllegalArgumentException if {@code quantity} is negative.
     */
    public void decreaseProductInventoryBy(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("non-positive quantity: " + quantity);
        }
        adjustProductInventory(-quantity);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // no @NotNull: database-generated identity; the value is null when the provider validates at pre-persist
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
    // read-only: the extending entity owns the writable mapping of each column. No @NotNull, since the field is
    // legitimately null until a load; the constraint belongs on the mapping which writes the column.
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Long storeId;

    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    private Long productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true, updatable = true)
    private Long productInventory;
}
