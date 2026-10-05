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
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity class for mapping the {@value FweekPscatSalesMv#TABLE_NAME} materialized view, whose composite identifier
 * is mapped with an {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see FweekPscatSalesMvId
 */
@Entity
@IdClass(FweekPscatSalesMvId.class)
@Table(name = FweekPscatSalesMv.TABLE_NAME)
public class FweekPscatSalesMv {

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY = "prodSubcategory";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected FweekPscatSalesMv() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "weekEndingDay=" + weekEndingDay +
               ",prodSubcategory=" + prodSubcategory +
               ",channelId=" + channelId +
               ",promoId=" + promoId +
               ",dollars=" + dollars +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the four {@code @Id} attributes, compared as the {@link #getId() id} they make up.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof FweekPscatSalesMv that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the four {@code @Id} attributes, through {@link #getId()}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns a new {@link FweekPscatSalesMvId} holding current values of the identifying attributes.
     *
     * @return a new {@link FweekPscatSalesMvId} holding current values of the identifying attributes.
     */
    public FweekPscatSalesMvId getId() {
        return FweekPscatSalesMvId.of(getWeekEndingDay(), getProdSubcategory(), getChannelId(), getPromoId());
    }

    /**
     * Replaces current values of the identifying attributes with those of the specified identifier.
     *
     * @param id the identifier whose values are applied; may be {@code null}, which clears every identifying
     *           attribute.
     */
    protected void setId(final FweekPscatSalesMvId id) {
        setWeekEndingDay(
                Optional.ofNullable(id)
                        .map(FweekPscatSalesMvId::getWeekEndingDay)
                        .orElse(null)
        );
        setProdSubcategory(
                Optional.ofNullable(id)
                        .map(FweekPscatSalesMvId::getProdSubcategory)
                        .orElse(null)
        );
        setChannelId(
                Optional.ofNullable(id)
                        .map(FweekPscatSalesMvId::getChannelId)
                        .orElse(null)
        );
        setPromoId(
                Optional.ofNullable(id)
                        .map(FweekPscatSalesMvId::getPromoId)
                        .orElse(null)
        );
    }

    // --------------------------------------------------------------------------------------------------- weekEndingDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public LocalDate getWeekEndingDay() {
        return weekEndingDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute with the specified value.
     *
     * @param weekEndingDay new value for {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute.
     */
    public void setWeekEndingDay(final LocalDate weekEndingDay) {
        this.weekEndingDay = weekEndingDay;
    }

    // ------------------------------------------------------------------------------------------------- prodSubcategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     */
    public String getProdSubcategory() {
        return prodSubcategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute with the specified value.
     *
     * @param prodSubcategory new value for {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute.
     */
    public void setProdSubcategory(final String prodSubcategory) {
        this.prodSubcategory = prodSubcategory;
    }

    // ------------------------------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    // --------------------------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public Integer getPromoId() {
        return promoId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute with the specified value.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public void setPromoId(final Integer promoId) {
        this.promoId = promoId;
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

    @Id
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_WEEK_ENDING_DAY, nullable = false, insertable = true, updatable = false)
    private LocalDate weekEndingDay;

    @Id
    @Size(max = SIZE_MAX_PROD_SUBCATEGORY)
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_PROD_SUBCATEGORY,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_PROD_SUBCATEGORY)
    private String prodSubcategory;

    @Id
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    @Id
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOLLARS, nullable = true, insertable = false, updatable = false)
    private BigDecimal dollars;
}
