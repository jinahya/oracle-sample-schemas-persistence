package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class JavaUtilLocaleTypeManufacturer implements TypeManufacturer<Locale> {

    private static final List<Locale> AVAILABLE_LOCALES = Locale.availableLocales().toList();

    private static Locale randomLocale() {
        if (AVAILABLE_LOCALES.isEmpty()) {
            return null;
        }
        return AVAILABLE_LOCALES.get(ThreadLocalRandom.current().nextInt(AVAILABLE_LOCALES.size()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static class InstanceHodler {

        private static final JavaUtilLocaleTypeManufacturer INSTANCE = new JavaUtilLocaleTypeManufacturer();

        private InstanceHodler() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static JavaUtilLocaleTypeManufacturer getInstance() {
        return InstanceHodler.INSTANCE;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JavaUtilLocaleTypeManufacturer() {
        super();
    }
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public Locale getType(final DataProviderStrategy strategy, final AttributeMetadata attributeMetadata,
                          final ManufacturingContext manufacturingCtx) {
        return randomLocale();
    }
}
