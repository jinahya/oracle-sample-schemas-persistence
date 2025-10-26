package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
class _PersistenceUnit_IT extends __PersistenceUnit_IT {

    _PersistenceUnit_IT() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    final void assumeNoDestructivePrivileges() {
        applyEntityManager(em -> {
            _PersistenceUnit_ITUtils.assumeNoDestructivePrivileges(em);
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
