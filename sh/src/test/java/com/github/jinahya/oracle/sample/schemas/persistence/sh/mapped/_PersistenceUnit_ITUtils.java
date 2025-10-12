package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__Database_TestUtils;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public final class _PersistenceUnit_ITUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static void assumeNoDestructivePrivileges(@Nonnull final EntityManager entityManager) {
        ___JakartaPersistence_TestUtils.applyConnectionAndRollback(
                entityManager,
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
        );
        ___JakartaPersistence_TestUtils.applyConnectionAndRollback(
                entityManager,
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
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _PersistenceUnit_ITUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
