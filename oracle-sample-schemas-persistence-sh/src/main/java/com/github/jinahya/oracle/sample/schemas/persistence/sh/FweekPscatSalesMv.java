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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedFweekPscatSalesMv;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * An entity class for mapping the {@value FweekPscatSalesMv#TABLE_NAME} materialized view, whose composite identifier
 * is mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see FweekPscatSalesMvId
 */
@Entity
@Table(name = FweekPscatSalesMv.TABLE_NAME)
public class FweekPscatSalesMv extends MappedFweekPscatSalesMv<FweekPscatSalesMvId> implements __DomainEntity<FweekPscatSalesMvId> {

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY}
     * column; this entity reaches it as {@value #ATTRIBUTE_NAME_ID_WEEK_ENDING_DAY}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY}
     * column; this entity reaches it as {@value #ATTRIBUTE_NAME_ID_PROD_SUBCATEGORY}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY = "prodSubcategory";

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column;
     * this entity reaches it as {@value #ATTRIBUTE_NAME_ID_CHANNEL_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column; this
     * entity reaches it as {@value #ATTRIBUTE_NAME_ID_PROMO_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected FweekPscatSalesMv() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public FweekPscatSalesMvId getId() {
        return id;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ID} attribute with the specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public void setId(final FweekPscatSalesMvId id) {
        this.id = id;
    }

    // --------------------------------------------------------------------------------------------------- weekEndingDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute of the {@link #getId() id};
     * {@code null} if the id is {@code null}.
     */
    public LocalDateTime getWeekEndingDay() {
        return Optional.ofNullable(getId()).map(FweekPscatSalesMvId::getWeekEndingDay).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param weekEndingDay new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public void setWeekEndingDay(final LocalDateTime weekEndingDay) {
        if (getId() == null) {
            setId(new FweekPscatSalesMvId());
        }
        getId().setWeekEndingDay(weekEndingDay);
    }

    // ------------------------------------------------------------------------------------------------- prodSubcategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute of the {@link #getId() id};
     * {@code null} if the id is {@code null}.
     */
    public String getProdSubcategory() {
        return Optional.ofNullable(getId()).map(FweekPscatSalesMvId::getProdSubcategory).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param prodSubcategory new value for {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     */
    public void setProdSubcategory(final String prodSubcategory) {
        if (getId() == null) {
            setId(new FweekPscatSalesMvId());
        }
        getId().setProdSubcategory(prodSubcategory);
    }

    // ------------------------------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute of the {@link #getId() id}; {@code null}
     * if the id is {@code null}.
     */
    public Long getChannelId() {
        return Optional.ofNullable(getId()).map(FweekPscatSalesMvId::getChannelId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public void setChannelId(final Long channelId) {
        if (getId() == null) {
            setId(new FweekPscatSalesMvId());
        }
        getId().setChannelId(channelId);
    }

    // --------------------------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute of the {@link #getId() id}.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute of the {@link #getId() id}; {@code null} if
     * the id is {@code null}.
     */
    public Integer getPromoId() {
        return Optional.ofNullable(getId()).map(FweekPscatSalesMvId::getPromoId).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public void setPromoId(final Integer promoId) {
        if (getId() == null) {
            setId(new FweekPscatSalesMvId());
        }
        getId().setPromoId(promoId);
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    @Valid
    @NotNull
    @EmbeddedId
    private FweekPscatSalesMvId id;

    // ------------------------------------------------------------------------------------------------------ getIdValue

    @Override
    protected FweekPscatSalesMvId getIdValue() {
        return getId();
    }
}
