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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static com.github.jinahya.oracle.sample.schemas.persistence.sh._Entity_Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for testing {@link FweekPscatSalesMvUtils} against the {@code sh-test} unit, where the materialized view is
 * an ordinary table: a row is inserted with a random key, the context cleared, and the row read back through the
 * utility. The key is not reused to find the row, because its {@code DATE} component loses its fraction of a second
 * in the column.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Entity_Persistence_Test_Producer.class)
@EnableAutoWeld
class FweekPscatSalesMvUtils_PersistenceTest {

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

    private <R> R inRolledBackTransaction(final Function<? super EntityManager, ? extends R> function) {
        return ___Persistence_TestUtils.applyInTransactionAndRollback(entityManager, function);
    }

    private static FweekPscatSalesMv persistAndReload(final EntityManager em) {
        em.persist(withField(new FweekPscatSalesMv(), FweekPscatSalesMv.ATTRIBUTE_NAME_ID,
                             new FweekPscatSalesMvId_Randomizer().get()));
        em.flush();
        em.clear();
        return em.createQuery("SELECT e FROM FweekPscatSalesMv e", FweekPscatSalesMv.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    // ------------------------------------------------------------------------------------------------ id.weekEndingDay

    @DisplayName("getIdWeekEndingDay(fweekPscatSalesMv)")
    @Nested
    class GetIdWeekEndingDay_Test {

        @Test
        void _Component_Reloaded() {
            inRolledBackTransaction(em -> {
                final var reloaded = persistAndReload(em);
                assertThat(FweekPscatSalesMvUtils.getIdWeekEndingDay(reloaded))
                        .isEqualTo(reloaded.getId().getWeekEndingDay());
                return null;
            });
        }
    }

    // ---------------------------------------------------------------------------------------------- id.prodSubcategory

    @DisplayName("getIdProdSubcategory(fweekPscatSalesMv)")
    @Nested
    class GetIdProdSubcategory_Test {

        @Test
        void _Component_Reloaded() {
            inRolledBackTransaction(em -> {
                final var reloaded = persistAndReload(em);
                assertThat(FweekPscatSalesMvUtils.getIdProdSubcategory(reloaded))
                        .isEqualTo(reloaded.getId().getProdSubcategory());
                return null;
            });
        }
    }

    // ---------------------------------------------------------------------------------------------------- id.channelId

    @DisplayName("getIdChannelId(fweekPscatSalesMv)")
    @Nested
    class GetIdChannelId_Test {

        @Test
        void _Component_Reloaded() {
            inRolledBackTransaction(em -> {
                final var reloaded = persistAndReload(em);
                assertThat(FweekPscatSalesMvUtils.getIdChannelId(reloaded)).isEqualTo(reloaded.getId().getChannelId());
                return null;
            });
        }
    }

    // ------------------------------------------------------------------------------------------------------ id.promoId

    @DisplayName("getIdPromoId(fweekPscatSalesMv)")
    @Nested
    class GetIdPromoId_Test {

        @Test
        void _Component_Reloaded() {
            inRolledBackTransaction(em -> {
                final var reloaded = persistAndReload(em);
                assertThat(FweekPscatSalesMvUtils.getIdPromoId(reloaded)).isEqualTo(reloaded.getId().getPromoId());
                return null;
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__TestPU
    @Inject
    private EntityManager entityManager;
}
