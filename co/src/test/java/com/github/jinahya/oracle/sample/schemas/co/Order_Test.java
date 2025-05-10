package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas._MappedEntity_Test;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class Order_Test extends _MappedEntity_Test<Order> {

    Order_Test() {
        super(Order.class);
    }

    @Override
    protected SingleTypeEqualsVerifierApi<Order> equalsVerifier() {
        return super.equalsVerifier()
                .withPrefabValues(
                        List.class, Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() {
        List<BigDecimal> numbers = Arrays.asList(
                new BigDecimal("10.123"),
                new BigDecimal("20.45"),
                new BigDecimal("30.6")
        );

        BigDecimal sum = numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Sum without adjusting scale: " + sum);

        BigDecimal sumWithScale = numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        System.out.println("Sum with adjusted scale: " + sumWithScale);
    }
}