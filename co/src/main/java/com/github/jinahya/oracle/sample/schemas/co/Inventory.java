package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Entity
@Table(name = Inventory.TABLE_NAME)
class Inventory extends __MappedEntity<Inventory, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "INVENTORY";

    // ---------------------------------------------------------------------------------------------------- INVENTORY_ID

    /**
     * The name of the table column to which the {@link Inventory_#inventoryId inventoryId} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_ID = "INVENTORY_ID";

    // -------------------------------------------------------------------------------------------------------- STORE_ID
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    // ----------------------------------------------------------------------------------------------- PRODUCT_INVENTORY
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

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
//                ",store=" + store +
//                ",product=" + product +
                ",productInventory=" + productInventory +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (false && !(obj instanceof Inventory)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    // ------------------------------------------------------------------------------------------------------ super.id__
    @Override
    protected Long getId__() {
        return getInventoryId();
    }

    @Override
    protected void setId__(final Long id__) {
        setInventoryId(id__);
    }

    // ----------------------------------------------------------------------------------------------------- inventoryId
    public Long getInventoryId() {
        return inventoryId;
    }

    protected void setInventoryId(final Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    @Nonnull
    public Store getStore() {
        return store;
    }

    public void setStore(@Nonnull final Store store) {
        this.store = store;
    }

    // --------------------------------------------------------------------------------------------------------- product
    @Nonnull
    public Product getProduct() {
        return product;
    }

    public void setProduct(@Nonnull final Product product) {
        this.product = product;
    }

    // ------------------------------------------------------------------------------------------------ productInventory
    @Nonnull
    public Long getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(@Nonnull final Long productInventory) {
        this.productInventory = productInventory;
    }

    public Inventory adjustProductInventoryBy(final int delta) {
        setProductInventory(
                Optional.ofNullable(getProductInventory()).orElse(0L) + delta
        );
        return this;
    }

    public Inventory increaseProductInventoryBy(final int delta) {
        if (delta < 0L) {
            throw new IllegalArgumentException("negative delta: " + delta);
        }
        return adjustProductInventoryBy(+delta);
    }

    public Inventory decreaseProductInventoryBy(final int delta) {
        if (delta < 0L) {
            throw new IllegalArgumentException("negative delta: " + delta);
        }
        return adjustProductInventoryBy(-delta);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_INVENTORY_ID, nullable = false,
//            insertable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long inventoryId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, referencedColumnName = Store.COLUMN_NAME_STORE_ID, nullable = false,
                insertable = true, updatable = false)
    private Store store;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, referencedColumnName = Product.COLUMN_NAME_PRODUCT_ID, nullable = false,
                insertable = true, updatable = false)
    private Product product;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true, updatable = true)
    private Long productInventory;
}
