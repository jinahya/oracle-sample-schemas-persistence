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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link FweekPscatSalesMvUtils}, a nested class per method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class FweekPscatSalesMvUtils_Test {

    /** Sets the named field of the specified instance reflectively, for an entity which exposes no setter for it. */
    private static <T> T withField(final T instance, final String name, final Object value) {
        try {
            final var field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private static FweekPscatSalesMv instanceOf(final FweekPscatSalesMvId id) {
        return withField(new FweekPscatSalesMv(), FweekPscatSalesMv.ATTRIBUTE_NAME_ID, id);
    }

    // ------------------------------------------------------------------------------------------------ id.weekEndingDay

    @DisplayName("getIdWeekEndingDay(fweekPscatSalesMv)")
    @Nested
    class GetIdWeekEndingDay_Test {

        @Test
        void _NullPointerException_NullEntity() {
            assertThatThrownBy(() -> FweekPscatSalesMvUtils.getIdWeekEndingDay(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullId() {
            assertThat(FweekPscatSalesMvUtils.getIdWeekEndingDay(instanceOf(null))).isNull();
        }

        @Test
        void _Component_() {
            final var id = new FweekPscatSalesMvId_Randomizer().get();
            assertThat(FweekPscatSalesMvUtils.getIdWeekEndingDay(instanceOf(id))).isEqualTo(id.getWeekEndingDay());
        }
    }

    // ---------------------------------------------------------------------------------------------- id.prodSubcategory

    @DisplayName("getIdProdSubcategory(fweekPscatSalesMv)")
    @Nested
    class GetIdProdSubcategory_Test {

        @Test
        void _NullPointerException_NullEntity() {
            assertThatThrownBy(() -> FweekPscatSalesMvUtils.getIdProdSubcategory(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullId() {
            assertThat(FweekPscatSalesMvUtils.getIdProdSubcategory(instanceOf(null))).isNull();
        }

        @Test
        void _Component_() {
            final var id = new FweekPscatSalesMvId_Randomizer().get();
            assertThat(FweekPscatSalesMvUtils.getIdProdSubcategory(instanceOf(id))).isEqualTo(id.getProdSubcategory());
        }
    }

    // ---------------------------------------------------------------------------------------------------- id.channelId

    @DisplayName("getIdChannelId(fweekPscatSalesMv)")
    @Nested
    class GetIdChannelId_Test {

        @Test
        void _NullPointerException_NullEntity() {
            assertThatThrownBy(() -> FweekPscatSalesMvUtils.getIdChannelId(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullId() {
            assertThat(FweekPscatSalesMvUtils.getIdChannelId(instanceOf(null))).isNull();
        }

        @Test
        void _Component_() {
            final var id = new FweekPscatSalesMvId_Randomizer().get();
            assertThat(FweekPscatSalesMvUtils.getIdChannelId(instanceOf(id))).isEqualTo(id.getChannelId());
        }
    }

    // ------------------------------------------------------------------------------------------------------ id.promoId

    @DisplayName("getIdPromoId(fweekPscatSalesMv)")
    @Nested
    class GetIdPromoId_Test {

        @Test
        void _NullPointerException_NullEntity() {
            assertThatThrownBy(() -> FweekPscatSalesMvUtils.getIdPromoId(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        void _Null_NullId() {
            assertThat(FweekPscatSalesMvUtils.getIdPromoId(instanceOf(null))).isNull();
        }

        @Test
        void _Component_() {
            final var id = new FweekPscatSalesMvId_Randomizer().get();
            assertThat(FweekPscatSalesMvUtils.getIdPromoId(instanceOf(id))).isEqualTo(id.getPromoId());
        }
    }
}
