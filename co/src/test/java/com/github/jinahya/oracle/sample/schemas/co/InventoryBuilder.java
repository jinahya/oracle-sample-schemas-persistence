package com.github.jinahya.oracle.sample.schemas.co;

class InventoryBuilder extends MappedInventoryBuilder<InventoryBuilder, Inventory, Store, Product> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected InventoryBuilder() {
        super(Inventory.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
}
