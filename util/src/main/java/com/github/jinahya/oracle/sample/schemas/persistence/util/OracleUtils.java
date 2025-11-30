package com.github.jinahya.oracle.sample.schemas.persistence.util;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZoneOffset;

public final class OracleUtils {

    private static final System.Logger logger = System.getLogger(MethodHandles.lookup().lookupClass().getName());

    // -----------------------------------------------------------------------------------------------------------------
    private static ZoneId zoneIdOf(final String string) {
        try {
            return ZoneOffset.of(string);
        } catch (final DateTimeException dte) {
            // empty
        }
        return ZoneId.of(string);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static final String SELECT_DBTIMEZONE = "SELECT DBTIMEZONE FROM DUAL";

    /**
     * Executes {@value #SELECT_DBTIMEZONE} and returns a {@link ZoneId} instance got from the result.
     *
     * @param connection a database connection.
     * @return a {@link ZoneId} (or a {@link ZoneOffset}) from the result.
     * @throws SQLException if an error occurs.
     * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/26/sqlrf/DBTIMEZONE.html">DBTIMEZONE</a>
     * (Oracle Database / Relase 26)
     */
    public static ZoneId DBTIMEZONE(final Connection connection) throws SQLException {
        try (var statement = connection.createStatement();
             var result = statement.executeQuery(SELECT_DBTIMEZONE)) {
            if (!result.next()) {
                throw new SQLException("no result for " + SELECT_DBTIMEZONE);
            }
            final var string = result.getString(1);
            if (string == null) {
                throw new SQLException("null column value for " + SELECT_DBTIMEZONE);
            }
            return zoneIdOf(string);
        }
    }

    public static final String SELECT_SESSIONTIMEZONE = "SELECT SESSIONTIMEZONE FROM DUAL";

    public static ZoneId SESSIONTIMEZONE(final Connection connection) throws SQLException {
        try (var statement = connection.createStatement();
             var result = statement.executeQuery(SELECT_SESSIONTIMEZONE)) {
            if (!result.next()) {
                throw new SQLException("no result for " + SELECT_SESSIONTIMEZONE);
            }
            final var string = result.getString(1);
            if (string == null) {
                throw new SQLException("null column value for " + SELECT_SESSIONTIMEZONE);
            }
            return zoneIdOf(string);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private OracleUtils() {
        super();
    }
}
