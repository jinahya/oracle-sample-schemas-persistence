package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public abstract class __AttributeEnumConverter<E extends Enum<E> & __AttributeEnum<E, A>, A>
        implements AttributeConverter<E, A> {

    // -----------------------------------------------------------------------------------------------------------------
    public static abstract class __OfString<E extends Enum<E> & __AttributeEnum.__OfString<E>>
            extends __AttributeEnumConverter<E, String> {

        protected __OfString(final Class<E> enumClass) {
            super(enumClass);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for converting specified enum class.
     *
     * @param enumClass the enum class.
     * @see #enumClass
     */
    protected __AttributeEnumConverter(final Class<E> enumClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public A convertToDatabaseColumn(final E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getAttributeValue();
    }

    @Override
    public E convertToEntityAttribute(final A dbData) {
        if (dbData == null) {
            return null;
        }
        return __AttributeEnum.valueOfAttributeValue(enumClass, dbData);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The enum class.
     */
    protected final Class<? extends E> enumClass;
}
