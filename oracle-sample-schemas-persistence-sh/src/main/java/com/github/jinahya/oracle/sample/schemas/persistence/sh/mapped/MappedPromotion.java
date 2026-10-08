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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedPromotion#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedPromotion {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PROMOTIONS";

    // -------------------------------------------------------------------------------------------------------- PROMO_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_ID = "PROMO_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    /**
     * The precision of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROMO_ID = 6;

    /**
     * The scale of the {@value #COLUMN_NAME_PROMO_ID} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROMO_ID = 0;

    // ------------------------------------------------------------------------------------------------------ PROMO_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_NAME = "PROMO_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PROMO_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROMO_NAME = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_NAME = "promoName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROMO_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROMO_NAME = COLUMN_LENGTH_PROMO_NAME;

    // ----------------------------------------------------------------------------------------------- PROMO_SUBCATEGORY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_SUBCATEGORY = "PROMO_SUBCATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROMO_SUBCATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROMO_SUBCATEGORY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_SUBCATEGORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_SUBCATEGORY = "promoSubcategory";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROMO_SUBCATEGORY = COLUMN_LENGTH_PROMO_SUBCATEGORY;

    // -------------------------------------------------------------------------------------------- PROMO_SUBCATEGORY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROMO_SUBCATEGORY_ID = "PROMO_SUBCATEGORY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_SUBCATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_SUBCATEGORY_ID = "promoSubcategoryId";

    // -------------------------------------------------------------------------------------------------- PROMO_CATEGORY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_CATEGORY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_CATEGORY = "PROMO_CATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROMO_CATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROMO_CATEGORY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_CATEGORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_CATEGORY = "promoCategory";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROMO_CATEGORY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROMO_CATEGORY = COLUMN_LENGTH_PROMO_CATEGORY;

    // ----------------------------------------------------------------------------------------------- PROMO_CATEGORY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_CATEGORY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_CATEGORY_ID = "PROMO_CATEGORY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_CATEGORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_CATEGORY_ID = "promoCategoryId";

    // ------------------------------------------------------------------------------------------------------ PROMO_COST

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_COST} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_COST = "PROMO_COST";

    /**
     * The precision of the {@value #COLUMN_NAME_PROMO_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROMO_COST = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_PROMO_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROMO_COST = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_COST = "promoCost";

    // ------------------------------------------------------------------------------------------------ PROMO_BEGIN_DATE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_BEGIN_DATE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_BEGIN_DATE = "PROMO_BEGIN_DATE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_BEGIN_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_BEGIN_DATE = "promoBeginDate";

    // -------------------------------------------------------------------------------------------------- PROMO_END_DATE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_END_DATE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_END_DATE = "PROMO_END_DATE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_END_DATE = "promoEndDate";

    // ----------------------------------------------------------------------------------------------------- PROMO_TOTAL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_TOTAL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_TOTAL = "PROMO_TOTAL";

    /**
     * The length of the {@value #COLUMN_NAME_PROMO_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROMO_TOTAL = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_TOTAL = "promoTotal";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROMO_TOTAL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROMO_TOTAL = COLUMN_LENGTH_PROMO_TOTAL;

    // -------------------------------------------------------------------------------------------------- PROMO_TOTAL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROMO_TOTAL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROMO_TOTAL_ID = "PROMO_TOTAL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_TOTAL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_TOTAL_ID = "promoTotalId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPromotion() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "promoId=" + promoId +
               ",promoName=" + promoName +
               ",promoSubcategory=" + promoSubcategory +
               ",promoSubcategoryId=" + promoSubcategoryId +
               ",promoCategory=" + promoCategory +
               ",promoCategoryId=" + promoCategoryId +
               ",promoCost=" + promoCost +
               ",promoBeginDate=" + promoBeginDate +
               ",promoEndDate=" + promoEndDate +
               ",promoTotal=" + promoTotal +
               ",promoTotalId=" + promoTotalId +
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
        if (!(obj instanceof MappedPromotion that)) {
            return false;
        }
        return Objects.equals(getPromoId(), that.getPromoId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getPromoId());
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
    protected void setPromoId(final Integer promoId) {
        this.promoId = promoId;
    }

    // ------------------------------------------------------------------------------------------------------- promoName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_NAME} attribute.
     */
    public String getPromoName() {
        return promoName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_NAME} attribute with the specified value.
     *
     * @param promoName new value for {@value #ATTRIBUTE_NAME_PROMO_NAME} attribute.
     */
    public void setPromoName(final String promoName) {
        this.promoName = promoName;
    }

    // ------------------------------------------------------------------------------------------------ promoSubcategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY} attribute.
     */
    public String getPromoSubcategory() {
        return promoSubcategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY} attribute with the specified value.
     *
     * @param promoSubcategory new value for {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY} attribute.
     */
    public void setPromoSubcategory(final String promoSubcategory) {
        this.promoSubcategory = promoSubcategory;
    }

    // ---------------------------------------------------------------------------------------------- promoSubcategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY_ID} attribute.
     */
    public Long getPromoSubcategoryId() {
        return promoSubcategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY_ID} attribute with the specified value.
     *
     * @param promoSubcategoryId new value for {@value #ATTRIBUTE_NAME_PROMO_SUBCATEGORY_ID} attribute.
     */
    public void setPromoSubcategoryId(final Long promoSubcategoryId) {
        this.promoSubcategoryId = promoSubcategoryId;
    }

    // --------------------------------------------------------------------------------------------------- promoCategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_CATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_CATEGORY} attribute.
     */
    public String getPromoCategory() {
        return promoCategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_CATEGORY} attribute with the specified value.
     *
     * @param promoCategory new value for {@value #ATTRIBUTE_NAME_PROMO_CATEGORY} attribute.
     */
    public void setPromoCategory(final String promoCategory) {
        this.promoCategory = promoCategory;
    }

    // ------------------------------------------------------------------------------------------------- promoCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_CATEGORY_ID} attribute.
     */
    public Long getPromoCategoryId() {
        return promoCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_CATEGORY_ID} attribute with the specified value.
     *
     * @param promoCategoryId new value for {@value #ATTRIBUTE_NAME_PROMO_CATEGORY_ID} attribute.
     */
    public void setPromoCategoryId(final Long promoCategoryId) {
        this.promoCategoryId = promoCategoryId;
    }

    // ------------------------------------------------------------------------------------------------------- promoCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_COST} attribute.
     */
    public BigDecimal getPromoCost() {
        return promoCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_COST} attribute with the specified value.
     *
     * @param promoCost new value for {@value #ATTRIBUTE_NAME_PROMO_COST} attribute.
     */
    public void setPromoCost(final BigDecimal promoCost) {
        this.promoCost = promoCost;
    }

    // -------------------------------------------------------------------------------------------------- promoBeginDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_BEGIN_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_BEGIN_DATE} attribute.
     */
    public LocalDateTime getPromoBeginDate() {
        return promoBeginDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_BEGIN_DATE} attribute with the specified value.
     *
     * @param promoBeginDate new value for {@value #ATTRIBUTE_NAME_PROMO_BEGIN_DATE} attribute.
     */
    public void setPromoBeginDate(final LocalDateTime promoBeginDate) {
        this.promoBeginDate = promoBeginDate;
    }

    // ---------------------------------------------------------------------------------------------------- promoEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_END_DATE} attribute.
     */
    public LocalDateTime getPromoEndDate() {
        return promoEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_END_DATE} attribute with the specified value.
     *
     * @param promoEndDate new value for {@value #ATTRIBUTE_NAME_PROMO_END_DATE} attribute.
     */
    public void setPromoEndDate(final LocalDateTime promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    // ------------------------------------------------------------------------------------------------------ promoTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_TOTAL} attribute.
     */
    public String getPromoTotal() {
        return promoTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_TOTAL} attribute with the specified value.
     *
     * @param promoTotal new value for {@value #ATTRIBUTE_NAME_PROMO_TOTAL} attribute.
     */
    public void setPromoTotal(final String promoTotal) {
        this.promoTotal = promoTotal;
    }

    // ---------------------------------------------------------------------------------------------------- promoTotalId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_TOTAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_TOTAL_ID} attribute.
     */
    public Long getPromoTotalId() {
        return promoTotalId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_TOTAL_ID} attribute with the specified value.
     *
     * @param promoTotalId new value for {@value #ATTRIBUTE_NAME_PROMO_TOTAL_ID} attribute.
     */
    public void setPromoTotalId(final Long promoTotalId) {
        this.promoTotalId = promoTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Digits(integer = COLUMN_PRECISION_PROMO_ID - COLUMN_SCALE_PROMO_ID, fraction = COLUMN_SCALE_PROMO_ID)
    @Column(name = COLUMN_NAME_PROMO_ID, nullable = false, insertable = true, updatable = false)
    private Integer promoId;

    @Size(max = SIZE_MAX_PROMO_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROMO_NAME
    )
    private String promoName;

    @Size(max = SIZE_MAX_PROMO_SUBCATEGORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_SUBCATEGORY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROMO_SUBCATEGORY
    )
    private String promoSubcategory;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_SUBCATEGORY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long promoSubcategoryId;

    @Size(max = SIZE_MAX_PROMO_CATEGORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_CATEGORY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROMO_CATEGORY
    )
    private String promoCategory;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_CATEGORY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long promoCategoryId;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_PROMO_COST - COLUMN_SCALE_PROMO_COST, fraction = COLUMN_SCALE_PROMO_COST)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_COST,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PROMO_COST,
            scale = COLUMN_SCALE_PROMO_COST
    )
    private BigDecimal promoCost;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_BEGIN_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime promoBeginDate;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_END_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime promoEndDate;

    @Size(max = SIZE_MAX_PROMO_TOTAL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROMO_TOTAL
    )
    private String promoTotal;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMO_TOTAL_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long promoTotalId;
}
