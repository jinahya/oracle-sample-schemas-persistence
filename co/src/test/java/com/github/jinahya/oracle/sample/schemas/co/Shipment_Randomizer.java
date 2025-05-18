package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.Lang_TestUtils;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer_Utils;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.common.ManufacturingContext;
import uk.co.jemos.podam.typeManufacturers.TypeTypeManufacturerImpl;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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
                        new TypeTypeManufacturerImpl() {
                            @Override
                            public Object getType(final DataProviderStrategy s, final AttributeMetadata m,
                                                  final ManufacturingContext c) {
                                if (Shipment.class.isAssignableFrom(m.getPojoClass())
                                        && m.getAttributeName().equals("store")) {
                                    return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(
                                            Store.class);
                                }
                                return super.getType(s, m, c);
                            }
                        }
                )
                .addOrReplaceTypeManufacturer(
                        Customer.class,
                        new TypeTypeManufacturerImpl() {
                            @Override
                            public Object getType(final DataProviderStrategy s, final AttributeMetadata m,
                                                  final ManufacturingContext c) {
                                if (Shipment.class.isAssignableFrom(m.getPojoClass())
                                        && m.getAttributeName().equals("customer")) {
                                    return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(
                                            Customer.class);
                                }
                                return super.getType(s, m, c);
                            }
                        }
                );
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
        log.debug("manufacturing shipment...");
        final var shipment = super.manufacturePojo();
        log.debug("shipment manufactured: {}", shipment);
        assertThat(shipment.getShipmentId()).isNull();
        assertThat(shipment.getShipmentStatus()).isNull();
        shipment.setShipmentStatus(
                Lang_TestUtils.randomEnumConstant(Shipment._ShipmentStatus.class)
        );
        return shipment;
    }
}