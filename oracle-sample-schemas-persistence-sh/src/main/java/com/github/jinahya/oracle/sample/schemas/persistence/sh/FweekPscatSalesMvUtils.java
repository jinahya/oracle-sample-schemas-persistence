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
 * Utilities for {@link FweekPscatSalesMv}: readers of the components of its {@link FweekPscatSalesMv#getId() id},
 * kept out of the entity so that they are not bean properties of it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see FweekPscatSalesMv
 * @see FweekPscatSalesMvId
 */
public final class FweekPscatSalesMvUtils {

    // ------------------------------------------------------------------------------------------------ id.weekEndingDay

    /**
     * Returns current value of the {@value FweekPscatSalesMv#ATTRIBUTE_PATH_ID_WEEK_ENDING_DAY} attribute of the
     * specified entity -- the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_WEEK_ENDING_DAY} component of its
     * {@link FweekPscatSalesMv#getId() id}.
     *
     * @param fweekPscatSalesMv the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code fweekPscatSalesMv} is {@code null}.
     */
    public static LocalDateTime getIdWeekEndingDay(final FweekPscatSalesMv fweekPscatSalesMv) {
        Objects.requireNonNull(fweekPscatSalesMv, "fweekPscatSalesMv is null");
        return Optional.ofNullable(fweekPscatSalesMv.getId()).map(FweekPscatSalesMvId::getWeekEndingDay).orElse(null);
    }

    // ---------------------------------------------------------------------------------------------- id.prodSubcategory

    /**
     * Returns current value of the {@value FweekPscatSalesMv#ATTRIBUTE_PATH_ID_PROD_SUBCATEGORY} attribute of the
     * specified entity -- the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROD_SUBCATEGORY} component of its
     * {@link FweekPscatSalesMv#getId() id}.
     *
     * @param fweekPscatSalesMv the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code fweekPscatSalesMv} is {@code null}.
     */
    public static String getIdProdSubcategory(final FweekPscatSalesMv fweekPscatSalesMv) {
        Objects.requireNonNull(fweekPscatSalesMv, "fweekPscatSalesMv is null");
        return Optional.ofNullable(fweekPscatSalesMv.getId()).map(FweekPscatSalesMvId::getProdSubcategory).orElse(null);
    }

    // ---------------------------------------------------------------------------------------------------- id.channelId

    /**
     * Returns current value of the {@value FweekPscatSalesMv#ATTRIBUTE_PATH_ID_CHANNEL_ID} attribute of the
     * specified entity -- the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_CHANNEL_ID} component of its
     * {@link FweekPscatSalesMv#getId() id}.
     *
     * @param fweekPscatSalesMv the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code fweekPscatSalesMv} is {@code null}.
     */
    public static Long getIdChannelId(final FweekPscatSalesMv fweekPscatSalesMv) {
        Objects.requireNonNull(fweekPscatSalesMv, "fweekPscatSalesMv is null");
        return Optional.ofNullable(fweekPscatSalesMv.getId()).map(FweekPscatSalesMvId::getChannelId).orElse(null);
    }

    // ------------------------------------------------------------------------------------------------------ id.promoId

    /**
     * Returns current value of the {@value FweekPscatSalesMv#ATTRIBUTE_PATH_ID_PROMO_ID} attribute of the
     * specified entity -- the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROMO_ID} component of its
     * {@link FweekPscatSalesMv#getId() id}.
     *
     * @param fweekPscatSalesMv the entity.
     * @return the component; {@code null} when the id is {@code null}.
     * @throws NullPointerException if {@code fweekPscatSalesMv} is {@code null}.
     */
    public static Integer getIdPromoId(final FweekPscatSalesMv fweekPscatSalesMv) {
        Objects.requireNonNull(fweekPscatSalesMv, "fweekPscatSalesMv is null");
        return Optional.ofNullable(fweekPscatSalesMv.getId()).map(FweekPscatSalesMvId::getPromoId).orElse(null);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    private FweekPscatSalesMvUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
