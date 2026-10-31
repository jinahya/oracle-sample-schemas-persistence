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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCost;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.util.Optional;

/**
 * An entity class for mapping the {@value Cost#TABLE_NAME} table, whose composite identifier is mapped with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 * <p>
 * The table declares no primary key; the four dimension columns are its grain, and the {@code CANDIDATE_KEYS} section
 * of {@code src/test/sql/COSTS.sql} is what measured them.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see CostId
 */
@NamedQuery(name = "Cost.selectOneByProdIdAndTimeIdAndPromoIdAndChannelId",
            query = """
                    SELECT e
                    FROM Cost e
                    WHERE e.prodId = :prodId
                      AND e.timeId = :timeId
                      AND e.promoId = :promoId
                      AND e.channelId = :channelId"""
)
@NamedQuery(name = "Cost.selectListOrderByProdIdAscTimeIdAscPromoIdAscChannelIdAsc",
            query = """
                    SELECT e
                    FROM Cost e
                    ORDER BY e.prodId ASC, e.timeId ASC, e.promoId ASC, e.channelId ASC"""
)
@NamedQuery(name = "Cost.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Cost e"""
)
@NamedQuery(name = "Cost.selectListByProductOrderByTimeIdAscPromoIdAscChannelIdAsc",
            query = """
                    SELECT e
                    FROM Cost e
                    WHERE e.product = :product
                    ORDER BY e.timeId ASC, e.promoId ASC, e.channelId ASC"""
)
@NamedQuery(name = "Cost.countByProduct",
            query = """
                    SELECT COUNT(e)
                    FROM Cost e
                    WHERE e.product = :product"""
)
@NamedQuery(name = "Cost.selectListByTimeOrderByProdIdAscPromoIdAscChannelIdAsc",
            query = """
                    SELECT e
                    FROM Cost e
                    WHERE e.time = :time
                    ORDER BY e.prodId ASC, e.promoId ASC, e.channelId ASC"""
)
@NamedQuery(name = "Cost.countByTime",
            query = """
                    SELECT COUNT(e)
                    FROM Cost e
                    WHERE e.time = :time"""
)
@NamedQuery(name = "Cost.selectListByPromotionOrderByProdIdAscTimeIdAscChannelIdAsc",
            query = """
                    SELECT e
                    FROM Cost e
                    WHERE e.promotion = :promotion
                    ORDER BY e.prodId ASC, e.timeId ASC, e.channelId ASC"""
)
@NamedQuery(name = "Cost.countByPromotion",
            query = """
                    SELECT COUNT(e)
                    FROM Cost e
                    WHERE e.promotion = :promotion"""
)
@NamedQuery(name = "Cost.selectListByChannelOrderByProdIdAscTimeIdAscPromoIdAsc",
            query = """
                    SELECT e
                    FROM Cost e
                    WHERE e.channel = :channel
                    ORDER BY e.prodId ASC, e.timeId ASC, e.promoId ASC"""
)
@NamedQuery(name = "Cost.countByChannel",
            query = """
                    SELECT COUNT(e)
                    FROM Cost e
                    WHERE e.channel = :channel"""
)
@Entity
@IdClass(CostId.class)
@Table(name = MappedCost.TABLE_NAME)
public class Cost extends MappedCost {

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROD_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME = "time";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMO_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION = "promotion";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column as a
     * {@link jakarta.persistence.ManyToOne @ManyToOne} association. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL = "channel";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Cost() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns a new {@link CostId} holding current values of the identifying attributes.
     *
     * @return a new {@link CostId} holding current values of the identifying attributes.
     */
    public CostId getId() {
        return CostId.of(getProdId(), getTimeId(), getPromoId(), getChannelId());
    }

    /**
     * Replaces current values of the identifying attributes with those of the specified identifier.
     *
     * @param id the identifier whose values are applied; may be {@code null}, which clears every identifying
     *           attribute.
     */
    protected void setId(final CostId id) {
        setProdId(Optional.ofNullable(id).map(CostId::getProdId).orElse(null));
        setTimeId(Optional.ofNullable(id).map(CostId::getTimeId).orElse(null));
        setPromoId(Optional.ofNullable(id).map(CostId::getPromoId).orElse(null));
        setChannelId(Optional.ofNullable(id).map(CostId::getChannelId).orElse(null));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROD_ID,
                referencedColumnName = Product.COLUMN_NAME_PROD_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Product product;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TIME_ID,
                referencedColumnName = Time.COLUMN_NAME_TIME_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Time time;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PROMO_ID,
                referencedColumnName = Promotion.COLUMN_NAME_PROMO_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Promotion promotion;

    @Valid
    // no @NotNull: the column is written by the @Id, and this mapping only reads it back, so the
    // association is still null at the prePersist at which the provider validates. The column stays NOT
    // NULL, and @ManyToOne(optional = false) still says so to the provider.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CHANNEL_ID,
                referencedColumnName = Channel.COLUMN_NAME_CHANNEL_ID,
                nullable = false,
                insertable = false,
                updatable = false)
    private Channel channel;
}
