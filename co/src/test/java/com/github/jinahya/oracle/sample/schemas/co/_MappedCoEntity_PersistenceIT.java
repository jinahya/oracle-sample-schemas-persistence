package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Database_TestUtils;
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
    protected void a() {
        applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnectionAndRollback(
                em,
                c -> {
                    try {
                        final var privileges = __Database_TestUtils.Oracle.USER_SYS_PRIVS__PRIVILEGES(c);
                        log.debug("USER_SYS_PRIVS__PRIVILEGES: {}", privileges);
                        privileges.remove("CREATE SESSION");
                        privileges.forEach(p -> {
                            assertThat(p).doesNotContain("CREATE", "ALTER", "DROP");
                        });
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
        ));
        applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnectionAndRollback(
                em,
                c -> {
                    try {
                        final var privileges = __Database_TestUtils.Oracle.USER_TAB_PRIVS__PRIVILEGES(c);
                        log.debug("USER_TAB_PRIVS__PRIVILEGES: {}", privileges);
                        privileges.remove("CREATE SESSION");
                        privileges.forEach(p -> {
                            assertThat(p).doesNotContain("CREATE", "ALTER", "DROP");
                        });
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
        ));
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
