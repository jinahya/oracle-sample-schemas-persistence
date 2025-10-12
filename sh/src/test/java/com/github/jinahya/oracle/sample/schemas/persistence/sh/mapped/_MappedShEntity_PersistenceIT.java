package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class _MappedShEntity_PersistenceIT<ENTITY extends _MappedShEntity<ID>, ID>
        extends __MappedEntity_PersistenceIT<ENTITY, ID> {

    protected _MappedShEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
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
            return;
        }
        applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnection(
                em,
                c -> {
                    try (var statement = c.createStatement()) {
                        final var result = statement.execute(
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
