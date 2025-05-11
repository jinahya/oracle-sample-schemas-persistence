package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Inventory_Test extends __MappedEntity_Test<Inventory, Long> {

    Inventory_Test() {
        super(Inventory.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Inventory> equalsVerifier() {
        return super.equalsVerifier();
    }
}