package com.github.jinahya.oracle.sample.persistence.co.co.schema;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

import com.github.jinahya.oracle.sample.persistence.co.Inventory_;
import com.github.jinahya.oracle.sample.persistence.co.Product;
import com.github.jinahya.oracle.sample.persistence.co.Product_;
import com.github.jinahya.oracle.sample.persistence.co.__Exercises_TestUtils;
import jakarta.persistence.criteria.JoinType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code CO_SCHEMA_I_06} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("CO_SCHEMA_I_06: Out of stock everywhere")
@Slf4j
class CO_SCHEMA_I_06_PersistenceIT {

    // Product maps PRODUCT_DETAILS and PRODUCT_IMAGE, both BLOBs, and Oracle cannot group by a BLOB
    // (ORA-22848), so the query groups by the product's key and name, as the SQL does, rather than
    // by the Product.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT p.productId, p.productName, COALESCE(SUM(i.productInventory), 0)
                                        FROM Product p
                                                 LEFT JOIN p.inventories i
                                        GROUP BY p.productId, p.productName
                                        HAVING COALESCE(SUM(i.productInventory), 0) = 0
                                        ORDER BY p.productName""",
                                Object[].class
                        )
                        .getResultList()
        );
        verify(list);
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var p = cq.from(Product.class);
            final var i = p.join(Product_.inventories, JoinType.LEFT);
            final var productId = p.get(Product_.productId);
            final var productName = p.get(Product_.productName);
            // SUM over no row is null, hence the COALESCE
            final var totalStock = cb.coalesce(cb.sum(i.get(Inventory_.productInventory)), 0L);
            cq.select(cb.array(productId, productName, totalStock))
                    .groupBy(productId, productName)
                    .having(cb.equal(totalStock, 0L))
                    .orderBy(cb.asc(productName));
            return em.createQuery(cq).getResultList();
        });
        verify(list);
    }

    // every product of the installed data is in stock somewhere
    private static void verify(final List<Object[]> list) {
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list).isEmpty();
    }
}
