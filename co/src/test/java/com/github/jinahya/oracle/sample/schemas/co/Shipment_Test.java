package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Shipment_Test extends __MappedEntity_Test<Shipment, Long> {

    Shipment_Test() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> equalsVerifier() {
        return super.equalsVerifier()
                .withPrefabValues(Store.class, Store.of(1L), Store.of(2L))
                .withPrefabValues(Customer.class, Customer.of(1L), Customer.of(2L))
                ;
    }
}