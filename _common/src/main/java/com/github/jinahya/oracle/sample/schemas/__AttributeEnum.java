package com.github.jinahya.oracle.sample.schemas;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@SuppressWarnings({
        "java:S114", // Interface names should comply with a naming convention
        "java:S119" // Type parameter names should comply with a naming convention
})
public interface __AttributeEnum<E extends Enum<E> & __AttributeEnum<E, ATTRIBUTE>, ATTRIBUTE> {

    interface __OfString<E extends Enum<E> & __OfString<E>> extends __AttributeEnum<E, String> {

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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the attribute value of this enum constant.
     *
     * @return the attribute value of this enum constant.
     */
    @NotNull
    ATTRIBUTE getAttributeValue();
}
