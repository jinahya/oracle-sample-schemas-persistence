package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.Lang_TestUtils;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer_Utils;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import static org.assertj.core.api.Assertions.assertThat;

class Shipment_Randomizer extends __MappedEntity_Randomizer<Shipment> {

    Shipment_Randomizer() {
        super(Shipment.class, "shipmentId", "shipmentStatus");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy()
                .addOrReplaceTypeManufacturer(
                        Store.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Store.class)
                )
                .addOrReplaceTypeManufacturer(
                        Customer.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Customer.class)
                )
                ;
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected Shipment manufacturePojo() {
        final var pojo = super.manufacturePojo();
        assertThat(pojo.getShipmentId()).isNull();
        assertThat(pojo.getShipmentStatus()).isNull();
        pojo.setShipmentStatus(
                Lang_TestUtils.randomEnumConstant(Shipment._ShipmentStatus.class)
        );
        return pojo;
    }
}