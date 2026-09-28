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

import com.github.jinahya.oracle.sample.schemas.persistence.test.__Persistence_TestUtils;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.oracle.sample.schemas.persistence.test.__Persistence_Test_Producer.__TestPU;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Boots the persistence unit against the in-memory database and checks that the provider accepts every mapping in the
 * {@code SH} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AddBeanClasses(_Persistence_Test_Producer.class)
@EnableAutoWeld
class PersistenceUnit_Test {

    @DisplayName("the persistence unit bootstraps")
    @Test
    void entityManagerFactory_IsOpen_() {
        assertThat(entityManagerFactory.isOpen()).isTrue();
    }

    @DisplayName("every mapped entity reaches the metamodel with an id")
    @Test
    void metamodel_HasEveryEntityWithAnId_() {
        final var entities = entityManagerFactory.getMetamodel().getEntities();
        assertThat(entities)
                .as("entity types of %s", _Persistence_Test_Producer.PERSISTENCE_UNIT_NAME)
                .isNotEmpty()
                .allSatisfy(e -> assertThat(e.hasSingleIdAttribute() || !e.getIdClassAttributes().isEmpty())
                        .as("%s has an id", e.getName())
                        .isTrue());
    }

    @DisplayName("the schema generated from the mappings is queryable")
    @Test
    void generatedSchema_IsQueryable_() {
        entityManagerFactory.getMetamodel().getEntities().forEach(e -> {
            final var count = __Persistence_TestUtils.applyInTransactionAndRollback(
                    entityManager, em -> __Persistence_TestUtils.count(em, e));
            assertThat(count).as("row count of %s", e.getName()).isNotNegative();
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__TestPU
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @__TestPU
    @Inject
    private EntityManager entityManager;
}
