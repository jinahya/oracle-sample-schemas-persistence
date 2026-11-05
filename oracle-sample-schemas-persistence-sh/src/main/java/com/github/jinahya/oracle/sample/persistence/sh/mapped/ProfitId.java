package com.github.jinahya.oracle.sample.persistence.sh.mapped;

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


import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The id class of the {@value MappedProfit#TABLE_NAME} view, which an entity extending
 * {@link MappedProfit} names with {@link jakarta.persistence.IdClass @IdClass}.
 * <p>
 * A plain class with no columns: its attributes are the {@link jakarta.persistence.Id @Id}
 * attributes of {@link MappedProfit}, by name and by type.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ProfitId {

    /**
     * The name of the attribute which matches the {@value MappedProfit#COLUMN_NAME_CHANNEL_ID}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    /**
     * The name of the attribute which matches the {@value MappedProfit#COLUMN_NAME_CUST_ID}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    /**
     * The name of the attribute which matches the {@value MappedProfit#COLUMN_NAME_PROD_ID}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROD_ID = "prodId";

    /**
     * The name of the attribute which matches the {@value MappedProfit#COLUMN_NAME_PROMO_ID}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMO_ID = "promoId";

    /**
     * The name of the attribute which matches the {@value MappedProfit#COLUMN_NAME_TIME_ID}
     * column's {@code @Id} attribute. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_ID = "timeId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProfitId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "channelId=" + channelId +
               ",custId=" + custId +
               ",prodId=" + prodId +
               ",promoId=" + promoId +
               ",timeId=" + timeId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProfitId that)) {
            return false;
        }
        return Objects.equals(getChannelId(), that.getChannelId())
               && Objects.equals(getCustId(), that.getCustId())
               && Objects.equals(getProdId(), that.getProdId())
               && Objects.equals(getPromoId(), that.getPromoId())
               && Objects.equals(getTimeId(), that.getTimeId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getChannelId(), getCustId(), getProdId(), getPromoId(), getTimeId());
    }

    // ----------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified
     * value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    // -------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public Long getCustId() {
        return custId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified
     * value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public void setCustId(final Long custId) {
        this.custId = custId;
    }

    // -------------------------------------------------------------------------------------- prodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROD_ID} attribute with the specified
     * value.
     *
     * @param prodId new value for {@value #ATTRIBUTE_NAME_PROD_ID} attribute.
     */
    public void setProdId(final Integer prodId) {
        this.prodId = prodId;
    }

    // ------------------------------------------------------------------------------------- promoId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public Integer getPromoId() {
        return promoId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMO_ID} attribute with the specified
     * value.
     *
     * @param promoId new value for {@value #ATTRIBUTE_NAME_PROMO_ID} attribute.
     */
    public void setPromoId(final Integer promoId) {
        this.promoId = promoId;
    }

    // -------------------------------------------------------------------------------------- timeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public LocalDateTime getTimeId() {
        return timeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_ID} attribute with the specified
     * value.
     *
     * @param timeId new value for {@value #ATTRIBUTE_NAME_TIME_ID} attribute.
     */
    public void setTimeId(final LocalDateTime timeId) {
        this.timeId = timeId;
    }

    // ---------------------------------------------------------------------------------------------

    @NotNull
    private Long channelId;

    @NotNull
    private Long custId;

    @NotNull
    private Integer prodId;

    @NotNull
    private Integer promoId;

    @NotNull
    private LocalDateTime timeId;
}
