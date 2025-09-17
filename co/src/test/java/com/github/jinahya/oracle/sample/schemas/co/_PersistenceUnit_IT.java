package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Database_TestUtils;
import com.github.jinahya.persistence.mapped.test.__PersistenceUnit_IT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
class _PersistenceUnit_IT
        extends __PersistenceUnit_IT {

    _PersistenceUnit_IT() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    protected void a() {
        applyEntityManager(em -> ___JakartaPersistence_TestUtils.applyConnectionAndRollback(
                em,
                c -> {
                    try {
                        final var userSysPrivs_Privileges =
                                __Database_TestUtils.Oracle.USER_SYS_PRIVS__PRIVILEGES(c);
                        log.debug("USER_SYS_PRIVS__PRIVILEGES: {}", userSysPrivs_Privileges);
                        userSysPrivs_Privileges.remove("CREATE SESSION");
                        userSysPrivs_Privileges.forEach(p -> {
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
                        final var userSysPrivs_Privileges =
                                __Database_TestUtils.Oracle.USER_TAB_PRIVS__PRIVILEGES(c);
                        log.debug("USER_TAB_PRIVS__PRIVILEGES: {}", userSysPrivs_Privileges);
                        userSysPrivs_Privileges.remove("CREATE SESSION");
                        userSysPrivs_Privileges.forEach(p -> {
                            assertThat(p).doesNotContain("CREATE", "ALTER", "DROP");
                        });
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
        ));
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
