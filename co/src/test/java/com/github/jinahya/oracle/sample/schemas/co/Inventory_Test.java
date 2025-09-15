package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Configure_EqualsVerifier;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Inventory_Test extends __MappedEntity_Test<Inventory, Long> {

    Inventory_Test() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__Configure_EqualsVerifier
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<Inventory> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier)
                .withOnlyTheseFields(
                        MappedInventory.ATTRIBUTE_NAME_STORE_ID,
                        MappedInventory.ATTRIBUTE_NAME_PRODUCT_ID
                )
                ;
    }
}
