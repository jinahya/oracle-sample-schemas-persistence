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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedProfit;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value Profit#TABLE_NAME} view, whose composite identifier is mapped with an
 * {@link jakarta.persistence.IdClass @IdClass}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProfitId
 */
@NamedQuery(name = "Profit.selectOneByChannelIdAndCustIdAndProdIdAndPromoIdAndTimeId",
            query = """
                    SELECT e
                    FROM Profit e
                    WHERE e.channelId = :channelId
                      AND e.custId = :custId
                      AND e.prodId = :prodId
                      AND e.promoId = :promoId
                      AND e.timeId = :timeId"""
)
@NamedQuery(name = "Profit.selectListOrderByChannelIdAscCustIdAscProdIdAscPromoIdAscTimeIdAsc",
            query = """
                    SELECT e
                    FROM Profit e
                    ORDER BY e.channelId ASC, e.custId ASC, e.prodId ASC, e.promoId ASC, e.timeId ASC"""
)
@NamedQuery(name = "Profit.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Profit e"""
)
@Entity
@IdClass(ProfitId.class)
@Table(name = MappedProfit.TABLE_NAME)
public class Profit extends MappedProfit {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Profit() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns a new {@link ProfitId} holding current values of the identifying attributes.
     *
     * @return a new {@link ProfitId} holding current values of the identifying attributes.
     */
    public ProfitId getId() {
        return ProfitId.of(getChannelId(), getCustId(), getProdId(), getPromoId(), getTimeId());
    }
}
