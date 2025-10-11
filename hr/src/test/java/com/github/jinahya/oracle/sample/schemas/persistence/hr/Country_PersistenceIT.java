package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._PersistenceUnit_ITUtils;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Country_PersistenceIT extends __MappedEntity_PersistenceIT<Country, String> {

    Country_PersistenceIT() {
        super(Country.class, String.class);
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

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("java.util.Locale#getISOCountries()")
    @Test
    void __JavaUtilsLocaleIsoCountries() {
        final var countries = applyEntityManager(em -> {
            return em.createQuery("SELECT e FROM Country e", Country.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(Country::getCountryId, Function.identity()));
        });
        Arrays.stream(Locale.getISOCountries()).forEach(isoc -> {
            final var country = countries.remove(isoc);
            if (country == null) {
                log.debug("missing; iso: {}", isoc);
            } else {
                log.debug("found; iso: {}, country: {}", isoc, country);
            }
        });
    }
}
