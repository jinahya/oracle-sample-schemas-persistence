package com.github.jinahya.oracle.sample.persistence.co.mapped;

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

import com.github.jinahya.oracle.sample.persistence.common.annotation.OraComment;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

/**
 * A mapped superclass for mapping the {@value MappedInventory#TABLE_NAME} table.
 * <p>
 * The {@value #COLUMN_NAME_STORE_ID} and {@value #COLUMN_NAME_PRODUCT_ID} columns are mapped
 * read-only ({@code insertable = false, updatable = false}), with a public getter and a
 * {@code protected} setter, for {@link #toString()} and for queries. How the relationship behind
 * each is mapped -- fetch type, cascade, whether there is an association at all -- is the extending
 * entity's decision, so the extending entity also owns their writable mapping, by an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}. <strong>An extending entity which maps none
 * never writes them.</strong> Being read-only, they are populated by a load or a refresh, or by the
 * extending entity, which sets them through their {@code protected} setters in step with the
 * associations which write the columns.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
@OraComment("Details of the quantity of stock available for products at each location")
public abstract class MappedInventory {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "INVENTORY";

    // -------------------------------------------------------------------------------- INVENTORY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_ID = "INVENTORY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVENTORY_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVENTORY_ID = "inventoryId";

    // ------------------------------------------------------------------------------------ STORE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STORE_ID} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column, read-only.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ---------------------------------------------------------------------------------- PRODUCT_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column, read-only.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // --------------------------------------------------------------------------- PRODUCT_INVENTORY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY}
     * attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_INVENTORY} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_INVENTORY = "productInventory";
    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object
    @Override
    public String toString() {
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
     * @implSpec Equality is by the generated {@code @Id} alone, the other instance's read
     *         through its getter, which is what makes the comparison correct when the other
     *         instance is still a lazy proxy. An instance whose {@code @Id} is still {@code null}
     *         -- one not yet persisted -- equals itself only.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedInventory that)) {
            return false;
        }
        final var inventoryId = getInventoryId();
        return inventoryId != null && inventoryId.equals(that.getInventoryId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is constant so that it does not change when the generated
     *         {@code @Id} is assigned on persist, which would lose an instance already held in a
     *         hash-based collection. It is {@code MappedInventory}'s rather than
     *         {@link #getClass()}'s, because a lazy proxy's class is a generated subclass and must
     *         hash alike to the instance it stands for.
     */
    @Override
    public final int hashCode() {
        return MappedInventory.class.hashCode();
    }

    // ----------------------------------------------------------------------------- Bean-Validation

    /**
     * Tests whether {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute is non-negative.
     *
     * @return {@code true} if {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} is {@code null} or
     *         non-negative; {@code false} otherwise.
     */
//    @jakarta.validation.constraints.AssertTrue
    protected boolean isProductInventoryNonNegative() {
        if (productInventory == null) {
            return true;
        }
        return productInventory >= 0L;
    }

    // --------------------------------------------------------------------------------- inventoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     */
    public Long getInventoryId() {
        return inventoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute with the specified
     * value.
     *
     * @param inventoryId new value for {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     */
    protected void setInventoryId(final Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    // ------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    @Nonnull
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified
     * value.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(@Nonnull final Long storeId) {
        this.storeId = storeId;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    @Nonnull
    public Long getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(@Nonnull final Long productId) {
        this.productId = productId;
    }

    // ---------------------------------------------------------------------------- productInventory

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute with the
     * specified value.
     *
     * @param productInventory new value for {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute.
     */
    public void setProductInventory(@Nonnull final Long productInventory) {
        this.productInventory = productInventory;
    }

    /**
     * Adjusts current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute by the
     * specified delta.
     *
     * @param delta the delta to adjust.
     * @throws NullPointerException if current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY}
     *                              attribute is {@code null}.
     */
    public void adjustProductInventory(final int delta) {
        setProductInventory(getProductInventory() + delta);
    }

    /**
     * Increases current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute by the
     * specified quantity.
     *
     * @param quantity the quantity to adjust which should be non-negative.
     * @throws IllegalArgumentException if {@code quantity} is negative.
     * @throws NullPointerException     if current value of
     *                                  {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute is
     *                                  {@code null}.
     */
    public void increaseProductInventoryBy(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("negative quantity: " + quantity);
        }
        adjustProductInventory(+quantity);
    }

    /**
     * Decreases current value of {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute by the
     * specified quantity.
     *
     * @param quantity the quantity to adjust which should be non-negative.
     * @throws IllegalArgumentException if {@code quantity} is negative.
     * @throws NullPointerException     if current value of
     *                                  {@value #ATTRIBUTE_NAME_PRODUCT_INVENTORY} attribute is
     *                                  {@code null}.
     */
    public void decreaseProductInventoryBy(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("non-positive quantity: " + quantity);
        }
        adjustProductInventory(-quantity);
    }

    // ---------------------------------------------------------------------------------------------
    // no @NotNull: database-generated identity; the value is null when the provider validates at
    // pre-persist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = COLUMN_NAME_INVENTORY_ID,
            nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5
            // .0.1.
            insertable = true,
            updatable = false,
            comment = "Auto-incrementing primary key"
    )
    private Long inventoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false,
            comment = "Which location the goods are located at")
    private Long storeId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false,
            comment = "Which item this stock is for")
    private Long productId;

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true,
            updatable = true,
            comment = "The current quantity in stock")
    private Long productInventory;
}
