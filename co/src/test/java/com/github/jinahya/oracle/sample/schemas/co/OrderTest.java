package com.github.jinahya.oracle.sample.schemas.co;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

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