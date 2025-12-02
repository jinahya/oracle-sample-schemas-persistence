package com.github.jinahya.oracle.sample.schemas.co.mapped;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class MappedInventory_Test<ENTITY extends MappedInventory> extends _MappedCoEntity_Test<ENTITY, Long> {

    protected MappedInventory_Test(final Class<ENTITY> entityClass) {
        super(entityClass, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("increaseProductInventoryBy(quantity)V")
    @Nested
    class IncreaseProductInventoryBy_Test {

        @DisplayName("(negative)IllegalArgumentException")
        @Test
        void _ThrowIllegalArgumentException_QuantityNegative() {
            final var inventory = newMappedInstance();
            final var quantity = ThreadLocalRandom.current().nextInt() | Integer.MIN_VALUE;
            assertThatThrownBy(() -> {
                inventory.increaseProductInventoryBy(quantity);
            }).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
