package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityRandomizer;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class OrderRandomizer extends __MappedEntityRandomizer<Order, Long> {

    OrderRandomizer() {
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
        return super.get();
    }
}
