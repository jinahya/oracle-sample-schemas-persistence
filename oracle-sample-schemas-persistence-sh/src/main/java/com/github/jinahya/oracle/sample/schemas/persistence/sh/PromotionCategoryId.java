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

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * The {@value Promotion#COLUMN_NAME_PROMO_CATEGORY} / {@value Promotion#COLUMN_NAME_PROMO_CATEGORY_ID} pair of the
 * {@value Promotion#TABLE_NAME} table, a name and the number which identifies it, as a {@link PromotionNamedId}. The
 * two columns are named here, by the class-level {@link AttributeOverride @AttributeOverride}s, so that embedding it
 * takes an {@link jakarta.persistence.Embedded @Embedded} and nothing else. The validation constraints sit on the named
 * getters, since the fields belong to the superclass; the embedding field has to be {@code @Valid} for them to run.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Promotion
 */
@AttributeOverride(name = PromotionNamedId.ATTRIBUTE_NAME_NAME,
                   column = @Column(name = Promotion.COLUMN_NAME_PROMO_CATEGORY,
                                    nullable = false,
                                    insertable = true,
                                    updatable = true,
                                    length = Promotion.COLUMN_LENGTH_PROMO_CATEGORY))
@AttributeOverride(name = PromotionNamedId.ATTRIBUTE_NAME_ID,
                   column = @Column(name = Promotion.COLUMN_NAME_PROMO_CATEGORY_ID,
                                    nullable = false,
                                    insertable = true,
                                    updatable = true))
@Embeddable
public class PromotionCategoryId extends PromotionNamedId<Long> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified name and id.
     *
     * @param promotionCategory   the name; the {@value Promotion#COLUMN_NAME_PROMO_CATEGORY} column.
     * @param promotionCategoryId the id; the {@value Promotion#COLUMN_NAME_PROMO_CATEGORY_ID} column.
     * @return a new instance.
     */
    public static PromotionCategoryId of(final String promotionCategory, final Long promotionCategoryId) {
        return of(PromotionCategoryId::new, promotionCategory, promotionCategoryId);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public PromotionCategoryId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    /**
     * {@inheritDoc}
     *
     * @implNote Spelled with this class's attribute names, {@code promoCategory} and {@code promoCategoryId},
     * rather than the superclass's {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} and
     * {@value PromotionNamedId#ATTRIBUTE_NAME_ID}.
     */
    @Override
    public String toString() {
        return super.toString() + '{' +
               "promoCategory=" + getPromoCategory() +
               ",promoCategoryId=" + getPromoCategoryId() +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec Equality is by {@code promoCategory} and {@code promoCategoryId}, the two attributes this class
     * inherits, between instances of this class only.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof PromotionCategoryId that)) {
            return false;
        }
        return Objects.equals(getPromoCategory(), that.getPromoCategory())
               && Objects.equals(getPromoCategoryId(), that.getPromoCategoryId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getPromoCategory(), getPromoCategoryId());
    }

    // --------------------------------------------------------------------------------------------------- promoCategory

    /**
     * Returns current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_CATEGORY} column.
     *
     * @return current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute.
     */
    @Size(min = Promotion.SIZE_MIN_PROMO_CATEGORY, max = Promotion.SIZE_MAX_PROMO_CATEGORY)
    @NotNull
    public String getPromoCategory() {
        return getName();
    }

    /**
     * Replaces current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_CATEGORY} column, with the specified value.
     *
     * @param promoCategory new value for the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setPromoCategory(final String promoCategory) {
        setName(promoCategory);
    }

    // ------------------------------------------------------------------------------------------------- promoCategoryId

    /**
     * Returns current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_CATEGORY_ID} column.
     *
     * @return current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute.
     */
    @NotNull
    public Long getPromoCategoryId() {
        return getId();
    }

    /**
     * Replaces current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_CATEGORY_ID} column, with the specified value.
     *
     * @param promoCategoryId new value for the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute.
     */
    public void setPromoCategoryId(final Long promoCategoryId) {
        setId(promoCategoryId);
    }
}
