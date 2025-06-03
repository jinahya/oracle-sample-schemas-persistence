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
class Order_Randomizer extends __MappedEntity_Randomizer<Order> {

    Order_Randomizer() {
        super(Order.class,
              "orderId",
              "orderTmsAsZonedDateTime",
              "orderTmsFromZonedDateTime",
              "orderTmsAsOffsetDateTime",
              "orderTmsFromOffsetDateTime",
              "orderTmsAsInstant",
              "orderTmsFromInstant",
              "orderStatus",
              "orderItems"
//                ,
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
                        new TypeTypeManufacturerImpl() {
                            @Override
                            public Object getType(final DataProviderStrategy s, final AttributeMetadata m,
                                                  final ManufacturingContext c) {
                                if (Order.class.isAssignableFrom(m.getPojoClass())
                                        && m.getAttributeName().equals("customer")) {
                                    return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(
                                            Customer.class);
                                }
                                return super.getType(s, m, c);
                            }
                        }
                )
                .addOrReplaceTypeManufacturer(
                        Store.class,
                        new TypeTypeManufacturerImpl() {
                            @Override
                            public Object getType(final DataProviderStrategy s, final AttributeMetadata m,
                                                  final ManufacturingContext c) {
                                if (Order.class.isAssignableFrom(m.getPojoClass()) &&
                                        m.getAttributeName().equals("store")) {
                                    return __MappedEntity_Randomizer_Utils.newRandomizedInstanceOfOrElseThrow(
                                            Store.class);
                                }
                                return super.getType(s, m, c);
                            }
                        }
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
        final var order = super.manufacturePojo();
        log.debug("order: {}", order);
        assertThat(order.getOrderId()).isNull();
        order.setOrderStatus(Order.OrderStatus.OPEN);
        return order;
    }
}