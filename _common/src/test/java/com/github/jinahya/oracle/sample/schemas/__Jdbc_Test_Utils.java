package com.github.jinahya.oracle.sample.schemas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class __Jdbc_Test_Utils {

    private static final String CATALOG = "dev1_prime";

    private static final String SCHEMA = null;

    // -----------------------------------------------------------------------------------------------------------------
    public static void acceptEachTableName(final Connection connection, final Consumer<? super String> consumer)
            throws SQLException {
        try (var result = connection.getMetaData().getTables(CATALOG, "%", "%", null)) {
            while (result.next()) {
                consumer.accept(result.getString("TABLE_NAME"));
            }
        }
    }

    public static <C extends Collection<? super String>> C addAllTableNames(final Connection connection,
                                                                            final C collection)
            throws SQLException {
        acceptEachTableName(connection, collection::add);
        return collection;
    }

    public static List<String> getTableNames(final Connection connection) throws SQLException {
        return addAllTableNames(connection, new ArrayList<>());
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static void acceptEachColumnName(final Connection connection, final String tableName,
                                            final Consumer<? super String> consumer)
            throws SQLException {
        try (var result = connection.getMetaData().getColumns(CATALOG, "%", tableName, "%")) {
            while (result.next()) {
                consumer.accept(result.getString("COLUMN_NAME"));
            }
        }
    }

    public static <C extends Collection<? super String>> C addAllColumnNames(final Connection connection,
                                                                             final String tableName, final C collection)
            throws SQLException {
        acceptEachColumnName(connection, tableName, collection::add);
        return collection;
    }

    public static List<String> getAllColumnNames(final Connection connection, final String tableName)
            throws SQLException {
        return Collections.unmodifiableList(
                addAllColumnNames(connection, tableName, new ArrayList<>())
        );
    }
}
