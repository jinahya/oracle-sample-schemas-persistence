package com.github.jinahya.oracle.sample.schemas;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.Objects;

public abstract class __AttributeEnumConverter2<E extends Enum<E> & __AttributeEnum<E, A>, A>
        implements AttributeConverter<E, A> {

    protected __AttributeEnumConverter2(final Class<? extends E>[] enumClasses) {
        super();
        if (Objects.requireNonNull(enumClasses, "enumClasses is null").length == 0) {
            throw new IllegalArgumentException("empty enumClasses: " + Arrays.toString(enumClasses));
        }
        this.enumClasses = enumClasses;
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
        return __AttributeEnumUtils.valueOfAttributeValue(dbData, enumClasses);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<? extends E>[] enumClasses;
}
