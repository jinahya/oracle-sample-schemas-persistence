package com.github.jinahya.oracle.sample.schemas.co;

/*-
 * #%L
 * co
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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _MappedCoEntity_PersistenceIT<ENTITY extends _MappedCoEntity<ID>, ID>
        extends __MappedEntity_PersistenceIT<ENTITY, ID> {

    _MappedCoEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void assumeNoDestructivePrivileges() {
        applyEntityManager(em -> {
            _PersistenceUnit_ITUtils.assumeNoDestructivePrivileges(em);
            return null;
        });
    }

    @BeforeEach
    void setCurrentSchema() {
        final var usePreparedStatement = false;
        if (usePreparedStatement) {
            // TODO: 이건 왜 안될까?
            applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnection(
                    em,
                    c -> {
                        try (var statement = c.prepareStatement("ALTER SESSION SET CURRENT_SCHEMA = ?")) {
                            statement.setString(1, tableSchema());
                            final var result = statement.execute();
                            assertThat(result).isFalse(); // not a ResultSet
                        } catch (final SQLException sqle) {
                            throw new RuntimeException(sqle);
                        }
                        return null;
                    },
                    false
            ));
        } else {
            applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnection(
                    em,
                    c -> {
                        try (var statement = c.createStatement()) {
                            final boolean result = statement.execute(
                                    "ALTER SESSION SET CURRENT_SCHEMA = " + tableSchema()
                            );
                            assertThat(result).isFalse(); // not a ResultSet
                        } catch (final SQLException sqle) {
                            throw new RuntimeException(sqle);
                        }
                        return null;
                    },
                    false
            ));
        }
    }
}
