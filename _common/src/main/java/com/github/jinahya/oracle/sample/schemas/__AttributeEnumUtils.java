package com.github.jinahya.oracle.sample.schemas;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({
        "java:S114", // Interface names should comply with a naming convention
        "java:S119" // Type parameter names should comply with a naming convention
})
public final class __AttributeEnumUtils {

    public static <E extends Enum<E> & __AttributeEnum<E, ATTRIBUTE>, ATTRIBUTE> E valueOfAttributeValue(
            final ATTRIBUTE attributeValue, final Class<? extends E>... enumClasses) {
        Objects.requireNonNull(attributeValue, "attributeValue is null");
        if (Objects.requireNonNull(enumClasses, "enumClasses is null").length == 0) {
            throw new IllegalArgumentException("enumClasses is empty");
        }
        for (final var enumClass : enumClasses) {
            try {
                return __AttributeEnum.valueOfAttributeValue(enumClass, attributeValue);
            } catch (final IllegalArgumentException iae) {
                // empty
            }
        }
        throw new IllegalArgumentException(
                "no enum constant, of " + Arrays.toString(enumClasses) + ", for attributeValue: " + attributeValue
        );
    }

    private __AttributeEnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
