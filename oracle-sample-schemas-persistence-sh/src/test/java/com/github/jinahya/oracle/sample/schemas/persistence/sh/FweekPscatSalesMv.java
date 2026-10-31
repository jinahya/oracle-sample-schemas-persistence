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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedFweekPscatSalesMv;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
@Table(name = MappedFweekPscatSalesMv.TABLE_NAME)
public class FweekPscatSalesMv extends MappedFweekPscatSalesMv {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected FweekPscatSalesMv() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    @Override
    public FweekPscatSalesMvId getId() {
        return id;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Valid
    @NotNull
    @EmbeddedId
    private FweekPscatSalesMvId id;
}
