package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityTest;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

class Inventory_Test extends __MappedEntityTest<Inventory, Long> {

    Inventory_Test() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void equals_Verify_() {
        super.equals_Verify_();
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> createEqualsVerifier() {
        return super.createEqualsVerifier()
                ;
    }

    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> configureEqualsVerifier(
            @Nonnull final SingleTypeEqualsVerifierApi<Inventory> equalsVerifier) {
        return super.configureEqualsVerifier(equalsVerifier)
                .withOnlyTheseFields(
                        MappedInventory.ATTRIBUTE_NAME_STORE_ID,
                        MappedInventory.ATTRIBUTE_NAME_PRODUCT_ID
                )
                ;
    }
}
