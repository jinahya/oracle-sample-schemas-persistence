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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedProduct#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedProduct {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCTS";

    // --------------------------------------------------------------------------------------------------------- PROD_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_ID = "PROD_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    // ------------------------------------------------------------------------------------------------------- PROD_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_NAME = "PROD_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_NAME = "prodName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_NAME = COLUMN_LENGTH_PROD_NAME;

    // ------------------------------------------------------------------------------------------------------- PROD_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_DESC} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_DESC = "PROD_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_DESC = 4000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_DESC} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_DESC = "prodDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_DESC = COLUMN_LENGTH_PROD_DESC;

    // ------------------------------------------------------------------------------------------------ PROD_SUBCATEGORY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute maps. The value is
     * {@value}.
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

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_SUBCATEGORY = COLUMN_LENGTH_PROD_SUBCATEGORY;

    // --------------------------------------------------------------------------------------------- PROD_SUBCATEGORY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROD_SUBCATEGORY_ID = "PROD_SUBCATEGORY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY_ID = "prodSubcategoryId";

    // ------------------------------------------------------------------------------------------- PROD_SUBCATEGORY_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_PROD_SUBCATEGORY_DESC = "PROD_SUBCATEGORY_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_SUBCATEGORY_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_SUBCATEGORY_DESC = 2000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SUBCATEGORY_DESC} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC = "prodSubcategoryDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_SUBCATEGORY_DESC = COLUMN_LENGTH_PROD_SUBCATEGORY_DESC;

    // --------------------------------------------------------------------------------------------------- PROD_CATEGORY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_CATEGORY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_CATEGORY = "PROD_CATEGORY";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_CATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_CATEGORY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_CATEGORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_CATEGORY = "prodCategory";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_CATEGORY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_CATEGORY = COLUMN_LENGTH_PROD_CATEGORY;

    // ------------------------------------------------------------------------------------------------ PROD_CATEGORY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_CATEGORY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_CATEGORY_ID = "PROD_CATEGORY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_CATEGORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_CATEGORY_ID = "prodCategoryId";

    // ---------------------------------------------------------------------------------------------- PROD_CATEGORY_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_CATEGORY_DESC} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROD_CATEGORY_DESC = "PROD_CATEGORY_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_CATEGORY_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_CATEGORY_DESC = 2000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_CATEGORY_DESC} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_CATEGORY_DESC = "prodCategoryDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_CATEGORY_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_CATEGORY_DESC = COLUMN_LENGTH_PROD_CATEGORY_DESC;

    // ----------------------------------------------------------------------------------------------- PROD_WEIGHT_CLASS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_WEIGHT_CLASS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_WEIGHT_CLASS = "PROD_WEIGHT_CLASS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_WEIGHT_CLASS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_WEIGHT_CLASS = "prodWeightClass";

    // -------------------------------------------------------------------------------------------- PROD_UNIT_OF_MEASURE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_PROD_UNIT_OF_MEASURE = "PROD_UNIT_OF_MEASURE";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_UNIT_OF_MEASURE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_UNIT_OF_MEASURE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_UNIT_OF_MEASURE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE = "prodUnitOfMeasure";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_UNIT_OF_MEASURE = COLUMN_LENGTH_PROD_UNIT_OF_MEASURE;

    // -------------------------------------------------------------------------------------------------- PROD_PACK_SIZE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_PACK_SIZE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_PACK_SIZE = "PROD_PACK_SIZE";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_PACK_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_PACK_SIZE = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_PACK_SIZE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_PACK_SIZE = "prodPackSize";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_PACK_SIZE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_PACK_SIZE = COLUMN_LENGTH_PROD_PACK_SIZE;

    // ----------------------------------------------------------------------------------------------------- SUPPLIER_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SUPPLIER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ----------------------------------------------------------------------------------------------------- PROD_STATUS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_STATUS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_STATUS = "PROD_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_STATUS = "prodStatus";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_STATUS = COLUMN_LENGTH_PROD_STATUS;

    // ------------------------------------------------------------------------------------------------- PROD_LIST_PRICE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_LIST_PRICE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_LIST_PRICE = "PROD_LIST_PRICE";

    /**
     * The precision of the {@value #COLUMN_NAME_PROD_LIST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROD_LIST_PRICE = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_PROD_LIST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROD_LIST_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_LIST_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_LIST_PRICE = "prodListPrice";

    // -------------------------------------------------------------------------------------------------- PROD_MIN_PRICE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_MIN_PRICE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_MIN_PRICE = "PROD_MIN_PRICE";

    /**
     * The precision of the {@value #COLUMN_NAME_PROD_MIN_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROD_MIN_PRICE = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_PROD_MIN_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROD_MIN_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_MIN_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_MIN_PRICE = "prodMinPrice";

    // ------------------------------------------------------------------------------------------------------ PROD_TOTAL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_TOTAL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_TOTAL = "PROD_TOTAL";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_TOTAL = 13;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_TOTAL = "prodTotal";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_TOTAL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_TOTAL = COLUMN_LENGTH_PROD_TOTAL;

    // --------------------------------------------------------------------------------------------------- PROD_TOTAL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_TOTAL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_TOTAL_ID = "PROD_TOTAL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_TOTAL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_TOTAL_ID = "prodTotalId";

    // ----------------------------------------------------------------------------------------------------- PROD_SRC_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_SRC_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_SRC_ID = "PROD_SRC_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_SRC_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_SRC_ID = "prodSrcId";

    // --------------------------------------------------------------------------------------------------- PROD_EFF_FROM

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_EFF_FROM} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_EFF_FROM = "PROD_EFF_FROM";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_EFF_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_EFF_FROM = "prodEffFrom";

    // ----------------------------------------------------------------------------------------------------- PROD_EFF_TO

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_EFF_TO} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_EFF_TO = "PROD_EFF_TO";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_EFF_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_EFF_TO = "prodEffTo";

    // ------------------------------------------------------------------------------------------------------ PROD_VALID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_PROD_VALID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PROD_VALID = "PROD_VALID";

    /**
     * The length of the {@value #COLUMN_NAME_PROD_VALID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROD_VALID = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_VALID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_VALID = "prodValid";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PROD_VALID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_PROD_VALID = COLUMN_LENGTH_PROD_VALID;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProduct() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "prodId=" + prodId +
               ",prodName=" + prodName +
               ",prodDesc=" + prodDesc +
               ",prodSubcategory=" + prodSubcategory +
               ",prodSubcategoryId=" + prodSubcategoryId +
               ",prodSubcategoryDesc=" + prodSubcategoryDesc +
               ",prodCategory=" + prodCategory +
               ",prodCategoryId=" + prodCategoryId +
               ",prodCategoryDesc=" + prodCategoryDesc +
               ",prodWeightClass=" + prodWeightClass +
               ",prodUnitOfMeasure=" + prodUnitOfMeasure +
               ",prodPackSize=" + prodPackSize +
               ",supplierId=" + supplierId +
               ",prodStatus=" + prodStatus +
               ",prodListPrice=" + prodListPrice +
               ",prodMinPrice=" + prodMinPrice +
               ",prodTotal=" + prodTotal +
               ",prodTotalId=" + prodTotalId +
               ",prodSrcId=" + prodSrcId +
               ",prodEffFrom=" + prodEffFrom +
               ",prodEffTo=" + prodEffTo +
               ",prodValid=" + prodValid +
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
        if (!(obj instanceof MappedProduct that)) {
            return false;
        }
        return Objects.equals(getProdId(), that.getProdId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getProdId());
    }

    // ---------------------------------------------------------------------------------------------------------- prodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute with the specified value.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    protected void setProdId(final Integer prodId) {
        this.prodId = prodId;
    }

    // -------------------------------------------------------------------------------------------------------- prodName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_NAME} attribute.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_NAME} attribute with the specified value.
     *
     * @param prodName new value for {@value #ATTRIBUTE_NAME_PROD_NAME} attribute.
     */
    public void setProdName(final String prodName) {
        this.prodName = prodName;
    }

    // -------------------------------------------------------------------------------------------------------- prodDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_DESC} attribute.
     */
    public String getProdDesc() {
        return prodDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_DESC} attribute with the specified value.
     *
     * @param prodDesc new value for {@value #ATTRIBUTE_NAME_PROD_DESC} attribute.
     */
    public void setProdDesc(final String prodDesc) {
        this.prodDesc = prodDesc;
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

    // ----------------------------------------------------------------------------------------------- prodSubcategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_ID} attribute.
     */
    public Long getProdSubcategoryId() {
        return prodSubcategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_ID} attribute with the specified value.
     *
     * @param prodSubcategoryId new value for {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_ID} attribute.
     */
    public void setProdSubcategoryId(final Long prodSubcategoryId) {
        this.prodSubcategoryId = prodSubcategoryId;
    }

    // --------------------------------------------------------------------------------------------- prodSubcategoryDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC} attribute.
     */
    public String getProdSubcategoryDesc() {
        return prodSubcategoryDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC} attribute with the specified value.
     *
     * @param prodSubcategoryDesc new value for {@value #ATTRIBUTE_NAME_PROD_SUBCATEGORY_DESC} attribute.
     */
    public void setProdSubcategoryDesc(final String prodSubcategoryDesc) {
        this.prodSubcategoryDesc = prodSubcategoryDesc;
    }

    // ---------------------------------------------------------------------------------------------------- prodCategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY} attribute.
     */
    public String getProdCategory() {
        return prodCategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY} attribute with the specified value.
     *
     * @param prodCategory new value for {@value #ATTRIBUTE_NAME_PROD_CATEGORY} attribute.
     */
    public void setProdCategory(final String prodCategory) {
        this.prodCategory = prodCategory;
    }

    // -------------------------------------------------------------------------------------------------- prodCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY_ID} attribute.
     */
    public Long getProdCategoryId() {
        return prodCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY_ID} attribute with the specified value.
     *
     * @param prodCategoryId new value for {@value #ATTRIBUTE_NAME_PROD_CATEGORY_ID} attribute.
     */
    public void setProdCategoryId(final Long prodCategoryId) {
        this.prodCategoryId = prodCategoryId;
    }

    // ------------------------------------------------------------------------------------------------ prodCategoryDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY_DESC} attribute.
     */
    public String getProdCategoryDesc() {
        return prodCategoryDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_CATEGORY_DESC} attribute with the specified value.
     *
     * @param prodCategoryDesc new value for {@value #ATTRIBUTE_NAME_PROD_CATEGORY_DESC} attribute.
     */
    public void setProdCategoryDesc(final String prodCategoryDesc) {
        this.prodCategoryDesc = prodCategoryDesc;
    }

    // ------------------------------------------------------------------------------------------------- prodWeightClass

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_WEIGHT_CLASS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_WEIGHT_CLASS} attribute.
     */
    public Integer getProdWeightClass() {
        return prodWeightClass;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_WEIGHT_CLASS} attribute with the specified value.
     *
     * @param prodWeightClass new value for {@value #ATTRIBUTE_NAME_PROD_WEIGHT_CLASS} attribute.
     */
    public void setProdWeightClass(final Integer prodWeightClass) {
        this.prodWeightClass = prodWeightClass;
    }

    // ----------------------------------------------------------------------------------------------- prodUnitOfMeasure

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE} attribute.
     */
    public String getProdUnitOfMeasure() {
        return prodUnitOfMeasure;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE} attribute with the specified value.
     *
     * @param prodUnitOfMeasure new value for {@value #ATTRIBUTE_NAME_PROD_UNIT_OF_MEASURE} attribute.
     */
    public void setProdUnitOfMeasure(final String prodUnitOfMeasure) {
        this.prodUnitOfMeasure = prodUnitOfMeasure;
    }

    // ---------------------------------------------------------------------------------------------------- prodPackSize

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_PACK_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_PACK_SIZE} attribute.
     */
    public String getProdPackSize() {
        return prodPackSize;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_PACK_SIZE} attribute with the specified value.
     *
     * @param prodPackSize new value for {@value #ATTRIBUTE_NAME_PROD_PACK_SIZE} attribute.
     */
    public void setProdPackSize(final String prodPackSize) {
        this.prodPackSize = prodPackSize;
    }

    // ------------------------------------------------------------------------------------------------------ supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    public void setSupplierId(final Integer supplierId) {
        this.supplierId = supplierId;
    }

    // ------------------------------------------------------------------------------------------------------ prodStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_STATUS} attribute.
     */
    public String getProdStatus() {
        return prodStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_STATUS} attribute with the specified value.
     *
     * @param prodStatus new value for {@value #ATTRIBUTE_NAME_PROD_STATUS} attribute.
     */
    public void setProdStatus(final String prodStatus) {
        this.prodStatus = prodStatus;
    }

    // --------------------------------------------------------------------------------------------------- prodListPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_LIST_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_LIST_PRICE} attribute.
     */
    public BigDecimal getProdListPrice() {
        return prodListPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_LIST_PRICE} attribute with the specified value.
     *
     * @param prodListPrice new value for {@value #ATTRIBUTE_NAME_PROD_LIST_PRICE} attribute.
     */
    public void setProdListPrice(final BigDecimal prodListPrice) {
        this.prodListPrice = prodListPrice;
    }

    // ---------------------------------------------------------------------------------------------------- prodMinPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_MIN_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_MIN_PRICE} attribute.
     */
    public BigDecimal getProdMinPrice() {
        return prodMinPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_MIN_PRICE} attribute with the specified value.
     *
     * @param prodMinPrice new value for {@value #ATTRIBUTE_NAME_PROD_MIN_PRICE} attribute.
     */
    public void setProdMinPrice(final BigDecimal prodMinPrice) {
        this.prodMinPrice = prodMinPrice;
    }

    // ------------------------------------------------------------------------------------------------------- prodTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_TOTAL} attribute.
     */
    public String getProdTotal() {
        return prodTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_TOTAL} attribute with the specified value.
     *
     * @param prodTotal new value for {@value #ATTRIBUTE_NAME_PROD_TOTAL} attribute.
     */
    public void setProdTotal(final String prodTotal) {
        this.prodTotal = prodTotal;
    }

    // ----------------------------------------------------------------------------------------------------- prodTotalId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_TOTAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_TOTAL_ID} attribute.
     */
    public Long getProdTotalId() {
        return prodTotalId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_TOTAL_ID} attribute with the specified value.
     *
     * @param prodTotalId new value for {@value #ATTRIBUTE_NAME_PROD_TOTAL_ID} attribute.
     */
    public void setProdTotalId(final Long prodTotalId) {
        this.prodTotalId = prodTotalId;
    }

    // ------------------------------------------------------------------------------------------------------- prodSrcId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_SRC_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_SRC_ID} attribute.
     */
    public Long getProdSrcId() {
        return prodSrcId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_SRC_ID} attribute with the specified value.
     *
     * @param prodSrcId new value for {@value #ATTRIBUTE_NAME_PROD_SRC_ID} attribute.
     */
    public void setProdSrcId(final Long prodSrcId) {
        this.prodSrcId = prodSrcId;
    }

    // ----------------------------------------------------------------------------------------------------- prodEffFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_EFF_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_EFF_FROM} attribute.
     */
    public LocalDate getProdEffFrom() {
        return prodEffFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_EFF_FROM} attribute with the specified value.
     *
     * @param prodEffFrom new value for {@value #ATTRIBUTE_NAME_PROD_EFF_FROM} attribute.
     */
    public void setProdEffFrom(final LocalDate prodEffFrom) {
        this.prodEffFrom = prodEffFrom;
    }

    // ------------------------------------------------------------------------------------------------------- prodEffTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_EFF_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_EFF_TO} attribute.
     */
    public LocalDate getProdEffTo() {
        return prodEffTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_EFF_TO} attribute with the specified value.
     *
     * @param prodEffTo new value for {@value #ATTRIBUTE_NAME_PROD_EFF_TO} attribute.
     */
    public void setProdEffTo(final LocalDate prodEffTo) {
        this.prodEffTo = prodEffTo;
    }

    // ------------------------------------------------------------------------------------------------------- prodValid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_VALID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_VALID} attribute.
     */
    public String getProdValid() {
        return prodValid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_VALID} attribute with the specified value.
     *
     * @param prodValid new value for {@value #ATTRIBUTE_NAME_PROD_VALID} attribute.
     */
    public void setProdValid(final String prodValid) {
        this.prodValid = prodValid;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_PROD_ID, nullable = false, insertable = true, updatable = false)
    private Integer prodId;

    @Size(max = SIZE_MAX_PROD_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_NAME
    )
    private String prodName;

    @Size(max = SIZE_MAX_PROD_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_DESC
    )
    private String prodDesc;

    @Size(max = SIZE_MAX_PROD_SUBCATEGORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_SUBCATEGORY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_SUBCATEGORY
    )
    private String prodSubcategory;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_SUBCATEGORY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long prodSubcategoryId;

    @Size(max = SIZE_MAX_PROD_SUBCATEGORY_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_SUBCATEGORY_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_SUBCATEGORY_DESC
    )
    private String prodSubcategoryDesc;

    @Size(max = SIZE_MAX_PROD_CATEGORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_CATEGORY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_CATEGORY
    )
    private String prodCategory;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_CATEGORY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long prodCategoryId;

    @Size(max = SIZE_MAX_PROD_CATEGORY_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_CATEGORY_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_CATEGORY_DESC
    )
    private String prodCategoryDesc;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_WEIGHT_CLASS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer prodWeightClass;

    @Size(max = SIZE_MAX_PROD_UNIT_OF_MEASURE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROD_UNIT_OF_MEASURE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_UNIT_OF_MEASURE
    )
    private String prodUnitOfMeasure;

    @Size(max = SIZE_MAX_PROD_PACK_SIZE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_PACK_SIZE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_PACK_SIZE
    )
    private String prodPackSize;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer supplierId;

    @Size(max = SIZE_MAX_PROD_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_STATUS
    )
    private String prodStatus;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_LIST_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PROD_LIST_PRICE,
            scale = COLUMN_SCALE_PROD_LIST_PRICE
    )
    private BigDecimal prodListPrice;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_MIN_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PROD_MIN_PRICE,
            scale = COLUMN_SCALE_PROD_MIN_PRICE
    )
    private BigDecimal prodMinPrice;

    @Size(max = SIZE_MAX_PROD_TOTAL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_TOTAL
    )
    private String prodTotal;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROD_TOTAL_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long prodTotalId;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROD_SRC_ID,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long prodSrcId;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROD_EFF_FROM,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate prodEffFrom;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROD_EFF_TO,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate prodEffTo;

    @Size(max = SIZE_MAX_PROD_VALID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROD_VALID,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PROD_VALID
    )
    private String prodValid;
}
