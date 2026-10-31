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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedSalesId;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * An embeddable class for the composite identifier of the {@link Sales} entity class, which maps it with an
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class SalesId extends MappedSalesId {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified column values.
     *
     * @param prodId    the {@value Sales#COLUMN_NAME_PROD_ID} column value.
     * @param custId    the {@value Sales#COLUMN_NAME_CUST_ID} column value.
     * @param timeId    the {@value Sales#COLUMN_NAME_TIME_ID} column value.
     * @param channelId the {@value Sales#COLUMN_NAME_CHANNEL_ID} column value.
     * @param promoId   the {@value Sales#COLUMN_NAME_PROMO_ID} column value.
     * @return a new instance with the specified column values.
     */
    public static SalesId of(final Integer prodId,
                            final Long custId,
                            final LocalDateTime timeId,
                            final Long channelId,
                            final Integer promoId) {
        return of(SalesId::new, prodId, custId, timeId, channelId, promoId);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public SalesId() {
        super();
    }
}
