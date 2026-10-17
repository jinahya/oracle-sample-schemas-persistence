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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An entity class for mapping the {@value FweekPscatSalesMv#TABLE_NAME} materialized view, whose composite identifier
 * is mapped with an {@link jakarta.persistence.EmbeddedId @EmbeddedId}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see FweekPscatSalesMvId
 */
@NamedQuery(name = "FweekPscatSalesMv.selectOneByWeekEndingDayAndProdSubcategoryAndChannelIdAndPromoId",
            query = """
                    SELECT e
                    FROM FweekPscatSalesMv e
                    WHERE e.id.weekEndingDay = :weekEndingDay
                      AND e.id.prodSubcategory = :prodSubcategory
                      AND e.id.channelId = :channelId
                      AND e.id.promoId = :promoId"""
)
@NamedQuery(name = "FweekPscatSalesMv.selectListOrderByWeekEndingDayAscProdSubcategoryAscChannelIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM FweekPscatSalesMv e
                    ORDER BY e.id.weekEndingDay ASC,
                             e.id.prodSubcategory ASC,
                             e.id.channelId ASC,
                             e.id.promoId ASC"""
)
@NamedQuery(name = "FweekPscatSalesMv.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM FweekPscatSalesMv e"""
)
@Entity
@Table(name = FweekPscatSalesMv.TABLE_NAME)
public class FweekPscatSalesMv {

    /**
     * The name of the database materialized view to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "FWEEK_PSCAT_SALES_MV";

    // ------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

    /**
     * The name of the materialized view column to which the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_WEEK_ENDING_DAY}
     * attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY = "WEEK_ENDING_DAY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_WEEK_ENDING_DAY = "id.weekEndingDay";

    // ------------------------------------------------------------------------------------------------ PROD_SUBCATEGORY

    /**
     * The name of the materialized view column to which the
     * {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_SUBCATEGORY = "PROD_SUBCATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_SUBCATEGORY = 50;

    /**
     * The maximum size of the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_PROD_SUBCATEGORY = COLUMN_LENGTH_PROD_SUBCATEGORY;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PROD_SUBCATEGORY = "id.prodSubcategory";

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
     * The name of the materialized view column to which the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_CHANNEL_ID}
     * attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_CHANNEL_ID = "id.channelId";

    /**
     * The precision of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CHANNEL_ID = 1;

    /**
     * The scale of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CHANNEL_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_CHANNEL_ID = -9;

    /**
     * The maximum value of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_CHANNEL_ID = +9;

    /**
     * The minimum value of the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_CHANNEL_ID = COLUMN_MIN_CHANNEL_ID;

    /**
     * The maximum value of the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_CHANNEL_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_CHANNEL_ID = COLUMN_MAX_CHANNEL_ID;

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the materialized view column to which the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROMO_ID}
     * attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column -- a path into the
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, which is where the column actually lives. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PROMO_ID = "id.promoId";

    /**
     * The precision of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROMO_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROMO_ID = 0;

    /**
     * The minimum value of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_PROMO_ID = -999999;

    /**
     * The maximum value of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_PROMO_ID = +999999;

    /**
     * The minimum value of the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PROMO_ID = COLUMN_MIN_PROMO_ID;

    /**
     * The maximum value of the {@value FweekPscatSalesMvId#ATTRIBUTE_NAME_PROMO_ID} attribute. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PROMO_ID = COLUMN_MAX_PROMO_ID;

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * The name of the attribute which maps the identifying columns, as an
     * {@link jakarta.persistence.EmbeddedId @EmbeddedId}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

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
        if (!(obj instanceof FweekPscatSalesMv that)) {
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

    // --------------------------------------------------------------------------------------------------------- dollars

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DOLLARS} attribute.
     */
    public BigDecimal getDollars() {
        return dollars;
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
