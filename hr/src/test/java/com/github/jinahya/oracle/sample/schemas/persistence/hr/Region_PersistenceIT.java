package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._PersistenceUnit_ITUtils;
import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.___JakartaPersistence_TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class Region_PersistenceIT extends __MappedEntity_PersistenceIT<Region, Long> {

    Region_PersistenceIT() {
        super(Region.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    final void assumeNoDestructivePrivileges() {
        applyEntityManager(em -> {
            _PersistenceUnit_ITUtils.assumeNoDestructivePrivileges(em);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * ({@code dmlonly} 가 바라보는) current schema 를 {@code HR} 로 변경한다.
     */
    @BeforeEach
    final void setCurrentSchema() {
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
    @DisplayName("selectOneWhereRegionNameEqual")
    @Nested
    class SelectOneByNameEqual_Test {

        @Nested
        class NamedQueryTest {

            @Test
            void __() {
                applyEntityManager(em -> {
                    final var query = em.createNamedQuery("Region.selectListWhereRegionNameEqual", Region.class);
                    final var regionName = "Europe";
                    query.setParameter("regionName", regionName);
                    final var result = query.getResultList();
                    assertThat(result).extracting(Region::getRegionName).allSatisfy(e -> {
                        assertThat(e).isEqualTo(regionName);
                    });
                    return null;
                });
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectListWhereRegionNameLike")
    @Nested
    class SelectListByNameLike_Test {

        @Test
        void __() {
            applyEntityManager(em -> {
                final var query = em.createNamedQuery("Region.selectListWhereRegionNameLike", Region.class);
                query.setParameter("regionNamePattern", "%a%");
                assertThatCode(() -> {
                    final var result = query.getResultList();
                    assertThat(result)
                            .extracting(Region::getRegionName)
                            .isNotEmpty()
                            .allSatisfy(rn -> {
                                assertThat(rn).contains("a");
                            });
                }).doesNotThrowAnyException();
                return null;
            });
        }
    }
}
