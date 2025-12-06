package com.github.jinahya.oracle.sample.schemas.persistence.util;

/*-
 * #%L
 * util
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
