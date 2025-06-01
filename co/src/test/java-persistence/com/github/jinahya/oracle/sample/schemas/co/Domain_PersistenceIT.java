package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__Persistence_Test_Utils;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@Slf4j
class Domain_PersistenceIT {

    @Disabled
    @Test
    void __() {
        __Persistence_Test_Utils.applyConnection(
                entityManager,
                c -> {
                    final DatabaseMetaData metaData;
                    try {
                        metaData = c.getMetaData();
                        try (var r = metaData.getCatalogs()) {
                            while (r.next()) {
                                log.debug("TABLE_CAT: {}", r.getString("TABLE_CAT"));
                            }
                        }
                        try (var r = metaData.getSchemas()) {
                            while (r.next()) {
                                log.debug("TABLE_SCHEM: {}, TABLE_CATALOG: {}", r.getString("TABLE_SCHEM"),
                                          r.getString("TABLE_CATALOG"));
                            }
                        }
                    } catch (final SQLException sqle) {
                        throw new RuntimeException(sqle);
                    }
                    return null;
                },
                true
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private EntityManager entityManager;
}