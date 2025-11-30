package com.github.jinahya.oracle.sample.schemas.persistence.util;

import com.github.jinahya.persistence.mapped.test.__PersistenceUnit_IT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
class OracleUtilsTest extends __PersistenceUnit_IT {

    @Test
    void DBTIMEZONE__() {
        // -------------------------------------------------------------------------------------------------------- when
        final var dbtimezone = applyEntityManager(em -> {
            return ___JakartaPersistence_TestUtils.getInTransactionAndRollback(em, () -> {
                return ___JakartaPersistence_TestUtils.applyUnwrappedConnection(em, c -> {
                    try {
                        return OracleUtils.DBTIMEZONE(c);
                    } catch (final SQLException sqle) {
                        throw new RuntimeException(sqle);
                    }
                });
            });
        });
        // -------------------------------------------------------------------------------------------------------- then
        log.debug("DBTIMEZONE: {} ({})", dbtimezone, dbtimezone.getClass());
    }
}
