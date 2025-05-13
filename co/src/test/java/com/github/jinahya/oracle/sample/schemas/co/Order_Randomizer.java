package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer_Utils;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Order_Randomizer extends __MappedEntity_Randomizer<Order> {

    Order_Randomizer() {
        super(Order.class,
              "orderId",
              "orderStatus"
//                ,
//              "orderItems",
//              "orderItemsTotalPrice1",
//              "orderItemsTotalPrice2",
//              "orderItemsTotalPrice3"
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy()
                .addOrReplaceTypeManufacturer(
                        Customer.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Customer.class)
                )
                .addOrReplaceTypeManufacturer(
                        Store.class,
                        (s, m, c) -> __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(Store.class)
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
    protected Order manufacturePojo() {
        log.debug("manufacturing order...");
        final var pojo = super.manufacturePojo();
        log.debug("order: {}", pojo);
        assertThat(pojo.getOrderId()).isNull();
        assertThat(pojo.getOrderStatus()).isSameAs(Order.OrderStatus.OPEN);
        return pojo;
    }
}