package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedInventoryBuilder<
        SELF extends MappedInventoryBuilder<SELF, INVENTORY, STORE, PRODUCT>,
        INVENTORY extends MappedInventory<STORE, PRODUCT>,
        STORE extends MappedStore,
        PRODUCT extends MappedProduct
        >
        extends __MappedEntityBuilder<SELF, INVENTORY> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedInventoryBuilder(final Class<INVENTORY> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------------------- storeId
    @Deprecated(forRemoval = true)
    public Long storeId() {
        return storeId;
    }

    @Deprecated(forRemoval = true)
    public SELF storeId(final Long storeId) {
        this.storeId = storeId;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    public STORE store() {
        return store;
    }

    public SELF store(final STORE store) {
        this.store = store;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- productId
    @Deprecated(forRemoval = true)
    public Long productId() {
        return productId;
    }

    @Deprecated(forRemoval = true)
    public SELF productId(final Long productId) {
        this.productId = productId;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- product
    public PRODUCT product() {
        return product;
    }

    public SELF product(final PRODUCT product) {
        this.product = product;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------ productInventory
    public Long productInventory() {
        return productInventory;
    }

    public SELF productInventory(final Long productInventory) {
        this.productInventory = productInventory;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long storeId;

    private STORE store;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long productId;

    private PRODUCT product;

    // -----------------------------------------------------------------------------------------------------------------
    private Long productInventory;
}
