package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer_Utils;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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
        final var pojo = super.manufacturePojo();
        assertThat(pojo.getOrderId()).isNull();
        return pojo;
    }
}