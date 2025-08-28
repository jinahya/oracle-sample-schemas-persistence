package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
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
        extends __MappedEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "INVENTORY";

    // ---------------------------------------------------------------------------------------------------- INVENTORY_ID

    /**
     * The name of the table column to which the {@link MappedInventory_#inventoryId inventoryId} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_ID = "INVENTORY_ID";

    // -------------------------------------------------------------------------------------- STORE_ID / storeId / store
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

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

    protected MappedInventory(final MappedInventoryBuilder<?, ?, STORE, PRODUCT> builder) {
        super(builder);
        setStore(builder.store());
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

    // ----------------------------------------------------------------------------------------------------------- store
    @Nonnull
    public STORE getStore() {
        return store;
    }

    void setStore(@Nonnull final STORE store) {
        this.store = store;
        storeId = Optional.ofNullable(this.store).map(MappedStore::getStoreId).orElse(null);
    }

    // --------------------------------------------------------------------------------------------------------- product
    @Nonnull
    public PRODUCT getProduct() {
        return product;
    }

    void setProduct(@Nonnull final PRODUCT product) {
        this.product = product;
        productId = Optional.ofNullable(this.product).map(MappedProduct::getProductId).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------ productInventory
    @Nonnull
    public Long getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(@Nonnull final Long productInventory) {
        this.productInventory = productInventory;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_INVENTORY_ID,
            nullable = false,
            /* insertable = false,*/ insertable = true, // EclipseLink
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
