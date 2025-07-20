package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__PersistenceProducerIT;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.Test;

import java.util.List;

@SuppressWarnings({
        "java:S3577" // Test classes should comply with a naming convention
})
class _PersistenceProducerIT extends __PersistenceProducerIT {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void checkAllDatabaseTablesMapped__() {
        super.checkAllDatabaseTablesMapped__();
    }

    @Override
    protected void checkingAllDatabaseTablesMapped(@Nonnull List<String> databaseTableNames) {
        super.checkingAllDatabaseTablesMapped(databaseTableNames);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void checkAllEntityTableNamesKnown__() {
        super.checkAllEntityTableNamesKnown__();
    }

    @Override
    protected void checkingAllEntityTableNamesKnown(@Nonnull List<String> entityTableNames) {
        super.checkingAllEntityTableNamesKnown(entityTableNames);
    }
}
