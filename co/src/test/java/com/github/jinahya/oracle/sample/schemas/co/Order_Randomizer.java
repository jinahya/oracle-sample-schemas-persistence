package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import com.github.jinahya.persistence.more.test.__AttributeEnum_TestUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.Optional;

@Slf4j
class Order_Randomizer extends __MappedEntity_Randomizer<Order, Long> {

    Order_Randomizer() {
        super(Order.class, Long.class,
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

    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    public Order get() {
        final var value = super.get();
        value.setOrderStatus(
                Optional.ofNullable(
                        __AttributeEnum_TestUtils.getRandomAttributeValue(Order._OrderStatus.class)
                ).orElseThrow()
        );
        return value;
    }
}
