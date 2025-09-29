package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._PersistenceUnit_IT_Utils;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class Region_PersistenceIT extends __MappedEntity_PersistenceIT<Region, Long> {

    Region_PersistenceIT() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void assumeNoDestructivePrivileges() {
        applyEntityManager(em -> {
            _PersistenceUnit_IT_Utils.assumeNoDestructivePrivileges(em);
            return null;
        });
    }

    /**
     * ({@code dmlonly} 가 바라보는) current schema 를 {@code HR} 로 변경한다.
     */
    @BeforeEach
    void setCurrentSchema() {
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
