package com.github.jinahya.oracle.sample.schemas;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public final class __MappedEntity_Test_Utils {

    public static <ENTITY extends __MappedEntity<ENTITY, ?>>
    ENTITY newEntityInstanceOf(final Class<ENTITY> entityClass) {
        try {
            final var constructor = entityClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to instantiate " + entityClass, roe);
        }
    }

//    static BigDecimal randomLatitude() {
//        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble())
//                .multiply(BigDecimal.valueOf(__MappedEntityConstants.MAX_LATITUDE)
//                                  .subtract(BigDecimal.valueOf(__MappedEntityConstants.MIN_LATITUDE)))
//                .subtract(BigDecimal.valueOf(__MappedEntityConstants.MAX_LATITUDE));
//    }
//
//    static BigDecimal randomLongitude() {
//        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble())
//                .multiply(BigDecimal.valueOf(__MappedEntityConstants.MAX_LONGITUDE)
//                                  .subtract(BigDecimal.valueOf(__MappedEntityConstants.MIN_LONGITUDE)))
//                .subtract(BigDecimal.valueOf(__MappedEntityConstants.MAX_LONGITUDE));
//    }

    private __MappedEntity_Test_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}