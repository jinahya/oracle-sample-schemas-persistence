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

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract class for integration-testing persistence of a specific subclass of the {@link _MappedHrEntity} class.
 *
 * @param <ENTITY> entity type parameter
 * @param <ID>     id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntity_PersistenceIT<ENTITY extends _MappedHrEntity<ID>, ID>
        extends __MappedEntity_PersistenceIT<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     * @param idClass     the id class of the {@code entityClass}.
     */
    protected _MappedHrEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Assumes no destructive privileges.
     *
     * @see _PersistenceUnit_ITUtils#assumeNoDestructivePrivileges(EntityManager)
     */
    @BeforeEach
    final void assumeNoDestructivePrivileges() {
        applyEntityManager(em -> {
            _PersistenceUnit_ITUtils.assumeNoDestructivePrivileges(em);
            return null;
        });
    }

    /**
     * ({@code dmlonly} 가 바라보는) current schema 를 {@code HR} 로 변경한다.
     */
    @BeforeEach
    final void setCurrentSchema() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnection(
                    em,
                    c -> {
                        try (var statement = c.createStatement()) {
                            final var result = statement.execute(
                                    "ALTER SESSION SET CURRENT_SCHEMA = " + getJinahyaTableSchema()
                            );
                            assertThat(result).isFalse(); // not a ResultSet
                        } catch (final SQLException sqle) {
                            throw new RuntimeException(sqle);
                        }
                        return null;
                    },
                    false
            ));
            return;
        }
        applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnection(
                em,
                c -> {
                    try (var statement = c.createStatement()) {
                        final var result = statement.execute(
                                "ALTER SESSION SET CURRENT_SCHEMA = " + getJinahyaTableSchema()
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
