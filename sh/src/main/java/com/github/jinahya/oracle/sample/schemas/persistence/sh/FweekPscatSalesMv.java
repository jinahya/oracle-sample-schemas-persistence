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

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A class for the {@value FweekPscatSalesMv#TABLE_NAME} materialized view.
 * <p>
 * A materialized view carries no primary key, so this is not an {@link jakarta.persistence.Entity @Entity}: the columns it projects
 * are written out here, and every one of them is read-only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class FweekPscatSalesMv {

    /**
     * The name of the database materialized view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "FWEEK_PSCAT_SALES_MV";

    // -------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY = "WEEK_ENDING_DAY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK_ENDING_DAY = "weekEndingDay";

    // ------------------------------------------------------------------------------------------------- PROD_SUBCATEGORY

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_SUBCATEGORY = "PROD_SUBCATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_SUBCATEGORY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY = "prodSubcategory";

    // ---------------------------------------------------------------------------------------------------------- DOLLARS

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_DOLLARS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DOLLARS = "DOLLARS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DOLLARS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DOLLARS = "dollars";

    // ------------------------------------------------------------------------------------------------------- CHANNEL_ID

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // --------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the materialized view column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is {@value}.
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
               ",dollars=" + dollars +
               ",channelId=" + channelId +
               ",promoId=" + promoId +
               '}';
    }

    // ---------------------------------------------------------------------------------------------------- weekEndingDay

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

    // -------------------------------------------------------------------------------------------------- prodSubcategory

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

    // ---------------------------------------------------------------------------------------------------------- dollars

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

    // -------------------------------------------------------------------------------------------------------- channelId

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

    // ---------------------------------------------------------------------------------------------------------- promoId

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


    // -----------------------------------------------------------------------------------------------------------------

    @Column(name = COLUMN_NAME_WEEK_ENDING_DAY, insertable = false, updatable = false)
    private LocalDate weekEndingDay;

    @Column(name = COLUMN_NAME_PROD_SUBCATEGORY, insertable = false, updatable = false, length = COLUMN_LENGTH_PROD_SUBCATEGORY)
    private String prodSubcategory;

    @Column(name = COLUMN_NAME_DOLLARS, insertable = false, updatable = false)
    private BigDecimal dollars;

    @Column(name = COLUMN_NAME_CHANNEL_ID, insertable = false, updatable = false)
    private Long channelId;

    @Column(name = COLUMN_NAME_PROMO_ID, insertable = false, updatable = false)
    private Integer promoId;
}
