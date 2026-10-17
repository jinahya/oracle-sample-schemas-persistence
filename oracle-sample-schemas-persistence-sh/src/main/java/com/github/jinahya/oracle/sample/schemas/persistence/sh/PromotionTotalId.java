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
 * The {@value Promotion#COLUMN_NAME_PROMO_TOTAL} / {@value Promotion#COLUMN_NAME_PROMO_TOTAL_ID} pair of the
 * {@value Promotion#TABLE_NAME} table, a name and the number which identifies it, as a {@link PromotionNamedId}. The
 * two columns are named here, by the class-level {@link AttributeOverride @AttributeOverride}s, so that embedding it
 * takes an {@link jakarta.persistence.Embedded @Embedded} and nothing else. The validation constraints sit on the
 * named getters, since the fields belong to the superclass; the embedding field has to be {@code @Valid} for them to
 * run.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Promotion
 */
@AttributeOverride(name = PromotionNamedId.ATTRIBUTE_NAME_NAME,
                   column = @Column(name = Promotion.COLUMN_NAME_PROMO_TOTAL,
                                    nullable = false,
                                    insertable = true,
                                    updatable = true,
                                    length = Promotion.COLUMN_LENGTH_PROMO_TOTAL))
@AttributeOverride(name = PromotionNamedId.ATTRIBUTE_NAME_ID,
                   column = @Column(name = Promotion.COLUMN_NAME_PROMO_TOTAL_ID,
                                    nullable = false,
                                    insertable = true,
                                    updatable = true))
@Embeddable
public class PromotionTotalId extends PromotionNamedId<Long> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with the specified name and id.
     *
     * @param promotionTotal   the name; the {@value Promotion#COLUMN_NAME_PROMO_TOTAL} column.
     * @param promotionTotalId the id; the {@value Promotion#COLUMN_NAME_PROMO_TOTAL_ID} column.
     * @return a new instance.
     */
    public static PromotionTotalId of(final String promotionTotal, final Long promotionTotalId) {
        return of(PromotionTotalId::new, promotionTotal, promotionTotalId);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public PromotionTotalId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    /**
     * {@inheritDoc}
     *
     * @implNote Spelled with this class's attribute names, {@code promoTotal} and {@code promoTotalId},
     * rather than the superclass's {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} and
     * {@value PromotionNamedId#ATTRIBUTE_NAME_ID}.
     */
    @Override
    public String toString() {
        return super.toString() + '{' +
               "promoTotal=" + getPromoTotal() +
               ",promoTotalId=" + getPromoTotalId() +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec Equality is by {@code promoTotal} and {@code promoTotalId}, the two attributes this class
     * inherits, between instances of this class only.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof PromotionTotalId that)) {
            return false;
        }
        return Objects.equals(getPromoTotal(), that.getPromoTotal())
               && Objects.equals(getPromoTotalId(), that.getPromoTotalId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getPromoTotal(), getPromoTotalId());
    }

    // ------------------------------------------------------------------------------------------------------ promoTotal

    /**
     * Returns current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_TOTAL} column.
     *
     * @return current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute.
     */
    @Size(max = Promotion.SIZE_MAX_PROMO_TOTAL)
    @NotNull
    public String getPromoTotal() {
        return getName();
    }

    /**
     * Replaces current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_TOTAL} column, with the specified value.
     *
     * @param promoTotal new value for the {@value PromotionNamedId#ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setPromoTotal(final String promoTotal) {
        setName(promoTotal);
    }

    // ---------------------------------------------------------------------------------------------------- promoTotalId

    /**
     * Returns current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_TOTAL_ID} column.
     *
     * @return current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute.
     */
    @NotNull
    public Long getPromoTotalId() {
        return getId();
    }

    /**
     * Replaces current value of the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute, the
     * {@value Promotion#COLUMN_NAME_PROMO_TOTAL_ID} column, with the specified value.
     *
     * @param promoTotalId new value for the {@value PromotionNamedId#ATTRIBUTE_NAME_ID} attribute.
     */
    public void setPromoTotalId(final Long promoTotalId) {
        setId(promoTotalId);
    }
}
