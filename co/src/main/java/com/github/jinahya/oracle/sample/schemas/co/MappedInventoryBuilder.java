package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

@SuppressWarnings({
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

    // ----------------------------------------------------------------------------------------------------------- store
    public STORE store() {
        return store;
    }

    public void store(final STORE store) {
        this.store = store;
    }

    // --------------------------------------------------------------------------------------------------------- product
    public PRODUCT product() {
        return product;
    }

    protected void product(final PRODUCT product) {
        this.product = product;
    }

    // ------------------------------------------------------------------------------------------------ productInventory
    public Long productInventory() {
        return productInventory;
    }

    public void productInventory(final Long productInventory) {
        this.productInventory = productInventory;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private STORE store;

    private PRODUCT product;

    private Long productInventory;
}
