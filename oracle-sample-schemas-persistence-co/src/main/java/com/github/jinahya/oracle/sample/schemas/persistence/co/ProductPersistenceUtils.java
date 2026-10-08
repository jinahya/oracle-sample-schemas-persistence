package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Utilities for querying {@link Product} entities in a persistence context.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ProductPersistenceUtils {

    /**
     * Selects the minimum and the maximum of the {@value Product#ATTRIBUTE_NAME_UNIT_PRICE} attribute, in a single
     * query, and returns the result of applying the specified mapperFunction to them.
     * <p>
     * {@snippet lang = "java":
     * final var range = ProductPersistenceUtils.applyMinUnitPriceAndMaxUnitPrice(
     *         entityManager,
     *         (min, max) -> max == null ? null : max.subtract(min)
     * );
     *}
     *
     * @param entityManager  an entity manager to query with.
     * @param mapperFunction a mapperFunction to be applied with the minimum and the maximum unit price, in that order;
     *                       may be applied with {@code null} and {@code null}.
     * @param <R>            result type parameter
     * @return the result of the {@code mapperFunction}.
     * @apiNote The {@code mapperFunction} is always applied, and is applied with {@code null} and {@code null} when the
     * table is empty, or when no product has a unit price.
     */
    public static <R> R applyMinUnitPriceAndMaxUnitPrice(
            final EntityManager entityManager,
            final BiFunction<? super BigDecimal, ? super BigDecimal, ? extends R> mapperFunction) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        Objects.requireNonNull(mapperFunction, "mapperFunction is null");
        // Object[] rather than Tuple: EclipseLink returns an Object[] for a multi-select even when asked for a Tuple
        final var row = entityManager.createQuery(
                        """
                                SELECT MIN(e.unitPrice), MAX(e.unitPrice)
                                FROM Product AS e""",
                        Object[].class
                )
                .getSingleResult(); // an aggregate without GROUP BY always yields exactly one row
        return mapperFunction.apply(
                (BigDecimal) row[0],
                (BigDecimal) row[1]
        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Prevents instantiation.
     *
     * @throws AssertionError always.
     */
    private ProductPersistenceUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
