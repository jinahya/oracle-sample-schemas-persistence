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
        extends _MappedCoEntityBuilder<SELF, INVENTORY> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedInventoryBuilder(final Class<INVENTORY> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "storeId=" + storeId +
               ",store=" + store +
               ",productId=" + productId +
               ",product=" + product +
               ",productInventory=" + productInventory +
               '}';
    }

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
