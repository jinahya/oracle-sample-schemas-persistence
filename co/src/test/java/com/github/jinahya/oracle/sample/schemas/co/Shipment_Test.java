package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test_Utils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class Shipment_Test extends __MappedEntity_Test<Shipment, Long> {

    Shipment_Test() {
        super(Shipment.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Shipment> equalsVerifier() {
        return super.equalsVerifier()
                .withPrefabValues(
                        Store.class,
                        __MappedEntity_Test_Utils.newEntityInstanceOf(Store.class, 1L),
                        __MappedEntity_Test_Utils.newEntityInstanceOf(Store.class, 2L)
                )
                .withPrefabValues(
                        Customer.class,
                        __MappedEntity_Test_Utils.newEntityInstanceOf(Customer.class, 1L),
                        __MappedEntity_Test_Utils.newEntityInstanceOf(Customer.class, 2L)
                )
                ;
    }
}