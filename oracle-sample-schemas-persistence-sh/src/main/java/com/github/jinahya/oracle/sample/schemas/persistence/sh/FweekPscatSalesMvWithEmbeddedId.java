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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value FweekPscatSalesMvWithEmbeddedId#TABLE_NAME} materialized view, whose
 * composite identifier is mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see FweekPscatSalesMvWithIdClass
 */
@Entity
@Table(name = FweekPscatSalesMvWithEmbeddedId.TABLE_NAME)
public class FweekPscatSalesMvWithEmbeddedId {

    /**
     * The name of the database materialized view to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "FWEEK_PSCAT_SALES_MV";

    // ------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY = "WEEK_ENDING_DAY";

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY}
     * column; this entity reaches it as {@value #ATTRIBUTE_NAME_ID_WEEK_ENDING_DAY}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_WEEK_ENDING_DAY = "id.weekEndingDay";

    // ------------------------------------------------------------------------------------------------ PROD_SUBCATEGORY

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_SUBCATEGORY = "PROD_SUBCATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_SUBCATEGORY = 50;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_SUBCATEGORY = COLUMN_LENGTH_PROD_SUBCATEGORY;

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY}
     * column; this entity reaches it as {@value #ATTRIBUTE_NAME_ID_PROD_SUBCATEGORY}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY = "prodSubcategory";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROD_SUBCATEGORY = "id.prodSubcategory";

    // --------------------------------------------------------------------------------------------------------- DOLLARS

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_DOLLARS} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_DOLLARS = "DOLLARS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOLLARS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOLLARS = "dollars";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column;
     * this entity reaches it as {@value #ATTRIBUTE_NAME_ID_CHANNEL_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CHANNEL_ID = "id.channelId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the {@link FweekPscatSalesMvId} attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column; this
     * entity reaches it as {@value #ATTRIBUTE_NAME_ID_PROMO_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROMO_ID = "id.promoId";

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected FweekPscatSalesMvWithEmbeddedId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
               ",dollars=" + dollars +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof FweekPscatSalesMvWithEmbeddedId that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
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
    public LocalDate getWeekEndingDay() {
        return Optional.ofNullable(getId()).map(FweekPscatSalesMvId::getWeekEndingDay).orElse(null);
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute of the {@link #getId() id} with the
     * specified value, setting a new id first if there is none.
     *
     * @param weekEndingDay new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    protected void setWeekEndingDay(final LocalDate weekEndingDay) {
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
    protected void setProdSubcategory(final String prodSubcategory) {
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
    protected void setChannelId(final Long channelId) {
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
    protected void setPromoId(final Integer promoId) {
        if (getId() == null) {
            setId(new FweekPscatSalesMvId());
        }
        getId().setPromoId(promoId);
    }

    // --------------------------------------------------------------------------------------------------------- dollars

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public BigDecimal getDollars() {
        return dollars;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute with the specified value.
     *
     * @param dollars new value for {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public void setDollars(final BigDecimal dollars) {
        this.dollars = dollars;
    }

    // ---------------------------------------------------------------------------------------------------------------- 

    @Valid
    @NotNull
    @EmbeddedId
    private FweekPscatSalesMvId id;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOLLARS, nullable = true, insertable = false, updatable = false)
    private BigDecimal dollars;
}
