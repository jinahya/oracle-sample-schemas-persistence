package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Inventory_Test extends __MappedEntity_Test<Inventory, Long> {

    Inventory_Test() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> equals_Verify_Create() {
        return super.equals_Verify_Create();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> equals_Verify_Configure(
            @Nonnull final SingleTypeEqualsVerifierApi<Inventory> equalsVerifier) {
        return super.equals_Verify_Configure(equalsVerifier)
                .withOnlyTheseFields(
                        MappedInventory.ATTRIBUTE_NAME_STORE_ID,
                        MappedInventory.ATTRIBUTE_NAME_PRODUCT_ID
                )
                ;
    }
}
