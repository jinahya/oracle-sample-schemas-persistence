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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedProfit;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * An entity class for mapping the {@value Profit#TABLE_NAME} view, whose composite identifier is mapped with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProfitId
 */
@Entity
@IdClass(ProfitId.class)
@Table(name = Profit.TABLE_NAME)
public class Profit extends MappedProfit implements __DomainEntity<ProfitId> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Profit() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns a new {@link ProfitId} holding current values of the identifying attributes.
     *
     * @return a new {@link ProfitId} holding current values of the identifying attributes.
     */
    public ProfitId getId() {
        return ProfitId.of(getChannelId(), getCustId(), getProdId(), getPromoId(), getTimeId());
    }

    /**
     * Replaces current values of the identifying attributes with those of the specified identifier.
     *
     * @param id the identifier whose values are applied; may be {@code null}, which clears every identifying
     *           attribute.
     */
    protected void setId(final ProfitId id) {
        setChannelId(
                Optional.ofNullable(id)
                        .map(ProfitId::getChannelId)
                        .orElse(null)
        );
        setCustId(
                Optional.ofNullable(id)
                        .map(ProfitId::getCustId)
                        .orElse(null)
        );
        setProdId(
                Optional.ofNullable(id)
                        .map(ProfitId::getProdId)
                        .orElse(null)
        );
        setPromoId(
                Optional.ofNullable(id)
                        .map(ProfitId::getPromoId)
                        .orElse(null)
        );
        setTimeId(
                Optional.ofNullable(id)
                        .map(ProfitId::getTimeId)
                        .orElse(null)
        );
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    @Override
    public void setChannelId(final Long channelId) {
        super.setChannelId(channelId);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    @Override
    public void setCustId(final Long custId) {
        super.setCustId(custId);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute with the specified value.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    @Override
    public void setProdId(final Integer prodId) {
        super.setProdId(prodId);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute with the specified value.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    @Override
    public void setPromoId(final Integer promoId) {
        super.setPromoId(promoId);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    @Override
    public void setTimeId(final LocalDateTime timeId) {
        super.setTimeId(timeId);
    }
}
