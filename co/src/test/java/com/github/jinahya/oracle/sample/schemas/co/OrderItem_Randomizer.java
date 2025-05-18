package com.github.jinahya.oracle.sample.schemas.co;

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
class OrderItem_Randomizer extends __MappedEntity_Randomizer<OrderItem> {

    OrderItem_Randomizer() {
        super(OrderItem.class
                , "order"
                , "shipment"
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        final var factory = super.podamFactory();
        final var strategy = factory.getStrategy();
        strategy
                .addOrReplaceTypeManufacturer(
                        Order.class,
                        new TypeTypeManufacturerImpl() {
                            @Override
                            public Object getType(final DataProviderStrategy s, final AttributeMetadata m,
                                                  final ManufacturingContext c) {
                                if (OrderItem.class.isAssignableFrom(m.getPojoClass())
                                        && m.getAttributeName().equals("order")) {
                                    return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(
                                            Order.class);
                                }
                                return super.getType(s, m, c);
                            }
                        }
                )
                .addOrReplaceTypeManufacturer(
                        Shipment.class,
                        new TypeTypeManufacturerImpl() {
                            @Override
                            public Object getType(final DataProviderStrategy s, final AttributeMetadata m,
                                                  final ManufacturingContext c) {
                                if (OrderItem.class.isAssignableFrom(m.getPojoClass())
                                        && m.getAttributeName().equals("shipment")) {
                                    return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(
                                            Shipment.class);
                                }
                                return super.getType(s, m, c);
                            }
                        }
                )
        ;
        return factory;
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected OrderItem manufacturePojo() {
        log.debug("manufacturing orderItem...");
        final var orderItem = super.manufacturePojo();
        log.debug("orderItem manufactured: {}", orderItem);
        assertThat(orderItem.getShipment()).isNull();
        return orderItem;
    }
}