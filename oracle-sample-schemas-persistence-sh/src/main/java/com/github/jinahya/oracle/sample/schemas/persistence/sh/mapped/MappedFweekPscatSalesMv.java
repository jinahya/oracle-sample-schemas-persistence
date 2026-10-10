package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedFweekPscatSalesMv#TABLE_NAME} materialized view,
 * except for its identifier.
 *
 * @param <T> the type of the identifier; a subclass maps it as it chooses, and exposes it through
 *            {@link #getIdValue()}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedFweekPscatSalesMvId
 */
@MappedSuperclass
public abstract class MappedFweekPscatSalesMv<T extends MappedFweekPscatSalesMvId> implements __MappedDomainEntity<T> {

    /**
     * The name of the database materialized view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "FWEEK_PSCAT_SALES_MV";

    // ------------------------------------------------------------------------------------------------- WEEK_ENDING_DAY

    /**
     * The name of the materialized view column to which the
     * {@value MappedFweekPscatSalesMvId#ATTRIBUTE_NAME_WEEK_ENDING_DAY} attribute of {@link MappedFweekPscatSalesMvId}
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEEK_ENDING_DAY = "WEEK_ENDING_DAY";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_WEEK_ENDING_DAY} column, for a subclass whose
     * identifier is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_WEEK_ENDING_DAY = "id.weekEndingDay";

    // ------------------------------------------------------------------------------------------------ PROD_SUBCATEGORY

    /**
     * The name of the materialized view column to which the
     * {@value MappedFweekPscatSalesMvId#ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute of {@link MappedFweekPscatSalesMvId}
     * maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_SUBCATEGORY = "PROD_SUBCATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_SUBCATEGORY = 50;

    /**
     * The maximum size of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column. The value is
     * {@value}.
     */
    public static final int SIZE_MAX_PROD_SUBCATEGORY = COLUMN_LENGTH_PROD_SUBCATEGORY;

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY} column, for a subclass whose
     * identifier is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROD_SUBCATEGORY = "id.prodSubcategory";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the materialized view column to which the
     * {@value MappedFweekPscatSalesMvId#ATTRIBUTE_NAME_CHANNEL_ID} attribute of {@link MappedFweekPscatSalesMvId} maps.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_CHANNEL_ID = "id.channelId";

    /**
     * The precision of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CHANNEL_ID = 1;

    /**
     * The scale of the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CHANNEL_ID = 0;

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the materialized view column to which the {@value MappedFweekPscatSalesMvId#ATTRIBUTE_NAME_PROMO_ID}
     * attribute of {@link MappedFweekPscatSalesMvId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The path of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column, for a subclass whose identifier
     * is an {@link EmbeddedId @EmbeddedId} named {@value #ATTRIBUTE_NAME_ID}. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID_PROMO_ID = "id.promoId";

    // ------------------------------------------------- WEEK_ENDING_DAY / PROD_SUBCATEGORY / CHANNEL_ID / PROMO_ID / id

    /**
     * The name of the identifier attribute, declared by a subclass, which maps all of the
     * {@value #COLUMN_NAME_WEEK_ENDING_DAY}, {@value #COLUMN_NAME_PROD_SUBCATEGORY}, {@value #COLUMN_NAME_CHANNEL_ID},
     * and {@value #COLUMN_NAME_PROMO_ID} columns. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    /**
     * The precision of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROMO_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROMO_ID = 0;

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedFweekPscatSalesMv() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "id=" + getIdValue() +
               ",dollars=" + dollars +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier a subclass exposes through {@link #getIdValue()}, alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedFweekPscatSalesMv<?> that)) {
            return false;
        }
        return Objects.equals(getIdValue(), that.getIdValue());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier a subclass exposes through {@link #getIdValue()}, consistent with
     * {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getIdValue());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the identifier of this row. A subclass implements this with whichever attributes it maps the identifier
     * to; {@link #equals(Object)} and {@link #hashCode()} compare by it.
     *
     * @return the identifier of this row; {@code null} if it has none yet.
     */
    @Transient
    protected abstract T getIdValue();

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

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DOLLARS, nullable = true, insertable = false, updatable = false)
    private BigDecimal dollars;
}
