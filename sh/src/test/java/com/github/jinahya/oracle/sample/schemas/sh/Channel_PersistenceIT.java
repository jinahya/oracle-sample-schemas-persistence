package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class Channel_PersistenceIT extends __MappedEntity_PersistenceIT<Channel, Long> {

    Channel_PersistenceIT() {
        super(Channel.class, Long.class);
    }

    @BeforeEach
    void setCurrentSchema() {
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
