package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer_Utils;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class OrderItem_Randomizer extends __MappedEntity_Randomizer<OrderItem> {

    OrderItem_Randomizer() {
        super(OrderItem.class,
              "order", // TODO: 왜!
//              "product",
              "shipment"
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy()
                .addOrReplaceTypeManufacturer(
                        Order.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Order.class)
                )
                .addOrReplaceTypeManufacturer(
                        Product.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Product.class)
                )
                .addOrReplaceTypeManufacturer(
                        Shipment.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Shipment.class)
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
    protected OrderItem manufacturePojo() {
        log.debug("manufacturing orderItem...");
        final var orderItem = super.manufacturePojo();
        log.debug("orderItem manufactured: {}", orderItem);
        assertThat(orderItem.getShipment()).isNull();
        return orderItem;
    }
}