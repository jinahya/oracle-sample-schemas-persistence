package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__PersistenceUnit_IT;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

@Slf4j
@SuppressWarnings({
        "java:S117", // Local variable and method parameter names should comply with a naming convention
        "java:S119", // Type parameter names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class _PersistenceUnit_IT
        extends __PersistenceUnit_IT {

    _PersistenceUnit_IT() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void assumeNoDestructivePrivileges() {
        applyEntityManager(em -> {
            _PersistenceUnit_IT_Utils.assumeNoDestructivePrivileges(em);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @Override
    protected void printDatabaseInfo__() {
        super.printDatabaseInfo__();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void _Mapped_AllDatabaseTableNames(@Nonnull final Collection<String> remainingDatabaseTableNames) {
        remainingDatabaseTableNames.clear();
        super._Mapped_AllDatabaseTableNames(remainingDatabaseTableNames);
    }

    @Override
    protected void _Known_AllPersistenceTableNames(@Nonnull final Collection<String> remainingPersistenceTableNames) {
        remainingPersistenceTableNames.clear();
        super._Known_AllPersistenceTableNames(remainingPersistenceTableNames);
    }
}
