package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LongSummaryStatistics;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Utilities for {@link Sales}: readers of the components of its {@link Sales#getId() id}, kept out of the entity so
 * that they are not bean properties of it. There are no writers: a row gets its id once, whole, through
 * {@link SalesId#of(Integer, Long, java.time.LocalDateTime, Long, Integer)} and {@link Sales#setId(SalesId)}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Sales
 * @see SalesId
 */
public final class SalesUtils {

    /**
     * Statistics of the {@value Sales#ATTRIBUTE_NAME_AMOUNT_SOLD} attribute over a set of entities; the
     * {@link BigDecimal} counterpart of {@link LongSummaryStatistics}, which the JDK does not provide.
     *
     * @param count the number of entities.
     * @param sum   the sum; {@link BigDecimal#ZERO} when {@code count} is {@code 0}.
     * @param min   the minimum; {@code null} when {@code count} is {@code 0}.
     * @param max   the maximum; {@code null} when {@code count} is {@code 0}.
     */
    public record AmountSoldStatistics(long count, BigDecimal sum, @Nullable BigDecimal min, @Nullable BigDecimal max) {

        /**
         * Returns the average, {@code sum} divided by {@code count} in the specified context.
         *
         * @param mathContext the context of the division.
         * @return the average; {@link BigDecimal#ZERO} when {@code count} is {@code 0}.
         */
        public BigDecimal average(final MathContext mathContext) {
            Objects.requireNonNull(mathContext, "mathContext is null");
            if (count == 0L) {
                return BigDecimal.ZERO;
            }
            return sum.divide(BigDecimal.valueOf(count), mathContext);
        }
    }

    // ------------------------------------------------------------------------------------------------------- id.prodId

    /**
     * Returns current value of the {@value Sales#ATTRIBUTE_PATH_ID_PROD_ID} attribute of the specified entity -- the
     * {@value SalesId#ATTRIBUTE_NAME_PROD_ID} component of its {@link Sales#getId() id}.
     *
     * @param sales the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code sales} is {@code null}.
     */
    public static Integer getIdProdId(final Sales sales) {
        Objects.requireNonNull(sales, "sales is null");
        return Optional.ofNullable(sales.getId()).map(SalesId::getProdId).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------- id.custId

    /**
     * Returns current value of the {@value Sales#ATTRIBUTE_PATH_ID_CUST_ID} attribute of the specified entity -- the
     * {@value SalesId#ATTRIBUTE_NAME_CUST_ID} component of its {@link Sales#getId() id}.
     *
     * @param sales the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code sales} is {@code null}.
     */
    public static Long getIdCustId(final Sales sales) {
        Objects.requireNonNull(sales, "sales is null");
        return Optional.ofNullable(sales.getId()).map(SalesId::getCustId).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------- id.timeId

    /**
     * Returns current value of the {@value Sales#ATTRIBUTE_PATH_ID_TIME_ID} attribute of the specified entity -- the
     * {@value SalesId#ATTRIBUTE_NAME_TIME_ID} component of its {@link Sales#getId() id}.
     *
     * @param sales the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code sales} is {@code null}.
     */
    public static LocalDateTime getIdTimeId(final Sales sales) {
        Objects.requireNonNull(sales, "sales is null");
        return Optional.ofNullable(sales.getId()).map(SalesId::getTimeId).orElse(null);
    }

    // ---------------------------------------------------------------------------------------------------- id.channelId

    /**
     * Returns current value of the {@value Sales#ATTRIBUTE_PATH_ID_CHANNEL_ID} attribute of the specified entity -- the
     * {@value SalesId#ATTRIBUTE_NAME_CHANNEL_ID} component of its {@link Sales#getId() id}.
     *
     * @param sales the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code sales} is {@code null}.
     */
    public static Long getIdChannelId(final Sales sales) {
        Objects.requireNonNull(sales, "sales is null");
        return Optional.ofNullable(sales.getId()).map(SalesId::getChannelId).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------ id.promoId

    /**
     * Returns current value of the {@value Sales#ATTRIBUTE_PATH_ID_PROMO_ID} attribute of the specified entity -- the
     * {@value SalesId#ATTRIBUTE_NAME_PROMO_ID} component of its {@link Sales#getId() id}.
     *
     * @param sales the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code sales} is {@code null}.
     */
    public static Integer getIdPromoId(final Sales sales) {
        Objects.requireNonNull(sales, "sales is null");
        return Optional.ofNullable(sales.getId()).map(SalesId::getPromoId).orElse(null);
    }

    // ---------------------------------------------------------------------------------------------------- quantitySold

    /**
     * Returns the sum of the {@value Sales#ATTRIBUTE_NAME_QUANTITY_SOLD} attribute over the specified entities.
     *
     * @param sales the entities.
     * @return the sum; {@code 0} when {@code sales} is empty.
     * @throws NullPointerException if {@code sales} is {@code null}, contains {@code null}, or contains an entity whose
     *                              {@value Sales#ATTRIBUTE_NAME_QUANTITY_SOLD} attribute is {@code null}.
     */
    public static long getTotalQuantitySold(final Iterable<? extends Sales> sales) {
        Objects.requireNonNull(sales, "sales is null");
        var total = 0L;
        for (final var each : sales) {
            total += each.getQuantitySold();
        }
        return total;
    }

    /**
     * Returns the sum of the {@value Sales#ATTRIBUTE_NAME_QUANTITY_SOLD} attribute over the specified entities.
     *
     * @param sales the entities.
     * @return the sum; {@code 0} when {@code sales} is empty.
     * @throws NullPointerException if {@code sales} is {@code null}, contains {@code null}, or contains an entity whose
     *                              {@value Sales#ATTRIBUTE_NAME_QUANTITY_SOLD} attribute is {@code null}.
     * @see #getTotalQuantitySold(Iterable)
     */
    public static long getTotalQuantitySold(final Collection<? extends Sales> sales) {
        Objects.requireNonNull(sales, "sales is null");
        return sales.stream()
                .mapToLong(Sales::getQuantitySold)
                .sum();
    }

    // ------------------------------------------------------------------------------------------------------ amountSold

    /**
     * Returns the sum of the {@value Sales#ATTRIBUTE_NAME_AMOUNT_SOLD} attribute over the specified entities.
     *
     * @param sales the entities.
     * @return the sum; {@link BigDecimal#ZERO} when {@code sales} is empty.
     * @throws NullPointerException if {@code sales} is {@code null}, contains {@code null}, or contains an entity whose
     *                              {@value Sales#ATTRIBUTE_NAME_AMOUNT_SOLD} attribute is {@code null}.
     */
    public static BigDecimal getTotalAmountSold(final Iterable<? extends Sales> sales) {
        Objects.requireNonNull(sales, "sales is null");
        var total = BigDecimal.ZERO;
        for (final var each : sales) {
            total = total.add(each.getAmountSold());
        }
        return total;
    }

    /**
     * Returns the sum of the {@value Sales#ATTRIBUTE_NAME_AMOUNT_SOLD} attribute over the specified entities.
     *
     * @param sales the entities.
     * @return the sum; {@link BigDecimal#ZERO} when {@code sales} is empty.
     * @throws NullPointerException if {@code sales} is {@code null}, contains {@code null}, or contains an entity whose
     *                              {@value Sales#ATTRIBUTE_NAME_AMOUNT_SOLD} attribute is {@code null}.
     * @see #getTotalAmountSold(Iterable)
     */
    public static BigDecimal getTotalAmountSold(final Collection<? extends Sales> sales) {
        Objects.requireNonNull(sales, "sales is null");
        return sales.stream()
                .map(Sales::getAmountSold)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ----------------------------------------------------------------------------------------- quantitySold/statistics

    /**
     * Returns the statistics of the {@value Sales#ATTRIBUTE_NAME_QUANTITY_SOLD} attribute over the entities the
     * specified function selects, in one aggregate query.
     *
     * @param entityManager the entity manager to query with.
     * @param where         a function building the restriction from the query's builder and root; {@code null} for
     *                      every entity.
     * @return the statistics; empty, with a count of {@code 0}, when no entity is selected.
     * @throws NullPointerException if {@code entityManager} is {@code null}.
     */
    public static LongSummaryStatistics getQuantitySoldStatistics(
            final EntityManager entityManager,
            @Nullable final BiFunction<? super CriteriaBuilder, ? super Root<Sales>, ? extends Predicate> where) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createTupleQuery();
        final var root = query.from(Sales.class);
        final var path = root.get(Sales_.quantitySold);
        query.select(builder.tuple(builder.count(root), builder.sumAsLong(path), builder.min(path), builder.max(path)));
        if (where != null) {
            query.where(where.apply(builder, root));
        }
        final var tuple = entityManager.createQuery(query).getSingleResult();
        final var count = ((Number) tuple.get(0)).longValue();
        if (count == 0L) {
            return new LongSummaryStatistics();
        }
        // the constructor takes count, min, max, sum -- not count, sum, min, max
        return new LongSummaryStatistics(
                count,
                ((Number) tuple.get(2)).longValue(),
                ((Number) tuple.get(3)).longValue(),
                ((Number) tuple.get(1)).longValue()
        );
    }

    // ------------------------------------------------------------------------------------------- amountSold/statistics

    /**
     * Returns the specified aggregate value as a {@link BigDecimal}, whatever numeric type the provider chose for it.
     */
    private static BigDecimal decimal(final Object value) {
        if (value instanceof BigDecimal decimal) {
            return decimal;
        }
        return new BigDecimal(value.toString());
    }

    /**
     * Returns the statistics of the {@value Sales#ATTRIBUTE_NAME_AMOUNT_SOLD} attribute over the entities the specified
     * function selects, in one aggregate query.
     *
     * @param entityManager the entity manager to query with.
     * @param where         a function building the restriction from the query's builder and root; {@code null} for
     *                      every entity.
     * @return the statistics; with a count of {@code 0}, a sum of {@link BigDecimal#ZERO} and no minimum or maximum,
     * when no entity is selected.
     * @throws NullPointerException if {@code entityManager} is {@code null}.
     */
    public static AmountSoldStatistics getAmountSoldStatistics(
            final EntityManager entityManager,
            @Nullable final BiFunction<? super CriteriaBuilder, ? super Root<Sales>, ? extends Predicate> where) {
        Objects.requireNonNull(entityManager, "entityManager is null");
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createTupleQuery();
        final var root = query.from(Sales.class);
        final var path = root.get(Sales_.amountSold);
        query.select(builder.tuple(builder.count(root), builder.sum(path), builder.min(path), builder.max(path)));
        if (where != null) {
            query.where(where.apply(builder, root));
        }
        final var tuple = entityManager.createQuery(query).getSingleResult();
        final var count = ((Number) tuple.get(0)).longValue();
        if (count == 0L) {
            return new AmountSoldStatistics(0L, BigDecimal.ZERO, null, null);
        }
        return new AmountSoldStatistics(count, decimal(tuple.get(1)), decimal(tuple.get(2)), decimal(tuple.get(3)));
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private SalesUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
