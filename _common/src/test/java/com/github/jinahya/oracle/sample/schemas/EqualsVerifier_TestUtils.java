package com.github.jinahya.oracle.sample.schemas;

import nl.jqno.equalsverifier.Func;
import nl.jqno.equalsverifier.api.EqualsVerifierApi;

public final class EqualsVerifier_TestUtils {

    public static <T extends EqualsVerifierApi<?>> T withPrefabValues(final T verifier, final Class<?> otherType,
                                                                      final Object red, final Object blue) {
        try {
            return (T) EqualsVerifierApi.class.getMethod("withPrefabValues", Class.class, Object.class, Object.class)
                    .invoke(verifier, otherType, red, blue);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    public static <T extends EqualsVerifierApi<?>, U> T withGenericPrefabValues(final T verifier,
                                                                                final Class<U> otherType,
                                                                                final Object red) {
        try {
            return (T) EqualsVerifierApi.class.getDeclaredMethod("withGenericPrefabValues", Class.class,
                                                                 Func.Func1.class)
                    .invoke(verifier, new Object[]{otherType, new Func.Func1<Object, U>() {
                        @Override
                        public U supply(final Object o) {
                            return (U) red;
                        }
                    }});
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    public static <T extends EqualsVerifierApi<?>, U> T withPrefabValuesHelper(final T verifier,
                                                                               final Class<U> otherType,
                                                                               final Object red, final Object blue) {
        try {
            return (T) EqualsVerifierApi.class.getMethod("withPrefabValues", Class.class, Object.class, Object.class)
                    .invoke(verifier, otherType, otherType.cast(red), otherType.cast(blue));
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private EqualsVerifier_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
