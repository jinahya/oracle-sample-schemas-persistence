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

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_IT_Producer.__ItPU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * A class for testing {@link FweekPscatSalesMvUtils} against the rows of {@value FweekPscatSalesMv#TABLE_NAME} as
 * installed, a nested class per method. The installer leaves the materialized view empty until it is refreshed, and
 * then every test here is skipped rather than failed.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_IT_Producer.class)
@EnableAutoWeld
class FweekPscatSalesMvUtils_PersistenceIT {

    private List<FweekPscatSalesMv> all;

    @BeforeEach
    void loadSome() {
        all = entityManager.createQuery("SELECT e FROM FweekPscatSalesMv e", FweekPscatSalesMv.class)
                .setMaxResults(1024)
                .getResultList();
        assumeTrue(!all.isEmpty(), () -> FweekPscatSalesMv.TABLE_NAME + " holds no row; refresh it first");
    }

    // ------------------------------------------------------------------------------------------------ id.weekEndingDay

    @DisplayName("getIdWeekEndingDay(fweekPscatSalesMv)")
    @Nested
    class GetIdWeekEndingDay_IT {

        @Test
        void _Component_EveryRow() {
            for (final var each : all) {
                assertThat(FweekPscatSalesMvUtils.getIdWeekEndingDay(each)).isEqualTo(each.getId().getWeekEndingDay());
            }
        }
    }

    // ---------------------------------------------------------------------------------------------- id.prodSubcategory

    @DisplayName("getIdProdSubcategory(fweekPscatSalesMv)")
    @Nested
    class GetIdProdSubcategory_IT {

        @Test
        void _Component_EveryRow() {
            for (final var each : all) {
                assertThat(FweekPscatSalesMvUtils.getIdProdSubcategory(each))
                        .isEqualTo(each.getId().getProdSubcategory());
            }
        }
    }

    // ---------------------------------------------------------------------------------------------------- id.channelId

    @DisplayName("getIdChannelId(fweekPscatSalesMv)")
    @Nested
    class GetIdChannelId_IT {

        @Test
        void _Component_EveryRow() {
            for (final var each : all) {
                assertThat(FweekPscatSalesMvUtils.getIdChannelId(each)).isEqualTo(each.getId().getChannelId());
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------ id.promoId

    @DisplayName("getIdPromoId(fweekPscatSalesMv)")
    @Nested
    class GetIdPromoId_IT {

        @Test
        void _Component_EveryRow() {
            for (final var each : all) {
                assertThat(FweekPscatSalesMvUtils.getIdPromoId(each)).isEqualTo(each.getId().getPromoId());
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__ItPU
    @Inject
    private EntityManager entityManager;
}
