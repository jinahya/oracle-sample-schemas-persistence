package com.github.jinahya.oracle.sample.schemas;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@SuppressWarnings({
        "java:S114", // Interface names should comply with a naming convention
        "java:S119" // Type parameter names should comply with a naming convention
})
public interface __AttributeEnum<E extends Enum<E> & __AttributeEnum<E, ATTRIBUTE>, ATTRIBUTE> {

    interface __OfInteger<E extends Enum<E> & __OfInteger<E>> extends __AttributeEnum<E, Integer> {

        static <E extends Enum<E> & __OfInteger<E>> E valueOfAttributeValue(
                final Class<? extends E> enumClass, final Integer attributeValue) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(attributeValue, "attributeValue is null");
            return __AttributeEnum.valueOfAttributeValue(enumClass, attributeValue);
        }
    }

    interface __OfLong<E extends Enum<E> & __OfLong<E>> extends __AttributeEnum<E, Long> {

        static <E extends Enum<E> & __OfLong<E>> E valueOfAttributeValue(
                final Class<? extends E> enumClass, final Long attributeValue) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(attributeValue, "attributeValue is null");
            return __AttributeEnum.valueOfAttributeValue(enumClass, attributeValue);
        }
    }

    interface __OfString<E extends Enum<E> & __OfString<E>> extends __AttributeEnum<E, String> {

        static <E extends Enum<E> & __OfString<E>> E valueOfAttributeValue(
                final Class<? extends E> enumClass, final String attributeValue) {
            Objects.requireNonNull(enumClass, "enumClass is null");
            Objects.requireNonNull(attributeValue, "attributeValue is null");
            return __AttributeEnum.valueOfAttributeValue(enumClass, attributeValue);
        }

        @Override
        @SuppressWarnings({"unchecked"})
        default String getAttributeValue() {
            return ((Enum<E>) this).name();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static <E extends Enum<E> & __AttributeEnum<E, ATTRIBUTE>, ATTRIBUTE> E valueOfAttributeValue(
            final Class<? extends E> enumClass, final ATTRIBUTE attributeValue) {
        Objects.requireNonNull(enumClass, "enumClass is null");
        Objects.requireNonNull(attributeValue, "attributeValue is null");
        for (final E enumConstant : enumClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.getAttributeValue(), attributeValue)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException(
                "no enum constant, of " + enumClass + ", for attributeValue: " + attributeValue
        );
    }

    /**
     * Returns the attribute value of this enum constant.
     *
     * @return the attribute value of this enum constant.
     */
    @NotNull
    ATTRIBUTE getAttributeValue();
}
