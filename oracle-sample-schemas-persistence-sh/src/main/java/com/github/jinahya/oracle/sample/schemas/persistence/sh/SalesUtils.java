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

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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

    // ------------------------------------------------------------------------------------------------------- id.prodId

    /**
     * Returns current value of the {@value Sales#ATTRIBUTE_NAME_ID_PROD_ID} attribute of the specified entity -- the
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
     * Returns current value of the {@value Sales#ATTRIBUTE_NAME_ID_CUST_ID} attribute of the specified entity -- the
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
     * Returns current value of the {@value Sales#ATTRIBUTE_NAME_ID_TIME_ID} attribute of the specified entity -- the
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
     * Returns current value of the {@value Sales#ATTRIBUTE_NAME_ID_CHANNEL_ID} attribute of the specified entity -- the
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
     * Returns current value of the {@value Sales#ATTRIBUTE_NAME_ID_PROMO_ID} attribute of the specified entity -- the
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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private SalesUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
