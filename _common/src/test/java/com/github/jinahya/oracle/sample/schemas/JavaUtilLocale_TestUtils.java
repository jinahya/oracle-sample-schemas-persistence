package com.github.jinahya.oracle.sample.schemas;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public final class JavaUtilLocale_TestUtils {

    private static final List<Locale> AVAILABLE_LOCALES = Locale.availableLocales().toList();

    public static Locale randomLocale() {
        if (AVAILABLE_LOCALES.isEmpty()) {
            return null;
        }
        return AVAILABLE_LOCALES.get(ThreadLocalRandom.current().nextInt(AVAILABLE_LOCALES.size()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JavaUtilLocale_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
