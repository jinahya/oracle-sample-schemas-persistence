package com.github.jinahya.oracle.sample.schemas;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

final class Lang_TestUtils {

    private static Method AUTO_CLOSEABLE_CLOSE;

    static {
        try {
            AUTO_CLOSEABLE_CLOSE = AutoCloseable.class.getMethod("close");
        } catch (final NoSuchMethodException nsme) {
            throw new ExceptionInInitializerError(nsme);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T extends AutoCloseable> T unclosable(Class<T> clazz, final T closeable) {
        Objects.requireNonNull(closeable, "closeable is null");
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                (p, m, a) -> {
                    if (m == AUTO_CLOSEABLE_CLOSE) {
                        throw new UnsupportedOperationException("close() is not supported");
                    }
                    return m.invoke(closeable, a);
                }
        );
    }

    private Lang_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
